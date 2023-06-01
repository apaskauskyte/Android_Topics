package lt.ausra.android_topics.mainActivity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import lt.ausra.android_topics.ActivityLifeCycles
import lt.ausra.android_topics.R
import lt.ausra.android_topics.secondActivity.SecondActivity
import lt.ausra.android_topics.databinding.ActivityMainBinding
import lt.ausra.android_topics.repository.Item

class MainActivity : ActivityLifeCycles() {

    private lateinit var adapter: CustomAdapter
    private lateinit var binding: ActivityMainBinding
    private val activityViewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainActivity = this
        binding.viewModel = activityViewModel
        binding.lifecycleOwner = this

        setUpListView()
        setUpObservables()
        onItemLongClick()
        setClickOpenItemDetails()
    }

    override fun onResume() {
        super.onResume()
        activityViewModel.fetchItems()
    }

    fun openSecondActivity(view: View) {
        startActivity(Intent(this, SecondActivity::class.java))
    }

    private fun setUpListView() {
        adapter = CustomAdapter(this)
        binding.itemListView.adapter = adapter
    }

    private fun setUpObservables() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                activityViewModel.uiState.collect { uiState ->
                    adapter.add(uiState.items)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                activityViewModel.isDeletedUiState.collect {
                    isDeleted ->
                    if (isDeleted) {
                        displaySnackBar("Item was deleted from repository")
                    } else {
                        displaySnackBar("Item wasn't deleted from repository")
                    }
                }
            }
        }
    }

    private fun onItemLongClick() {
        binding.itemListView.setOnItemLongClickListener { adapterView, view, position, l ->
            val item = adapterView.getItemAtPosition(position) as Item
            displayDeleteItemAlertDialog(item)
            true
        }
    }

    private fun setClickOpenItemDetails() {
        binding.itemListView.setOnItemClickListener { adapterView, view, position, l ->
            val item: Item = adapterView.getItemAtPosition(position) as Item

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra(MAIN_ACTIVITY_ITEM_ID, item.id)

            startActivity(intent)
        }
    }

    private fun displayDeleteItemAlertDialog(item: Item) {
        AlertDialog
            .Builder(this)
            .setTitle("Delete")
            .setMessage("Do you really want to delete this item")
            .setIcon(R.drawable.ic_clear_24)
            .setPositiveButton("Yes") {
                _,_ ->
                activityViewModel.deleteItem(item)
                adapter.remove(item)
            }
            .setNegativeButton("No", null)
            .show()
    }

    private fun displaySnackBar(message: String) {
        Snackbar
            .make(
                    binding.openButton,
                    message,
                    Snackbar.LENGTH_LONG
                )
            .setAnchorView(binding.openButton)
            .show()
    }

    companion object {
        const val MAIN_ACTIVITY_ITEM_ID =
            "lt.ausra.android_topics_Item_Id"
    }
}
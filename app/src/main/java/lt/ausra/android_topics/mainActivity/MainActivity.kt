package lt.ausra.android_topics.mainActivity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import android.widget.AbsListView.OnScrollListener
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
        onScrollListView()
        setUpObservables()

        onItemLongClick()
        setClickOpenItemDetails()
    }

    override fun onResume() {
        super.onResume()
        activityViewModel.fetchItems()

        if (adapter.getMaxID() != -1) {
            binding.itemListView.smoothScrollToPosition(
                activityViewModel.positionListViewStateFlow.value
            )
        }
    }

    fun openSecondActivity(view: View) {
        startActivity(Intent(this, SecondActivity::class.java))
    }

    private fun setUpListView() {
        adapter = CustomAdapter(this)
        binding.itemListView.adapter = adapter
    }

    private fun onScrollListView() {
//        binding.itemListView.setOnScrollListener(
//            object : OnScrollListener {
//                override fun onScrollStateChanged(p0: AbsListView?, p1: Int) {}
//
//                override fun onScroll(p0: AbsListView?, position: Int, p2: Int, p3: Int) {
//
//                    if (activityViewModel.positionListViewStateFlow.value != position) {
//                        activityViewModel.savePositionListView(position)
//                    }
//                }
//            }
//        )
        binding.itemListView.setOnScrollChangeListener { _, _, _, _, _ ->
            val position = binding.itemListView.firstVisiblePosition

            if (activityViewModel.positionListViewStateFlow.value != position) {
                activityViewModel.savePositionListView(position)
            }
        }
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
                activityViewModel.isDeletedUiState.collect { displayUiState ->
                    if (displayUiState.isDeleted) {
                        displaySnackBar("Item was deleted from repository")
                        adapter.remove(displayUiState.item)
                    } else {
                        displaySnackBar("Item wasn't deleted from repository")
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                activityViewModel.positionListViewStateFlow.collect { firstVisiblePosition ->
                    displaySnackBar("First visible item  $firstVisiblePosition")
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
            .setPositiveButton("Yes") { _, _ ->
                activityViewModel.deleteItem(item)
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
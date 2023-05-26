package lt.ausra.android_topics.MainActivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import lt.ausra.android_topics.ActivityLifeCycles
import lt.ausra.android_topics.SecondActivity.SecondActivity
import lt.ausra.android_topics.databinding.ActivityMainBinding
import lt.ausra.android_topics.repository.Item

class MainActivity : ActivityLifeCycles() {

    private lateinit var adapter: CustomAdapter
    private lateinit var binding: ActivityMainBinding
    private val activityViewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpListView()

        activityViewModel.itemsLiveData.observe(
            this,
            Observer { listOfItems ->
                adapter.add(listOfItems)
            }
        )

        activityViewModel.fetchItems()

        openSecondActivity()
        setClickOpenItemDetails()
    }

    private fun setUpListView() {
        adapter = CustomAdapter(this)
        binding.itemListView.adapter = adapter
    }

    private fun openSecondActivity() {
        binding.openButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            val id = adapter.getMaxID().inc()
            intent.putExtra(MAIN_ACTIVITY_ITEM_ID, id)

            startActivity(Intent(this, SecondActivity::class.java))
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

    companion object {
        const val MAIN_ACTIVITY_ITEM_ID =
            "lt.ausra.android_topics_Item_Id"
        const val MAIN_ACTIVITY_ITEM_INTENT_OBJECT =
            "lt.ausra.android_topics_Save_Instance_state_item_intent_object"
    }
}
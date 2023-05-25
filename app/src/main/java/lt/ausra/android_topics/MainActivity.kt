package lt.ausra.android_topics

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import lt.ausra.android_topics.databinding.ActivityMainBinding

class MainActivity : ActivityLifeCycles() {

    private lateinit var adapter: CustomAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = mutableListOf<Item>()
        generateListOfItems(items)

        setUpListView()
        updateAdapter(items)

        openSecondActivity()
        setClickOpenItemDetails()
    }

    private fun generateListOfItems(items: MutableList<Item>) {
        for (item in 1..10) {
            items.add(
                Item(
                    item,
                    "Text01_%04x".format(item),
                    "Text02_%06x".format(item)
                )
            )
        }
    }

    private fun setUpListView() {
        adapter = CustomAdapter(this)
        binding.itemListView.adapter = adapter
    }

    private fun updateAdapter(items: MutableList<Item>) {
        adapter.add(items)
        adapter.add(Item(101, "text01", "text02"))
        adapter.add(
            Item(101, "text01", "text02"),
            Item(102, "text01", "text02"),
            Item(103, "text01", "text02"),
            Item(104, "text01", "text02"),
            Item(105, "text01", "text02"),
        )
    }

    private fun openSecondActivity() {
        binding.openButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            val id = adapter.getMaxID().inc()
            intent.putExtra(MAIN_ACTIVITY_ITEM_ID, id)

            startActivityForResult.launch(intent)
        }
    }

    private fun setClickOpenItemDetails() {
        binding.itemListView.setOnItemClickListener { adapterView, view, position, l ->
            val item: Item = adapterView.getItemAtPosition(position) as Item

            val itemIntent = Intent(this, SecondActivity::class.java)
            itemIntent.putExtra(MAIN_ACTIVITY_ITEM_INTENT_OBJECT, item)

            startActivityForResult.launch(itemIntent)
        }
    }

    private val startActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

            val item: Item?

            when (result.resultCode) {
                SecondActivity.SECOND_ACTIVITY_ITEM_INTENT_RETURN_NEW -> {
                    item = getExtraFromParcelable(
                        result.data,
                        SecondActivity.SECOND_ACTIVITY_ITEM_INTENT_RETURN_OBJECT
                    )

                    if (item != null) {
                        adapter.add(item)
                    }
                }

                SecondActivity.SECOND_ACTIVITY_ITEM_INTENT_RETURN_UPDATE -> {
                    item = getExtraFromParcelable(
                        result.data,
                        SecondActivity.SECOND_ACTIVITY_ITEM_INTENT_RETURN_OBJECT
                    )
                    adapter.update(item)
                }
            }
        }

    companion object {
        const val MAIN_ACTIVITY_ITEM_ID =
            "lt.ausra.android_topics_Item_Id"
        const val MAIN_ACTIVITY_ITEM_INTENT_OBJECT =
            "lt.ausra.android_topics_Save_Instance_state_item_intent_object"
    }
}
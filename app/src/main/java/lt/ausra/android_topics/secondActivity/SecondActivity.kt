package lt.ausra.android_topics.secondActivity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import lt.ausra.android_topics.ActivityLifeCycles
import lt.ausra.android_topics.mainActivity.MainActivity
import lt.ausra.android_topics.R
import lt.ausra.android_topics.databinding.ActivitySecondBinding
import lt.ausra.android_topics.repository.Item


class SecondActivity : ActivityLifeCycles() {

    private lateinit var binding: ActivitySecondBinding
    private val activityViewModel: SecondActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_second)
        binding.secondActivity = this

        activityViewModel.itemLiveData.observe(
            this,
            Observer { item ->
                binding.item = item
            }
        )

        activityViewModel.fetchItem(getIntentExtra())
    }

    private fun getIntentExtra() =
        intent.getIntExtra(MainActivity.MAIN_ACTIVITY_ITEM_ID, -1)

    fun onClickCloseButton(view: View) {
        finish()
    }

    fun onClickSaveButton() {
        activityViewModel.saveItem(binding.item as Item)
        finish()
    }
}
package lt.ausra.android_topics.SecondActivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import lt.ausra.android_topics.ActivityLifeCycles
import lt.ausra.android_topics.MainActivity.MainActivity
import lt.ausra.android_topics.R
import lt.ausra.android_topics.databinding.ActivitySecondBinding


class SecondActivity : ActivityLifeCycles() {

    private lateinit var binding: ActivitySecondBinding
    private var finishIntentStatus = SECOND_ACTIVITY_ITEM_INTENT_RETURN_UPDATE
    private val activityViewModel: SecondActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_second)
        binding.secondActivity = this

        activityViewModel.itemLiveData.observe(
            this,
            Observer { item ->
                binding.item = item
            }
        )

        activityViewModel.fetchItem(getIntentExtra())

        getIntentExtra()
        // comments just for merging purpose: commit02
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putParcelable(SECOND_ACTIVITY_ITEM_SAVE_INSTANCE_STATE, binding.item)
            putInt(SECOND_ACTIVITY_FINISH_INTENT_STATUS, finishIntentStatus)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        with(savedInstanceState) {
            binding.item = getParcelable(SECOND_ACTIVITY_ITEM_SAVE_INSTANCE_STATE)
            finishIntentStatus = this.getInt(SECOND_ACTIVITY_FINISH_INTENT_STATUS)
        }
    }

    private fun getIntentExtra() =
        intent.getIntExtra(MainActivity.MAIN_ACTIVITY_ITEM_ID, -1)

    fun onClickCloseButton(view: View) {
        finish()
    }

    fun onClickSaveButton() {
        val finishIntent = Intent()
        finishIntent.putExtra(SECOND_ACTIVITY_ITEM_INTENT_RETURN_OBJECT, binding.item)

        if (binding.idEditText.text.toString().toInt() < 0) {
            finishIntentStatus = RESULT_CANCELED
        }

        setResult(finishIntentStatus, finishIntent)
        finish()
    }

    companion object {
        const val SECOND_ACTIVITY_ITEM_INTENT_RETURN_OBJECT =
            "lt.ausra.android_topics.secondactivity_return_object"
        const val SECOND_ACTIVITY_FINISH_INTENT_STATUS =
            "lt.ausra.android_topics.secondactivity_finish_intent_status"
        const val SECOND_ACTIVITY_ITEM_SAVE_INSTANCE_STATE =
            "lt.ausra.android_topics.secondactivity_item_save_instance_state"

        const val SECOND_ACTIVITY_ITEM_INTENT_RETURN_NEW = 101
        const val SECOND_ACTIVITY_ITEM_INTENT_RETURN_UPDATE = 102
    }
}
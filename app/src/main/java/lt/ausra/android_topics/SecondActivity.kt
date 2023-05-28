package lt.ausra.android_topics

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import lt.ausra.android_topics.databinding.ActivitySecondBinding

class SecondActivity : ActivityLifeCycles() {

    private lateinit var binding: ActivitySecondBinding
    private var finishIntentStatus = SECOND_ACTIVITY_ITEM_INTENT_RETURN_UPDATE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_second)
        binding.item = getIntentExtra()
        binding.secondActivity = this

        getIntentExtra()
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

    private fun getIntentExtra(): Item {

        if (intent.hasExtra(MainActivity.MAIN_ACTIVITY_ITEM_INTENT_OBJECT)) {

            return getExtraFromParcelable(
                intent,
                MainActivity.MAIN_ACTIVITY_ITEM_INTENT_OBJECT
            ) ?: Item(-1, "", "")

        } else if (intent.hasExtra(MainActivity.MAIN_ACTIVITY_ITEM_ID)) {

            finishIntentStatus = SECOND_ACTIVITY_ITEM_INTENT_RETURN_NEW

            return Item(
                intent.getIntExtra(MainActivity.MAIN_ACTIVITY_ITEM_ID, -1),
                "",
                ""
            )
        } else {
            finishIntentStatus = RESULT_CANCELED
            return Item(-1, "", "")
        }
    }

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
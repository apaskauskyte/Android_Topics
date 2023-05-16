package lt.ausra.android_topics

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

class SecondActivity : ActivityLifeCycles() {

    private lateinit var idEditText: EditText
    private lateinit var text01EditText: EditText
    private lateinit var text02EditText: EditText
    private lateinit var closeButton: Button
    private lateinit var saveButton: Button
    private var finishIntentStatus = SECOND_ACTIVITY_ITEM_INTENT_RETURN_UPDATE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        idEditText = findViewById(R.id.idEditText)
        text01EditText = findViewById(R.id.text01EditText)
        text02EditText = findViewById(R.id.text02EditText)
        closeButton = findViewById(R.id.closeButton)
        saveButton = findViewById(R.id.saveButton)

//        if (savedInstanceState != null) {
//            with(savedInstanceState) {
//                idEditText.setText(getString(SECOND_ACTIVITY_ITEM_ID))
//                text01EditText.setText(getString(SECOND_ACTIVITY_ITEM_TEXT01))
//                text02EditText.setText(getString(SECOND_ACTIVITY_ITEM_TEXT02))
//            }
//        } else {
//            getIntentExtra()
//        }

        getIntentExtra()
        setClickListenerOfCloseButton()
        setClickListenerOfSaveButton()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString(SECOND_ACTIVITY_ITEM_ID, idEditText.text.toString())
            putString(SECOND_ACTIVITY_ITEM_TEXT01, text01EditText.text.toString())
            putString(SECOND_ACTIVITY_ITEM_TEXT02, text02EditText.text.toString())
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        with(savedInstanceState) {
            idEditText.setText(this.getString(SECOND_ACTIVITY_ITEM_ID))
            text01EditText.setText(this.getString(SECOND_ACTIVITY_ITEM_TEXT01))
            text02EditText.setText(this.getString(SECOND_ACTIVITY_ITEM_TEXT02))
        }
    }

    private fun getIntentExtra() { //kvieciamas kada spaudziam ant itemo ir atpazistam ar kazka gaunam ar nullas ten

        val itemId: Int = intent.getIntExtra(MainActivity.MAIN_ACTIVITY_ITEM_ID, -1)
        val itemText01: String = intent.getStringExtra(MainActivity.MAIN_ACTIVITY_ITEM_TEXT01) ?: ""
        val itemText02: String = intent.getStringExtra(MainActivity.MAIN_ACTIVITY_ITEM_TEXT02) ?: ""

        if (itemId >= 0) {
            idEditText.setText(itemId.toString())
            text01EditText.setText(itemText01)
            text02EditText.setText(itemText02)
        } else {
            finishIntentStatus = SECOND_ACTIVITY_ITEM_INTENT_RETURN_NEW
        }
    }

    private fun setClickListenerOfCloseButton() {
        closeButton.setOnClickListener {
            finish()
        }
    }

    private fun setClickListenerOfSaveButton() {
        saveButton.setOnClickListener {
            val finishIntent = Intent()

            finishIntent.putExtra(SECOND_ACTIVITY_ITEM_ID, (idEditText.text.toString()).toInt())
            finishIntent.putExtra(SECOND_ACTIVITY_ITEM_TEXT01, text01EditText.text.toString())
            finishIntent.putExtra(SECOND_ACTIVITY_ITEM_TEXT02, text02EditText.text.toString())
            setResult(finishIntentStatus, finishIntent)
            finish()
        }
    }

    companion object {
        const val SECOND_ACTIVITY_ITEM_ID = "lt.ausra.android_topics.secondactivity_Item_Id"
        const val SECOND_ACTIVITY_ITEM_TEXT01 = "lt.ausra.android_topics.secondactivity_text01"
        const val SECOND_ACTIVITY_ITEM_TEXT02 = "lt.ausra.android_topics.secondactivity_text02"
        const val SECOND_ACTIVITY_ITEM_INTENT_RETURN_NEW = 101
        const val SECOND_ACTIVITY_ITEM_INTENT_RETURN_UPDATE = 102
    }
}
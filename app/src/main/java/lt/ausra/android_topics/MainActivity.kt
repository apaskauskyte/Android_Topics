package lt.ausra.android_topics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    lateinit var openButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        openButton = findViewById(R.id.openButton)
        timber("onCreate")

        openButton.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
    override fun onStart() {
        super.onStart()
        timber("onStart")
    }

    override fun onResume() {
        super.onResume()
        timber("onResume")
    }

    override fun onStop() {
        super.onStop()
        timber("onStop")
    }

    override fun onPause() {
        super.onPause()
        timber("onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        timber("onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        timber("onRestart")
    }

    fun timber(text: String) {
        Timber.i(
            """
            ********************
            * ${this.localClassName}
            * $text
            ********************
        """.trimIndent()
        )
    }
}
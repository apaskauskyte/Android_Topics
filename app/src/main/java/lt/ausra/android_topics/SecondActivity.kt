package lt.ausra.android_topics

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        timber("onCreate")
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
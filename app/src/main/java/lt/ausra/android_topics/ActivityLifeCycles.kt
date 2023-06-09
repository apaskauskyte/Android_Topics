package lt.ausra.android_topics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

open class ActivityLifeCycles : AppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        timber("onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        timber("onRestoreInstanceState")
    }
}
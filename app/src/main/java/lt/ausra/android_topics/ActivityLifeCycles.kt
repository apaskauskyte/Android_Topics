package lt.ausra.android_topics

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
}
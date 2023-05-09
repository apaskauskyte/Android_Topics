package lt.ausra.android_topics

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

class SecondActivity : ActivityLifeCycles() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }
}
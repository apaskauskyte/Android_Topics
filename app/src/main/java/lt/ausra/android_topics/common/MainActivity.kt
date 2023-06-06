package lt.ausra.android_topics.common

import android.os.Bundle
import lt.ausra.android_topics.R

class MainActivity : ActivityLifecyclesPresentation() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
package lt.ausra.android_topics.common

import android.os.Bundle
import androidx.fragment.app.commit
import lt.ausra.android_topics.R
import lt.ausra.android_topics.first_fragment.FirstFragment

class MainActivity : ActivityLifecyclesPresentation() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openFragment()
    }

    private fun openFragment() {
        supportFragmentManager.commit {
            replace(
                R.id.fragmentContainerView,
                FirstFragment.newInstance(),
                "first_fragment"
            )
            setReorderingAllowed(true)
        }
    }
}
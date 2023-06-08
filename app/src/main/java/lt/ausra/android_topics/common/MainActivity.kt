package lt.ausra.android_topics.common

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import lt.ausra.android_topics.R
import lt.ausra.android_topics.first_fragment.FirstFragment

class MainActivity : ActivityLifecyclesPresentation() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (
            supportFragmentManager.fragments.isEmpty() ||
            supportFragmentManager.fragments[0] is FirstFragment
        ) {
            openFragment(FirstFragment.newInstance(), FirstFragment.TAG)
        }
        onBack()
    }

    fun openFragment(fragment: Fragment, tag: String) {
//        supportFragmentManager.setFragmentResult("", bundleOf())
        supportFragmentManager.commit {
            replace(
                R.id.fragmentContainerView,
                fragment,
                tag
            )
            setReorderingAllowed(true)
            if (fragment !is FirstFragment) {
                addToBackStack(tag)
            }
        }
    }

    private fun onBack() {
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val fragment =
                        supportFragmentManager.findFragmentById(R.id.fragmentContainerView)

                    if (fragment is FirstFragment) {
                        finish()
                    }

                    supportFragmentManager.popBackStack()
                }
            }
        )
    }
}
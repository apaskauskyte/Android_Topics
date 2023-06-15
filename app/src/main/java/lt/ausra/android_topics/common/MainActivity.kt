package lt.ausra.android_topics.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import lt.ausra.android_topics.R
import lt.ausra.android_topics.first_fragment.FirstFragment
import lt.ausra.android_topics.second_fragment.SecondFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            openFirstFragment()
        }
    }

    fun openSecondFragment() {
        setCurrentFragment(SecondFragment.newInstance(), SecondFragment.TAG)
    }

    private fun openFirstFragment() {
        setCurrentFragment(FirstFragment.newInstance(), FirstFragment.TAG)
    }

    private fun setCurrentFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.commit {
            replace(
                R.id.fragmentContainerView,
                fragment,
                tag
            )
            setReorderingAllowed(true)
        }
    }
}

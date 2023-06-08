package lt.ausra.android_topics.first_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import lt.ausra.android_topics.R
import lt.ausra.android_topics.common.FragmentLifecyclesPresentation
import lt.ausra.android_topics.databinding.FragmentFirstBinding
import lt.ausra.android_topics.second_fragment.SecondFragment

class FirstFragment : FragmentLifecyclesPresentation() {

    private val viewModel: FirstFragmentViewModel by viewModels()

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onCLickOpenButton()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onCLickOpenButton() {
        binding.openSecondFragmentButton.setOnClickListener {
            parentFragmentManager.commit {
                replace(
                    R.id.fragmentContainerView,
                    SecondFragment.newInstance(),
                    "second_fragment"
                )
                setReorderingAllowed(true)
            }
        }
    }

    companion object {
        fun newInstance() = FirstFragment()
    }
}

package lt.ausra.android_topics.second_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import lt.ausra.android_topics.common.FragmentLifecyclesPresentation
import lt.ausra.android_topics.databinding.FragmentSecondBinding

class SecondFragment : FragmentLifecyclesPresentation() {

    private val viewModel: SecondFragmentViewModel by viewModels()

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = SecondFragment()
    }
}
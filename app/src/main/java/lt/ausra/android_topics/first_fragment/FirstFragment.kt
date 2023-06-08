package lt.ausra.android_topics.first_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import lt.ausra.android_topics.common.FragmentLifecyclesPresentation
import lt.ausra.android_topics.common.MainActivity
import lt.ausra.android_topics.databinding.FragmentFirstBinding
import lt.ausra.android_topics.second_fragment.SecondFragment
import lt.vcs.demoapp.second_fragment.Item

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
        receiveDataFromSecondFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onCLickOpenButton() {
        binding.openSecondFragmentButton.setOnClickListener {
//            val mainActivity = activity as MainActivity
            val item = Item(3, "txt01_03", "txt02_03")
            (activity as MainActivity).openFragment(
                SecondFragment.newInstance(item),
                SecondFragment.TAG
            )
            transferDataToSecondFragment()
        }
    }

    private fun transferDataToSecondFragment() {
        val bundle = bundleOf(
            "ff_text" to "text_from_first_fragment",
            "ff_item" to Item(1, "txt01", "txt02")
        )
        setFragmentResult("first_fragment_result_key", bundle)
    }

    private fun receiveDataFromSecondFragment() {
        setFragmentResultListener("second_fragment_result_key") { requestKey, bundle ->
            val text = bundle.getString("sf_text")
            val item = bundle.getParcelable<Item>("sf_item")
            binding.textView.text = """
                $text
                +++++++++++++++++
                ${item.toString()}
                """.trimIndent()
        }
    }

    companion object {
        const val TAG = "first_fragment"
        fun newInstance() = FirstFragment()
    }
}

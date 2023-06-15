package lt.ausra.android_topics.first_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import lt.ausra.android_topics.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

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

//        viewModel.fetchUsers()
        viewModel.fetchTopNews()

//        userStateFlow()
        observeTopNewsStateFlow()
    }

    private fun userStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.itemsStateFlow.collect { response ->
                    //                    Log.i(TAG, "onViewCreated: ${listOfItems?.userList}")
                    val list = response?.userList

                    //                    var myText = ""
                    //
                    if (list != null) {

                        val stringBuilder = buildString {
                            list?.forEach { append("$it\n\n") }
                        }
                        binding.textView.text = stringBuilder

                        //                        for (item in list) {
                        //                            myText += "${item}\n\n"
                        //                        }

                    }
                }
            }
        }
    }

    private fun observeTopNewsStateFlow() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.topNewsStateFlow.collect { response ->
                    val list = response?.articles

                    Log.i(TAG, "onViewCreated: $list")

                    if (list != null) {
                        val stringBuilder = buildString {
                            list?.forEach { append("$it\n\n") }
                        }
                        binding.textView.text = stringBuilder
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "my_first_fragment"
        fun newInstance() = FirstFragment()
    }
}

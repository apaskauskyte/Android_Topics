package lt.ausra.android_topics.second_fragment

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
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.launch
import lt.ausra.android_topics.databinding.FragmentSecondBinding
import lt.ausra.android_topics.first_fragment.FirstFragment

class SecondFragment : Fragment() {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchUsers((1..12).random())

        observeUserStateFlow()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeUserStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.itemsStateFlow.collect { response ->
                    val user = response?.data

                    Log.i(FirstFragment.TAG, "onViewCreated: $user")

                    if (user != null) {
                        binding.textView.text = user.toString()
                    }
                }
            }
        }
    }

    companion object {
        const val TAG = "second_fragment"
        fun newInstance() = SecondFragment()
    }
}
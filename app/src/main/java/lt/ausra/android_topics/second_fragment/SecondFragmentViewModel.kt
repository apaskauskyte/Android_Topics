package lt.ausra.android_topics.second_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import lt.ausra.android_topics.repository.reqres.ReqresServiceClient
import lt.ausra.android_topics.repository.reqres.UserResponse

class SecondFragmentViewModel : ViewModel() {

    private val _itemsStateFlow: MutableStateFlow<UserResponse?> =
        MutableStateFlow(UserResponse())
    val itemsStateFlow = _itemsStateFlow.asStateFlow()

    fun fetchUsers(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val resp = ReqresServiceClient.providesApiService().getUserDetails(id)
            _itemsStateFlow.value = resp.body()
        }
    }
}
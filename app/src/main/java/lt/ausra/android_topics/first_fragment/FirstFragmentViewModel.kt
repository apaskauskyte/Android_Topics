package lt.ausra.android_topics.first_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import lt.ausra.android_topics.repository.reqres.ReqresServiceClient
import lt.ausra.android_topics.repository.reqres.UsersResponse

class FirstFragmentViewModel : ViewModel() {

    private val _itemsStateFlow: MutableStateFlow<UsersResponse?> =
        MutableStateFlow(UsersResponse())

    val itemsStateFlow = _itemsStateFlow.asStateFlow()
    fun fetchUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            val resp = ReqresServiceClient.providesApiService().getUsers()
            _itemsStateFlow.value = resp.body()
        }
    }
}
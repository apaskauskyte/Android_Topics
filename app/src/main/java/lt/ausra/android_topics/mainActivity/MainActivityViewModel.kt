package lt.ausra.android_topics.mainActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import lt.ausra.android_topics.repository.Item
import lt.ausra.android_topics.repository.ItemRepository

class MainActivityViewModel : ViewModel() {

    private val _itemsStateFlow = MutableStateFlow<List<Item>>(mutableListOf())
    val itemsStateFlow = _itemsStateFlow.asStateFlow()

    private val _isLoadingStateFlow = MutableStateFlow(true)
    val isLoadingStateFlow = _isLoadingStateFlow.asStateFlow()

    fun fetchItems() {

        viewModelScope.launch(Dispatchers.IO) {
            _isLoadingStateFlow.value = true

            if (_itemsStateFlow.value.isEmpty()) {
                ItemRepository.instance.addDummyListOfItems()
            }

            delay((1000..4000).random().toLong())

            _itemsStateFlow.value = ItemRepository.instance.items

            _isLoadingStateFlow.value = false
        }
    }
}
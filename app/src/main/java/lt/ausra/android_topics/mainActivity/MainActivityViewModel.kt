package lt.ausra.android_topics.mainActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import lt.ausra.android_topics.repository.Item
import lt.ausra.android_topics.repository.ItemRepository

class MainActivityViewModel : ViewModel() {

    private val _itemsLiveData = MutableLiveData<List<Item>>(mutableListOf())
    val itemsLiveData: LiveData<List<Item>>
        get() = _itemsLiveData

    private val _isLoadingLiveData = MutableLiveData(true)
    val isLoadingLiveData: LiveData<Boolean>
        get() = _isLoadingLiveData

    fun fetchItems() {

        viewModelScope.launch {
            _isLoadingLiveData.value = true

            if (_itemsLiveData.value == null || _itemsLiveData.value?.isEmpty() == true) {
                ItemRepository.instance.addDummyListOfItems()
            }

            delay((1000..4000).random().toLong())

            _itemsLiveData.value = ItemRepository.instance.items

            _isLoadingLiveData.value = false
        }
    }
}
package lt.ausra.android_topics.SecondActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lt.ausra.android_topics.repository.Item
import lt.ausra.android_topics.repository.ItemRepository

class SecondActivityViewModel : ViewModel() {

    private val _itemLiveData = MutableLiveData<Item>()
    val itemLiveData: LiveData<Item>
        get() = _itemLiveData

    fun fetchItem(itemId: Int) {

        if (_itemLiveData.value == null) {
            if (itemId > 0) {
                _itemLiveData.value = ItemRepository.instance.getItem(itemId)
            } else {
                _itemLiveData.value = Item(-1, "", "")
            }
        }
    }
}
package lt.ausra.android_topics.mainActivity

import lt.ausra.android_topics.repository.Item

data class MainActivityUiState(
    val items: List<Item> = mutableListOf(),
    val isLoading: Boolean = false,
    val isListVisible: Boolean = true
)

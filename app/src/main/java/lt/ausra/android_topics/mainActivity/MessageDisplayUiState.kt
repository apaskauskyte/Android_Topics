package lt.ausra.android_topics.mainActivity

import lt.ausra.android_topics.repository.Item

data class MessageDisplayUiState(
    val item: Item = Item(-1, "", ""),
    val isDeleted: Boolean = false
)

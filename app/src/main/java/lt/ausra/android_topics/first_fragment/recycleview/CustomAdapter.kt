package lt.ausra.android_topics.first_fragment.recycleview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import lt.ausra.android_topics.databinding.FragmentArticleListItemBinding
import lt.ausra.android_topics.repository.news_api.Article
import lt.ausra.android_topics.first_fragment.recycleview.CustomViewHolder

class CustomAdapter(
    private val onClick: (Article) -> Unit
) : ListAdapter<Article, CustomViewHolder>(
    Comparator()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CustomViewHolder(
        FragmentArticleListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false),
        onClick
    )

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class Comparator : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: Article, newItem: Article) =
            oldItem == newItem
    }
}

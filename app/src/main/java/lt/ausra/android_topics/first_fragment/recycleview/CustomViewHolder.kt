package lt.ausra.android_topics.first_fragment.recycleview

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.ViewSizeResolver
import lt.ausra.android_topics.databinding.FragmentArticleListItemBinding
import lt.ausra.android_topics.repository.news_api.Article

class CustomViewHolder(
    private val binding: FragmentArticleListItemBinding,
    private val onClick: (Article) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private var currentArticle: Article? = null

    init {
        binding.root.setOnClickListener {
            currentArticle?.let { result -> onClick(result) } }

//        if (currentArticle != null) { dar vienas variantas to kas virsuje
//            onClick(currentArticle!!)
//        }
    }

    fun bind(article: Article) {
        currentArticle = article
        binding.apply {
            titleTextView.text = article.title
            dateTextView.text = article.publishedAt
            descriptionTextView.text = article.description
            val photoPath = article.urlToImage
            articleIImageView.load(photoPath) {
                crossfade(enable = true)
                crossfade(durationMillis = 500)
                size(ViewSizeResolver(articleIImageView))
            }
        }
    }
}
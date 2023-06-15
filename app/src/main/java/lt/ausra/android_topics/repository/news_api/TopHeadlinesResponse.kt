package lt.ausra.android_topics.repository.news_api

data class TopHeadlinesResponse(
    val status: String = "",
    val totalResults: Int = -1,
    val articles: MutableList<Article> = mutableListOf()
)

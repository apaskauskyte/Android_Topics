package lt.ausra.android_topics.repository.news_api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/top-headlines")
    suspend fun getTopNews(
        @Query("country") country: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20,
        @Query("apiKey") apiKey: String = "144e0c5c31324ea199b7e9cb410e96d0"
    ): Response<TopHeadlinesResponse>
}

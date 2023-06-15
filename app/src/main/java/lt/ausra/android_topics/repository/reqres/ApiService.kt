package lt.ausra.android_topics.repository.reqres

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("api/users")
    suspend fun getUsers(): Response<UsersResponse>
}
package lt.ausra.android_topics.repository.reqres

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/users")
    suspend fun getUsers(): Response<UsersResponse>

    @GET("api/users/{id}")
    suspend fun getUserDetails(@Path("id") id: Int): Response<UserResponse>
}
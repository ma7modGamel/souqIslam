package com.safwa.souqclean.data.datasource.api


import com.safwa.souqclean.data.models.auth.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.POST

interface AuthService {
    @POST("login")
    suspend fun login(
        @Field("name") name: String,
        @Field("password") password: String
    ): Response<LoginResponse>
}
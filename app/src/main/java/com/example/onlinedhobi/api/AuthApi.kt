package com.example.onlinedhobi.api

import com.example.onlinedhobi.model.User
import com.example.onlinedhobi.model.UserRequest
import com.example.onlinedhobi.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/users/signup")
    suspend fun signup(@Body user: User) : Response<UserResponse>

    @POST("/users/signin")
    suspend fun signin(@Body userRequest: UserRequest) : Response<UserResponse>

}


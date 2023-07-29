package com.example.onlinedhobi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.onlinedhobi.api.AuthApi
import com.example.onlinedhobi.model.User
import com.example.onlinedhobi.model.UserRequest
import com.example.onlinedhobi.model.UserResponse
import com.example.onlinedhobi.util.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authApi: AuthApi) {

    private val _userResponseLiveData = MutableLiveData<NetworkResult<UserResponse>>()

    val userResponseLiveData: LiveData<NetworkResult<UserResponse>>
        get() = _userResponseLiveData

    suspend fun registerUser(user: User) {
        _userResponseLiveData.postValue(NetworkResult.Loading())
        val response = authApi.signup(user)
        handleResponse(response)
    }

    suspend fun loginUser(userRequest: UserRequest) {
        _userResponseLiveData.postValue(NetworkResult.Loading())
        val response = authApi.signin(userRequest)
        handleResponse(response)
    }


    private fun handleResponse(response: Response<UserResponse>) {
        if (response.isSuccessful && response.body() != null) {
            _userResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _userResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _userResponseLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }
}
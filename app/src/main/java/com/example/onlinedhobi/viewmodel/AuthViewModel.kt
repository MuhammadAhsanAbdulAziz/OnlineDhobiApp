package com.example.onlinedhobi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinedhobi.model.User
import com.example.onlinedhobi.model.UserRequest
import com.example.onlinedhobi.model.UserResponse
import com.example.onlinedhobi.repository.AuthRepository
import com.example.onlinedhobi.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    val userResponseLiveData: LiveData<NetworkResult<UserResponse>>
        get() = authRepository.userResponseLiveData

    fun registerUser(user: User){
        viewModelScope.launch {
            authRepository.registerUser(user)
        }
    }

    fun loginUser(userRequest: UserRequest){
        viewModelScope.launch {
            authRepository.loginUser(userRequest)
        }
    }
}
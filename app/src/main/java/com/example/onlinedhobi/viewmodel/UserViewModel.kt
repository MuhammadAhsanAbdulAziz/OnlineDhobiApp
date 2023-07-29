package com.example.onlinedhobi.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinedhobi.model.UserRequest
import com.example.onlinedhobi.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    val userResponseLiveData get() = userRepository.userResponseLiveData
    val alluserResponseLiveData get() = userRepository.alluserResponseLiveData
    val statusLiveData get() = userRepository.statusLiveData

    fun getUser() {
        viewModelScope.launch {
            userRepository.getUser()
        }
    }

    fun getAllUser() {
        viewModelScope.launch {
            userRepository.getAllUser()
        }
    }

    fun deleteUser(complaintId: String){
        viewModelScope.launch {
            userRepository.deleteUser(complaintId)
        }
    }

    fun updateUser(userId : String, userRequest: UserRequest){
        viewModelScope.launch {
            userRepository.updateUser(userId,userRequest)
        }
    }

    fun updateUserRole(userId : String, userRequest: UserRequest){
        viewModelScope.launch {
            userRepository.updateUserRole(userId,userRequest)
        }
    }
}
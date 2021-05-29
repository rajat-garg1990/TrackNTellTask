package com.application.trackntelltask.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.application.trackntelltask.repositories.UserRepository
import com.application.trackntelltask.retro.UserDetails
import com.application.trackntelltask.retro.UserResponse

class UserViewModel() : ViewModel() {
    private val userRepository = UserRepository()
    val users: LiveData<UserResponse> = userRepository.users
    var user: LiveData<UserDetails> = userRepository.user
    //lateinit var user: LiveData<UserDetails>

    fun getUsers() {
        userRepository.getUsers()
    }

    fun getUserById(id:String) {
        userRepository.getUserById(id)
        //user=userRepository.user
        //Log.d("viewmodel", user.value!!.pic_1)
    }
}
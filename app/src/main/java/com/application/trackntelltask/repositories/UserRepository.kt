package com.application.trackntelltask.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.application.trackntelltask.retro.ApiInterface
import com.application.trackntelltask.retro.UserDetails
import com.application.trackntelltask.retro.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserRepository() {
    var users = MutableLiveData<UserResponse>()
    var user= MutableLiveData<UserDetails>()
    var baseUrl = "https://z.trakntell.com/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service = retrofit.create(ApiInterface::class.java)

    fun getUsers() {
        service.getUsers().enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                users.value = response.body()
                Log.d("user", "W")
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("user", t.message.toString())
            }
        })
    }

    fun getUserById(id: String) {
        service.getUserById(id).enqueue(object : Callback<UserDetails> {
            override fun onResponse(call: Call<UserDetails>, response: Response<UserDetails>) {
                user.value = response.body()
                Log.d("user id", id)
            }

            override fun onFailure(call: Call<UserDetails>, t: Throwable) {
                Log.d("user id", id + t.message.toString())
            }
        })
    }
}
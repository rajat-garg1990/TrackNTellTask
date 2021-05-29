package com.application.trackntelltask.retro

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("tnt/servlet/tntAPIGetUsers")
    fun getUsers() : Call<UserResponse>

    @GET("tnt/servlet/tntAPIGetUserDetails")
    fun getUserById(@Query("userid") id:String) :Call<UserDetails>
}
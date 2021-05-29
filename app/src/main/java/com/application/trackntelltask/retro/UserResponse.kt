package com.application.trackntelltask.retro

data class UserResponse(val users: List<User>)

data class User(
    var name: String,
    var userid: String
)

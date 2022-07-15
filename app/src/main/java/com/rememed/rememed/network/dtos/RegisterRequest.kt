package com.rememed.rememed.network.dtos

data class RegisterRequest (
    val username: String,
    val name: String,
    val lastName: String,
    val email: String,
    val password: String
)
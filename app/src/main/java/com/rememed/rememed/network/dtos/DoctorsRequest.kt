package com.rememed.rememed.network.dtos

data class DoctorsRequest (
    val name: String,
    val email: String,
    val phone: String,
    val hospital: String,
    val user: String
)
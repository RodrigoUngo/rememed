package com.rememed.rememed.network.dtos

data class PrescriptionRequest (
    val medicine: String,
    val dosis: String,
    val dayperiod: String,
    val day: String,
    val hourperiod: String,
    val hour: String,
    val food: String,
    val user: String
)
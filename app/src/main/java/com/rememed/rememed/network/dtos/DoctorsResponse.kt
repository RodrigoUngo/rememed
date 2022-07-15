package com.rememed.rememed.network.dtos

import com.google.gson.annotations.SerializedName
import com.rememed.rememed.data.models.Doctor

data class DoctorsResponse (
    @SerializedName("count")
    val count:Int,
    @SerializedName("doctors")
    val doctors:List<Doctor>
)
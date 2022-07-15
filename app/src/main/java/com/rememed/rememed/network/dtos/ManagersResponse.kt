package com.rememed.rememed.network.dtos

import com.google.gson.annotations.SerializedName
import com.rememed.rememed.data.models.Doctor
import com.rememed.rememed.data.models.Manager

data class ManagersResponse (
    @SerializedName("count")
    val count:Int,
    @SerializedName("managers")
    val managers:List<Manager>
)
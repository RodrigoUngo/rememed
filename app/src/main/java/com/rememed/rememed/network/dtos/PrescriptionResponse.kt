package com.rememed.rememed.network.dtos

import com.google.gson.annotations.SerializedName
import com.rememed.rememed.data.models.Prescription

data class PrescriptionResponse (
    @SerializedName("count")
    val count:Int,
    @SerializedName("prescriptions")
    val prescriptions:List<Prescription>
)
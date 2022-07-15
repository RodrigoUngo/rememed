package com.rememed.rememed.network.dtos

import com.rememed.rememed.data.models.Symptom

data class SymptomsRespose (
    val count:Int,
    val symptoms:List<Symptom>
)
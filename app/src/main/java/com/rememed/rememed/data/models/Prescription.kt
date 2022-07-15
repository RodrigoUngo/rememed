package com.rememed.rememed.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "prescription_table")
data class Prescription (
    @PrimaryKey @ColumnInfo(name = "id") @SerializedName("_id") val _id: String,
    @ColumnInfo(name = "medicine") @SerializedName("medicine") val medicine: String,
    @ColumnInfo(name = "dosis") @SerializedName("dosis") val dosis: String,
    @ColumnInfo(name = "dayperiod") @SerializedName("dayperiod") val dayperiod: String,
    @ColumnInfo(name = "day") @SerializedName("day") val day: String,
    @ColumnInfo(name = "hourperiod") @SerializedName("hourperiod") val hourperiod: String,
    @ColumnInfo(name = "hour") @SerializedName("hour") val hour: String,
    @ColumnInfo(name = "food") @SerializedName("food") val food: String
)
package com.rememed.rememed.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "doctor_table")
data class Doctor(
    @PrimaryKey @ColumnInfo(name = "email") @SerializedName("email") val email: String,
    @ColumnInfo(name = "name") @SerializedName("name") val name: String,
    @ColumnInfo(name = "phone") @SerializedName("phone") val phone: String,
    @ColumnInfo(name = "hospital") @SerializedName("hospital") val hospital: String
)
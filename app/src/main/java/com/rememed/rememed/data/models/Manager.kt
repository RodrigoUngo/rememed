package com.rememed.rememed.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "manager_table")
data class Manager(
    @PrimaryKey @ColumnInfo(name = "email") @SerializedName("email") val email: String,
    @ColumnInfo(name = "name") @SerializedName("name") val name: String,
    @ColumnInfo(name = "phone") @SerializedName("phone") val phone: String
)
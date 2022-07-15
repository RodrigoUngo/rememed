package com.rememed.rememed.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "symptom_table")
data class Symptom(
    @PrimaryKey @ColumnInfo(name = "id") @SerializedName("_id") val _id: String,
    @ColumnInfo(name = "date") @SerializedName("date") val date: String,
    @ColumnInfo(name = "text") @SerializedName("text") val text: String
)
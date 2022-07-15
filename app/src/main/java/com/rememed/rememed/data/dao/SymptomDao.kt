package com.rememed.rememed.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rememed.rememed.data.models.Symptom

@Dao
interface SymptomDao {

    @Query("SELECT * FROM  symptom_table")
    fun getAllSymptoms(): LiveData<List<Symptom>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSymptom(symptomList: List<Symptom>)

    @Update
    fun updateSymptom(symptom: Symptom)

    @Delete
    fun deleteSymptom(symptom: Symptom)
}
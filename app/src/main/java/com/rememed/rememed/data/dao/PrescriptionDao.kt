package com.rememed.rememed.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rememed.rememed.data.models.Prescription

@Dao
interface PrescriptionDao {
    @Query("SELECT * FROM  prescription_table")
    fun getAllPrescriptions(): LiveData<List<Prescription>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPrescription(prescriptionList: List<Prescription>)

    @Update
    fun updatePrescription(prescription: Prescription)

    @Delete
    fun deletePrescription(prescription: Prescription)
}
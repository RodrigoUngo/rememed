package com.rememed.rememed.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rememed.rememed.data.models.Doctor

@Dao
interface DoctorDao {

    @Query("SELECT * FROM  doctor_table")
    fun getAllDoctors(): LiveData<List<Doctor>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDoctor(doctorList: List<Doctor>)

    @Update
    fun updateDoctor(doctor: Doctor)

    @Delete
    fun deleteDoctor(doctor: Doctor)
}
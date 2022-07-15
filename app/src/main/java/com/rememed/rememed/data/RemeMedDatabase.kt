package com.rememed.rememed.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rememed.rememed.data.dao.DoctorDao
import com.rememed.rememed.data.dao.ManagerDao
import com.rememed.rememed.data.dao.PrescriptionDao
import com.rememed.rememed.data.dao.SymptomDao
import com.rememed.rememed.data.models.Doctor
import com.rememed.rememed.data.models.Manager
import com.rememed.rememed.data.models.Prescription
import com.rememed.rememed.data.models.Symptom

@Database(
    entities = [Doctor::class, Manager::class, Symptom::class, Prescription::class],
    version = 1,
    exportSchema = false
)
abstract class RemeMedDatabase : RoomDatabase() {
    abstract fun doctorDao(): DoctorDao
    abstract fun managerDao(): ManagerDao
    abstract fun symptonDao(): SymptomDao
    abstract fun prescriptionDao(): PrescriptionDao

    companion object {
        @Volatile
        private var INSTANCE: RemeMedDatabase? = null

        fun getInstance(context: Context): RemeMedDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RemeMedDatabase::class.java,
                    "rememed_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
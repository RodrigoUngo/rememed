package com.rememed.rememed.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rememed.rememed.data.models.Manager

@Dao
interface ManagerDao {

    @Query("SELECT * FROM  manager_table")
    fun getAllManagers(): LiveData<List<Manager>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertManager(managerList: List<Manager>)

    @Update
    fun updateManager(manager: Manager)

    @Delete
    fun deleteManager(manager: Manager)
}
package com.rememed.rememed.repository

import androidx.lifecycle.LiveData
import com.rememed.rememed.data.RemeMedDatabase
import com.rememed.rememed.data.models.Manager
import com.rememed.rememed.network.ApiResponse
import com.rememed.rememed.network.ProjectService
import com.rememed.rememed.network.dtos.ManagersRequest
import retrofit2.HttpException
import java.io.IOException

class ManagerRepository(
    database: RemeMedDatabase,
    private val api: ProjectService,
) {
    private val managerDao = database.managerDao()

    suspend fun getAllManagers(user: String): ApiResponse<LiveData<List<Manager>>> {
        return try{
            val response = api.getAllManager(user)
            if(response.count>0){
                managerDao.insertManager(response.managers)
            }
            ApiResponse.Sucess(data = managerDao.getAllManagers())
        } catch (e: HttpException){
            ApiResponse.Error(exception = e)
        } catch (e: IOException){
            ApiResponse.Error(exception = e)
        }
    }

    suspend fun saveManager(name: String, email: String, phone: String, user: String): ApiResponse<String>{
        try{

            val response = api.saveManager(ManagersRequest(name, email, phone, user))
            return ApiResponse.Sucess(response.message)
        } catch (e: HttpException){
            if(e.code()==400){
                return ApiResponse.ErrorWithMessage(e.response()?.body().toString())
            }
            return ApiResponse.Error(e)
        } catch (e: IOException){
            return ApiResponse.Error(e)
        }
    }
}
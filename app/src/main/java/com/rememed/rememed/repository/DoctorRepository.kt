package com.rememed.rememed.repository

import androidx.lifecycle.LiveData
import com.rememed.rememed.data.RemeMedDatabase
import com.rememed.rememed.data.models.Doctor
import com.rememed.rememed.network.ApiResponse
import com.rememed.rememed.network.ProjectService
import com.rememed.rememed.network.dtos.DoctorsRequest
import retrofit2.HttpException
import java.io.IOException

class DoctorRepository(
    database: RemeMedDatabase,
    private val api: ProjectService,
) {
    private val doctorDao = database.doctorDao()

    suspend fun getAllDoctors(user: String): ApiResponse<LiveData<List<Doctor>>> {
        return try{
            val response = api.getAllDoctor(user)
            if(response.count>0){
                doctorDao.insertDoctor(response.doctors)
            }
            ApiResponse.Sucess(data = doctorDao.getAllDoctors())
        } catch (e: HttpException){
            ApiResponse.Error(exception = e)
        } catch (e: IOException){
            ApiResponse.Error(exception = e)
        }
    }

    suspend fun saveDoctor(name: String, email: String, phone: String, hospital: String, user: String): ApiResponse<String>{
        try{

            val response = api.saveDoctor(DoctorsRequest(name, email, phone, hospital, user))
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
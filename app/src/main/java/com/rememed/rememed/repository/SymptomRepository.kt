package com.rememed.rememed.repository

import androidx.lifecycle.LiveData
import com.rememed.rememed.data.RemeMedDatabase
import com.rememed.rememed.data.models.Symptom
import com.rememed.rememed.network.ApiResponse
import com.rememed.rememed.network.ProjectService
import com.rememed.rememed.network.dtos.SymptomsRequest
import retrofit2.HttpException
import java.io.IOException

class SymptomRepository(
    database: RemeMedDatabase,
    private val api: ProjectService,
) {
    private val symptomDao = database.symptonDao()

    suspend fun getAllSymptoms(user: String): ApiResponse<LiveData<List<Symptom>>> {
        return try{
            val response = api.getAllSymptoms(user)
            if(response.count>0){
                symptomDao.insertSymptom(response.symptoms)
            }
            ApiResponse.Sucess(data = symptomDao.getAllSymptoms())
        } catch (e: HttpException){
            ApiResponse.Error(exception = e)
        } catch (e: IOException){
            ApiResponse.Error(exception = e)
        }
    }

    suspend fun saveSymptom(date: String, text: String, user: String): ApiResponse<String> {
        try{

            val response = api.saveSymptom(SymptomsRequest(date, text, user))
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
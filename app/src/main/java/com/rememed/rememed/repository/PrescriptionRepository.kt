package com.rememed.rememed.repository

import androidx.lifecycle.LiveData
import com.rememed.rememed.data.RemeMedDatabase
import com.rememed.rememed.data.models.Doctor
import com.rememed.rememed.data.models.Prescription
import com.rememed.rememed.network.ApiResponse
import com.rememed.rememed.network.ProjectService
import com.rememed.rememed.network.dtos.DoctorsRequest
import com.rememed.rememed.network.dtos.PrescriptionRequest
import retrofit2.HttpException
import java.io.IOException

class PrescriptionRepository(
    database: RemeMedDatabase,
    private val api: ProjectService,
) {
    private val prescriptionDao = database.prescriptionDao()

    suspend fun getAllPrescription(user: String): ApiResponse<LiveData<List<Prescription>>> {
        return try{
            val response = api.getAllPrescription(user)
            if(response.count>0){
                prescriptionDao.insertPrescription(response.prescriptions)
            }
            ApiResponse.Sucess(data = prescriptionDao.getAllPrescriptions())
        } catch (e: HttpException){
            ApiResponse.Error(exception = e)
        } catch (e: IOException){
            ApiResponse.Error(exception = e)
        }
    }

    suspend fun savePrescription(medicine: String,
                                 dosis: String,
                                 dayperiod: String,
                                 day: String,
                                 hourperiod:String,
                                 hour: String,
                                 food: String,
                                 user: String): ApiResponse<String> {
        try{

            val response = api.savePrescription(PrescriptionRequest(medicine, dosis, dayperiod, day, hourperiod, hour, food, user))
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
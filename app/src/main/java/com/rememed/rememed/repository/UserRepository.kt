package com.rememed.rememed.repository

import com.rememed.rememed.network.ApiResponse
import com.rememed.rememed.network.ProjectService
import com.rememed.rememed.network.dtos.LoginRequest
import com.rememed.rememed.network.dtos.RegisterRequest
import retrofit2.HttpException
import java.io.IOException

class UserRepository(private val api: ProjectService) {

    suspend fun login(username: String, password: String): ApiResponse<String> {
        try{

            val response = api.login(LoginRequest(username,password))
            return ApiResponse.Sucess(response.token)
        } catch (e: HttpException){
            if(e.code()==400){
                return ApiResponse.ErrorWithMessage(e.response()?.body().toString())
            }
            return ApiResponse.Error(e)
        } catch (e: IOException){
            return ApiResponse.Error(e)
        }
    }

    suspend fun register(username: String, name: String, lastName: String, email: String, password: String): ApiResponse<String>{
        try{

            val response = api.register(RegisterRequest(username, name, lastName, email, password))
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
package com.rememed.rememed.network

import com.rememed.rememed.network.dtos.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProjectService {
    @GET("/prescriptions/{user}")
    suspend fun getAllPrescription(@Path("user") user: String): PrescriptionResponse

    @POST("/prescriptions")
    suspend fun savePrescription(@Body fields: PrescriptionRequest): MessageResponse

    @GET("/doctors/{user}")
    suspend fun getAllDoctor(@Path("user") user: String): DoctorsResponse

    @POST("/doctors")
    suspend fun saveDoctor(@Body fields: DoctorsRequest): MessageResponse

    @GET("/managers/{user}")
    suspend fun getAllManager(@Path("user") user: String): ManagersResponse

    @POST("/managers")
    suspend fun saveManager(@Body fields: ManagersRequest): MessageResponse

    @GET("/symptoms/{user}")
    suspend fun getAllSymptoms(@Path("user") user: String): SymptomsRespose

    @POST("/symptoms")
    suspend fun saveSymptom(@Body fields: SymptomsRequest): MessageResponse

    @POST("/login")
    suspend fun login(@Body credentials: LoginRequest): LoginResponse

    @POST("/register")
    suspend fun register(@Body credentials: RegisterRequest): MessageResponse
}
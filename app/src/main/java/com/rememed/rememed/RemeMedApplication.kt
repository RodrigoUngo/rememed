package com.rememed.rememed

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.rememed.rememed.data.RemeMedDatabase
import com.rememed.rememed.network.RetrofitInstance
import com.rememed.rememed.repository.*

class RemeMedApplication : Application() {
    val prefs: SharedPreferences by lazy{
        getSharedPreferences("RemeMed", Context.MODE_PRIVATE)
    }

    val dataBase by lazy {
        RemeMedDatabase.getInstance(this)
    }

    private fun getAPIService() = with(RetrofitInstance){
        setToken(getToken())
        getWordServices()
    }

    fun getDoctorRepository() =
        DoctorRepository(dataBase,getAPIService())

    fun getManagerRepository() =
        ManagerRepository(dataBase,getAPIService())

    fun getUserRepository()=
        UserRepository(getAPIService())

    fun getSymptomRepository()=
        SymptomRepository(dataBase, getAPIService())

    fun getPrescriptionRepository()=
        PrescriptionRepository(dataBase, getAPIService())

    private fun getToken(): String = prefs.getString(USER_TOKEN,"")!!

    fun getUsername(): String = prefs.getString(USER_NAME,"")!!

    fun isUserLogin() = getToken() != ""

    fun saveAuthToken(token: String){
        val editor = prefs.edit()
        editor.putString(USER_TOKEN,token)
        editor.apply()
    }

    fun saveUsername(username: String){
        val editor = prefs.edit()
        editor.putString(USER_NAME,username)
        editor.apply()
    }

    fun deleteAuthToken(){
        val editor = prefs.edit()
        editor.remove(USER_TOKEN)
        editor.commit()
    }

    fun deleteUsername(){
        val editor = prefs.edit()
        editor.remove(USER_NAME)
        editor.commit()
    }

    companion object{
        const val USER_TOKEN = "user_token"
        var USER_NAME = "user_name"
    }
}
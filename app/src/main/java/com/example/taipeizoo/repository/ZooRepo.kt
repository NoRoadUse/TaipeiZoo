package com.example.taipeizoo.repository

import com.example.taipeizoo.http.TaipeiZoo
import retrofit2.Retrofit

class ZooRepo {
    companion object {
        private val retrofit = Retrofit.Builder().build()
        private val apiService = retrofit.create(TaipeiZoo::class.java)
    }
    
    suspend fun getZooSectionIntro() {
        val response = apiService.getZooSectionIntro()
        response.result
    }

    suspend fun getAnimalsInfo() {
        val response = apiService.getAnimalsInfo()
        response.result
    }
}
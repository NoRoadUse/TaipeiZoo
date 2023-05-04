package com.example.taipeizoo.repository

import com.example.taipeizoo.http.TaipeiZoo
import retrofit2.Retrofit

class ZooRepo {
    companion object {
        private val retrofit = Retrofit.Builder().build()
        private val apiService = retrofit.create(TaipeiZoo::class.java)
    }

    suspend fun getZooSectionIntro() = apiService.getZooSectionIntro()

    suspend fun getAnimalsInfo() = apiService.getAnimalsInfo()
}
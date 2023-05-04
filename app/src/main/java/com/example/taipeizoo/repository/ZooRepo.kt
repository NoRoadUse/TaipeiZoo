package com.example.taipeizoo.repository

import com.example.taipeizoo.http.TaipeiZoo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ZooRepo {
    companion object {
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://data.taipei/api/v1/dataset/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        private val apiService = retrofit.create(TaipeiZoo::class.java)
    }

    suspend fun getZooSectionIntro() = apiService.getZooSectionIntro()

    suspend fun getAnimalsInfo() = apiService.getAnimalsInfo()
}
package com.example.taipeizoo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taipeizoo.datamodel.ResponseAnimal
import com.example.taipeizoo.datamodel.ResponseSection
import com.example.taipeizoo.repository.ZooRepo
import kotlinx.coroutines.launch

class ZooViewModel : ViewModel() {

    companion object {
        private val repo = ZooRepo()
    }

    val zooSection = SingleLiveEvent<ResponseSection>()

    fun getZooSectionIntro() {
        viewModelScope.launch {
            zooSection.postValue(repo.getZooSectionIntro())
        }
    }

    val zooAnimal = SingleLiveEvent<ResponseAnimal>()

    fun getAnimalsInfo() {
        viewModelScope.launch {
            zooAnimal.postValue(repo.getAnimalsInfo())
        }
    }
}
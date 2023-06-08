package com.example.taipeizoo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taipeizoo.datamodel.AnimalData
import com.example.taipeizoo.datamodel.AnimalResultX
import com.example.taipeizoo.datamodel.SectionData
import com.example.taipeizoo.datamodel.SectionResultX
import com.example.taipeizoo.repository.ZooRepo
import kotlinx.coroutines.launch

class ZooViewModel(private val repo: ZooRepo = ZooRepo()) : ViewModel() {

    private var responseAnimal = SingleLiveEvent<AnimalData>()
    private var selectedSection: SectionResultX? = null
    private var selectedAnimal: AnimalResultX? = null

    private val _zooSection = SingleLiveEvent<SectionData>()
    val zooSection: LiveData<SectionData>
        get() = _zooSection

    fun getZooSectionIntro() {
        viewModelScope.launch {
            _zooSection.postValue(repo.getZooSectionIntro())
        }
    }

    fun getAnimalsInfo() {
        viewModelScope.launch {
            responseAnimal.value = repo.getAnimalsInfo()
        }
    }


    private val _animalResult = MutableLiveData<AnimalResultX>()
    val animalResult: LiveData<AnimalResultX>
        get() = _animalResult

    fun getAnimals(section: String): List<AnimalResultX>? {
        return responseAnimal.value?.animalResult?.results?.filter {
            it.aLocation == section
        }
    }

    fun setSection(section: SectionResultX) {
        selectedSection = section
    }

    fun getSelectSection() = selectedSection

    fun setAnimal(animal: AnimalResultX) {
        selectedAnimal = animal
    }

    fun getSelectAnimal() = selectedAnimal
}
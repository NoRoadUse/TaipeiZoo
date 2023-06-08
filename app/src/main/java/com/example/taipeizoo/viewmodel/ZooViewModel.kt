package com.example.taipeizoo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taipeizoo.datamodel.AnimalData
import com.example.taipeizoo.datamodel.AnimalResultX
import com.example.taipeizoo.datamodel.SectionData
import com.example.taipeizoo.datamodel.SectionResult
import com.example.taipeizoo.repository.ZooRepo
import kotlinx.coroutines.launch

class ZooViewModel(private val repo: ZooRepo = ZooRepo()) : ViewModel() {

    private val responseAnimal = SingleLiveEvent<AnimalData>()
    private val _selectedSection = SingleLiveEvent<SectionResult>()
    private val selectedSection: LiveData<SectionResult>
        get() = _selectedSection
    private val _selectedAnimal = SingleLiveEvent<AnimalResultX>()
    private val selectedAnimal: LiveData<AnimalResultX>
        get() = _selectedAnimal

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

    fun getAnimals(section: String): List<AnimalResultX>? {
        return responseAnimal.value?.animalResult?.results?.filter {
            it.aLocation == section
        }
    }

    fun setSection(section: SectionResult) {
        _selectedSection.value = section
    }

    fun getSelectSection() = selectedSection.value

    fun setAnimal(animal: AnimalResultX) {
        _selectedAnimal.value = animal
    }

    fun getSelectAnimal() = selectedAnimal.value
}
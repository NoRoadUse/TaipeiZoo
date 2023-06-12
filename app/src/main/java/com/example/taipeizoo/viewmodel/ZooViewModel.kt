package com.example.taipeizoo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taipeizoo.datamodel.AnimalData
import com.example.taipeizoo.datamodel.AnimalResult
import com.example.taipeizoo.datamodel.SectionData
import com.example.taipeizoo.datamodel.SectionResult
import com.example.taipeizoo.http.ResultCall
import com.example.taipeizoo.repository.ZooRepo
import kotlinx.coroutines.launch
import timber.log.Timber

class ZooViewModel(private val repo: ZooRepo = ZooRepo()) : ViewModel() {

    private val responseAnimal = SingleLiveEvent<AnimalData>()
    private val _selectedSection = SingleLiveEvent<SectionResult>()
    private val selectedSection: LiveData<SectionResult>
        get() = _selectedSection
    private val _selectedAnimal = SingleLiveEvent<AnimalResult>()
    private val selectedAnimal: LiveData<AnimalResult>
        get() = _selectedAnimal

    private val _zooSection = SingleLiveEvent<SectionData>()
    val zooSection: LiveData<SectionData>
        get() = _zooSection

    fun getZooSectionIntro() {
        viewModelScope.launch {

            val result = repo.getZooSectionIntro()

            result.onSuccess {
                _zooSection.postValue(it)
            }.onFailure {
                Timber.e("network issue ${it.printStackTrace()}")
            }
        }
    }

    fun getAnimalsInfo() {
        viewModelScope.launch {
            val result = repo.getAnimalsInfo()
            result.onSuccess { responseAnimal.value = it }
                .onFailure { Timber.e("network issue ${it.printStackTrace()}") }
        }
    }

    fun getAnimals(section: String): List<AnimalResult>? {
        return responseAnimal.value?.animalContent?.results?.filter {
            it.aLocation == section
        }
    }

    fun setSection(section: SectionResult) {
        _selectedSection.value = section
    }

    fun getSelectSection() = selectedSection.value

    fun setAnimal(animal: AnimalResult) {
        _selectedAnimal.value = animal
    }

    fun getSelectAnimal() = selectedAnimal.value
}
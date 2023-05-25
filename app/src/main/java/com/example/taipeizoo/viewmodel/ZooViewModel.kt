package com.example.taipeizoo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taipeizoo.datamodel.AnimalContent
import com.example.taipeizoo.datamodel.ResponseAnimal
import com.example.taipeizoo.datamodel.SectionData
import com.example.taipeizoo.datamodel.SectionResultX
import com.example.taipeizoo.repository.ZooRepo
import kotlinx.coroutines.launch

class ZooViewModel : ViewModel() {

    companion object {
        private val repo = ZooRepo()
    }

    private var responseAnimal: ResponseAnimal? = null
    private var selectedSection : SectionResultX? = null
    private var selectedAnimal : AnimalContent? = null

    val zooSection = SingleLiveEvent<SectionData>()

    fun getZooSectionIntro() {
        viewModelScope.launch {
            zooSection.postValue(repo.getZooSectionIntro())
        }
    }

    fun getAnimalsInfo() {
        viewModelScope.launch {
            responseAnimal = repo.getAnimalsInfo()
        }
    }

    fun getAnimals(section: String): List<AnimalContent>? {
        return responseAnimal?.result?.results?.filter {
            it.a_location == section
        }
    }

    fun setSection(section: SectionResultX) {
        selectedSection = section
    }

    fun getSelectSection() = selectedSection

    fun setAnimal(animal: AnimalContent) {
        selectedAnimal = animal
    }

    fun getSelectAnimal() = selectedAnimal
}
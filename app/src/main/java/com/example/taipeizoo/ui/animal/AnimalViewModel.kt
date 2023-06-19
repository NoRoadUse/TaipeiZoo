package com.example.taipeizoo.ui.animal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taipeizoo.datamodel.AnimalResult
import com.example.taipeizoo.datamodel.SectionResult

class AnimalViewModel : ViewModel() {

    private var selectedAnimal: AnimalResult? = null
    fun setSelectAnimal(animalResult: AnimalResult) {
        selectedAnimal = animalResult
    }

    fun getSelectAnimal() = selectedAnimal
}
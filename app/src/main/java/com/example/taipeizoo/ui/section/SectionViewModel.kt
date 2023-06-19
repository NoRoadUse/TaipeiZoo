package com.example.taipeizoo.ui.section

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taipeizoo.datamodel.AnimalData
import com.example.taipeizoo.datamodel.AnimalResult
import com.example.taipeizoo.datamodel.SectionResult
import com.example.taipeizoo.repository.ZooRepo
import com.example.taipeizoo.ui.animal.AnimalItem
import com.example.taipeizoo.viewmodel.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber

class SectionViewModel(private val repo: ZooRepo = ZooRepo()) : ViewModel() {

    private var selectedSection: SectionResult? = null

    private val _responseAnimal = SingleLiveEvent<List<AnimalResult>>()
    val responseAnimal: LiveData<List<AnimalResult>>
        get() = _responseAnimal

    private val _formatContentList = SingleLiveEvent<List<AnimalItem>>()
    val formatContentList: LiveData<List<AnimalItem>>
        get() = _formatContentList

    private var _section = ""
    fun setSelectedSection(sectionResult:SectionResult) {
        selectedSection = sectionResult
    }
    fun getAnimalsInfo() {
        viewModelScope.launch {
            val result = repo.getAnimalsInfo()
            result.onSuccess {
                _responseAnimal.value = it.animalContent?.results?.filter { it.aLocation == selectedSection?.eName }
            }
                .onFailure { Timber.e("network issue ${it.printStackTrace()}") }
        }
    }

    fun parseShowDataContent(animals: List<AnimalResult>?) {
        Timber.e("!! $animals")
        val formatContent = mutableListOf<AnimalItem>()
        selectedSection?.let {
            formatContent.add(AnimalItem.Header(it))
            animals?.forEach {
                formatContent.add(AnimalItem.Data(animalResult = it))
            }
        }
        _formatContentList.postValue(formatContent)

    }
}
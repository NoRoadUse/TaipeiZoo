package com.example.taipeizoo.ui.portal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taipeizoo.datamodel.SectionData
import com.example.taipeizoo.repository.ZooRepo
import com.example.taipeizoo.viewmodel.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber

class PortalViewModel(private val repo: ZooRepo = ZooRepo()) : ViewModel() {

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
}
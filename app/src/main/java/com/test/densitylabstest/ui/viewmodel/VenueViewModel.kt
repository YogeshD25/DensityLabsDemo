package com.test.densitylabstest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.densitylabstest.data.local.entities.Venues
import com.test.densitylabstest.data.repository.VenueRepository
import com.test.densitylabstest.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.collect


@HiltViewModel
class VenueViewModel @Inject constructor(
    private val repository: VenueRepository,
) : ViewModel() {

    private val _allVenuesData: MutableLiveData<Resource<List<Venues>>> = MutableLiveData()
    val allVenuesData: LiveData<Resource<List<Venues>>> = _allVenuesData

    private val _allSavedVenuesData: MutableLiveData<List<Venues>> = MutableLiveData()
    val allSavedVenuesData: LiveData<List<Venues>> = _allSavedVenuesData

    fun fetchAllVenueData() = viewModelScope.launch {
        repository.fetchVenues().collect {
            _allVenuesData.value = it
        }
    }

    fun setUserSavedMatch(id: String, value: Boolean) = viewModelScope.launch {
        if (value) {
            repository.userSavedPlace(id,value)
        } else {
            repository.userSavedPlace(id,value)
        }

    }

    fun getSavedMarkData(){
        viewModelScope.launch {
            repository.getUserSavedPlace().collect {
                _allSavedVenuesData.postValue(it)
            }
        }
    }




}
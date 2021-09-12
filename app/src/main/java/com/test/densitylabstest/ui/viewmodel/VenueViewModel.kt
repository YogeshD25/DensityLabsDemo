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


@HiltViewModel
class VenueViewModel @Inject constructor(
    private val repository: VenueRepository,
) : ViewModel() {

    private val _allVenuesData: MutableLiveData<Resource<List<Venues>>> = MutableLiveData()
    val allVenuesData: LiveData<Resource<List<Venues>>> = _allVenuesData




}
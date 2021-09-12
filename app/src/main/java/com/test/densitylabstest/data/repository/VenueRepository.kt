package com.test.densitylabstest.data.repository

import com.test.densitylabstest.data.local.entities.Venues
import com.test.densitylabstest.util.NetworkResult
import com.test.densitylabstest.util.Resource
import kotlinx.coroutines.flow.Flow

interface VenueRepository {

    suspend fun fetchVenues() : Flow<Resource<List<Venues>>>

    suspend fun userSavedPlace(id: String, value: Boolean)

    suspend fun getUserSavedPlace() : Flow<List<Venues>>

}
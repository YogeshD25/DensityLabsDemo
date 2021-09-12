package com.test.densitylabstest.data.repository

import com.test.densitylabstest.data.local.entities.Venues
import com.test.densitylabstest.util.Resource
import kotlinx.coroutines.flow.Flow

interface VenueRepository {

    fun fetchVenues() : Flow<Resource<List<Venues>>>

}
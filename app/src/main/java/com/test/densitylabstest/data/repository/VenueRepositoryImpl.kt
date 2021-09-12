package com.test.densitylabstest.data.repository

import com.test.densitylabstest.data.local.dao.VenueDao
import com.test.densitylabstest.data.local.entities.Venues
import com.test.densitylabstest.data.remote.VenueDataSource
import com.test.densitylabstest.util.Resource
import com.test.densitylabstest.util.networkBoundResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class VenueRepositoryImpl @Inject constructor(
    private val venueDataSource: VenueDataSource,
    private val venueDao: VenueDao,
) : VenueRepository {

    override fun fetchVenues(): Flow<Resource<List<Venues>>> = networkBoundResource(
        query = {
            venueDao.getAllVenue()
        },
        fetch = {
            venueDataSource.getVenues()
        },
        shouldFetch = { data ->
            data.isEmpty()
        },
        saveFetchResult = { categories ->
            withContext(Dispatchers.IO) {
                categories.body()?.let { venueDao.insertAllVenues(it.response.venues) }
            }
        })

}
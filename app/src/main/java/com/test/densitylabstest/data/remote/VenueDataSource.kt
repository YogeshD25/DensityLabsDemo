package com.test.densitylabstest.data.remote

import com.test.densitylabstest.data.remote.service.VenueService
import javax.inject.Inject

class VenueDataSource @Inject constructor(private val venueService: VenueService) {

    suspend fun getVenues() = venueService.getVenuesData()

}
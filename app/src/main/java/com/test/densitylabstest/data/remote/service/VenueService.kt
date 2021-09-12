package com.test.densitylabstest.data.remote.service

import com.test.densitylabstest.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface VenueService {

    @GET("v2/venues/search")
    suspend fun getVenuesData(
        @Query("ll") ll: String = "40.7484,-73.9857",
        @Query("oauth_token") oauth_token: String = "NPKYZ3WZ1VYMNAZ2FLX1WLECAWSMUVOQZOIDBN53F3LVZBPQ",
        @Query("v") v: String = "20180616"
    ): Response<ApiResponse>
}
package com.test.densitylabstest.model

import com.google.gson.annotations.SerializedName


data class BeenHere (

	@SerializedName("lastCheckinExpiredAt") val lastCheckinExpiredAt : Int
)
package com.test.densitylabstest.model

import com.google.gson.annotations.SerializedName


data class ApiResponse (

	@SerializedName("meta") val meta : Meta,
	@SerializedName("notifications") val notifications : List<Notifications>,
	@SerializedName("response") val response : Response
)
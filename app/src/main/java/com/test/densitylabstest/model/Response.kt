package com.test.densitylabstest.model

import com.google.gson.annotations.SerializedName
import com.test.densitylabstest.data.local.entities.Venues


data class Response (

	@SerializedName("venues") val venues : List<Venues>,
	@SerializedName("confident") val confident : Boolean
)
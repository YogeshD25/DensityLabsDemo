package com.test.densitylabstest.model

import com.google.gson.annotations.SerializedName


data class Meta (

	@SerializedName("code") val code : Int,
	@SerializedName("requestId") val requestId : String
)
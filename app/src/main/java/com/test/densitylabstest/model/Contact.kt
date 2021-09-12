package com.test.densitylabstest.model

import com.google.gson.annotations.SerializedName


data class Contact (

	@SerializedName("phone") val phone : Int,
	@SerializedName("formattedPhone") val formattedPhone : String
)
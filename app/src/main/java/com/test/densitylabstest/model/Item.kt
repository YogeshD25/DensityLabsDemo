package com.test.densitylabstest.model

import com.google.gson.annotations.SerializedName


data class Item (

	@SerializedName("unreadCount") val unreadCount : Int
)
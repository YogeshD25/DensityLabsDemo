
package com.test.densitylabstest.model
import com.google.gson.annotations.SerializedName


data class Notifications (

	@SerializedName("type") val type : String?,
	@SerializedName("item") val item : Item?
)
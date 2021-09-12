package com.test.densitylabstest.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.densitylabstest.model.*

@Entity(tableName = "venues")
data class Venues(

    @PrimaryKey
    val id : String,
    val name : String,
    @Embedded(prefix = "contact_")
    val contact : Contact?,
    @Embedded(prefix = "location_")
    val location : Location?,
    val verified : Boolean,
    @Embedded(prefix = "stats_")
    val stats : Stats,
    @Embedded(prefix = "beenhere_")
    val beenHere : BeenHere?,
    @Embedded(prefix = "venue_page_")
    val venuePage : VenuePage?,
    @Embedded(prefix = "here_now_")
    val hereNow : HereNow?,
    val referralId : String?,
    val hasPerk : Boolean?,
    var isSaved : Boolean = false



)

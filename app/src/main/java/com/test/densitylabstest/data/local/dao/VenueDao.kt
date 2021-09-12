package com.test.densitylabstest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.densitylabstest.data.local.entities.Venues
import kotlinx.coroutines.flow.Flow

@Dao
interface VenueDao {

    @Query("SELECT * FROM venues")
    fun getAllVenue(): Flow<List<Venues>>

    @Query("SELECT * FROM venues WHERE isSaved = 1")
    fun getAllSavedVenues(): Flow<List<Venues>>

    @Query("SELECT * FROM venues WHERE id = :id")
    fun getVenue(id: String): Flow<Venues>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllVenues(characters: List<Venues>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVenue(user: Venues)

    @Query("UPDATE venues SET isSaved = :value where id = :id")
    suspend fun userSavedPlace(id: String, value: Boolean)

}
package com.test.densitylabstest.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test.densitylabstest.data.local.dao.VenueDao
import com.test.densitylabstest.data.local.entities.Venues

@Database(entities = [Venues::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getVenueDao(): VenueDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null


        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "venues.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}
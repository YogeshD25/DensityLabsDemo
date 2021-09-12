package com.test.densitylabstest.di

import com.test.densitylabstest.data.repository.VenueRepository
import com.test.densitylabstest.data.repository.VenueRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindVenueRepository(
        venueRepositoryImpl: VenueRepositoryImpl
    ): VenueRepository
}
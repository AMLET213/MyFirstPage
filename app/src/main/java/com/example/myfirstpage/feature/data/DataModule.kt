package com.example.myfirstpage.feature.data

import com.example.myfirstpage.feature.domain.PlantRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    private fun networkDS(): NetworkDataSource = NetworkDataSource()

    private fun cacheDS(): CacheDataSource = CacheDataSource()

    @Provides
    fun provideRepository(): PlantRepository {
        return PlantRepositoryImpl(
            network = networkDS(),
            cache = cacheDS()
        )
    }
}

package com.example.myfirstpage.feature.data

import com.example.myfirstpage.feature.domain.PlantRepository

class DataModule {

    private fun networkDS(): NetworkDataSource = NetworkDataSource()

    private fun cacheDS(): CacheDataSource = CacheDataSource()

    fun repository(): PlantRepository {
        return PlantRepositoryImpl(
            network = networkDS(),
            cache = cacheDS()
        )
    }
}

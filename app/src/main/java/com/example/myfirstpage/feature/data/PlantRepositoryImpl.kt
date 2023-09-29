package com.example.myfirstpage.feature.data

import com.example.myfirstpage.feature.domain.PlantEntity
import com.example.myfirstpage.feature.domain.PlantRepository

internal class PlantRepositoryImpl(
    private val cache: CacheDataSource,
    private val network: NetworkDataSource,
) : PlantRepository {

    override suspend fun getPlants(): List<PlantEntity.Main> {
        if (cache.isEmpty()) {
            cache.save(network.loadPlants())
        }

        return cache.getPlants().map { PlantEntity.Main(it.id, it.title) }
    }

    override suspend fun addPlant(newPlant: PlantEntity.New) {
        val id = cache.getPlants().size
        cache.savePlant(PlantDto(id, newPlant.title))
    }

    override suspend fun deletePlant(plantId: Int) {
        cache.removePlant(plantId)
    }

    override suspend fun sortAsc() {
        cache.sortAsc()
    }

    override suspend fun sortDesc() {
        cache.sortDesc()
    }
}

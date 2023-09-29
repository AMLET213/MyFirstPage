package com.example.myfirstpage.feature.domain


interface PlantRepository {

    suspend fun getPlants(): List<PlantEntity.Main>

    suspend fun addPlant(newPlant: PlantEntity.New)

    suspend fun deletePlant(plantId: Int)

    suspend fun sortAsc()

    suspend fun sortDesc()
}

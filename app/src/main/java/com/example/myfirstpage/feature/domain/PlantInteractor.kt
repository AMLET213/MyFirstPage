package com.example.myfirstpage.feature.domain

interface PlantInteractor {

    suspend fun loadContent(): List<PlantEntity.Main>

    suspend fun addPlant(newPlant: PlantEntity.New)

    suspend fun deletePlant(plantId: Int)

    suspend fun sortAsc()

    suspend fun sortDesc()
}

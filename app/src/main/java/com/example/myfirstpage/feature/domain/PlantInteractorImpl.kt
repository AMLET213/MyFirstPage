package com.example.myfirstpage.feature.domain

internal class PlantInteractorImpl(private val repository : PlantRepository): PlantInteractor {

    override suspend fun loadContent(): List<PlantEntity.Main> {
        return repository.getPlants()
    }

    override suspend fun addPlant(newPlant: PlantEntity.New) {
        repository.addPlant(newPlant)
    }

    override suspend fun deletePlant(plantId: Int) {
        repository.deletePlant(plantId)
    }

    override suspend fun sortAsc() {
        repository.sortAsc()
    }

    override suspend fun sortDesc() {
        repository.sortDesc()
    }

}

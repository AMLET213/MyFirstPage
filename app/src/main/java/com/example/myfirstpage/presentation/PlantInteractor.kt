package com.example.myfirstpage.presentation


class PlantInteractor {

    private val repository = PlantRepository()

    suspend fun loadContent(): List<PlantEntity.Main> {
        return repository.getPlants()
    }

    suspend fun addPlant(newPlant: PlantEntity.New) {
        repository.addPlant(newPlant)
    }

    suspend fun deletePlant(plantId: Int) {
        repository.deletePlant(plantId)
    }

    suspend fun sortAsc() {
        repository.sortAsc()
    }

    suspend fun sortDesc() {
        repository.sortDesc()
    }

}

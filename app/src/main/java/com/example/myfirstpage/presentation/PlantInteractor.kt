package com.example.myfirstpage.presentation


class PlantInteractor {

    private val repository = PlantRepository()

     suspend fun loadContent(): List<PlantEntity.Main> {
        return repository.getPlants()
    }

    fun addPlant(newPlant:PlantEntity.New) {
        repository.addPlant(newPlant)
    }

    fun deletePlant(plantId:Int) {
        repository.deletePlant(plantId)
    }

    fun sortAsc(){
        repository.sortAsc()
    }

    fun sortDesc(){
        repository.sortDesc()
    }

}

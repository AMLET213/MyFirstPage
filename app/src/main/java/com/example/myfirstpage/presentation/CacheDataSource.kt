package com.example.myfirstpage.presentation


class CacheDataSource {

    private var plantList = mutableListOf<PlantDto>()

    fun isEmpty(): Boolean {
        return plantList.isEmpty()
    }

    suspend fun save(plants: List<PlantDto>) {
        plantList = ArrayList(plants)
    }

    suspend fun getPlants(): List<PlantDto> {
        return plantList
    }

    fun savePlant(plantDto: PlantDto){
        plantList.add(plantDto)
    }

    fun removePlant(plantId:Int) {
        plantList.removeIf { it.id == plantId }
    }
    fun sortAsc(){
        val selector: (PlantDto) -> Int? = { plant -> plant.id }
        plantList.sortBy(selector)
    }
    fun sortDesc(){
        val selector: (PlantDto) -> Int? = { plant -> plant.id }
        plantList.sortByDescending(selector)
    }

}

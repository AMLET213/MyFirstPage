package com.example.myfirstpage.presentation


class PlantRepository {

    private val cache = CacheDataSource()
    private val network = NetworkDataSource()

    suspend fun getPlants(): List<PlantEntity.Main> {
        if (cache.isEmpty()) {
            cache.save(network.loadPlants())
        }

        return cache.getPlants().map { PlantEntity.Main(it.id,it.title) }
    }

    fun addPlant(newPlant: PlantEntity.New) {
//        val id = cache.getPlants().size
//        cache.savePlant(PlantDto(id,newPlant.title))
    }

    fun deletePlant(plantId:Int){
        cache.removePlant(plantId)
    }

    fun sortAsc(){
        cache.sortAsc()
    }

    fun sortDesc(){
        cache.sortDesc()
    }
}

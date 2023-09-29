package com.example.myfirstpage.feature.data

internal class NetworkDataSource {

    private var plantList = mutableListOf<PlantDto>()
    private var index = 0

    suspend fun loadPlants(): List<PlantDto> {
        while (index <= 5) {
            val plant = PlantDto(index, "Plant $index")
            plantList.add(plant)
            index++
        }
        return plantList
    }
}

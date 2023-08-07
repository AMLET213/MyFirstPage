package com.example.myfirstpage.presentation

import com.example.myfirstpage.Plant

class PlantInteractor {
    private var plantList = ArrayList<Plant>()
    private var index = 0
    fun loadContentInteractor(): ArrayList<Plant> {
        while (index <= 5) {
            val plant = Plant(index, "Plant $index")
            plantList.add(plant)
            index++
        }
        return plantList
    }
}

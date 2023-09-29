package com.example.myfirstpage.feature.domain

sealed interface PlantEntity {

    val title: String

    data class New(override val title: String) : PlantEntity
    data class Main(val id: Int, override val title: String) : PlantEntity
}

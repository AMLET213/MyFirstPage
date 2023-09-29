package com.example.myfirstpage.feature.domain

class DomainModule {

    fun plantInteractor(repository: PlantRepository) : PlantInteractor {
        return PlantInteractorImpl(repository)
    }
}

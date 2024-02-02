package com.example.myfirstpage.feature.domain

import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun providePlantInteractor(repository: PlantRepository) : PlantInteractor {
        return PlantInteractorImpl(repository)
    }
}

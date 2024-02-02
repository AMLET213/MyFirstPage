package com.example.myfirstpage.feature.presentation

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myfirstpage.feature.data.DataModule
import com.example.myfirstpage.feature.domain.DomainModule
import com.example.myfirstpage.feature.domain.PlantInteractor
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun provideMainViewModelFactory(interactor: PlantInteractor): MainViewModelFactory {
        return MainViewModelFactory(interactor)
    }



//    @Provides
//    fun mainViewModel(owner: ViewModelStoreOwner, interactor: PlantInteractor): MainViewModel {
//        val factory = viewModelFactory { initializer { MainViewModel(interactor) } }
//
//        val viewModelProvider = ViewModelProvider(owner, factory)
//        return viewModelProvider[MainViewModel::class.java]
//    }
}

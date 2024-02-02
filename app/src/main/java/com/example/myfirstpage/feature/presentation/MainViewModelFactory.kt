package com.example.myfirstpage.feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myfirstpage.feature.domain.PlantInteractor

class MainViewModelFactory(val interactor: PlantInteractor) : ViewModelProvider.Factory {



    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(interactor) as T
    }
}

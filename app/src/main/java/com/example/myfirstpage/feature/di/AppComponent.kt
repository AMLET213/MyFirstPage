package com.example.myfirstpage.feature.di

import com.example.myfirstpage.feature.data.DataModule
import com.example.myfirstpage.feature.domain.DomainModule
import com.example.myfirstpage.feature.presentation.MainActivity
import com.example.myfirstpage.feature.presentation.PresentationModule
import dagger.Component

//@Component(
//    modules = [
//        DataModule::class,
//        DomainModule::class,
//        PresentationModule::class,
//    ]
//)
@Component(modules = [DataModule::class, DomainModule::class, PresentationModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}

//private val dataModule = DataModule()
//    private val domainModule = DomainModule()
//    private val presentationModule = PresentationModule()
//
//    private var viewModel: MainViewModel? = null
//
//    fun inject(mainActivity: MainActivity) {
//        mainActivity.viewModel = viewModel ?: createViewModel(mainActivity).also { viewModel = it }
//    }
//
//    private fun createViewModel(mainActivity: MainActivity): MainViewModel {
//        val repository = dataModule.repository()
//        val interactor = domainModule.plantInteractor(repository)
//        return presentationModule.mainViewModel(mainActivity, interactor)
//    }

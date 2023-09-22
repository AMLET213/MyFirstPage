package com.example.myfirstpage.presentation

import com.example.myfirstpage.PlantUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainPresenterImpl() : MainContract.Presenter {

    private var view: MainContract.View? = null
    private var plantList: List<PlantUI> = emptyList()
    private val interactor = PlantInteractor()
    private var currentSort = PlantSort.ASC

    override fun onCreate(view: MainContract.View) {
        this.view = view
        if (plantList.isNotEmpty()) {
            view.showContent(plantList)
        } else {
            loadContent()
        }
        view.showBtnSort(currentSort)
    }

    override fun onDestroy() {
        currentSort = PlantSort.ASC
        view = null
    }

    override fun onSave(): Pair<List<PlantUI>, PlantSort> {
        return plantList to currentSort
    }

    override fun onRestore(state: Pair<List<PlantUI>, PlantSort>) {
        val (list, sort) = state
        plantList = list
        currentSort = sort
        view?.showContent(plantList)
        view?.showBtnSort(currentSort)
    }

    private fun loadContent() {
        GlobalScope.launch {
            plantList = interactor.loadContent().map { PlantUI(it.id, it.title) }
            withContext(Dispatchers.Main) {
                view?.showContent(plantList)
            }
        }
    }

    override fun onClickAdd(editText: String) {
        GlobalScope.launch {
            if (editText.isNotEmpty()) {
                interactor.addPlant(PlantEntity.New(editText))
                sort()
                loadContent()
            } else {
                withContext(Dispatchers.Main) {
                    view?.showError("Поле ввода пустое!")
                }
            }
        }
    }

    override fun onClickDelete(editText: String) {
        GlobalScope.launch {
            if (editText.isNotEmpty() && editText.toInt() < plantList.size) {
                interactor.deletePlant(editText.toInt())
                loadContent()
                withContext(Dispatchers.Main) {
                    view?.showContent(plantList)
                    view?.clearEdit()
                }
            } else {
                withContext(Dispatchers.Main) {
                    view?.showError("число вне диапазона 0-${plantList.size - 1}")
                }
            }
        }
    }

    override fun onClickSort() {
        GlobalScope.launch {
            currentSort = when (currentSort) {
                PlantSort.ASC -> PlantSort.DESC
                PlantSort.DESC -> PlantSort.ASC
            }
            sort()
            loadContent()
            withContext(Dispatchers.Main) {
                view?.showBtnSort(currentSort)
                view?.showContent(plantList)
            }
        }
    }

    override fun onClickPlant(plant: PlantUI) {
        view?.openInfo(plant.id)
    }

    private fun sort() {
        GlobalScope.launch {
            when (currentSort) {
                PlantSort.ASC -> interactor.sortAsc()
                PlantSort.DESC -> interactor.sortDesc()
            }
        }
    }
}


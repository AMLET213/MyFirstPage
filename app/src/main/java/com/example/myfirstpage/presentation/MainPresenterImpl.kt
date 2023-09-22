package com.example.myfirstpage.presentation

import com.example.myfirstpage.PlantUI


class MainPresenterImpl() : MainContract.Presenter {
    private var view: MainContract.View? = null
    private var plantList: List<PlantUI> = emptyList()
    private val interactor = PlantInteractor()
    private var currentSort = Sort.ASC


    override fun onCreate(view: MainContract.View) {
        this.view = view
        if (plantList.isEmpty()) loadContent()
        view.showContent(plantList)
        view.showBtnSort(currentSort)
    }

    override fun onDestroy() {
        currentSort = Sort.ASC
        view = null
    }

    override fun onSave(): Pair<List<PlantUI>, Sort> {
        return plantList to currentSort
    }

    override fun onRestore(state: Pair<List<PlantUI>, Sort>) {
        val (list, sort) = state
        plantList = list
        currentSort = sort
        view?.showContent(plantList)
        view?.showBtnSort(currentSort)
    }


    private fun loadContent() {
        plantList = interactor.loadContent().map { PlantUI(it.id,it.title) }

    }

    override fun onClickAdd(editText: String) {
        if (editText.isNotEmpty()) {
                interactor.addPlant(PlantEntity.New(editText))
                sort()
                loadContent()
                view?.showContent(plantList)
            }
        else {
            view?.showError("Поле ввода пустое!")
        }
    }

    override fun onClickDelete(editText: String) {
        if (editText.isNotEmpty() && editText.toInt() < plantList.size) {
            interactor.deletePlant(editText.toInt())
            loadContent()
            view?.showContent(plantList)
            view?.clearEdit()
        } else {
            view?.showError("число вне диапазона 0-${plantList.size - 1}")
        }

    }

    override fun onClickSort() {
        currentSort = when (currentSort) {
            Sort.ASC -> Sort.DESC
            Sort.DESC -> Sort.ASC
        }
        sort()
        loadContent()
        view?.showBtnSort(currentSort)
        view?.showContent(plantList)
    }


    override fun onClickPlant(plant: PlantUI) {
        view?.openInfo(plant.id)
    }

    private fun sort() {
        when (currentSort) {
            Sort.ASC -> interactor.sortAsc()
            Sort.DESC -> interactor.sortDesc()
        }


    }


}


enum class Sort {
    ASC, DESC
}

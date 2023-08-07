package com.example.myfirstpage.presentation

import com.example.myfirstpage.Plant


class MainPresenterImpl() : MainContract.Presenter {
    private var view: MainContract.View? = null
    private var plantList = ArrayList<Plant>()
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

    override fun onSave(): Pair<ArrayList<Plant>, Sort> {
        return plantList to currentSort
    }

    override fun onRestore(state: Pair<ArrayList<Plant>, Sort>) {
        val (list, sort) = state
        plantList = ArrayList(list.toList())
        currentSort = sort
        view?.showContent(plantList)
        view?.showBtnSort(currentSort)
    }


    private fun loadContent() {
        plantList = interactor.loadContentInteractor()

    }

    override fun onClickAdd(editText: String) {
        val plant = Plant(plantList.size, "new Plant $editText")
        plantList.add(plant)
        sort()
        view?.showContent(plantList)
    }

    override fun onClickDelete(editText: String) {
        if (editText.isNotEmpty() && editText.toInt() < plantList.size) {
            plantList.removeIf { plant -> plant.id == editText.toInt() }
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
        view?.showBtnSort(currentSort)
        view?.showContent(plantList)
    }


    override fun onClickPlant(plant: Plant) {
        view?.openInfo(plant.id)
    }

    private fun sort() {
        val selector: (Plant) -> Int? = { plant -> plant.id }
        when (currentSort) {
            Sort.ASC -> plantList.sortBy(selector)
            Sort.DESC -> plantList.sortByDescending(selector)
        }


    }


}


enum class Sort {
    ASC, DESC
}

package com.example.myfirstpage.presentation

import com.example.myfirstpage.PlantUI


interface MainContract {
    interface View {
        fun showContent(plantList: List<PlantUI>)
        fun showBtnSort(currentSort: PlantSort)
        fun showError(errorText: String)
        fun openInfo(id: Int)
        fun clearEdit()
    }

    interface Presenter {
        fun onCreate(view: View)
        fun onDestroy()
        fun onSave(): Pair<List<PlantUI>, PlantSort>
        fun onRestore(state: Pair<List<PlantUI>, PlantSort>)
        fun onClickPlant(plant: PlantUI)
        fun onClickDelete(editText: String)
        fun onClickAdd(editText: String)
        fun onClickSort()
    }
}

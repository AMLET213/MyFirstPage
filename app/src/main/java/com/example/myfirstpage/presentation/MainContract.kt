package com.example.myfirstpage.presentation

import android.os.Bundle
import com.example.myfirstpage.Plant


interface MainContract {
    interface View {
        fun showContent(plantList: ArrayList<Plant>)
        fun showBtnSort(currentSort: Sort)
        fun showError(errorText: String)
        fun openInfo(id: Int)
        fun clearEdit()
    }

    interface Presenter {
        fun onCreate(view: View)
        fun onDestroy()
        fun onSave(): Pair<ArrayList<Plant>, Sort>
        fun onRestore(state: Pair<ArrayList<Plant>, Sort>)
        fun onClickPlant(plant: Plant)
        fun onClickDelete(editText: String)
        fun onClickAdd(editText: String)
        fun onClickSort()
    }
}

package com.example.myfirstpage.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstpage.PlantUI
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {

    private val _plantList: MutableLiveData<List<PlantUI>> = MutableLiveData(emptyList())
    val plantList: LiveData<List<PlantUI>> = _plantList

    private val _currentSort: MutableLiveData<PlantSort> = MutableLiveData(PlantSort.ASC)
    val currentSort: LiveData<PlantSort> = _currentSort

    private val _editNum: MutableLiveData<String> = MutableLiveData("")
    val editNum: LiveData<String> = _editNum

    private val _id: MutableLiveData<Int?> = MutableLiveData()
    val id: MutableLiveData<Int?> = _id

    private val _error: MutableLiveData<String?> = MutableLiveData()
    val error: LiveData<String?> = _error

    private val interactor = PlantInteractor()

    fun onCreate() {
//        if (_plantList.value.isNullOrEmpty()) {
//            view.showContent(plantList)
//        } else {
            loadContent()
//        }
//        view.showBtnSort(_currentSort)
    }

    override fun onCleared() {
        super.onCleared()
        _currentSort.value = PlantSort.ASC
    }

//    fun onSave(): Pair<List<PlantUI>, PlantSort> {
//        return plantList to currentSort
//    }
//
//    fun onRestore(state: Pair<List<PlantUI>, PlantSort>) {
//        val (list, sort) = state
//        _plantList.value = list
//        _currentSort.value = sort
//    }

    private fun loadContent() {
        viewModelScope.launch {
            _plantList.value = interactor.loadContent().map { PlantUI(it.id, it.title) }
        }
    }

    fun onClickAdd(editText: String) {
        viewModelScope.launch {
            if (editText.isNotEmpty()) {
                interactor.addPlant(PlantEntity.New(editText))
                sort()
                loadContent()
            } else {
                _error.value = "Поле ввода пустое!!!"
                _error.value = null
            }
        }
    }

    fun onClickDelete(enteredPlantId: String) {
        val plants = plantList.value ?: return
        viewModelScope.launch {
            if (enteredPlantId.isNotEmpty() && enteredPlantId.toInt() < plants.size) {
                interactor.deletePlant(enteredPlantId.toInt())
                loadContent()
                _editNum.value = ""
            } else {
                _error.value = "число вне диапазона 0-${plants.size - 1}"
                _error.value = null
            }
        }
    }

    fun onClickSort() {
        viewModelScope.launch {
            _currentSort.value = when (_currentSort.value ?: PlantSort.ASC) {
                PlantSort.ASC -> PlantSort.DESC
                PlantSort.DESC -> PlantSort.ASC
            }
            sort()
            loadContent()
        }
    }

    fun onClickPlant(plant: PlantUI) {
        _id.value=plant.id
        _id.value= null
    }

    private fun sort() {
        viewModelScope.launch {
            when (_currentSort.value ?: PlantSort.ASC) {
                PlantSort.ASC -> interactor.sortAsc()
                PlantSort.DESC -> interactor.sortDesc()
            }
        }
    }
}

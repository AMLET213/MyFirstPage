package com.example.myfirstpage.feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstpage.common.LiveEvent
import com.example.myfirstpage.common.MutableLiveEvent
import com.example.myfirstpage.feature.domain.PlantEntity
import com.example.myfirstpage.feature.domain.PlantInteractor
import kotlinx.coroutines.launch

class MainViewModel(
    // private val router: Router
    private val interactor: PlantInteractor
) : ViewModel() {

    private val _plantList: MutableLiveData<List<PlantUI>> = MutableLiveData(emptyList())
    val plantList: LiveData<List<PlantUI>> = _plantList

    private val _currentSort: MutableLiveData<PlantSort> = MutableLiveData(PlantSort.ASC)
    val currentSort: LiveData<PlantSort> = _currentSort

    private val _editNum: MutableLiveData<String> = MutableLiveData("")
    val editNum: LiveData<String> = _editNum

    private val _id: MutableLiveEvent<Int> = MutableLiveEvent()
    val id: LiveEvent<Int> = _id

    private val _error: MutableLiveEvent<String> = MutableLiveEvent()
    val error: LiveEvent<String> = _error

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
                _error.postEvent("Поле ввода пустое!!!")
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
                _error.postEvent("число вне диапазона 0-${plants.size - 1}")
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
        // router.forward(InfoActivity::class)
        _id.postEvent(plant.id)
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

package com.example.myfirstpage.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstpage.Plant
import com.example.myfirstpage.PlantAdapter
import com.example.myfirstpage.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var binding: ActivityMainBinding
    private val presenter: MainContract.Presenter = MainPresenterImpl()
    private val adapter = PlantAdapter { plant -> presenter.onClickPlant(plant) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btnAdd.setOnClickListener() {
                val edit = editNum.text.toString()
                presenter.onClickAdd(edit)
            }
            butDelete.setOnClickListener {
                val edit: String = editNum.text.toString()
                presenter.onClickDelete(edit)
            }
            btnSort.setOnClickListener() {
                presenter.onClickSort()
            }
            rcView.layoutManager = LinearLayoutManager(this@MainActivity)
            rcView.adapter = adapter
        }

        presenter.onCreate(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val (list, sort) = presenter.onSave()
        outState.putString(TAG_SORT, sort.toString())
        outState.putParcelableArrayList(TAG_LIST, list)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.apply {
            val sortStr = getString(TAG_SORT)
            val sort = if (sortStr == "DESC") {
                Sort.DESC
            } else {
                Sort.ASC
            }
            val list = getParcelableArrayList<Plant>(TAG_LIST)
            presenter.onRestore((list to sort) as Pair<ArrayList<Plant>, Sort>)
        }
        super.onRestoreInstanceState(savedInstanceState)
    }

//    override fun onRestoreInstanceState(
//        savedInstanceState: Bundle?
//        persistentState: PersistableBundle?
//    ) {
//        savedInstanceState?.apply {
//            val sort = Sort.ASC
//            val list = getParcelableArray(TAG_LIST) as Array<Plant>
//            presenter.onRestore(list to sort)
//        }
//        super.onRestoreInstanceState(savedInstanceState, persistentState)
//    }


    override fun showContent(plantList: ArrayList<Plant>) {
        adapter.setPlant(plantList)
        binding.editNum.setText(adapter.itemCount.toString())
    }

    override fun clearEdit() {
        binding.editNum.setText("")
    }

    override fun showError(errorText: String) {
        Toast.makeText(applicationContext, errorText, Toast.LENGTH_SHORT).show()
    }

    override fun openInfo(id: Int) {
        startActivity(Intent(this, InfoActivity::class.java).apply {
            putExtra("item", id)
        })
    }

    override fun showBtnSort(currentSort: Sort) {
        binding.btnSort.text = "Sort $currentSort"
    }

    companion object {
        private const val TAG_LIST = "tag list"
        private const val TAG_SORT = "tag sort"
    }

}

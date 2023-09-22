package com.example.myfirstpage.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstpage.PlantUI
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

//    override fun onSaveInstanceState(outState: Bundle) {
//        val (list, sort) = presenter.onSave()
//        outState.putString(TAG_SORT, sort.name)
//        outState.putParcelableArrayList(TAG_LIST, list)
//        super.onSaveInstanceState(outState)
//    }
//
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        savedInstanceState.apply {
//            val sort = Sort.valueOf(getString(TAG_SORT) ?: error("123123"))
//            val list = getParcelableArrayList<PlantUI>(TAG_LIST) ?: error("123")
//            presenter.onRestore(list to sort)
//        }
//        super.onRestoreInstanceState(savedInstanceState)
//    }


    override fun showContent(plantList: List<PlantUI>) {
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

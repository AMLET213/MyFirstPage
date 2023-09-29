package com.example.myfirstpage.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstpage.PlantUI
import com.example.myfirstpage.PlantAdapter
import com.example.myfirstpage.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var vm: MainViewModel
    private val adapter = PlantAdapter { plant -> vm.onClickPlant(plant) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        vm = ViewModelProvider(this).get(MainViewModel::class.java)
        setContentView(binding.root)
        binding.apply {
            btnAdd.setOnClickListener() {
                val edit = editNum.text.toString()
                vm.onClickAdd(edit)
            }
            butDelete.setOnClickListener {
                val edit: String = editNum.text.toString()
                vm.onClickDelete(edit)
            }
            btnSort.setOnClickListener() {
                vm.onClickSort()
            }
            rcView.layoutManager = LinearLayoutManager(this@MainActivity)
            rcView.adapter = adapter
        }

        vm.onCreate()
        vm.plantList.observe(this, Observer {
            adapter.setPlant(it)
            binding.editNum.setText(adapter.itemCount.toString())
        })
        vm.currentSort.observe(this, Observer {
            binding.btnSort.text = "Sort $it"
        })
        vm.editNum.observe(this, Observer {
            binding.editNum.setText(it)
        })
        vm.id.observe(this, Observer {
            if(it !=null) {
                startActivity(Intent(this, InfoActivity::class.java).apply {
                    putExtra("item", it)
                })
            }
        })
        vm.error.observe(this, Observer {
            if(it !=null) {
                Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()

    }


//    override fun onSaveInstanceState(outState: Bundle) {
//        val (list, sort) = vm.onSave()
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


    companion object {
        private const val TAG_LIST = "tag list"
        private const val TAG_SORT = "tag sort"
    }

}

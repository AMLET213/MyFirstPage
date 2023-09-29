package com.example.myfirstpage.feature.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstpage.R
import com.example.myfirstpage.databinding.ActivityMainBinding
import com.example.myfirstpage.feature.di.Component

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        Component.inject(this)
        super.onCreate(savedInstanceState)
        val adapter = PlantAdapter { plant -> viewModel.onClickPlant(plant) }
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView(binding, adapter)
        initUserListeners(binding, viewModel)
        observeViewModel(binding, viewModel, adapter)

        viewModel.onCreate()
    }

    private fun initView(binding: ActivityMainBinding, adapter: PlantAdapter) {
        binding.rcView.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.rcView.adapter = adapter
    }

    private fun initUserListeners(binding: ActivityMainBinding, viewModel: MainViewModel) {
        binding.btnAdd.setOnClickListener {
            val edit = binding.editNum.text.toString()
            viewModel.onClickAdd(edit)
        }
        binding.butDelete.setOnClickListener {
            val edit: String = binding.editNum.text.toString()
            viewModel.onClickDelete(edit)
        }
        binding.btnSort.setOnClickListener {
            viewModel.onClickSort()
        }
    }

    private fun observeViewModel(
        binding: ActivityMainBinding,
        viewModel: MainViewModel,
        adapter: PlantAdapter
    ) {
        viewModel.plantList.observe(this) {
            adapter.setPlant(it)
            binding.editNum.setText(adapter.itemCount.toString())
        }
        viewModel.currentSort.observe(this) {
            binding.btnSort.text = getString(R.string.button_sort, it)
        }
        viewModel.editNum.observe(this) {
            binding.editNum.setText(it)
        }
        viewModel.error.observe(this) {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        }

        // supportFragmentManager
        // startActivity
        // Navigator(startActivity/supportFragmentManager)
        // Buffer
        // Command(Forward, Back, Replace)
        // Router.navigateTo<InfoActivity>()
        // Router.toInfo()

        // Navigation (Jetpack Navigation, Cicerone)
        viewModel.id.observe(this) {
            startActivity(Intent(this, InfoActivity::class.java).apply {
                putExtra("item", it)
            })
        }
    }
}

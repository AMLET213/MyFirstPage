package com.example.myfirstpage.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstpage.Plant
import com.example.myfirstpage.PlantAdapter
import com.example.myfirstpage.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), PlantAdapter.Listener{

    private lateinit var binding:ActivityMainBinding
    private val adapter = PlantAdapter(this)
    private var index = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        binding.apply {
            butDelete.setOnClickListener() {
                val edit: String = editNum.text.toString()
                if (editNum.text.isNotEmpty() && edit.toInt() in 0..100) {
                    val plant = Plant("Plant ${edit.toInt()}")
                    adapter.deletePlant(plant)
                    editNum.setText("")
                }
                else Toast.makeText(applicationContext, "число вне диапазона 0-100", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun init(){
        binding.apply {
            rcView.layoutManager = LinearLayoutManager(this@MainActivity)
            rcView.adapter =adapter

            while (index <= 100){
                val plant = Plant( "Plant $index")
                adapter.addPlant(plant)
                index++
            }

        }
    }



    override fun onClick(plant: Plant) {
        startActivity(Intent(this, InfoActivity::class.java).apply {
            putExtra("item",plant)
        })
    }

}
package com.example.myfirstpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstpage.databinding.ActivityMainBinding





class MainActivity : AppCompatActivity(),PlantAdapter.Listener {

    private lateinit var binding:ActivityMainBinding
    private val adapter = PlantAdapter(this)
    private val imageIdList = listOf(R.drawable.plant1,R.drawable.plant2,R.drawable.plant3,R.drawable.plant4,R.drawable.plant5,R.drawable.plant6)
    private var index = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }


    private fun init(){
        binding.apply {
            rcView.layoutManager = LinearLayoutManager(this@MainActivity)
            rcView.adapter =adapter

            while (index <= imageIdList.size - 1){
                val plant = Plant(imageIdList[index], "Plant $index")
                adapter.addPlant(plant)
                index++
            }
            butDelete.setOnClickListener(){
                var edit : String = editNum.text.toString()
                val plant = Plant(imageIdList[edit.toInt()], "Plant ${edit.toInt()}")
                adapter.deletePlant(plant)
                editNum.setText("")
            }



        }
    }

    override fun onClick(plant: Plant) {
        startActivity(Intent(this,InfoActivity::class.java).apply {
            putExtra("item",plant)
        })
    }
}
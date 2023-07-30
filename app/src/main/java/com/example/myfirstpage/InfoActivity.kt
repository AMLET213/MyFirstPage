package com.example.myfirstpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myfirstpage.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity() {

    lateinit var binding : ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val item = intent.getSerializableExtra("item") as Plant

        binding.apply {
            imMain.setImageResource(item.imageId)
            title.text = item.title

            but.setOnClickListener(){
                finish()
            }
        }
    }
}
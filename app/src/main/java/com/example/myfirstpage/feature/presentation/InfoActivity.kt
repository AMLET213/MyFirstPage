package com.example.myfirstpage.feature.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myfirstpage.R
import com.example.myfirstpage.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val plantId = intent.getIntExtra("item",-1)
        if (plantId==-1) error("plantId is null")

        binding.apply {
            title.text = getString(R.string.plant, plantId)
            but.alpha=0f
            Cv.alpha=0f


            but.animate().alpha(1f).translationYBy((-100).toFloat()).setStartDelay(300).duration=1000
            Cv.animate().alpha(1f).translationYBy((50).toFloat()).setStartDelay(300).duration=1000


            but.setOnClickListener{
                finish()
            }
        }
    }
}

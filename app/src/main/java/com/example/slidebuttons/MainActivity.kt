package com.example.slidebuttons

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.daimajia.swipe.SwipeLayout
import com.example.slidebuttons.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val btn = binding.btn3dots
        val h_btn1 = binding.hdnBtn1
        val h_btn2 = binding.hdnBtn2
        val h_btn3 = binding.hdnBtn3
        val next_btn = binding.nextButton
        val swiper = binding.swiperView

        btn.setOnClickListener {
            if (swiper.openStatus == SwipeLayout.Status.Open){
                swiper.close()
            }else if(swiper.openStatus == SwipeLayout.Status.Close){
                swiper.open()
            }
        }

        h_btn1.setOnClickListener {
            Toast.makeText(this,"Button 1 Pressed",Toast.LENGTH_SHORT).show()
        }
        h_btn2.setOnClickListener {
            Toast.makeText(this,"Button 2 Pressed",Toast.LENGTH_SHORT).show()
        }
        h_btn3.setOnClickListener {
            Toast.makeText(this,"Button 3 Pressed",Toast.LENGTH_SHORT).show()
        }

        next_btn.setOnClickListener {
            Toast.makeText(this,"Button Next Pressed",Toast.LENGTH_SHORT).show()
            val intent = Intent(this,FavLayoutActivity::class.java)
            startActivity(intent)
        }

    }

}
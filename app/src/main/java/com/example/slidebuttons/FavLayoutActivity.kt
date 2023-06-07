package com.example.slidebuttons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fav_layout)



        val list:List<People> = listOf(
            People("A1",22,"A1"),
            People("A2",21,"A2"),
            People("A3",25,"A3"),
            People("A4",29,"A4")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_test)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TesterAdapter(this,list)
    }

}

data class People(
    val name: String,
    val age: Int,
    val id: String
)
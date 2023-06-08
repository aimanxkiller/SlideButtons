package com.example.slidebuttons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class FavLayoutActivity : AppCompatActivity() {

    lateinit var sortedList:List<People>
    lateinit var recyclerView:RecyclerView
    lateinit var search:TextInputEditText
    lateinit var list:List<People>
    lateinit var adapter:TesterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fav_layout)

        search = findViewById(R.id.et_search)
        recyclerView = findViewById(R.id.recycler_test)

        listandsort()

    }

    private fun searchfuntion(){

        val filteredList = list.toMutableList()

        search.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Filter the peopleList based on the search text
                val searchText = s.toString().trim().toLowerCase(Locale.getDefault())
                filteredList.clear()

                if (searchText.isEmpty()) {
                    // If the search text is empty, show all items
                    filteredList.addAll(list)
                } else {
                    // Iterate through the original peopleList and add matching items to filteredList
                    for (person in list) {
                        if (person.name.toLowerCase(Locale.getDefault()).contains(searchText)) {
                            filteredList.add(person)
                        }
                    }
                }

                sortedList = filteredList.sortedWith(
                    compareByDescending<People> { it.pin }
                        .thenBy { it.name }
                )
                // Update the RecyclerView with the filtered list
                adapter.setData(sortedList)
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun listandsort(){
        list = listOf(
            People("Alice", 22, "A1", false),
            People("Albert", 21, "A2", false),
            People("Zine", 88, "A3", true),
            People("Liam", 24, "A4", false),
            People("Ella", 25, "A5", false),
            People("Noah", 33, "A6", false),
            People("Aria", 29, "A7", false),
            People("Mason", 30, "A8", false),
            People("Olivia", 26, "A9", false),
            People("Lucas", 28, "A10", true),
            People("Amelia", 31, "A11", false),
            People("Carter", 27, "A12", false),
            People("Sophia", 32, "A13", false),
            People("Benjamin", 35, "A14", false),
            People("Avery", 23, "A15", false),
            People("Henry", 34, "A16", false),
            People("Charlotte", 36, "A17", false),
            People("Ethan", 38, "A18", false),
            People("Emily", 37, "A19", false),
            People("Daniel", 39, "A20", false),
            People("Harper", 40, "A21", false),
            People("Michael", 41, "A22", false),
            People("Mia", 42, "A23", false),
            People("Alexander", 43, "A24", false),
            People("Ava", 44, "A25", false),
            People("William", 45, "A26", false),
            People("Scarlett", 46, "A27", false),
            People("James", 47, "A28", true),
            People("Oliver", 39, "A29", false),
            People("Emma", 28, "A30", false)
        )


        sortedList = list.sortedWith(
            compareByDescending<People> { it.pin }
                .thenBy { it.name }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TesterAdapter(this,sortedList)
        recyclerView.adapter = adapter

        searchfuntion()
    }

}



data class People(
    val name: String,
    val age: Int,
    val id: String,
    val pin:Boolean
)
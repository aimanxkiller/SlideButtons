package com.example.slidebuttons.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.slidebuttons.ListClickListener
import com.example.slidebuttons.People
import com.example.slidebuttons.adapter.RecentTransactionAdapter
import com.example.slidebuttons.adapter.TesterAdapter
import com.example.slidebuttons.databinding.FavLayoutBinding
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class FavLayoutActivity : AppCompatActivity(), ListClickListener {

    lateinit var sortedList:List<People>
    lateinit var recyclerView:RecyclerView
    lateinit var search:TextInputEditText
    lateinit var list:List<People>
    lateinit var adapter: TesterAdapter

    private lateinit var binding:FavLayoutBinding

    private val mainList= arrayListOf("Ishtiaque","Abdul","Wayne","Azuwin","Aiman","Abdullah")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FavLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        search = binding.etSearch
        recyclerView = binding.recyclerTest

        binding.recyclerMostRecent.apply {
            adapter = RecentTransactionAdapter(mainList)
        }

        listAndSort()

    }

    private fun searchFunction(){

        val filteredList = list.toMutableList()
        search.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Filter the peopleList based on the search text
                val searchText = s.toString().trim().lowercase(Locale.getDefault())
                filteredList.clear()

                if (searchText.isEmpty()) {
                    // If the search text is empty, show all items
                    filteredList.addAll(list)
                } else {
                    // Iterate through the original peopleList and add matching items to filteredList
                    for (person in list) {
                        if (person.name.lowercase(Locale.getDefault()).contains(searchText)) {
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

    override fun onItemPin(position: String) {
        var count = 0

        list.forEach {
            if (it.pin){
                count++
            }
        }

        val x = list.find { people -> people.id ==position }

        if (x?.pin == true){
            for (person in list) {
                if (person.id == position) {
                    search.text?.clear()
                    search.clearFocus()
                    Toast.makeText(this,"Unpinned ${person.name}",Toast.LENGTH_SHORT).show()
                    person.pin = !person.pin // Toggling the boolean value
                    break // Exit the loop once the person is found and updated
                }
            }
        }
        else{
            if(count<3){
                for (person in list) {
                    if (person.id == position) {
                        search.text?.clear()
                        search.clearFocus()
                        Toast.makeText(this,"Pinned ${person.name}",Toast.LENGTH_SHORT).show()
                        person.pin = !person.pin // Toggling the boolean value
                        break // Exit the loop once the person is found and updated
                    }
                }
            }else{
                Toast.makeText(this,"Max pin is 3",Toast.LENGTH_SHORT).show()
            }
        }

        sortedList = list.sortedWith(
            compareByDescending<People> { it.pin }
                .thenBy { it.name }
        )

        adapter.setData(sortedList)

    }

    private fun listAndSort(){
        list = listOf(
            People("Alice", 22, "A1", false),
            People("Bella", 21, "A2", false),
            People("Charlie", 88, "A3", true),
            People("Liam", 24, "A4", false),
            People("Ella", 25, "A5", true),
            People("Felix", 33, "A6", false),
            People("Noah", 29, "A7", true),
            People("Mason", 30, "A8", false),
            People("Thomas", 26, "A9", false),
        )

        sortedList = list.sortedWith(
            compareByDescending<People> { it.pin }
                .thenBy { it.name }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TesterAdapter(this,sortedList)
        adapter.setItemClickListener(this)
        recyclerView.adapter = adapter

        searchFunction()
    }

}
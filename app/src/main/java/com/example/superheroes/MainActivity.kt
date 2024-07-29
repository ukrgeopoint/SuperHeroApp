package com.example.superheroes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listFragment = supportFragmentManager.findFragmentById(R.id.list) as? ListFragment

        listFragment?.let {
            it.setOnListFragmentItemClickListener { item ->
                val detailsFragmentToAdd = DetailsFragment()
                detailsFragmentToAdd.setName(item)
                supportFragmentManager.beginTransaction()
                    .add(R.id.list, detailsFragmentToAdd)
                    .addToBackStack("Details Fragment")
                    .commit()
            }
        }

    }
}
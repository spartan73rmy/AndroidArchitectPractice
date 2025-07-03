package com.example.eventbuspattern

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eventbuspattern.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var _binding :ActivityMainBinding
    private lateinit var adapter: ResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        setupAdapter()
        setupRecyclerView()

        getResultEventsInRealtime().forEach{
            if (it is SportEvent.ResultSuccess){
                adapter.add(it)
            }
        }
    }

    private fun setupAdapter() {
        adapter = ResultAdapter(this)
    }


    private fun setupRecyclerView() {
        _binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter= this@MainActivity.adapter
        }
    }

    override fun onClick(result: SportEvent.ResultSuccess) {

    }

}
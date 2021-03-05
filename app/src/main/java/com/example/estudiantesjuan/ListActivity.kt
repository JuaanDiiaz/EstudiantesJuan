package com.example.estudiantesjuan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.estudiantesjuan.Data.Liststudents
import com.example.estudiantesjuan.Tools.Constans
import com.example.estudiantesjuan.databinding.ActivityFormBinding
import com.example.estudiantesjuan.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding
    private val listStudent=Liststudents()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.txt_list)
        listStudent.showListStudents()
        val adapter = ArrayAdapter<String>(this@ListActivity,android.R.layout.simple_list_item_1,listStudent.getStringArray())
        binding.ltvStudents.adapter = adapter

        binding.ltvStudents.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position)
            Toast.makeText(this@ListActivity,"$position $id $selectedItem",Toast.LENGTH_SHORT).show()
            val intent = Intent(this@ListActivity,DetailActivity::class.java).apply {
                putExtra(Constans.ID,position)
            }
            startActivity(intent)
        }

    }

    override fun onRestart() {
        super.onRestart()
        val adapter = ArrayAdapter<String>(this@ListActivity,android.R.layout.simple_list_item_1,listStudent.getStringArray())
        binding.ltvStudents.adapter = adapter
    }
}
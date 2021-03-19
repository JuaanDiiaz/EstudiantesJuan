package com.example.estudiantesjuan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.estudiantesjuan.Adapters.StudentAdapter
import com.example.estudiantesjuan.Data.Liststudents
import com.example.estudiantesjuan.databinding.ActivityRecyclerBinding

class RecyclerActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRecyclerBinding
    private val listStudent= Liststudents()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.txt_recycler)

        loadRecycler()
    }
    fun loadRecycler(){
        var list = listStudent.getListStudentArray()
        val adapter = StudentAdapter(list,this@RecyclerActivity)
        val lineraLayout = LinearLayoutManager(this@RecyclerActivity,LinearLayoutManager.VERTICAL,false)
        binding.rvwStudents.layoutManager=lineraLayout
        binding.rvwStudents.setHasFixedSize(true)
        binding.rvwStudents.adapter=adapter
    }
    override fun onRestart() {
        super.onRestart()
        loadRecycler()
    }
}
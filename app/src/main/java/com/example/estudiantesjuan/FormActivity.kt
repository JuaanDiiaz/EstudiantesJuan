package com.example.estudiantesjuan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.example.estudiantesjuan.Data.Liststudents
import com.example.estudiantesjuan.Entity.EntityStudent
import com.example.estudiantesjuan.Tools.Constans
import com.example.estudiantesjuan.databinding.ActivityDetailBinding
import com.example.estudiantesjuan.databinding.ActivityFormBinding
import com.google.android.material.snackbar.Snackbar

class FormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormBinding
    private var listStudents = Liststudents()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.txt_home)

        binding.btnOk.setOnClickListener {
            if(binding.adtName.text.isNotEmpty() &&  binding.adtLastName.text.isNotEmpty()){
                val student = EntityStudent()
                student.name=binding.adtName.text.toString()
                student.lastName = binding.adtLastName.text.toString()
                student.gender =  binding.spnGender.selectedItemPosition
                when (binding.rgdDegree.checkedRadioButtonId){
                    binding.rdbUnfinishedStudies.id-> student.degree = "Trunco"
                    binding.rdbUniversityIntern.id-> student.degree = "Pasante"
                    binding.rdbFinishedStudies.id-> student.degree = "Titulado"
                }
                student.sport = binding.ckbSport.isChecked
                student.reading = binding.ckbRead.isChecked
                student.travel = binding.ckbTravel.isChecked
                student.financialAssistance = binding.swtFinancialAssistance.isChecked
                val index = listStudents.add(student)
                if(index>=0){
                    Toast.makeText(this@FormActivity,"Estudiante registrado",Toast.LENGTH_SHORT).show()
                    clear()
                }else{
                    Snackbar.make(it,"Estudiante existente, no se guardó nada",Snackbar.LENGTH_SHORT).show()
                }
            }else{
                Snackbar.make(it,"Ingresar nombre y apellidos",Snackbar.LENGTH_SHORT).show()
            }
        }
        /*
        binding.spnGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@FormActivity,"Nada seleccionado",Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedText = parent?.getItemIdAtPosition(position)
                Toast.makeText(this@FormActivity,"Evento onItemSelected $position $selectedText",Toast.LENGTH_SHORT).show()
            }

        }
        binding.swtFinancialAssistance.setOnCheckedChangeListener { it, isChecked ->
            val cheked = if(isChecked) "On" else "Off"
            Toast.makeText(this@FormActivity,"Evento setOnCheckedChangeListener $cheked",Toast.LENGTH_SHORT).show()
        }*/

    }
    private fun clear(){
        binding.adtName.text = null
        binding.adtLastName.text=null
        binding.spnGender.setSelection(0)
        binding.rdbUnfinishedStudies.isChecked=false
        binding.rdbUniversityIntern.isChecked=false
        binding.rdbFinishedStudies.isChecked=false
        binding.ckbSport.isChecked=false
        binding.ckbRead.isChecked=false
        binding.ckbTravel.isChecked=false
        binding.swtFinancialAssistance.isChecked=false
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_form,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.itmList->{
                val intent = Intent(this@FormActivity,ListActivity::class.java)
                startActivity(intent)
            }
            R.id.itmForm->{
                val intent = Intent(this@FormActivity,EditDeleteActivity::class.java)
                startActivity(intent)
            }
            R.id.itmExit->{
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
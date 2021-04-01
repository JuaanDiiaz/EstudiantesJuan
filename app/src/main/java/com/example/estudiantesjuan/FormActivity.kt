package com.example.estudiantesjuan

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.example.estudiantesjuan.Adapters.StudentAdapter
import com.example.estudiantesjuan.Data.Liststudents
import com.example.estudiantesjuan.Data.StudentDb
import com.example.estudiantesjuan.Entity.EntityStudent
import com.example.estudiantesjuan.Tools.Constans
import com.example.estudiantesjuan.Tools.PermissionAplication
import com.example.estudiantesjuan.databinding.ActivityDetailBinding
import com.example.estudiantesjuan.databinding.ActivityFormBinding
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class FormActivity : AppCompatActivity() {
    private val permissions = PermissionAplication(this@FormActivity)
    private lateinit var binding: ActivityFormBinding
    private var listStudents = Liststudents()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.txt_home)

        if(!permissions.hasPermissions(Constans.LIST_PERMISSIONS)){
            permissions.acceptPermission(Constans.LIST_PERMISSIONS,1)
        }
        binding.btnOk.setOnClickListener {
            if(isCorrect()){
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
            }
        }
        binding.editTextDate.setOnClickListener {
            val myCalendar=Calendar.getInstance()
            val year= myCalendar.get(Calendar.YEAR)
            val month= myCalendar.get(Calendar.MONTH)
            val day= myCalendar.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(this@FormActivity,DatePickerDialog.OnDateSetListener { view, y, m, d ->
                binding.editTextDate.setText("${twoDigits(d)} / ${twoDigits(m+1)} / $y")
            },year,month,day)
            dpd.show()
        }
        binding.editTextTime.setOnClickListener {
            val myCalendar=Calendar.getInstance()
            val h = myCalendar.get(Calendar.HOUR_OF_DAY)
            val m = myCalendar.get(Calendar.MINUTE)
            val tpd = TimePickerDialog(this@FormActivity, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                binding.editTextTime.setText("${twoDigits(hourOfDay)}:${twoDigits(minute)}")
            },h,m,true)
            tpd.show()
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


        val x=StudentDb(this@FormActivity)

        x.search("n")

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1->{
                for(p in permissions){
                    Log.d(Constans.LOG_TAG,p)
                }
                for(r in grantResults){
                    if(r!=PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this@FormActivity,"Permisos obligatorios",Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }
        }
    }
    private fun isCorrect():Boolean{
        var isAllCorrect = true
        var message= "Falta: "
        if(binding.adtName.text.isEmpty()){
            isAllCorrect=false
            message += "*Nombre "
        }
        if(binding.adtLastName.text.isEmpty()){
            isAllCorrect=false
            message += "*Apellidos "
        }
        if(binding.spnGender.selectedItemPosition==0){
            isAllCorrect=false
            message += "*Genero "
        }
        when (binding.rgdDegree.checkedRadioButtonId){
            binding.rdbUnfinishedStudies.id-> message += ""
            binding.rdbUniversityIntern.id-> message += ""
            binding.rdbFinishedStudies.id-> message += ""
            else-> {
                isAllCorrect=false
                message += "*Grado de estudios "
            }
        }
        if(!isAllCorrect){
            Toast.makeText(this@FormActivity,message,Toast.LENGTH_SHORT).show()
        }
        return isAllCorrect
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
            R.id.itmrecyclerView->{
                val intent = Intent(this@FormActivity,RecyclerActivity::class.java)
                startActivity(intent)
            }
            R.id.itmExit->{
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    fun twoDigits(number:Int):String{
        return if(number<=9)"0$number" else number.toString()
    }
}
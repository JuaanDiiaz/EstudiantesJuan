package com.example.estudiantesjuan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.estudiantesjuan.Data.Liststudents
import com.example.estudiantesjuan.Entity.EntityStudent
import com.example.estudiantesjuan.Tools.Constans
import com.example.estudiantesjuan.databinding.ActivityDetailBinding
import com.example.estudiantesjuan.databinding.ActivityEditStudentBinding

class EditStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditStudentBinding
    private val listStudets = Liststudents()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id:Int = intent.getIntExtra(Constans.ID,-1)
        if(id!=-1){
            val Student  = listStudets.getStudent(id)
            binding.txvStudentName.text = "${Student.name}"
            binding.edtLastName.setText(Student.lastName)
            if(Student.degree == "Trunco"){
                binding.rdbUnfinishedStudies.isChecked = true
            }
            if(Student.degree == "Pasante"){
                binding.rdbUniversityIntern.isChecked = true
            }
            if(Student.degree == "Titulado"){
                binding.rdbFinishedStudies.isChecked = true
            }
            binding.spnGender.setSelection(Student.gender)
            binding.ckbRead.isChecked = Student.reading
            binding.ckbTravel.isChecked = Student.travel
            binding.ckbSport.isChecked = Student.sport
            binding.swtFinancialAssistance.isChecked = Student.financialAssistance

        }else{
            Toast.makeText(this@EditStudentActivity,"Error", Toast.LENGTH_SHORT).show()
            finish()
        }
        binding.btnEdit.setOnClickListener {
            if(binding.edtLastName.text.isNotEmpty()){
                val student = EntityStudent()
                student.name = binding.txvStudentName.text.toString()
                student.lastName = binding.edtLastName.text.toString()
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
                val request = listStudets.edit(student)
                if(request){
                    Toast.makeText(this@EditStudentActivity,"Alumno editado correctamente", Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(this@EditStudentActivity,"Error al intentar editar alumno", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}
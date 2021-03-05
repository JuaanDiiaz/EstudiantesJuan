package com.example.estudiantesjuan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.estudiantesjuan.Data.Liststudents
import com.example.estudiantesjuan.Tools.Constans
import com.example.estudiantesjuan.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDetailBinding
    private val listStudets = Liststudents()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.txt_detail)

        val id:Int = intent.getIntExtra(Constans.ID,-1)
        if(id!=-1){
            val Student  = listStudets.getStudent(id)
            binding.txvFullName.text = "${Student.name} ${Student.lastName}"
            binding.txvGender.text = "${if(Student.gender==1)"Masculino" else if(Student.gender==2) "Femenino" else "género no seleccionado"}"
            binding.txvDegree.text = "${Student.degree}"
            binding.txvFinancialAssistance.text = "${if(Student.financialAssistance)"con beca" else "Sin beca"}"
            binding.txvHobbies.text = "Pasatiempos:  ${if(Student.reading) "Leer" else ""} ${if(Student.travel) "Viajar" else ""} ${if(Student.sport) "Deportes" else ""}"
            binding.btnDelete.setOnClickListener {
                if(listStudets.delete(Student.name)){
                    Toast.makeText(this@DetailActivity,"Estudiante eliminado",Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(this@DetailActivity,"Error al eliminar",Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            Toast.makeText(this@DetailActivity,"Error",Toast.LENGTH_SHORT).show()
            finish()
            //Snackbar.make(it,"sin alumno",Snackbar.LENGTH_SHORT).show()
        }


    }
}
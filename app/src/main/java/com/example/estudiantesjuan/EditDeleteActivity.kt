package com.example.estudiantesjuan

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.estudiantesjuan.Data.Liststudents
import com.example.estudiantesjuan.Tools.Constans
import com.example.estudiantesjuan.databinding.ActivityEditDeleteBinding
import com.example.estudiantesjuan.databinding.ActivityFormBinding

class EditDeleteActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditDeleteBinding
    private val listStudent= Liststudents()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listLoad()

        binding.ltvStudents.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position)
            Toast.makeText(this@EditDeleteActivity,"$position $id $selectedItem", Toast.LENGTH_SHORT).show()
            miDialogo(position).show()
        }
    }

    override fun onRestart() {
        super.onRestart()
        listLoad()
    }
    private fun listLoad(){
        val adapter = ArrayAdapter<String>(this@EditDeleteActivity, R.layout.simple_list_item_1,listStudent.getStringArray())
        binding.ltvStudents.adapter = adapter
    }
    private fun miDialogo(index:Int): AlertDialog {
        val miAlerta = AlertDialog.Builder(this@EditDeleteActivity)

        miAlerta.setTitle("Mensaje del sistema")
        miAlerta.setMessage("¿Que acción desea realizar con el estudiante?")
        miAlerta.setPositiveButton("Editar"){_,_ ->
            Toast.makeText(this@EditDeleteActivity,"Editar alumno",Toast.LENGTH_SHORT).show()
            val intent = Intent(this@EditDeleteActivity,EditStudentActivity::class.java).apply {
                putExtra(Constans.ID,index)
            }
            startActivity(intent)
        }
        miAlerta.setNegativeButton("Eliminar"){_,_ ->
            Toast.makeText(this@EditDeleteActivity,"Eliminar alumno",Toast.LENGTH_SHORT).show()
            val student = listStudent.getStudent(index)
            if(listStudent.delete(student.name)){
                Toast.makeText(this@EditDeleteActivity,"Alumno eliminado exitosamente",Toast.LENGTH_SHORT).show()
                listLoad()
            }

        }
        return miAlerta.create()
    }
}
package com.example.estudiantesjuan.Adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.estudiantesjuan.Data.Liststudents
import com.example.estudiantesjuan.DetailActivity
import com.example.estudiantesjuan.EditStudentActivity
import com.example.estudiantesjuan.Entity.EntityStudent
import com.example.estudiantesjuan.R
import com.example.estudiantesjuan.Tools.Constans
import com.example.estudiantesjuan.databinding.ItemStudentsBinding
import com.google.android.material.snackbar.Snackbar

class StudentAdapter(val studentList:ArrayList<EntityStudent>, val context: Context):RecyclerView.Adapter<StudentHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StudentHolder(inflater.inflate(R.layout.item_students,parent,false))
    }

    override fun getItemCount(): Int {
        return studentList.size
    }
    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        holder.textViewFullName.text = "${studentList[position].name} ${studentList[position].lastName}"
        holder.textViewGender.text = if(studentList[position].gender==1) "Masculino" else "Femenino"
        holder.textViewDegree.text = studentList[position].degree
        holder.imageButtonDelete.setOnClickListener{
            myDialog(studentList[position].name,it).show()
        }
        holder.imageButtonEdit.setOnClickListener{
            notifyItemChanged(position)
            val intent = Intent(context, EditStudentActivity::class.java).apply {
                putExtra(Constans.ID,position)
            }
            context.startActivity(intent)
        }
        holder.imageButtonSee.setOnClickListener{
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra(Constans.ID,position)
            }
            context.startActivity(intent)
        }
    }
    private fun myDialog(name:String,view:View):AlertDialog{
        val myAlert = AlertDialog.Builder(context)
        myAlert.setTitle("EstudiantesJuan v0.1")
        myAlert.setIcon(R.drawable.est)
        myAlert.setMessage("¿Desea eliminar el estudiante seleccionado?")
        myAlert.setPositiveButton("Simón"){ dialogInterface: DialogInterface, i: Int ->
            val listStudent = Liststudents()
            if(listStudent.delete(name)){
                Toast.makeText(context,"Alumno eliminado exitosamente",Toast.LENGTH_SHORT).show()

                notifyDataSetChanged()
            }
            else{
                Snackbar.make(view,"Error al intentar eliminar al alumno",Snackbar.LENGTH_SHORT).show()

            }
        }
        return myAlert.create()
    }

}
class StudentHolder(view: View):RecyclerView.ViewHolder(view){
    val binding = ItemStudentsBinding.bind(view)
    val imageViewLogo = binding.imageViewLogo
    val textViewFullName = binding.textViewFullName
    val textViewGender = binding.textViewGender
    val textViewDegree = binding.textViewDegree
    val imageButtonDelete= binding.imageButtonDelete
    val imageButtonEdit= binding.imageButtonEdit
    val imageButtonSee= binding.imageButtonSee
}
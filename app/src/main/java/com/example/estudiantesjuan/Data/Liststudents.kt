package com.example.estudiantesjuan.Data

import android.util.Log
import com.example.estudiantesjuan.Entity.EntityStudent
import com.example.estudiantesjuan.Tools.Constans
import kotlin.math.log

class Liststudents {

    fun add(student: EntityStudent):Int{
        var answer = -1
        if(!existNameFilter(student.name)){
            listStudents.add(student)
            answer = listStudents.size -1
        }
        return answer
    }
    fun showListStudents(){
        for((index,item) in listStudents.withIndex()){
            Log.d(Constans.LOG_TAG,"$index nombre: ${item.name}  genero: ${item.gender} carrera: ${item.degree}")
            //Log.d(Constans.LOG_TAG,"$index nombre: ${listStudents[index].name}  genero: ${listStudents[index].gender} carrera: ${listStudents[index].degree}")
        }
    }
    fun existsName(name:String):Boolean{
        var answer: Boolean = false
        for(element in listStudents){
            if(element.name == name){
                answer = true
                break
            }
        }
        return answer
    }
    fun existNameFilter(name:String):Boolean{
        var answer: Boolean = false
        if (listStudents.filter{e -> e.name==name}.count()==1) {
            answer=true
        }
        return answer
    }
    fun edit(index:Int, entityStudent: EntityStudent):Boolean{
        var answer = false
        if(index< listStudents.size && index>=0) {
            answer = true
            listStudents[index] = entityStudent
        }
        return answer
    }
    fun delete(name: String): Boolean{
        var answer = false
        for((index,item) in listStudents.withIndex()){
            if(item.name== name){
                listStudents.removeAt(index)
                answer = true
                break
            }
        }
        return answer
    }
    fun getStringArray():Array<String>{
        val answerList= arrayListOf<String>()
        for((index,item) in listStudents.withIndex()){
            answerList.add("${item.name} ${item.lastName}")
        }
        return answerList.toTypedArray()
    }
    fun getEntityStudent(): Array<EntityStudent>{
        return listStudents.toTypedArray()
    }
    fun getStudent(index:Int):EntityStudent{
        return listStudents[index]
    }
    companion object {
        private var listStudents= arrayListOf<EntityStudent>()
    }
}
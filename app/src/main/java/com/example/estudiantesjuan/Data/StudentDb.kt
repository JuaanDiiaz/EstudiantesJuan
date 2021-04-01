package com.example.estudiantesjuan.Data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.util.Log
import com.example.estudiantesjuan.Contracts.StudentContract
import com.example.estudiantesjuan.Entity.EntityStudent
import com.example.estudiantesjuan.Tools.Constans

class StudentDb(val context: Context) {
    val conectionDb=ConnectionDB(context)
    private lateinit var db:SQLiteDatabase

    fun add(student:EntityStudent):Long{
        db=conectionDb.openConnection(ConnectionDB.MODE_WRITE)
        val values = ContentValues().apply {
            put(StudentContract.Entry.COLUMN_NAME_NAME,student.name)
            put(StudentContract.Entry.COLUMN_NAME_LAST_NAME,student.lastName)
            put(StudentContract.Entry.COLUMN_NAME_GENDER,student.name)
            put(StudentContract.Entry.COLUMN_NAME_DEGREE,student.degree)
            put(StudentContract.Entry.COLUMN_NAME_READ,student.reading)
            put(StudentContract.Entry.COLUMN_NAME_SPORT,student.sport)
            put(StudentContract.Entry.COLUMN_NAME_TRAVEL,student.travel)
            put(StudentContract.Entry.COLUMN_NAME_FINANCIAL_ASSISTANCE,student.financialAssistance)
        }
        return db.insert(StudentContract.Entry.TABLE_NAME,null,values)
    }
    fun update(student:EntityStudent):Int{
        db=conectionDb.openConnection(ConnectionDB.MODE_WRITE)
        val values = ContentValues().apply {
            put(StudentContract.Entry.COLUMN_NAME_NAME,student.name)
            put(StudentContract.Entry.COLUMN_NAME_LAST_NAME,student.lastName)
            put(StudentContract.Entry.COLUMN_NAME_GENDER,student.name)
            put(StudentContract.Entry.COLUMN_NAME_DEGREE,student.degree)
            put(StudentContract.Entry.COLUMN_NAME_READ,student.reading)
            put(StudentContract.Entry.COLUMN_NAME_SPORT,student.sport)
            put(StudentContract.Entry.COLUMN_NAME_TRAVEL,student.travel)
            put(StudentContract.Entry.COLUMN_NAME_FINANCIAL_ASSISTANCE,student.financialAssistance)
        }
        val where = "${BaseColumns._ID} =?"
        val arg = arrayOf(student.id.toString())
        return db.update(StudentContract.Entry.TABLE_NAME,values,where,arg)
    }
    fun delete(id:Int):Int{
        db=conectionDb.openConnection(ConnectionDB.MODE_WRITE)
        val where = "${BaseColumns._ID} =?"
        val arg = arrayOf(id.toString())
        return db.delete(StudentContract.Entry.TABLE_NAME,where,arg)
    }
    fun getAll(){
        db=conectionDb.openConnection(ConnectionDB.MODE_READ)
        val projection = arrayOf(BaseColumns._ID,
                StudentContract.Entry.COLUMN_NAME_NAME,
                StudentContract.Entry.COLUMN_NAME_LAST_NAME,
                StudentContract.Entry.COLUMN_NAME_GENDER,
                StudentContract.Entry.COLUMN_NAME_DATE_SYSTEM)
        val sortOrder = "${StudentContract.Entry.COLUMN_NAME_NAME} DESC"
        val cursor=db.query(StudentContract.Entry.TABLE_NAME,projection,null,null,null,null,sortOrder)
        if(cursor.moveToFirst()){
            do{
                val id:Long=cursor.getLong(0)
                val name=cursor.getString(1)
                val lastName=cursor.getString(2)
                val gender=cursor.getInt(3)
                val date=cursor.getString(4)
                Log.d(Constans.LOG_TAG,"$id | $name | $lastName | $gender | $date" )
            }while (cursor.moveToNext())
        }
        else
        {
            Log.d(Constans.LOG_TAG,"Sin valores")
        }
    }
    fun getOne(id:Int){
        db=conectionDb.openConnection(ConnectionDB.MODE_READ)
        val projection = arrayOf(BaseColumns._ID,
                StudentContract.Entry.COLUMN_NAME_NAME,
                StudentContract.Entry.COLUMN_NAME_LAST_NAME,
                StudentContract.Entry.COLUMN_NAME_GENDER,
                StudentContract.Entry.COLUMN_NAME_DATE_SYSTEM)
        val where = "${BaseColumns._ID} =?"
        val arg = arrayOf(id.toString())
        val cursor=db.query(StudentContract.Entry.TABLE_NAME,projection,where,arg,null,null,null)
        if(cursor.moveToFirst()){
            val id:Long=cursor.getLong(0)
            val name=cursor.getString(1)
            val lastName=cursor.getString(2)
            val gender=cursor.getInt(3)
            val date=cursor.getString(4)
            Log.d(Constans.LOG_TAG,"$id | $name | $lastName | $gender | $date" )
        }
        else
        {
            Log.d(Constans.LOG_TAG,"Sin valores")
        }
    }
    fun search(valueToSearch:String){
        db=conectionDb.openConnection(ConnectionDB.MODE_READ)
        val projection = arrayOf(BaseColumns._ID,
                StudentContract.Entry.COLUMN_NAME_NAME,
                StudentContract.Entry.COLUMN_NAME_LAST_NAME,
                StudentContract.Entry.COLUMN_NAME_GENDER,
                StudentContract.Entry.COLUMN_NAME_DATE_SYSTEM)
        val sortOrder = "${StudentContract.Entry.COLUMN_NAME_NAME} DESC"
        val where = "${StudentContract.Entry.COLUMN_NAME_NAME} LIKE ?"
        val arg = arrayOf("%$valueToSearch%")
        val cursor=db.query(StudentContract.Entry.TABLE_NAME,projection,where,arg,null,null,sortOrder)
        if(cursor.moveToFirst()){
            do{
                val id:Long=cursor.getLong(0)
                val name=cursor.getString(1)
                val lastName=cursor.getString(2)
                val gender=cursor.getInt(3)
                val date=cursor.getString(4)
                Log.d(Constans.LOG_TAG,"$id | $name | $lastName | $gender | $date" )
            }while (cursor.moveToNext())
        }
        else
        {
            Log.d(Constans.LOG_TAG,"Sin valores")
        }
    }
}
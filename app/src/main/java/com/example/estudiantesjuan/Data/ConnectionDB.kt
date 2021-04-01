package com.example.estudiantesjuan.Data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.estudiantesjuan.Contracts.StudentContract

class ConnectionDB(val context:Context): SQLiteOpenHelper(context, DATABASE_NAME,null,DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_STUDENTS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP_TABLE_STUDENTS)
        onCreate(db)
    }
    fun openConnection(typeConnection:Int):SQLiteDatabase{
        return when(typeConnection){
            MODE_WRITE->{
                writableDatabase
            }
            MODE_READ->{
                readableDatabase
            }
            else->{
                readableDatabase
            }
        }
    }
    companion object{
        const val DATABASE_NAME="ESTUDIANTES_DB"
        const val DATABASE_VERSION=1
        const val CREATE_TABLE_STUDENTS = "CREATE TABLE ${StudentContract.Entry.TABLE_NAME} " +
                "( ${BaseColumns._ID} INTEGER PRIMARY KEY, " +
                "${StudentContract.Entry.COLUMN_NAME_NAME} VARCHAR(20), " +
                "${StudentContract.Entry.COLUMN_NAME_LAST_NAME} VARCHAR(20), " +
                "${StudentContract.Entry.COLUMN_NAME_GENDER} INTEGER, " +
                "${StudentContract.Entry.COLUMN_NAME_DEGREE} VARCHAR(50), " +
                "${StudentContract.Entry.COLUMN_NAME_READ} INTEGER, " +
                "${StudentContract.Entry.COLUMN_NAME_SPORT} INTEGER, " +
                "${StudentContract.Entry.COLUMN_NAME_TRAVEL} INTEGER, " +
                "${StudentContract.Entry.COLUMN_NAME_FINANCIAL_ASSISTANCE} INTEGER, " +
                "${StudentContract.Entry.COLUMN_NAME_DATE_SYSTEM} DATE DEFAULT CURRENT_DATE)"
        const val DROP_TABLE_STUDENTS="DROP TABLE IF EXISTS ${StudentContract.Entry.TABLE_NAME}"
        const val MODE_WRITE=1
        const val MODE_READ=2
    }
}

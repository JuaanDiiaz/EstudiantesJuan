package com.example.estudiantesjuan.Contracts

import android.provider.BaseColumns

object StudentContract {

    object Entry:BaseColumns{
        const val TABLE_NAME = "CTL_STUDENTS"
        const val  COLUMN_NAME_NAME ="Name"
        const val  COLUMN_NAME_LAST_NAME ="LastName"
        const val  COLUMN_NAME_GENDER ="Gender"
        const val  COLUMN_NAME_DEGREE ="Degree"
        const val  COLUMN_NAME_READ ="Read"
        const val  COLUMN_NAME_SPORT ="Sport"
        const val  COLUMN_NAME_TRAVEL ="Travel"
        const val  COLUMN_NAME_FINANCIAL_ASSISTANCE ="FinancialAssistance"
        const val  COLUMN_NAME_DATE_SYSTEM ="DateSystem"
    }
}
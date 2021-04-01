package com.example.estudiantesjuan.Entity

data class EntityStudent (
    var id:Int,
    var name:String,
    var lastName:String,
    var gender:Int,
    var degree:String,
    var reading:Boolean,
    var sport:Boolean,
    var travel:Boolean,
    var financialAssistance:Boolean){
    constructor():this(-1,"","",0,"",false,false,false,false )
}

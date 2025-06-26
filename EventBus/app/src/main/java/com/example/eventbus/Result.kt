package com.example.eventbus

data class Result(val sportKey:Int,
                  val sportName:String,
                  val results:List<String>?,
                  val isWarning:Boolean = false){

}


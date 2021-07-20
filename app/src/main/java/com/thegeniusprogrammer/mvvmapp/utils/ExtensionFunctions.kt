package com.thegeniusprogrammer.mvvmapp.utils

import android.content.Context
import android.util.Log
import android.widget.Toast

fun Context.toast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}


fun Context.logMessage(message:String= "message", print:String){
    Log.d(message, print)
}


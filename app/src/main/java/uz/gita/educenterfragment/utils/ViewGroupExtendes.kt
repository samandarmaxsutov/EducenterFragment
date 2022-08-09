package uz.gita.educentermvvm.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(res:Int): View {
   return LayoutInflater.from(this.context).inflate(res,this,false)
}
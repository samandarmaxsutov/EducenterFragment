package uz.gita.educenterfragment.models

import java.io.Serializable

data class StudentData(val id: Int,
                       val name: String,
                       val phone: String,
                       val image:Int,
                       val group_id:Int): Serializable
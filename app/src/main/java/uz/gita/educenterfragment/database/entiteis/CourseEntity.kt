package uz.gita.educenterfragment.database.entiteis

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data  class CourseEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var image: Int,
    var name: String,
):Serializable

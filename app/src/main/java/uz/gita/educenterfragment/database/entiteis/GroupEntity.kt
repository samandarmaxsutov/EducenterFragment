package uz.gita.educenterfragment.database.entiteis


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity (foreignKeys = arrayOf(ForeignKey(
    entity = CourseEntity::class,
    parentColumns = ["id"],
    childColumns = ["courseId"],
    onDelete = ForeignKey.CASCADE
)))
class GroupEntity(
    @PrimaryKey (autoGenerate = true)
    var id: Int,
    var nameGroup:String,

    var courseId:Int): Serializable
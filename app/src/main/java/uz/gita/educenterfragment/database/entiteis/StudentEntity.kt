package uz.gita.educenterfragment.database.entiteis

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(foreignKeys = arrayOf(
    ForeignKey(
    entity = GroupEntity::class,
    parentColumns = ["id"],
    childColumns = ["groupId"],
    onDelete = ForeignKey.CASCADE
)
))
data class StudentEntity(
@PrimaryKey (autoGenerate = true)
    var id:Int,

val name: String,
val phone: String,
val image:Int,
    var groupId:Int
): Serializable
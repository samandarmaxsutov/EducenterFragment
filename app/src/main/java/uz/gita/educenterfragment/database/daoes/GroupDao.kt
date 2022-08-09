package uz.gita.educenterfragment.database.daoes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import uz.gita.educenterfragment.database.entiteis.GroupEntity


@Dao
interface GroupDao {
    @Query("SELECT * FROM GroupEntity where courseId=:id")
    fun getAll(id: Int): List<GroupEntity>

    @Insert
    fun insert(vararg data: GroupEntity)

    @Delete
    fun delete(vararg data: GroupEntity)

    @Update
    fun update(vararg data: GroupEntity)
}
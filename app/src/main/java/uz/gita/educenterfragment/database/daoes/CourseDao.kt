package uz.gita.educenterfragment.database.daoes

import androidx.lifecycle.LiveData
import androidx.room.*
import uz.gita.educenterfragment.database.entiteis.CourseEntity


@Dao
interface CourseDao {
    @Query("SELECT * FROM CourseEntity ")
    fun getAll(): LiveData<List<CourseEntity>>

    @Insert
    fun insert(vararg data:CourseEntity)
    @Delete
    fun delete(vararg data:CourseEntity)
    @Update
    fun update(vararg data:CourseEntity)

}
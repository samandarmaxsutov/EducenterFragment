package uz.gita.educenterfragment.database.daoes


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import uz.gita.educenterfragment.database.entiteis.StudentEntity


@Dao
interface StudentDao {
    @Query("SELECT * FROM StudentEntity where groupId=:id")
    fun getAll(id:Int ): List<StudentEntity>

    @Insert
    fun insert(vararg data: StudentEntity)

    @Delete
    fun delete(vararg data: StudentEntity)
    @Update
    fun update(vararg data: StudentEntity)

}
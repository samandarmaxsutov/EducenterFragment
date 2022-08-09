package uz.gita.educenterfragment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.educenterfragment.database.daoes.CourseDao
import uz.gita.educenterfragment.database.daoes.GroupDao
import uz.gita.educenterfragment.database.daoes.StudentDao
import uz.gita.educenterfragment.database.entiteis.CourseEntity
import uz.gita.educenterfragment.database.entiteis.GroupEntity
import uz.gita.educenterfragment.database.entiteis.StudentEntity

@Database(entities = [
    CourseEntity::class,
    GroupEntity::class,
    StudentEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    companion object{
        private var _database:AppDatabase?=null

        fun instence(context: Context){
            if (_database==null){
                _database= Room.databaseBuilder(context, AppDatabase::class.java ,"AppDatabase")
                    .allowMainThreadQueries()
                    .build()
            }
        }
        fun getDatabase():AppDatabase{

            return _database!!
        }


    }
    abstract  fun getCourseDao(): CourseDao

    abstract fun getGroupDao(): GroupDao
    abstract fun getStudentDao(): StudentDao
}
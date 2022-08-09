package uz.gita.educenterfragment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.gita.educenterfragment.database.entiteis.CourseEntity
import uz.gita.educenterfragment.database.entiteis.GroupEntity
import uz.gita.educenterfragment.database.entiteis.StudentEntity
import uz.gita.educenterfragment.models.CourseData
import uz.gita.educenterfragment.models.GroupData
import uz.gita.educenterfragment.models.StudentData

interface RepositoryContract {

    interface RepositoryStudent{
        fun getAllS(id:Int):List<StudentEntity>
        fun insertS(data :StudentData)
        fun deleteS(data :StudentData)
        fun updateS(data :StudentData)
    }
    interface RepositoryCourse{
        fun getAllC():LiveData<List<CourseEntity>>
        fun insertC(data :CourseData)
        fun deleteC(data :CourseData)
        fun updateC(data :CourseData)
    }
    interface RepositoryGroup{
        fun getAllG(id:Int):List<GroupEntity>
        fun insertG(data :GroupData)
        fun deleteG(data :GroupData)
        fun updateG(data :GroupData)
    }
}
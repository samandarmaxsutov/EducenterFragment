package uz.gita.educenterfragment.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.educenterfragment.models.CourseData
import uz.gita.educenterfragment.repository.RepositoryContract
import uz.gita.educenterfragment.repository.impl.Repository

class CourseViewModel:ViewModel() {
    private val repository: RepositoryContract.RepositoryCourse = Repository.getInctense()
    val listCourseData = repository.getAllC()


    fun insert(data: CourseData){
        repository.insertC(data)

    }
    fun delete(data: CourseData){
        repository.deleteC(data)


    }
    fun update(data: CourseData){
        repository.updateC(data)

    }

}
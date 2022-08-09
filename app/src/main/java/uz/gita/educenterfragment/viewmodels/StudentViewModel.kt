package uz.gita.educenterfragment.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.educenterfragment.database.entiteis.StudentEntity
import uz.gita.educenterfragment.models.StudentData
import uz.gita.educenterfragment.repository.RepositoryContract
import uz.gita.educenterfragment.repository.impl.Repository

class StudentViewModel : ViewModel() {
    private val repository: RepositoryContract.RepositoryStudent = Repository.getInctense()

    var id2=0
    val listStudentData:MutableLiveData<List<StudentEntity>> = MutableLiveData()

    fun getAll() {
        listStudentData.value=repository.getAllS(id2)
    }



    fun insert(data: StudentData) {
        repository.insertS(data)
        getAll()

    }

    fun delete(data: StudentData) {
        repository.deleteS(data)
        getAll()
    }

    fun update(data: StudentData) {
        repository.updateS(data)
        getAll()
    }

}
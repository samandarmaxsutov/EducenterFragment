package uz.gita.educenterfragment.repository.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.gita.educenterfragment.database.AppDatabase
import uz.gita.educenterfragment.database.entiteis.CourseEntity
import uz.gita.educenterfragment.database.entiteis.GroupEntity
import uz.gita.educenterfragment.database.entiteis.StudentEntity
import uz.gita.educenterfragment.models.CourseData
import uz.gita.educenterfragment.models.GroupData
import uz.gita.educenterfragment.models.StudentData
import uz.gita.educenterfragment.repository.RepositoryContract
import uz.gita.educenterfragment.utils.*

class Repository private constructor():RepositoryContract.RepositoryCourse,RepositoryContract.RepositoryGroup,RepositoryContract.RepositoryStudent {


    companion object {

        private var repository:Repository?=null

        fun instence(){
            if (repository==null){
                repository=Repository()
            }
        }

        fun getInctense()= repository!!


    }


    private val appDatabase=AppDatabase.getDatabase()



    private val courseDao=appDatabase.getCourseDao()


    override fun getAllC(): LiveData<List<CourseEntity>> {
        return courseDao.getAll()
    }

    override fun insertC(data: CourseData) {
        courseDao.insert(data.toCourseEntity())
    }

    override fun deleteC(data: CourseData) {
        courseDao.delete(data.toCourseEntity())
    }

    override fun updateC(data: CourseData) {
        courseDao.update(data.toCourseEntity())
    }






    private val groupDao=appDatabase.getGroupDao()

    override fun getAllG(id:Int): List<GroupEntity> {
        return groupDao.getAll(id)
    }


    override fun insertG(data: GroupData) {
        groupDao.insert(data.toGroupEntity())
    }

    override fun deleteG(data: GroupData) {
        groupDao.delete(data.toGroupEntity())
    }

    override fun updateG(data: GroupData) {
        groupDao.update(data.toGroupEntity())
    }







    private val studentDao=appDatabase.getStudentDao()

    override fun insertS(data: StudentData) {
        studentDao.insert(data.toStudentEntity())
    }

    override fun deleteS(data: StudentData) {
        studentDao.delete(data.toStudentEntity())
    }

    override fun updateS(data: StudentData) {
        studentDao.update(data.toStudentEntity())
    }

    override fun getAllS(id: Int):List<StudentEntity>{
        return studentDao.getAll(id)
    }

}
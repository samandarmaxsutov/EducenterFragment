package uz.gita.educenterfragment.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.educenterfragment.database.entiteis.GroupEntity
import uz.gita.educenterfragment.models.GroupData
import uz.gita.educenterfragment.repository.RepositoryContract
import uz.gita.educenterfragment.repository.impl.Repository

class GroupViewModel:ViewModel() {
    private val repository: RepositoryContract.RepositoryGroup= Repository.getInctense()

    var id_l=0
    val listGroupData:MutableLiveData<List<GroupEntity>> = MutableLiveData()
    init {


    }
    fun insert(data: GroupData){
        repository.insertG(data)
        listGroupData.value=repository.getAllG(id_l)
    }

    fun delete(data: GroupData){
        repository.deleteG(data)
        listGroupData.value=repository.getAllG(id_l)


    }
    fun update(data: GroupData){
        repository.updateG(data)
        listGroupData.value=repository.getAllG(id_l)

    }

    fun getAll(){
        listGroupData.value=repository.getAllG(id_l)
    }

}
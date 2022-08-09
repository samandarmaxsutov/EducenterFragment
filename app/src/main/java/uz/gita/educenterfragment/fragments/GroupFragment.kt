package uz.gita.educenterfragment.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import uz.gita.educenterfragment.R
import uz.gita.educenterfragment.adapters.GroupAdapter
import uz.gita.educenterfragment.models.CourseData
import uz.gita.educenterfragment.models.GroupData
import uz.gita.educenterfragment.utils.toGroupData
import uz.gita.educenterfragment.viewmodels.GroupViewModel

class GroupFragment:Fragment(R.layout.fragment_group) {
    private lateinit var list: RecyclerView
    private lateinit var btnAdd: FloatingActionButton
    private lateinit var textTitle: TextView

    private var adapter1= GroupAdapter()
    private lateinit var viewModel: GroupViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel= ViewModelProvider(this)[GroupViewModel::class.java]
        val course=arguments?.getSerializable("course") as CourseData


        viewModel.id_l=course.id
        list=view.findViewById(R.id.listG)
        btnAdd=view.findViewById(R.id.btnAddG)
        textTitle=view.findViewById(R.id.groups)
        textTitle.text="${course.name} kursidagi gruhlar"
        list.adapter=adapter1

        viewModel.getAll()

        btnAdd.setOnClickListener {
            dialogAdd(view.context)
        }
        adapter1.deleteListenerInvoke {
            viewModel.delete(it)
        }
        adapter1.updateListenerInvoke {
            dialogUpdate(view.context,it)
        }
        adapter1.onClickListenerInvoke {
            parentFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter,R.anim.exit,R.anim.pop_enter,R.anim.pop_exit)
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container,StudentFragment::class.java, bundleOf(Pair("group",it)))
                .addToBackStack(null)
                .commit()
        }
        viewModel.listGroupData.observe(viewLifecycleOwner){a->
            adapter1.submitItems(a.map { it.toGroupData() })
        }
    }


    fun dialogAdd(context: Context){

        val builder = AlertDialog.Builder(context,R.style.CustomAlertDialog).create()
        val view: View = layoutInflater.inflate(R.layout.gruops,null)
        val inputName = view.findViewById<EditText>(R.id.gruh_nomi_add)


        val  saqlash=view.findViewById<TextView>(R.id.saqlash1)
        val  bekor_qilish=view.findViewById<TextView>(R.id.bekor_qilish1)
        builder.setView(view)

        saqlash.setOnClickListener {

            val title=inputName.text.toString()

            if (title!=""){
                viewModel.insert(GroupData(0,title,viewModel.id_l))
                builder.cancel()
            }



        }
        bekor_qilish.setOnClickListener {

            builder.cancel()
        }


        builder.show()


    }
    fun dialogUpdate(context: Context,oldData: GroupData){

        val builder = AlertDialog.Builder(context,R.style.CustomAlertDialog).create()
        val view: View = layoutInflater.inflate(R.layout.gruops,null)
        val inputName = view.findViewById<EditText>(R.id.gruh_nomi_add)


        inputName.setText(oldData.name.toString())
        val  saqlash=view.findViewById<TextView>(R.id.saqlash1)
        val  bekor_qilish=view.findViewById<TextView>(R.id.bekor_qilish1)
        builder.setView(view)

        saqlash.setOnClickListener {

            val title=inputName.text.toString()

            if (title!=""){
                viewModel.update(GroupData(oldData.id,title,viewModel.id_l))
                builder.cancel()
            }



        }
        bekor_qilish.setOnClickListener {

            builder.cancel()
        }


        builder.show()


    }
}
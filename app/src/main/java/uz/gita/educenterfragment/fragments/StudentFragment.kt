package uz.gita.educenterfragment.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import uz.gita.educenterfragment.R
import uz.gita.educenterfragment.adapters.StudentAdapter
import uz.gita.educenterfragment.models.GroupData
import uz.gita.educenterfragment.models.StudentData
import uz.gita.educenterfragment.utils.toStudentData

import uz.gita.educenterfragment.viewmodels.StudentViewModel

class StudentFragment:Fragment(R.layout.fragment_student) {
    private lateinit var list: RecyclerView
    private lateinit var btnAdd: FloatingActionButton
    private lateinit var textTitle: TextView
    private var adapter1= StudentAdapter()
    private lateinit var viewModel: StudentViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel= ViewModelProvider(this)[StudentViewModel::class.java]
        val group=arguments?.getSerializable("group") as GroupData
        viewModel.id2=group.id

        list=view.findViewById<RecyclerView>(R.id.listS)
        btnAdd=view.findViewById(R.id.btnAddS)
        textTitle=view.findViewById(R.id.students)

        textTitle.text="${group.name} a'zolari"
        list.adapter=adapter1

        viewModel.getAll()

        adapter1.deleteListenerInvoke {
            viewModel.delete(it)
        }
        adapter1.updateListenerInvoke {
            dialogUpdate(view.context,it)
        }
        btnAdd.setOnClickListener {
            dialogAdd(view.context)
        }
        viewModel.listStudentData.observe(viewLifecycleOwner) {a->

            adapter1.submitItems(a.map {it.toStudentData()  })
        }
    }

    fun dialogAdd(context: Context){

        val builder = AlertDialog.Builder(context,R.style.CustomAlertDialog).create()
        val view: View = layoutInflater.inflate(R.layout.add_student,null)
        val inputTitle = view.findViewById<EditText>(R.id.title_add)
        val inputNumber = view.findViewById<EditText>(R.id.number)
        val imageView: ImageView = view.findViewById(R.id.img_dilag)
        val radioGroup: RadioGroup = view.findViewById(R.id.radio_group)
        var res=0
        radioGroup.setOnCheckedChangeListener { g, i ->
            when (i) {
                R.id.male->{
                    res=R.drawable.erkak
                    imageView.setImageResource(res)
                }
                R.id.female->{
                    res=R.drawable.ayol
                    imageView.setImageResource(res)
                }
            }
        }




        val  saqlash=view.findViewById<TextView>(R.id.saqlash)
        val  bekor_qilish=view.findViewById<TextView>(R.id.bekor_qilish)
        builder.setView(view)

        saqlash.setOnClickListener {

            val title=inputTitle.text.toString()
            val number=inputNumber.text.toString()
            if (res>0&&title!=""&&number!=""){
                viewModel.insert(StudentData(0,title,number,res,viewModel.id2))
                builder.cancel()
            }
            else{
                Toast.makeText(context," !!!!", Toast.LENGTH_SHORT).show()
            }


        }
        bekor_qilish.setOnClickListener {

            builder.cancel()
        }

        builder.show()


    }
    fun dialogUpdate(context: Context,data: StudentData){

        val builder = AlertDialog.Builder(context,R.style.CustomAlertDialog).create()
        val view: View = layoutInflater.inflate(R.layout.add_student,null)
        val inputTitle = view.findViewById<EditText>(R.id.title_add)
        val inputNumber = view.findViewById<EditText>(R.id.number)
        val imageView: ImageView = view.findViewById(R.id.img_dilag)
        val radioGroup: RadioGroup = view.findViewById(R.id.radio_group)
        var res=0

        imageView.setImageResource(data.image)
        inputTitle.setText(data.name)
        inputNumber.setText(data.phone)

        radioGroup.setOnCheckedChangeListener { g, i ->
            when (i) {
                R.id.male->{
                    res=R.drawable.erkak
                    imageView.setImageResource(res)
                }
                R.id.female->{
                    res=R.drawable.ayol
                    imageView.setImageResource(res)
                }
            }
        }




        val  saqlash=view.findViewById<TextView>(R.id.saqlash)
        val  bekor_qilish=view.findViewById<TextView>(R.id.bekor_qilish)
        builder.setView(view)

        saqlash.setOnClickListener {

            val title=inputTitle.text.toString()
            val number=inputNumber.text.toString()
            if (res>0&&title!=""&&number!=""){
                viewModel.update(StudentData(data.id,title,number,res,viewModel.id2))
                builder.cancel()
            }
            else{
                Toast.makeText(context," !!!!", Toast.LENGTH_SHORT).show()
            }


        }
        bekor_qilish.setOnClickListener {

            builder.cancel()
        }

        builder.show()


    }
}
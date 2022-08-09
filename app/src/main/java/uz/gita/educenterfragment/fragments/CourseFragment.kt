package uz.gita.educenterfragment.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import uz.gita.educenterfragment.R
import uz.gita.educenterfragment.adapters.CourseAdapter
import uz.gita.educenterfragment.models.CourseData
import uz.gita.educenterfragment.utils.toCourseData
import uz.gita.educenterfragment.viewmodels.CourseViewModel

class CourseFragment:Fragment(R.layout.fragment_course) {
    private lateinit var list:RecyclerView
    private lateinit var btnAdd:FloatingActionButton
    private lateinit var viewModel: CourseViewModel
    private val adapter1= CourseAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list =view.findViewById(R.id.list)
        btnAdd =view.findViewById(R.id.btnAdd)

        viewModel= ViewModelProvider(this)[CourseViewModel::class.java]

        viewModel.listCourseData.observe(viewLifecycleOwner){a->
            adapter1.submitItems(a.map { it.toCourseData() })
        }
        list.adapter=adapter1
        btnAdd.setOnClickListener {
            dialogAdd(view.context)
        }
        adapter1.deleteListenerInvoke {
            viewModel.delete(it)
        }
        adapter1.updateListenerInvoke {
            dialoUpdate(view.context,it)
        }
        adapter1.onClickListenerInvoke {

            parentFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter,R.anim.exit,R.anim.pop_enter,R.anim.pop_exit)
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container,GroupFragment::class.java, bundleOf(Pair("course",it)))
                .addToBackStack(null)

                .commit()
        }
    }

    fun dialogAdd(context: Context){

        val builder = AlertDialog.Builder(context,R.style.CustomAlertDialog).create()
        val view: View = layoutInflater.inflate(R.layout.add_course,null)
        val inputTitle = view.findViewById<EditText>(R.id.title_add)
        val img = view.findViewById<ImageView>(R.id.select_img)

        var res=0

        val  saqlash=view.findViewById<TextView>(R.id.saqlash)
        val radioGroup=view.findViewById<RadioGroup>(R.id.radio_group)
        val  bekor_qilish=view.findViewById<TextView>(R.id.bekor_qilish)
        builder.setView(view)
        radioGroup.setOnCheckedChangeListener { g, i ->
            when (i) {
                R.id.android->{
                    res= R.drawable.android
                    img.setImageResource(res)
                }
                R.id.back_end->{
                    res= R.drawable.back_end
                    img.setImageResource(res)
                }
                R.id.front_end->{
                    res= R.drawable.front_end
                    img.setImageResource(res)
                }
                R.id.python->{
                    res= R.drawable.python
                    img.setImageResource(res)
                }

            }
        }

        saqlash.setOnClickListener {

            val title=inputTitle.text.toString()


            if (res!=0 && title!="") {
                viewModel.insert(CourseData(0, res, title))
            }
            else{ Toast.makeText(context, "!!!!", Toast.LENGTH_SHORT).show()}
            builder.cancel()

        }
        bekor_qilish.setOnClickListener {

            builder.cancel()
        }

        builder.show()


    }


    fun dialoUpdate(context: Context,oldCourse:CourseData){

        val builder = AlertDialog.Builder(context,R.style.CustomAlertDialog).create()
        val view: View = layoutInflater.inflate(R.layout.add_course,null)
        val inputTitle = view.findViewById<EditText>(R.id.title_add)
        val img = view.findViewById<ImageView>(R.id.select_img)

        img.setImageResource(oldCourse.image)
        inputTitle.setText(oldCourse.name)
        var res=0

        val  saqlash=view.findViewById<TextView>(R.id.saqlash)
        val radioGroup=view.findViewById<RadioGroup>(R.id.radio_group)
        val  bekor_qilish=view.findViewById<TextView>(R.id.bekor_qilish)
        builder.setView(view)
        radioGroup.setOnCheckedChangeListener { g, i ->
            when (i) {
                R.id.android->{
                    res= R.drawable.android
                    img.setImageResource(res)
                }
                R.id.back_end->{
                    res= R.drawable.back_end
                    img.setImageResource(res)
                }
                R.id.front_end->{
                    res= R.drawable.front_end
                    img.setImageResource(res)
                }
                R.id.python->{
                    res= R.drawable.python
                    img.setImageResource(res)
                }

            }
        }

        saqlash.setOnClickListener {

            val title=inputTitle.text.toString()


            if (res!=0 && title!="") {
                viewModel.update(CourseData(oldCourse.id, res, title))
                builder.cancel()

            }
            else{ Toast.makeText(context, "!!!!", Toast.LENGTH_SHORT).show()}

        }
        bekor_qilish.setOnClickListener {

            builder.cancel()
        }

        builder.show()


    }
}
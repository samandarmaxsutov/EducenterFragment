package uz.gita.educenterfragment.adapters

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.gita.educenterfragment.R
import uz.gita.educenterfragment.models.CourseData
import uz.gita.educentermvvm.utils.inflate


class CourseAdapter:RecyclerView.Adapter<CourseAdapter.Course>() {


    private var courseData=ArrayList<CourseData>()
    private var deleteListener:((CourseData)->Unit)?=null
    private var updateListener:((CourseData)->Unit)?=null
    private var onClickListener:((CourseData)->Unit)?=null


    @SuppressLint("NotifyDataSetChanged")
    fun submitItems(items:List<CourseData>){
        courseData.clear()
        courseData.addAll(items)
        notifyDataSetChanged()
    }
    fun deleteListenerInvoke(l:(CourseData)->Unit){
        deleteListener=l
    }
    fun updateListenerInvoke(l:(CourseData)->Unit){
        updateListener=l
    }
    fun onClickListenerInvoke(l:(CourseData)->Unit){
        onClickListener=l
    }

    inner class Course(view: View):RecyclerView.ViewHolder(view){

        private val image=itemView.findViewById<ImageView>(R.id.course_imageView)
        private val btnDelete=itemView.findViewById<ImageView>(R.id.btnDeleteC)
        private val name:TextView=itemView.findViewById(R.id.couresTextView)
        private val gr_soni:TextView=itemView.findViewById(R.id.gr_soni)
        private val st_soni:TextView=itemView.findViewById(R.id.st_soni)

        init {
            name.setOnClickListener {
                updateListener?.invoke(courseData[adapterPosition])
            }
            image.setOnClickListener {
                onClickListener?.invoke(courseData[adapterPosition])
            }
            btnDelete.setOnClickListener {
                deleteListener?.invoke(courseData[adapterPosition])
            }

        }
        fun bind(){
            val item=courseData[adapterPosition]
            image.setImageResource(item.image)
            name.text=item.name


        }
    }




    override fun getItemCount()=courseData.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= Course(parent.inflate(R.layout.course_custom))
    override fun onBindViewHolder(holder: Course, position: Int)=holder.bind()
}
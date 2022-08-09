package uz.gita.educenterfragment.adapters

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.gita.educenterfragment.R
import uz.gita.educenterfragment.models.StudentData
import uz.gita.educentermvvm.utils.inflate


class StudentAdapter :RecyclerView.Adapter<StudentAdapter.Student>(){
    private var studentData=ArrayList<StudentData>()
    private var deleteListener:((StudentData)->Unit)?=null
    private var updateListener:((StudentData)->Unit)?=null
    private var onClickListener:((StudentData)->Unit)?=null


    @SuppressLint("NotifyDataSetChanged")
    fun submitItems(items:List<StudentData>){
        studentData.clear()
        studentData.addAll(items)
        notifyDataSetChanged()
    }
    fun deleteListenerInvoke(l:(StudentData)->Unit){
        deleteListener=l
    }
    fun updateListenerInvoke(l:(StudentData)->Unit){
        updateListener=l
    }
    fun onClickListenerInvoke(l:(StudentData)->Unit){
        onClickListener=l
    }

    inner class Student(view: View): RecyclerView.ViewHolder(view){
        val image: ImageView =itemView.findViewById(R.id.imageS)
        val name: TextView=itemView.findViewById(R.id.txtNameS)
        val phone: TextView=itemView.findViewById(R.id.textPhoneS)
        val btnDelete:ImageView=itemView.findViewById(R.id.btnDeleteS)
        init {
            btnDelete.setOnClickListener {
                deleteListener?.invoke(studentData[adapterPosition])
            }
            name.setOnClickListener {
                updateListener?.invoke(studentData[adapterPosition])
            }
        }
        fun bind(){
            val item=studentData[adapterPosition]

            name.text=item.name
            image.setImageResource(item.image)
            phone.text=item.phone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=Student (
        parent.inflate(R.layout.item_students))
    override fun onBindViewHolder(holder: Student, position: Int)=holder.bind()

    override fun getItemCount(): Int=studentData.size
}
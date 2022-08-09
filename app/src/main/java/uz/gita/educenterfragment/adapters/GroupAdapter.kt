package uz.gita.educenterfragment.adapters

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.gita.educenterfragment.R
import uz.gita.educenterfragment.models.GroupData
import uz.gita.educentermvvm.utils.inflate


class GroupAdapter :RecyclerView.Adapter<GroupAdapter.Group>(){

    private var groupData=ArrayList<GroupData>()
    private var deleteListener:((GroupData)->Unit)?=null
    private var updateListener:((GroupData)->Unit)?=null
    private var onClickListener:((GroupData)->Unit)?=null


    @SuppressLint("NotifyDataSetChanged")
    fun submitItems(items:List<GroupData>){
        groupData.clear()
        groupData.addAll(items)
        notifyDataSetChanged()
    }
    fun deleteListenerInvoke(l:(GroupData)->Unit){
        deleteListener=l
    }
     fun updateListenerInvoke(l:(GroupData)->Unit){
        updateListener=l
    }
     fun onClickListenerInvoke(l:(GroupData)->Unit){
        onClickListener=l
    }


    inner class  Group(view: View): RecyclerView.ViewHolder(view){
       private val textView:TextView=itemView.findViewById(R.id.nameGruop)
       private val layout:LinearLayout=itemView.findViewById(R.id.layoutG)
        val image:ImageView=itemView.findViewById(R.id.btnDeleteG)
        init {
            textView.setOnClickListener {
               updateListener?.invoke(groupData[adapterPosition])
            }
            layout.setOnClickListener {
                onClickListener?.invoke(groupData[adapterPosition])
            }
            image.setOnClickListener {
                deleteListener?.invoke(groupData[adapterPosition])
            }
        }
        fun bind(){
            val item=groupData[position]
            textView.text=item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=Group(
    parent.inflate(R.layout.gruop_items)
    )

    override fun onBindViewHolder(holder: Group, position: Int)=holder.bind()

    override fun getItemCount(): Int=groupData.size
}
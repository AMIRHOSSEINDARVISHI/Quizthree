package code.with.cal.quizthree

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListAdapter(private val fragment: Fragment) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    private var dataList = mutableListOf<ListUser>()

    fun setDataList(dataList: List<ListUser>) {
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nationalCode: TextView = itemView.findViewById(R.id.nationalCode_card)
        var name: TextView = itemView.findViewById(R.id.name_card)
        var family: TextView = itemView.findViewById(R.id.family_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]

        holder.nationalCode.text = data.nationalCode
        holder.name.text = data.firstName
        holder.family.text = data.lastName

    }

    override fun getItemCount() = dataList.size

}
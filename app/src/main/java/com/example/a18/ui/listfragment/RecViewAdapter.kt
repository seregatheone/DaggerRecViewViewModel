package com.example.a18.ui.listfragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.a18.R
import com.example.a18.data.retrofitrequest.RequestModel

class RecViewAdapter : RecyclerView.Adapter<RecViewAdapter.MyViewHolder>() {

    var allData : List<RequestModel> = emptyList()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = allData[position]

        val id = holder.itemView.findViewById<TextView>(R.id.id)
        id.text = currentItem.id

        val title = holder.itemView.findViewById<TextView>(R.id.title)
        title.text = currentItem.title

        val rowLayout = holder.itemView.findViewById<ConstraintLayout>(R.id.constraintLayout)
        rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToChangeFIeldsFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return allData.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setRequestsData(requestsPool: List<RequestModel>){
        allData = requestsPool
        notifyDataSetChanged()
    }
}
package com.example.shoutbox

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecycleViewAdapter(private val RecycleViewList: List<RecycleViewItem>) : RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = RecycleViewList[position]

        holder.textViewLogin.text = currentItem.login
        holder.textViewDate.text = currentItem.textData
        holder.textViewContent.text = currentItem.description
    }

    override fun getItemCount() = RecycleViewList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewLogin: TextView = itemView.findViewById(R.id.id_textViewLogin)
        val textViewDate: TextView = itemView.findViewById(R.id.id_textViewDate)
        val textViewContent: TextView = itemView.findViewById(R.id.id_textViewContent)
    }

//    fun setData(data: List<RecycleViewItem?>) {
//
//        notifyDataSetChanged()
//    }
}
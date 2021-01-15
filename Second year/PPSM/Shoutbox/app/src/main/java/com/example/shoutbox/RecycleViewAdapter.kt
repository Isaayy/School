package com.example.shoutbox

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class RecycleViewAdapter(private val RecycleViewList: List<RecycleViewItem>) : RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>() {

    private var mContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_item, parent, false)
        mContext = parent.context;
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = RecycleViewList[position]

        holder.textViewLogin.text = currentItem.login
        holder.textViewDate.text = currentItem.textData
        holder.textViewContent.text = currentItem.description

        // ####################################################
        // single item onClickListener to open new activity
        holder.itemView.setOnClickListener {
            val intent = Intent(mContext, SingleComment::class.java)
            intent.putExtra("login", currentItem.login)
            intent.putExtra("date", currentItem.textData)
            intent.putExtra("comment", currentItem.description)

            mContext!!.startActivity(intent)
        }
    }

    override fun getItemCount() = RecycleViewList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewLogin: TextView = itemView.findViewById(R.id.id_textViewLogin)
        val textViewDate: TextView = itemView.findViewById(R.id.id_textViewDate)
        val textViewContent: TextView = itemView.findViewById(R.id.id_textViewContent)
    }
}

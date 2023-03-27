package com.dicoding.picodiploma.fovid.view.faskes

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.fovid.R
import com.dicoding.picodiploma.fovid.model.response.ResultsItem

class DialogAdapter(private val listDataItem: List<ResultsItem>, private val onClick:(provinceName:String)->Unit) : RecyclerView.Adapter<DialogAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.dialog_item, viewGroup, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val province = listDataItem[position]

        holder.tvName.text = province.value
        Log.d("isinya ini", province.value)

        holder.itemView.setOnClickListener {
            onClick(province.value)
        }
    }

    override fun getItemCount() = listDataItem.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvName: TextView = itemView.findViewById(R.id.tvDialogProvinceName)
    }
}
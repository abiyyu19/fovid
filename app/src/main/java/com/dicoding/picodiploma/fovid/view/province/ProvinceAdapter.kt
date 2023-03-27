package com.dicoding.picodiploma.fovid.view.province

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.fovid.R
import com.dicoding.picodiploma.fovid.model.response.ListDataItem

class ProvinceAdapter(private val listDataItem: List<ListDataItem>) : RecyclerView.Adapter<ProvinceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_province, viewGroup, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val province = listDataItem[position]

        holder.tvName.text = province.key

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ProvinceDetailActivity::class.java)
            intent.putExtra(ProvinceDetailActivity.EXTRA_DATA, province)

            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = listDataItem.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvName: TextView = itemView.findViewById(R.id.tvProvinceName)
    }
}
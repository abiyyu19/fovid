package com.dicoding.picodiploma.fovid.view.faskes

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.fovid.R
import com.dicoding.picodiploma.fovid.model.response.ResultsItem

class ProvinceNameAdapter(private val listDataItem: List<ResultsItem>) : RecyclerView.Adapter<ProvinceNameAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_province, viewGroup, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val province = listDataItem[position]

        holder.tvName.text = province.value

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ListCityActivity::class.java)
            intent.putExtra(ListCityActivity.EXTRA_DATA, province.value)

            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = listDataItem.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvName: TextView = itemView.findViewById(R.id.tvProvinceName)
    }

}
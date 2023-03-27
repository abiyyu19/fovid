package com.dicoding.picodiploma.fovid.view.faskes

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.fovid.R
import com.dicoding.picodiploma.fovid.model.response.DataItem

class ListFaskesAdapter(private val listDataItem: List<DataItem?>?) : RecyclerView.Adapter<ListFaskesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_faskes, viewGroup, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val faskes = listDataItem?.get(position)

        if (faskes != null) {
            if (faskes.alamat != null) {
                holder.tvAddress.text = faskes.alamat
            } else {
                holder.tvAddress.text = holder.itemView.context.getString(R.string.address_unknown)
            }

            if (faskes.nama != null) {
                holder.tvName.text = faskes.nama
            } else {
                holder.tvName.text = holder.itemView.context.getString(R.string.name_unknown)
            }
        }


        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, FaskesDetailActivity::class.java)
            intent.putExtra(FaskesDetailActivity.EXTRA_DATA, faskes)

            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = listDataItem!!.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvName: TextView = itemView.findViewById(R.id.tvFaskesName)
        var tvAddress: TextView = itemView.findViewById(R.id.tvFaskesAddress)
    }
}
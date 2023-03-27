package com.dicoding.picodiploma.fovid.view.hospital

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.fovid.R
import com.dicoding.picodiploma.fovid.model.response.HospitalResponse

class ListHospitalAdapter(private val listDataItem: List<HospitalResponse?>?) : RecyclerView.Adapter<ListHospitalAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_faskes, viewGroup, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hospital = listDataItem?.get(position)

        if (hospital != null) {
            if(hospital.alamat == "") {
                holder.tvAddress.text = holder.itemView.context.getString(R.string.address_unknown_hospital)
            } else {
                holder.tvAddress.text = hospital.alamat
            }

            if (hospital.nama != null) {
                holder.tvName.text = hospital.nama
            } else {
                holder.tvName.text = holder.itemView.context.getString(R.string.name_unknown)
            }
        }


        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, HospitalDetailActivity::class.java)
            intent.putExtra(HospitalDetailActivity.EXTRA_DATA, hospital)

            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listDataItem?.size!!

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvName: TextView = itemView.findViewById(R.id.tvFaskesName)
        var tvAddress: TextView = itemView.findViewById(R.id.tvFaskesAddress)
    }
}
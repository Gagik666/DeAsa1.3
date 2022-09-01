package com.example.deasa13.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deasa13.R
import com.example.deasa13.databinding.ItemOldTeamPointBinding

class OldPointAdapter(
    private val list: MutableList<String>
) : RecyclerView.Adapter<OldPointAdapter.PointViewHolder>() {
    class PointViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemOldTeamPointBinding.bind(itemView)
        fun bind(s: String) = with(binding) {
            tvOdPoint.text = s
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PointViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_old_team_point, parent, false)
    )

    override fun onBindViewHolder(holder: PointViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

}
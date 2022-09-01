package com.example.deasa13.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deasa13.R
import com.example.deasa13.databinding.ItemPointBinding
import com.example.deasa13.model.TeamModel

class PointAdapter(
    private val list: MutableList<TeamModel>
) : RecyclerView.Adapter<PointAdapter.PointViewHolder>() {
    class PointViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemPointBinding.bind(itemView)
        fun bind(teamModel: TeamModel) = with(binding) {
            tvTeam.text = teamModel.team
            tvPoint.text = teamModel.point.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PointViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_point, parent, false)
    )

    override fun onBindViewHolder(holder: PointViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}
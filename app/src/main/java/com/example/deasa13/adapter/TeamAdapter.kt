package com.example.deasa13.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.deasa13.DataList
import com.example.deasa13.R
import com.example.deasa13.Value
import com.example.deasa13.databinding.ItemTeamBinding
import com.example.deasa13.model.TeamModel

class TeamAdapter(
    private val teamList: MutableList<TeamModel>,
    private val onClickItem: (index: Int) -> Unit
) : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {
    lateinit var context: Context
    inner class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var binding = ItemTeamBinding.bind(itemView)

        @SuppressLint("SetTextI18n")
        fun bind(teamModel: TeamModel, index: Int) = with(binding) {
            tvTeam.text = teamModel.team

            if (DataList.teamList.size > 2)
                binding.imgBtnClear.visibility = View.VISIBLE
            else
                binding.imgBtnClear.visibility = View.INVISIBLE
            tvTeam.setOnClickListener {
                onClickItem.invoke(index)
            }

            binding.imgBtnClear.setOnClickListener {
                DataList.teamList.removeAt(index)
                notifyDataSetChanged()
                ActivityCompat.recreate(context as Activity)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : TeamViewHolder {
       context = parent.context
       return TeamViewHolder( LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false))
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(teamList[position], position)
    }

    override fun getItemCount() = teamList.size
}
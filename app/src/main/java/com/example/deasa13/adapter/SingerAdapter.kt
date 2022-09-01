package com.example.deasa13.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deasa13.R
import com.example.deasa13.databinding.ItemSingerBinding

class SingerAdapter(
    private val singerList: MutableList<String>,
    private val onClickItem: (click: Boolean) -> Unit
): RecyclerView.Adapter<SingerAdapter.SingerViewHolder>() {
    inner class SingerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = ItemSingerBinding.bind(itemView)
        @SuppressLint("ResourceAsColor")
        fun bind(s: String) = with(binding) {
            btnSinger.text = s
            var click = false
            btnSinger.setOnClickListener {
                if (!click) {
                    binding.btnSinger.setTextColor(Color.parseColor("#CFB6DF"))
                    click = true
                } else {
                    binding.btnSinger.setTextColor(Color.parseColor("#38006b"))
                    click = false
                }
                onClickItem.invoke(click)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SingerViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_singer, parent, false)
    )

    override fun onBindViewHolder(holder: SingerViewHolder, position: Int) {
        holder.bind(singerList[position])
    }

    override fun getItemCount() = singerList.size
}
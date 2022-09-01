package com.example.deasa13.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deasa13.R
import com.example.deasa13.databinding.ItemAllUsersBinding
import com.example.deasa13.model.UserModel
import com.example.deasa13.model.UsersModel
import com.squareup.picasso.Picasso

class UsersAdapter(
    private val userList: MutableList<UsersModel>
) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = ItemAllUsersBinding.bind(itemView)
        fun bind(user: UsersModel) = with(binding) {
            tvFirstname.text = user.firstName
            tvLastName.text = user.lastName
            Picasso.get().load(user.imgURL).into(imgUser)
            when (user.rating) {
                1 -> {
                    imgStar1.setImageResource(R.drawable.ic_star_rating_activ)
                    imgStar2.setImageResource(R.drawable.ic_star_reting)
                    imgStar3.setImageResource(R.drawable.ic_star_reting)
                    imgStar4.setImageResource(R.drawable.ic_star_reting)
                    imgStar5.setImageResource(R.drawable.ic_star_reting)
                }
                2 -> {
                    imgStar1.setImageResource(R.drawable.ic_star_rating_activ)
                    imgStar2.setImageResource(R.drawable.ic_star_rating_activ)
                    imgStar3.setImageResource(R.drawable.ic_star_reting)
                    imgStar4.setImageResource(R.drawable.ic_star_reting)
                    imgStar5.setImageResource(R.drawable.ic_star_reting)
                }
                3 -> {
                    imgStar1.setImageResource(R.drawable.ic_star_rating_activ)
                    imgStar2.setImageResource(R.drawable.ic_star_rating_activ)
                    imgStar3.setImageResource(R.drawable.ic_star_rating_activ)
                    imgStar4.setImageResource(R.drawable.ic_star_reting)
                    imgStar5.setImageResource(R.drawable.ic_star_reting)
                }
                4 -> {
                    imgStar1.setImageResource(R.drawable.ic_star_rating_activ)
                    imgStar2.setImageResource(R.drawable.ic_star_rating_activ)
                    imgStar3.setImageResource(R.drawable.ic_star_rating_activ)
                    imgStar4.setImageResource(R.drawable.ic_star_rating_activ)
                    imgStar5.setImageResource(R.drawable.ic_star_reting)
                }
                5 -> {
                    imgStar1.setImageResource(R.drawable.ic_star_rating_activ)
                    imgStar2.setImageResource(R.drawable.ic_star_rating_activ)
                    imgStar3.setImageResource(R.drawable.ic_star_rating_activ)
                    imgStar4.setImageResource(R.drawable.ic_star_rating_activ)
                    imgStar5.setImageResource(R.drawable.ic_star_rating_activ)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_all_users, parent, false)
    )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount() = userList.size
}
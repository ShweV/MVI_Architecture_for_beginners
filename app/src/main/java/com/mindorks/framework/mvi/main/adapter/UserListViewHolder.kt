package com.mindorks.framework.mvi.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mindorks.framework.mvi.data.model.User
import kotlinx.android.synthetic.main.item_user_info.view.*

class UserListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(user: User) {
        with(itemView) {
            user_name_text.text = user.name
            user_email_text.text = user.email
            Glide.with(this.context).load(user.avatar)
                .into(user_image)
        }
    }
}
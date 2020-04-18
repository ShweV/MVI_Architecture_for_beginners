package com.mindorks.framework.mvi.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mindorks.framework.mvi.R
import com.mindorks.framework.mvi.data.model.User

class UserListAdapter : RecyclerView.Adapter<UserListViewHolder>() {

    private val userList = arrayListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_user_info, parent, false)
        return UserListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int = userList.size

    fun setUserList(userList: List<User>) {
        if (this.userList != userList) {
            this.userList.clear()
            this.userList.addAll(userList)
            notifyDataSetChanged()
        }
    }
}
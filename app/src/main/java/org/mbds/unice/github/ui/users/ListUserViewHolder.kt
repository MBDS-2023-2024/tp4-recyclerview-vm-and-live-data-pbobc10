package org.mbds.unice.github.ui.users

import android.util.Log

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.mbds.unice.github.data.model.User
import org.mbds.unice.github.databinding.ItemListUserBinding

class ListUserViewHolder(private val binding: ItemListUserBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User, callback: UserListAdapter.Listener) {
        // Load image using Glide
        Glide.with(itemView.context)
            .load(user.avatarUrl)
            .into(binding.itemListUserAvatar)
        //Log.d("ImageLoading", "Loading image from URL: ${user.avatarUrl}")
        binding.itemListUserUsername.text = user.login

        binding.itemListUserDeleteButton.setOnClickListener {
            callback.onClickDelete(user)
            Log.d("test", "delete user")
        }
    }
}
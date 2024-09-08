package org.mbds.unice.github.ui.users

import android.util.Log

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.mbds.unice.github.R
import org.mbds.unice.github.data.model.User
import org.mbds.unice.github.databinding.ItemListUserBinding

class ListUserViewHolder(private val binding: ItemListUserBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User, callback: UserListAdapter.Listener) {
        // Load image using Glide
        Glide.with(itemView.context)
            .load(user.avatarUrl)
            .into(binding.itemListUserAvatar)

        binding.itemListUserUsername.text = user.login

        // Changer le background en fonction du statut de l'utilisateur
        itemView.setBackgroundColor(
            if (user.isActive) android.graphics.Color.WHITE
            else android.graphics.Color.RED
        )

        // Changer l'icône du bouton en fonction du statut de l'utilisateur
        binding.itemListUserDeleteButton.setImageResource(
            if (user.isActive) R.drawable.ic_delete_black_24dp
            else R.drawable.ic_restore_black_24dp
        )
        binding.itemListUserDeleteButton.setOnClickListener {
            callback.onClickDelete(user)
            Log.d("test", "delete user")
        }
    }
}
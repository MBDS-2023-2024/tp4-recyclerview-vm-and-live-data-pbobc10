package org.mbds.unice.github.ui.utils

import androidx.recyclerview.widget.DiffUtil
import org.mbds.unice.github.data.model.User

class UserDiffCallback : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id === newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        // prend en compte le changement de statut de l'utilisateur. Pourque la liste puisse se rafraîchir lorsque vous changez le statut.
        return oldItem.login == newItem.login &&
                oldItem.avatarUrl == newItem.avatarUrl &&
                oldItem.isActive == newItem.isActive  // Ajout de la vérification du statut
    }
}
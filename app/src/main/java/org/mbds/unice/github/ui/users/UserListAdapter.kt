package org.mbds.unice.github.ui.users

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import org.mbds.unice.github.R
import org.mbds.unice.github.data.model.User
import org.mbds.unice.github.databinding.ItemListUserBinding
import org.mbds.unice.github.ui.utils.UserDiffCallback

class UserListAdapter(
    private val callback: Listener
) : RecyclerView.Adapter<ListUserViewHolder>() {

    // Permet de gérer la mise à jour de la liste de données
    private val mDiffer: AsyncListDiffer<User> =
        AsyncListDiffer(this, UserDiffCallback())

    interface Listener {
        fun onClickDelete(user: User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserViewHolder {
        val context = parent.context
        // Use ViewBinding to inflate the view
        val binding = ItemListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_list_user, parent, false)
        return ListUserViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        holder.bind(mDiffer.currentList[position], callback)
    }

    override fun getItemCount(): Int {
        return mDiffer.currentList.size
    }

    // PUBLIC API ---
    fun updateList(newList: List<User>) {
        mDiffer.submitList(ArrayList(newList))
        Log.d("test","new list size"+newList.size)
    }
}
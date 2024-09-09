package org.mbds.unice.github.ui.users

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import org.mbds.unice.github.R
import org.mbds.unice.github.data.model.SortOption
import org.mbds.unice.github.data.model.User
import org.mbds.unice.github.databinding.ActivityListUserBinding

class ListUserActivity : AppCompatActivity(), UserListAdapter.Listener {
    lateinit var binding: ActivityListUserBinding
    // By lazy permet de faire du chargement parresseux, L'adapteur sera crée au premier appel
    private val adapter: UserListAdapter by lazy {
        UserListAdapter(this)
    }

    private val viewModel: UserViewModel by lazy {
        ViewModelProvider(this)[UserViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  Permet de lier automatiquement les vues entre le XML et la vue
        binding = ActivityListUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureFab()
        configureRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        viewModel.users.observe(this) {
            adapter.updateList(it)
        }
    }

    private fun configureRecyclerView() {
        binding.activityListUserRv.adapter = adapter
    }

    private fun configureFab() {
        binding.activityListUserFab.setOnClickListener {
            viewModel.generateRandomUser()
            Toast.makeText(this, "adding user", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClickDelete(user: User) {
        //Ajouter des logs pour tracer les actions de l'utilisateur
        Log.d("UserAction", "Toggle status requested for user: $user")

        val newStatus = !user.isActive
        val action = if (newStatus) "activate" else "deactivate"

        //Ajouter une boite de dialogue pour confirmer l'action de l'utilisateur"
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Confirm status change")
        builder.setMessage("Are you sure you want to $action ${user.login}?")

        // If the user confirms action
        builder.setPositiveButton("yes") { dialog, _ ->
            // Perform the action here
            viewModel.toggleUserStatus(user)
            Log.d("UserAction", "User ${user.login} status changed to $newStatus")
            dialog.dismiss() // Close the dialog
            }

        // If the user cancels action
        builder.setNegativeButton("NO") { dialog, _ ->
            Log.d("UserAction", "Status change canceled for user: ${user.login}")
            dialog.dismiss() // Close the dialog
        }
            // show the dialog
            val dialog = builder.create()
            dialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list_user,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sort_name_asc -> {
                viewModel.sortUsers(SortOption.NAME_ASC)
                true
            }
            R.id.sort_name_desc -> {
                viewModel.sortUsers(SortOption.NAME_DESC)
                true
            }
            R.id.sort_date_asc -> {
                viewModel.sortUsers(SortOption.DATE_ASC)
                true
            }
            R.id.sort_date_desc -> {
                viewModel.sortUsers(SortOption.DATE_DESC)
                true
            }
            R.id.sort_status_active -> {
                viewModel.sortUsers(SortOption.STATUS_ACTIVE)
                true
            }
            R.id.sort_status_inactive -> {
                viewModel.sortUsers(SortOption.STATUS_INACTIVE)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
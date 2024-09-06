package org.mbds.unice.github.ui.users

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.mbds.unice.github.R
import org.mbds.unice.github.data.model.User
import org.mbds.unice.github.databinding.ActivityListUserBinding

class ListUserActivity : AppCompatActivity(), UserListAdapter.Listener {
    // TODO : Utiliser viewBinding
    lateinit var recyclerView: RecyclerView
    lateinit var fab: FloatingActionButton
    lateinit var binding: ActivityListUserBinding


    // By lazy permet de faire du chargement parresseux,
    // L'adapteur sera crée au premier appel
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
       // recyclerView = findViewById(R.id.activity_list_user_rv)
        binding.activityListUserRv.adapter = adapter
        //recyclerView.adapter = adapter
    }

    private fun configureFab() {
        //fab = findViewById(R.id.activity_list_user_fab)
        binding.activityListUserFab.setOnClickListener {
            viewModel.generateRandomUser()
            Toast.makeText(this, "adding user", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClickDelete(user: User) {
        //Ajouter des logs pour tracer les actions de l'utilisateur
        Log.d("UserAction", "Delete requested for user: $user")
        //Ajouter une boite de dialogue pour confirmer la suppression et supprimer l'utilisateur si l'utilisateur confirme"
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Confirm delete")
        builder.setMessage("Are you sure you want to delete $user?")

        // If the user confirms deletion
        builder.setPositiveButton("yes") { dialog, _ ->
            // Perform the deletion here
            viewModel.deleteUser(user)
            Log.d("UserAction", "User $user deleted")
            dialog.dismiss() // Close the dialog
            }

        // If the user cancels deletion
        builder.setNegativeButton("NO") { dialog, _ ->
            Log.d("UserAction", "Deletion canceled for user: ${user}")
            dialog.dismiss() // Close the dialog
        }
            // show the dialog
            val dialog = builder.create()
            dialog.show()


    }


}
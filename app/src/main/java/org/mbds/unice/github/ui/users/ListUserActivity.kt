package org.mbds.unice.github.ui.users

import android.os.Bundle
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
        val binding : ActivityListUserBinding = ActivityListUserBinding.inflate(layoutInflater)
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
        recyclerView = findViewById(R.id.activity_list_user_rv)
        recyclerView.adapter = adapter
    }

    private fun configureFab() {
        fab = findViewById(R.id.activity_list_user_fab)
        fab.setOnClickListener {
            //TODO("Ajouter un utilisateur aléatoire")

        }
    }

    override fun onClickDelete(user: User) {
        TODO("Ajouter des logs pour tracer les actions de l'utilisateur")
        TODO("Ajouter une boite de dialogue pour confirmer la suppression et supprimer l'utilisateur si l'utilisateur confirme")
    }
}
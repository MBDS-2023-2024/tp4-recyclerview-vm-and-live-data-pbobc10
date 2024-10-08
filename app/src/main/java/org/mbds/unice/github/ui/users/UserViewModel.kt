package org.mbds.unice.github.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.mbds.unice.github.data.model.SortOption
import org.mbds.unice.github.data.model.User
import org.mbds.unice.github.data.repository.UserRepository
import org.mbds.unice.github.di.Injection


class UserViewModel : ViewModel() {

    // On a besoin d'instancier le repository une fois (cf. Singleton)
    private val userRepository: UserRepository = Injection.getRepository()

    // On utilise un MutableLiveData pour pouvoir modifier la liste des utilisateurs dans le ViewModel
    // On expose un LiveData (qui n'autorise pas les mpodifications) pour que l'activité puisse observer les changements
    private val _users: MutableLiveData<List<User>> = MutableLiveData()
    val users: LiveData<List<User>> = _users

    init {
        // On initialise la liste des utilisateurs à la création du ViewModel
        refresh()
    }

    private fun refresh() {
        _users.value = userRepository.getUsers()
    }

    fun generateRandomUser() {
        userRepository.addRandomUser()
        refresh()
    }

    fun deleteUser(user: User) {
        userRepository.deleteUser(user)
        refresh()
    }

    fun toggleUserStatus(user: User) {
        val currentList = _users.value?.toMutableList() ?: mutableListOf()
        val index = currentList.indexOfFirst { it.id == user.id }
        if (index != -1) {
            val updatedUser = currentList[index].copy(isActive = !currentList[index].isActive)
            currentList[index] = updatedUser
            _users.value = currentList  // Ceci déclenchera une mise à jour
        }
    }

    fun sortUsers(sortOption: SortOption) {
        val currentList = _users.value ?: return
        val sortedList = when (sortOption) {
            SortOption.NAME_ASC -> currentList.sortedBy { it.login.lowercase() }
            SortOption.NAME_DESC -> currentList.sortedByDescending { it.login.lowercase() }
            SortOption.DATE_ASC -> currentList.sortedBy { it.createdAt }
            SortOption.DATE_DESC -> currentList.sortedByDescending { it.createdAt }
            SortOption.STATUS_ACTIVE -> currentList.sortedByDescending { it.isActive }
            SortOption.STATUS_INACTIVE -> currentList.sortedBy { it.isActive }
        }
        _users.value = sortedList
    }
}
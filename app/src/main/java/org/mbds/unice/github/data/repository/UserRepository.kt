package org.mbds.unice.github.data.repository

import org.mbds.unice.github.data.api.ApiService
import org.mbds.unice.github.data.api.FakeApiServiceGenerator
import org.mbds.unice.github.data.api.FakeApiServiceGenerator.FAKE_USERS
import org.mbds.unice.github.data.api.FakeApiServiceGenerator.FAKE_USERS_RANDOM
import org.mbds.unice.github.data.model.User

class UserRepository(
    private val apiService: ApiService
) {
    fun getUsers(): List<User> {
        // Should return the list of users retrieved from the api service
        return apiService.getUsers()
    }

    fun addRandomUser() {
        //Should add a random user
        apiService.addRandomUser()
    }

    fun deleteUser(user: User) {
       // Should remove the user
        apiService.deleteUser(user)

    }
}
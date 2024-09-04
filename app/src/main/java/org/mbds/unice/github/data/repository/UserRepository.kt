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
        return FAKE_USERS
    }

    fun addRandomUser() {
        //TODO("Should add a random user")
        val fakeUser = FAKE_USERS_RANDOM.random()
        FAKE_USERS.add(fakeUser)
        println("test "+ fakeUser)
    }

    fun deleteUser(user: User) {
        TODO("Should remove the user")
    }
}
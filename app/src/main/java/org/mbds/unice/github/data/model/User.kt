package org.mbds.unice.github.data.model

import java.util.*

data class User(
    val id: String,
    val login: String,
    val avatarUrl: String,
    var isActive: Boolean = true,
    // Ajout de la date de création
    val createdAt: Date = Date()
)
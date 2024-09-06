package org.mbds.unice.github.data.api

import org.mbds.unice.github.data.model.User
import java.util.*

object FakeApiServiceGenerator {

    @JvmField
    var FAKE_USERS = mutableListOf(
        User("001", "Jake", "https://xsgames.co/randomusers/assets/avatars/male/12.jpg"),
        User("002", "Paul", "https://xsgames.co/randomusers/assets/avatars/male/1.jpg"),
        User("003", "Phil", "https://xsgames.co/randomusers/assets/avatars/male/2.jpg"),
        User("004", "Guillaume", "https://xsgames.co/randomusers/assets/avatars/male/3.jpg"),
        User("005", "Francis", "https://xsgames.co/randomusers/assets/avatars/female/12.jpg"),
        User("006", "George", "https://xsgames.co/randomusers/assets/avatars/male/4.jpg"),
        User("007", "Louis", "https://xsgames.co/randomusers/assets/avatars/male/5.jpg"),
        User("008", "Mateo", "https://xsgames.co/randomusers/assets/avatars/male/6.jpg"),
        User("009", "April", "https://xsgames.co/randomusers/assets/avatars/male/7.jpg"),
        User("010", "Louise", "https://xsgames.co/randomusers/assets/avatars/female/6.jpg"),
        User("011", "Elodie", "https://xsgames.co/randomusers/assets/avatars/female/2.jpg"),
        User("012", "Helene", "https://xsgames.co/randomusers/assets/avatars/female/3.jpg"),
        User("013", "Fanny", "https://xsgames.co/randomusers/assets/avatars/female/4.jpg"),
        User("014", "Laura", "https://xsgames.co/randomusers/assets/avatars/female/5.jpg"),
        User("015", "Gertrude", "https://xsgames.co/randomusers/assets/avatars/female/6.jpg"),
        User("016", "Chloé", "https://xsgames.co/randomusers/assets/avatars/female/7.jpg"),
        User("017", "April", "https://xsgames.co/randomusers/assets/avatars/female/8.jpg"),
        User("018", "Marie", "https://xsgames.co/randomusers/assets/avatars/female/9.jpg"),
        User("019", "Henri", "https://xsgames.co/randomusers/assets/avatars/male/10.jpg"),
        User("020", "Rémi", "https://xsgames.co/randomusers/assets/avatars/male/11.jpg")
    )

    @JvmField
    var FAKE_USERS_RANDOM = Arrays.asList(
        User("021", "Lea", "https://xsgames.co/randomusers/assets/avatars/female/13.jpg"),
        User("022", "Geoffrey", "https://xsgames.co/randomusers/assets/avatars/male/13.jpg"),
        User("023", "Simon", "https://xsgames.co/randomusers/assets/avatars/male/14.jpg"),
        User("024", "André", "https://xsgames.co/randomusers/assets/avatars/male/15.jpg"),
        User("025", "Leopold", "https://xsgames.co/randomusers/assets/avatars/male/16.jpg")
    )
}
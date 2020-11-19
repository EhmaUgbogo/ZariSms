package com.ehmaugbogo.zarisms.model

/**
 * @author .: Ehma Ugbogo
 * @email ..: ehmaugbogo@gmail.com
 * @created : 2020-11-19
 */

data class User(
    var Id: String = "",
    var idToken: String = "", //for backend server
    var email: String = "",
    var displayName: String = "",
    var familyName: String = "",
    var photoUrl: String = "",
)
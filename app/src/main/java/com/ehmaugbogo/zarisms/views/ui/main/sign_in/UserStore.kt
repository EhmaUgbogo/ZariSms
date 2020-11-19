package com.ehmaugbogo.zarisms.views.ui.main.sign_in

import com.ehmaugbogo.zarisms.model.User
import com.ehmaugbogo.zarisms.util.Constant.DISPLAY_NAME
import com.ehmaugbogo.zarisms.util.Constant.EMAIL
import com.ehmaugbogo.zarisms.util.Constant.FAMILY_NAME
import com.ehmaugbogo.zarisms.util.Constant.ID
import com.ehmaugbogo.zarisms.util.Constant.ID_TOKEN
import com.ehmaugbogo.zarisms.util.Constant.PHOTO_URL
import com.ehmaugbogo.zarisms.util.mail_helper.AppUserMailCredentials
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.pixplicity.easyprefs.library.Prefs


/**
 * @author .: Ehma Ugbogo
 * @email ..: ehmaugbogo@gmail.com
 * @created : 2020-11-19
 */

object UserStore {

    fun initializeFrom(account: GoogleSignInAccount) {
        _storeUser = retrieveUser(account)
        AppUserMailCredentials.email = account.email!!
    }

    val storeUser get() = _storeUser


    //unexposed
    private var _storeUser: User
        get() = User(
            Id = Prefs.getString(ID, ""),
            idToken = Prefs.getString(ID_TOKEN, ""),
            email = Prefs.getString(EMAIL, ""),
            displayName = Prefs.getString(DISPLAY_NAME, ""),
            familyName = Prefs.getString(FAMILY_NAME, ""),
            photoUrl = Prefs.getString(PHOTO_URL, ""),
        )
        set(user) {
            Prefs.putString(ID, user.Id)
            Prefs.putString(ID_TOKEN, user.idToken)
            Prefs.putString(EMAIL, user.email)
            Prefs.putString(DISPLAY_NAME, user.displayName)
            Prefs.putString(FAMILY_NAME, user.familyName)
            Prefs.putString(PHOTO_URL, user.photoUrl)
        }

    private fun retrieveUser(acc: GoogleSignInAccount) =
        User(
            Id = acc.id!!,
            idToken = acc.idToken ?: "",
            email = acc.email!!,
            displayName = acc.displayName!!,
            familyName = acc.familyName!!,
            photoUrl = acc.photoUrl.toString(),
        )

    fun deactivate() {
        _storeUser = User()
        AppUserMailCredentials.deactivate()
    }

}

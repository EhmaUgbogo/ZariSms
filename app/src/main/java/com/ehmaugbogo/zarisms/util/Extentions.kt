package com.ehmaugbogo.zarisms.util

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.ehmaugbogo.zarisms.R
import com.ehmaugbogo.zarisms.views.ui.main.sign_in.UserStore
import com.google.android.material.snackbar.Snackbar


/**
 * @author .: Ehma Ugbogo
 * @email ..: ehmaugbogo@gmail.com
 * @created : 2020-11-17
 */

//Toast
fun Activity?.showToast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
fun Fragment.showToast(msg: String) =
    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()

//SnackBar
fun Activity.showSnackBar(msg: String) {
    Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT).show()
}

fun Fragment.showSnackBar(
    msg: String, actionText: String = "Ok",
    duration: Int = Snackbar.LENGTH_INDEFINITE, execute: (view: View) -> Unit
)
: Snackbar{
    return Snackbar.make(view!!, msg, duration)
        .setAction(actionText, execute).apply { show() }
}

//EditText
fun EditText.content() = this.text.toString().trim()

//ImageView
fun ImageView.load(context: Context, uri: String) {
    Glide.with(context).load(uri)
        //.placeholder(R.drawable.profile_pic)
        .into(this)
}

fun showView(show: Boolean = true, vararg views: View){
    views.forEach { it.isVisible = show }
}

//User
val Fragment.appUser get() = UserStore.storeUser
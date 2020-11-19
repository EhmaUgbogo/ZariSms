package com.ehmaugbogo.zarisms.util

import android.app.Activity
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ehmaugbogo.zarisms.R
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

fun Fragment.showSnackBar(msg: String, actionText: String = "Ok",
                          duration: Int = Snackbar.LENGTH_INDEFINITE,execute: (view: View) -> Unit)
: Snackbar{
    return Snackbar.make(view!!, msg, duration)
        .setAction(actionText, execute).apply { show() }
}


fun Activity.setMainTitle(msg: String) {
    findViewById<TextView>(R.id.nav_textView).text = msg
}

fun EditText.content(): String {
    return this.text.toString().trim()
}
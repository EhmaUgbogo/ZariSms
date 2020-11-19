package com.ehmaugbogo.zarisms.util

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import androidx.fragment.app.Fragment
import dmax.dialog.SpotsDialog

/**
 * @author .: Ehma Ugbogo
 * @email ..: ehmaugbogo@gmail.com
 * @created : 2020-11-18
 */


fun Activity.spotDialog(msg: String): AlertDialog {
    return SpotDialog(this, msg).dialog //shows & return AlertDialog
}

fun Fragment.spotDialog(msg: String): AlertDialog {
    return SpotDialog(requireContext(), msg).dialog //shows & return AlertDialog
}

private class SpotDialog(private val context: Context, msg: String) {
    val dialog: AlertDialog = SpotsDialog.Builder()
        .setContext(context)
        .setMessage(msg)
        .setCancelable(false)
        .build()
        .apply { show() }
}
package com.ehmaugbogo.zarisms.util.mail_helper

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.ehmaugbogo.rigotrak.utils.Validation
import com.ehmaugbogo.zarisms.R
import com.ehmaugbogo.zarisms.util.content
import com.ehmaugbogo.zarisms.util.showToast
import kotlinx.android.synthetic.main.dialog_sender_mail_details.view.*

/**
 * @author .: Ehma Ugbogo
 * @email ..: ehmaugbogo@gmail.com
 * @created : 2020-11-18
 */

class SenderDetailsDialog(private val activity: Activity, private val execute: () -> Unit) {
    fun show() {
        val view = activity.layoutInflater.inflate(R.layout.dialog_sender_mail_details, null, false)
        val dialog = Dialog(activity)
        dialog.setContentView(view)

        view.apply {
            my_mail_email.setText(AppUserMailCredentials.email)
            my_mail_password.setText(AppUserMailCredentials.password)

            proceed_btn.setOnClickListener {
                val email = my_mail_email.content()
                val password = my_mail_password.content()

                if (!validateFields(email, password)) return@setOnClickListener
                AppUserMailCredentials.email = email
                AppUserMailCredentials.password = password
                execute()
                dialog.cancel()
            }
        }

        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }


    private fun validateFields(email: String, password: String): Boolean {
        if (!Validation.isValidEmail(email)) {
            activity.showToast("invalid email"); return false
        }
        if (!Validation.isValidPassword(password)) {
            activity.showToast("password should be 6 characters or above"); return false
        }
        return true
    }
}
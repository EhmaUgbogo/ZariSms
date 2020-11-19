package com.ehmaugbogo.zarisms.views.ui

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import com.ehmaugbogo.rigotrak.utils.Validation.isValidEmail
import com.ehmaugbogo.rigotrak.utils.Validation.isValidName
import com.ehmaugbogo.zarisms.R
import com.ehmaugbogo.zarisms.util.mail_helper.SenderDetailsDialog
import com.ehmaugbogo.zarisms.util.content
import com.ehmaugbogo.zarisms.util.mail_helper.MailExecutors
import com.ehmaugbogo.zarisms.util.mail_helper.Result
import com.ehmaugbogo.zarisms.util.showSnackBar
import com.ehmaugbogo.zarisms.util.showToast
import com.ehmaugbogo.zarisms.util.spotDialog
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_mail.*


class MailFragment : Fragment(R.layout.fragment_mail) {
    private lateinit var mailExecutors: MailExecutors
    private var snackBar: Snackbar? = null


    override fun onAttach(context: Context) {
        mailExecutors = MailExecutors()
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //appExecutors = AppExecutors()

        mail_btn.setOnClickListener {
            if (!validateFields()) return@setOnClickListener

            val title = mail_title.content()
            val email = mail_destination.content()
            val body = mail_details.content()

            //Getting sender details first
            SenderDetailsDialog(requireActivity()){ // Details gotten
                sendMail(title, body, email)
            }.show()
        }

        observeMailContent()
    }

    private fun sendMail(title: String, body: String, email: String) {
        val spotsDialog = spotDialog("Sending Mail")
        mailExecutors.sendEmail(title, body, email) { result ->
            when(result){
                is Result.Success -> {
                    showToast(result.msg)
                    spotsDialog.dismiss()
                    requireActivity().onBackPressed()
                }
                is Result.Failure -> {
                    spotsDialog.dismiss()
                    sortMessage(result.error)
                }
            }
        }
    }

    private fun sortMessage(error: Throwable) {
        error.message?.let {
            if(it.contains("Username and Password not accepted")){
                showToast("Rejected credentials")
                val msg = "Process Obstructed: Grant app access to send mail from your google account settings"
                snackBar = showSnackBar(msg){
                    //visit website or navigate user to settings
                    snackBar?.dismiss()
                    snackBar = null
                }
            } else {
                showToast(it)
            }
        }
    }

    private fun observeMailContent() {
        mail_details.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val counter = s?.length ?: 0
                mail_details_counter.text = "$counter/160"
            }
        })
    }

    private fun validateFields(): Boolean {
        if (!isValidName(mail_title.content())) {
            showToast("Mail title is required"); return false
        }
        if (!isValidName(mail_details.content())) {
            showToast("Mail require a body"); return false
        }
        if (!isValidEmail(mail_destination.content())) {
            showToast("Recipient email required"); return false
        }
        return true
    }
}

package com.ehmaugbogo.zarisms.util.mail_helper

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.ehmaugbogo.zarisms.util.Constant.EMAIL
import com.ehmaugbogo.zarisms.util.Constant.PASSWORD
import com.pixplicity.easyprefs.library.Prefs
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

/**
 * @author .: Ehma Ugbogo
 * @email ..: ehmaugbogo@gmail.com
 * @created : 2020-11-17
 */


//@Singleton
open class MailExecutors(
    private val diskIO: Executor = Executors.newSingleThreadExecutor(),
    private val networkIO: Executor = Executors.newFixedThreadPool(3),
    private val mainThread: Executor = MainThreadExecutor()
) {

    private val TAG = "MailExecutors"

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }

    fun sendEmail(
        title: String,
        body: String,
        recipientEmail: String,
        execute: (result: Result) -> Unit
    ) {
        diskIO.execute {
            val properties = System.getProperties()
            properties.put("mail.smtp.host", "smtp.gmail.com")
            properties.put("mail.smtp.socketFactory.port", "465")
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
            properties.put("mail.smtp.auth", "true")
            properties.put("mail.smtp.port", "465")

            val session = Session.getInstance(properties,
                object : javax.mail.Authenticator() {
                    //Authenticating the password
                    override fun getPasswordAuthentication(): PasswordAuthentication {
                        return PasswordAuthentication(
                            AppUserMailCredentials.email,
                            AppUserMailCredentials.password
                        )
                    }
                })

            //Creating MimeMessage object
            val message = MimeMessage(session)
            message.setFrom(InternetAddress(AppUserMailCredentials.email)) //Setting sender address
            message.addRecipient(Message.RecipientType.TO, InternetAddress(recipientEmail)) //Adding receiver
            message.subject = title //Adding subject
            message.setText(body) //Adding message

            try {
                Transport.send(message) //Sending email
                mainThread.execute { //Something that should be executed on main thread.
                    execute(Result.Success("Mail sent successfully"))
                }

            } catch (e: MessagingException) {
                e.printStackTrace()
                mainThread.execute {
                    //Something that should be executed on main thread.
                    Log.d(TAG, e.message.orEmpty())
                    execute(Result.Failure(e))
                }
            }
        }
    }

}

// Make sure to set before use to avoid null crash
object AppUserMailCredentials {
    var email: String
        get() = Prefs.getString(EMAIL, "")
        set(value) = Prefs.putString(EMAIL, value)

    var password: String
        get() = Prefs.getString(PASSWORD, "")
        set(value) = Prefs.putString(PASSWORD, value)
}
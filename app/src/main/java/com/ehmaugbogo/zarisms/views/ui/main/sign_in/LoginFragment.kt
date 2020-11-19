package com.ehmaugbogo.zarisms.views.ui.main.sign_in

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.ehmaugbogo.zarisms.R
import com.ehmaugbogo.zarisms.util.navController
import com.ehmaugbogo.zarisms.util.navigateTo
import com.ehmaugbogo.zarisms.util.showToast
import com.ehmaugbogo.zarisms.util.showView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment(R.layout.fragment_login) {
    private val TAG = "LoginFragment"
    private val RC_SIGN_IN = 90

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkIfAlreadySignedIn()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sign_in_button.setSize(SignInButton.SIZE_WIDE) //SIZE_WIDE
        sign_in_button.setOnClickListener { signIn() }

    }

    private fun checkIfAlreadySignedIn() {
        val account = GoogleSignIn.getLastSignedInAccount(requireActivity())
        updateUI(account)
    }

    private fun updateUI(account: GoogleSignInAccount?) {
        account?.let {
            UserStore.initializeFrom(account)
            navigateTo(R.id.homeFragment, popUpToHome = true)
        }
    }

    private fun signIn() {
        val signInIntent: Intent = getSignInClient().signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
        showView(true, login_progress)
    }

    private fun getSignInClient(): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(requireActivity(), gso)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            showToast("Welcome ${account?.displayName}")
            updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.

            Log.w(TAG, "signInResult:failed code= ${e.statusCode} msg ${e.message}")

            showToast("Error signing in - ${e.message}")
            showView(false, login_progress)
            updateUI(null)
        }
    }

}

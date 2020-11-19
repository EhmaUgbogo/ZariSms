package com.ehmaugbogo.zarisms.views

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ehmaugbogo.zarisms.R
import com.ehmaugbogo.zarisms.util.showSnackBar
import com.ehmaugbogo.zarisms.util.spotDialog
import com.ehmaugbogo.zarisms.views.ui.main.home.createHomeItems
import com.ehmaugbogo.zarisms.views.ui.main.sign_in.UserStore
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.activity_main.*
import me.saket.cascade.CascadePopupMenu


class MainActivity : AppCompatActivity() {
    private val handler: Handler by lazy { Handler() }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setNavGraph()

        menu_btn.setOnClickListener { showAndDelaySpotDialog("Menu") {} }
        share_btn.setOnClickListener { showSnackBar("Share") }
        more_btn.setOnClickListener { logOut(it) }
        destinationListener()

    }

    private fun logOut(anchor: View) {
        val popupMenu = CascadePopupMenu(this, anchor)
        popupMenu.menu.apply {
            add("Sign Out").setIcon(R.drawable.ic_person_outline).setOnMenuItemClickListener {
                signOut()
                true
            }
            popupMenu.show()
        }
    }

    private fun signOut() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        mGoogleSignInClient.signOut().addOnCompleteListener(this) {
            showAndDelaySpotDialog("Signing Out", 1300) {
                navController.popBackStack(
                    R.id.homeFragment,
                    true
                ) //remove stack children including homeFragment
                navController.navigate(R.id.loginFragment)
                UserStore.deactivate()
            }
        }
    }

    private fun setNavGraph() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val graph = navController.navInflater.inflate(R.navigation.main_nav_graph)
        navHostFragment.navController.graph = graph

        //Not needed, just added because of animation
        //navigateTo(R.id.mainFragment)
    }

    private fun showAndDelaySpotDialog(msg: String, time: Long = 1000, run: () -> Unit) {
        val spotDialog = spotDialog(msg)
        handler.postDelayed({ spotDialog.dismiss(); run() }, time)
    }

    private fun destinationListener() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            materialCardView.isVisible = destination.id != R.id.loginFragment

            nav_textView.text = when (destination.id) {
                R.id.loginFragment -> ""
                R.id.homeFragment -> "Dash Board"
                else -> {
                    val openedScreen = createHomeItems().find { it.destination == destination.id }
                    openedScreen?.pageTitle!!
                }
            }
        }
    }


}

package com.ehmaugbogo.zarisms.views

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ehmaugbogo.zarisms.R
import com.ehmaugbogo.zarisms.util.navigateTo
import com.ehmaugbogo.zarisms.util.showSnackBar
import com.ehmaugbogo.zarisms.util.spotDialog
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val handler: Handler by lazy { Handler() }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setNavGraph()

        menu_btn.setOnClickListener { showAndDelaySpotDialog("Menu") }
        share_btn.setOnClickListener { showSnackBar("Share") }
        more_btn.setOnClickListener { showAndDelaySpotDialog("More Option") }

    }

    private fun setNavGraph() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val graph = navController.navInflater.inflate(R.navigation.main_nav_graph)
        navHostFragment.navController.graph = graph

        //Not needed, just added because of animation
        navigateTo(R.id.mainFragment)
    }

    private fun showAndDelaySpotDialog(msg: String) {
        val spotDialog = spotDialog(msg)
        handler.postDelayed({ spotDialog.dismiss() }, 1000)
    }

    private fun destinationListener() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.getId()) {
                /*R.id.nav_main_home, R.id.nav_notes, R.id.displayNoteFragment, R.id.newNoteFragment, R.id.profileFragment, R.id.editProfileFragment, R.id.contactFragment, R.id.nav_bookmark -> showView(
                    toolbar
                )
                R.id.nav_take_test, R.id.testCriteriaFragment, R.id.lifeLineFragment, R.id.takeTestFragment -> hideToolBar()
                else -> {
                }*/
            }
        }
    }


}

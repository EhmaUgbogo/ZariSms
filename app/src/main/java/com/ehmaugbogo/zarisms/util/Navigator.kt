package com.ehmaugbogo.zarisms.util

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.ehmaugbogo.zarisms.R


/**
 * @author .: Ehma Ugbogo
 * @email ..: ehmaugbogo@gmail.com
 * @created : 2020-11-18
 */


fun FragmentActivity.navigateTo(@IdRes destination: Int) {
    val navOptions = NavOptions.Builder()
        .setLaunchSingleTop(true)
        .setEnterAnim(R.anim.enter_from_right)
        .setExitAnim(R.anim.exit_to_right)
        .setPopEnterAnim(R.anim.enter_from_left)
        .setPopExitAnim(R.anim.exit_to_left)
        .build()

    val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    navHostFragment.navController.navigate(destination, null, navOptions)
}

fun FragmentActivity.navigateTo(destination: Fragment) {
    supportFragmentManager.beginTransaction()
        .setCustomAnimations(
            R.anim.enter_from_right, R.anim.exit_to_right,
            R.anim.enter_from_left, R.anim.exit_to_left
        )
        .replace(R.id.nav_host_fragment, destination)
        .commit()
}

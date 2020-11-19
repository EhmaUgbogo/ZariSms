package com.ehmaugbogo.zarisms.util

import android.app.Application
import android.content.ContextWrapper
import com.pixplicity.easyprefs.library.Prefs

/**
 * @author .: Ehma Ugbogo
 * @email ..: ehmaugbogo@gmail.com
 * @created : 2020-11-18
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()


        initPref()
    }

    private fun initPref() {
        Prefs.Builder().setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()
    }
}
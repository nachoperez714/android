package com.kotlin.kotlintest.LoginModule

import android.app.Application
import java.util.concurrent.TimeUnit
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import java.lang.ref.WeakReference


/**
 * Created by Codika on 14/11/2018.
 */

class LoginModule  {
  /*  lateinit var navigator : Navigator
    lateinit var splashActivity : SplashActivity
    private lateinit var receiver : BroadcastReceiver


    companion object Factory {
        fun create(): LoginModule = LoginModule()
    }

    fun start(nav : Navigator) {
        navigator = nav

        receiver = object : BroadcastReceiver() {
            override fun onReceive(contxt: Context?, intent: Intent?) {

                when (intent?.action) {
                    "STARTED_SPLASH_ACTIVITY" -> registerSplashActivity(intent.extras["activity"])
                }
            }
        }

        nav.app
                .registerReceiver(receiver, IntentFilter("STARTED_SPLASH_ACTIVITY"))


        /*timer(1500, TimeUnit.MILLISECONDS)
                //.subscribeOn(Schedulers.io())
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    nav.navigateToLogin()

                }, {
                    Timber.e("Error.")
                })*/


    }

    private fun registerSplashActivity(bundle : Any) {
        splashActivity = ((bundle as Bundle).getParcelable<SplashActivity>("splash"))
        splashActivity.toast()

    }
*/

}

package com.kotlin.kotlintest

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.kotlin.kotlintest.AseguradosModule.*
import com.kotlin.kotlintest.HomeModule.HomeActivity
import com.kotlin.kotlintest.HomeModule.HomeController
import com.kotlin.kotlintest.HomeModule.HomeView
import com.kotlin.kotlintest.LoginModule.*

/**
 * Created by Codika on 19/10/2018.
 */
interface ContextProvider {
    fun getActivityContext() : Context
}


class MyApplication : Application(), ContextProvider {

    lateinit var currentActivity: Activity
    lateinit var nav: Navigator
    lateinit var loginController : LoginController
    lateinit var aseguradosController : AseguradosController
    lateinit var loggedUser : User

    override fun getActivityContext(): Context {
        return currentActivity as Context
    }


    override fun onCreate() {
        super.onCreate()


        loginController = LoginController.create()
        loginController.setCallbacks(loginSuccess,legajoPicked)
        val homeController = HomeController.create()
        homeController.setCallbacks(homeOptionPicked)
        aseguradosController = AseguradosController.create()
        //aseguradosController.setCallbacks(aseguradoPicked,navigatoToPolizaList)
        nav = Navigator.create()
        nav.init(this)
        /* val loginModule = LoginModule.create()
         loginModule.start(navigator)*/

        registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                currentActivity = activity
                // (currentActivity as SplashActivity).toast()
            }

            override fun onActivityStarted(activity: Activity) {
                currentActivity = activity
                //  (currentActivity as SplashActivity).toast()
                when(currentActivity::class.java){


                    LogInActivity::class.java  -> {
                        val fragment = LoginFragment.newInstance()
                        (currentActivity as LogInActivity).attachFragment(fragment)
                        loginController.attachView(fragment as LoginView)
                    }

                    HomeActivity::class.java -> homeController.attachView(currentActivity as HomeView)

                    AseguradosActivity::class.java -> {
                        val fragment = AseguradosListFragment.newInstance()
                        (currentActivity as AseguradosActivity).attachFragment(fragment)
                        aseguradosController.attachView(fragment as AseguradosListView)
                    }



                }


            }

            override fun onActivityResumed(activity: Activity) {
                currentActivity = activity
            }

            override fun onActivityPaused(activity: Activity) {
                //currentActivity = null
            }

            override fun onActivityStopped(activity: Activity) {
                // don't clear current activity because activity may get stopped after
                // the new activity is resumed
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

            }

            override fun onActivityDestroyed(activity: Activity) {
                // don't clear current activity because activity may get destroyed after
                // the new activity is resumed
            }
        })

    }


    val loginSuccess : () -> Unit = {
        //nav.navigateToHome()
        val fragment = LegajoFragment.newInstance()
        nav.navigatoToLegajos(currentActivity,fragment)
        loginController.attachView(fragment)
    }

    val legajoPicked :(User, Legajo)-> Unit = {
        u: User, l: Legajo ->
        loggedUser = User(u.user,u.pass,l.numLegajo)
        nav.navigateToHome(u,l)
    }

    val homeOptionPicked :()-> Unit = {
        nav.navigateToAsegurados()
    }


}
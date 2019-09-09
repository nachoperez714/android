package com.kotlin.kotlintest

import android.app.Activity
import android.content.Intent
import android.support.v4.app.Fragment
import com.kotlin.kotlintest.AseguradosModule.*
import com.kotlin.kotlintest.HomeModule.HomeActivity
import com.kotlin.kotlintest.LoginModule.Legajo
import com.kotlin.kotlintest.LoginModule.LogInActivity
import com.kotlin.kotlintest.LoginModule.User


/**
 * Created by Codika on 15/11/2018.
 */

class Navigator {
    lateinit var app : MyApplication

    companion object Factory {
        fun create(): Navigator = Navigator()
    }

    fun init(application: MyApplication) {
        app = application
    }


    fun navigateToLogin() {
        app.startActivity(Intent(app, LogInActivity::class.java))

    }

    fun navigatoToLegajos(currentActivity: Activity, fragment: Fragment) {
        (currentActivity as LogInActivity).attachFragment(fragment)
    }

    fun navigateToHome(u: User, l: Legajo) {
        val intent = Intent(app, HomeActivity::class.java)
        intent.putExtra("user",u.user)
        intent.putExtra("pass",u.pass)
        intent.putExtra("legajo",l.numLegajo)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        app.startActivity(intent)
    }

    fun navigateToAsegurados() {
        val intent = Intent(app, AseguradosActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        app.startActivity(intent)
    }

    fun navigateToAseguradoDetalle(a: Activity, fragment: AseguradoDetailFragment) {
        (a as AseguradosActivity).attachFragment(fragment)
    }

    fun navigateToPolizaList(a: Activity, fragment: PolizaListFragment) {
        (a as AseguradosActivity).attachFragment(fragment)
    }


}
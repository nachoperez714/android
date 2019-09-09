package com.kotlin.kotlintest.LoginModule

import android.support.v7.app.AppCompatActivity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.kotlin.kotlintest.R


import kotlinx.android.synthetic.main.activity_login.*

/**
 * A login screen that offers login via email/password.
 */
class LogInActivity : AppCompatActivity() {

    lateinit var fragment : Fragment




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    fun attachFragment(f: Fragment) {
        fragment = f
        supportFragmentManager
                .beginTransaction()
                .replace(container.id,fragment)
                .commit()
    }

    public fun getCurrentFragment() : Fragment {
        return fragment
    }


}


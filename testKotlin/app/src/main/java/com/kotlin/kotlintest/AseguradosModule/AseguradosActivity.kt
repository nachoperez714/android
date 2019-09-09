package com.kotlin.kotlintest.AseguradosModule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.kotlin.kotlintest.R
import kotlinx.android.synthetic.main.activity_asegurados.*
import kotlinx.android.synthetic.main.activity_login.*

class AseguradosActivity : AppCompatActivity() {

    lateinit var fragment : Fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asegurados)

        setTitle("Asegurados")
    }

    fun attachFragment(f: Fragment) {
        fragment = f
        supportFragmentManager
                .beginTransaction()
                .add(container_asegurados.id,fragment)
                .addToBackStack(fragment.tag)
                .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.getBackStackEntryCount() > 1)
        {supportFragmentManager.popBackStackImmediate()}
        else finish()
    }
}

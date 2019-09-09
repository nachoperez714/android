package com.kotlin.kotlintest.HomeModule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kotlin.kotlintest.LoginModule.Legajo
import com.kotlin.kotlintest.LoginModule.User
import com.kotlin.kotlintest.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), HomeView {

    override fun setCallbacks(homeOptionPicked: (String) -> Unit) {
        this.homeOptionPicked = homeOptionPicked
    }

    lateinit var user : User
    lateinit var legajo : Legajo
    lateinit var homeOptionPicked : (String) -> Unit

/*    override fun loadList(lista: List<Any>, viewType: Int) {
        myList.loadList(lista, viewType)
    }*/

    override fun loadItemsCallback(getListItems: () -> List<Any>, viewType: Int) {
        myList.loadItemsCallback(getListItems, viewType, homeOptionPicked as (Any?)->Unit)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_home)

        var u = intent.getStringArrayExtra("user")
        var l = intent.getStringArrayExtra("pass")

        setTitle("Usuario con legajo: "+ intent.getIntExtra("legajo",0))

        }

}

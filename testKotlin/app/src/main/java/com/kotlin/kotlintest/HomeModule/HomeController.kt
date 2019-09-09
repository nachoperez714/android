package com.kotlin.kotlintest.HomeModule

import com.kotlin.kotlintest.R
import java.lang.Thread.sleep

/**
 * Created by Codika on 21/11/2018.
 */

class HomeController{

    //lateinit var navigator: Navigator
    lateinit var view : HomeView
    lateinit var homeOptionPicked : () -> Unit

    companion object Factory {
        fun create(): HomeController = HomeController()
    }

    fun attachView(nView: HomeView) {
        view = nView

        view.setCallbacks(onOptionClick)
        view.loadItemsCallback(getListItems, R.layout.string_item_layout)

    }

    val getListItems: () -> List<Any> = {
        sleep(3000)
         listOf<Any>("Asegurados", "Siniestros","Comisiones")
    }

    val onOptionClick: (String) -> Unit = {
        homeOptionPicked()
    }

    fun setCallbacks(homeOptionPicked: () -> Unit) {
        this.homeOptionPicked = homeOptionPicked
    }

/*    inner class getItemsTask(private val onTaskCompleted: (List<Any>)->Unit) : AsyncTask<Any, Any, Any>() {
        override fun doInBackground(vararg params: Any?): List<Any> {
            sleep(3000)
            //GET ITEMS FROM SERVICE
            return listOf<Any>("Tu mama jajajajaj", "Rape of nanking","Tu mama jajajajaj")
        }
        override fun onPostExecute(o: Any) {
            // your stuff
            onTaskCompleted(o as List<Any>)
        }
    }*/
}
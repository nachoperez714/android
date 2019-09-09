package com.kotlin.kotlintest.AseguradosModule

import com.kotlin.kotlintest.R

/**
 * Created by Codika on 14/12/2018.
 */
class AseguradosController {

    lateinit var aListView : AseguradosListView
    lateinit var aDetailView: AseguradoDetailView
    lateinit var pListView : PolizasListView
    //lateinit var aseguradoPicked : (AseguradoBrief) -> Unit
    //lateinit var navigatoToPolizaList : (AseguradoBrief) -> Unit


    companion object Factory {
        fun create(): AseguradosController = AseguradosController()
    }

    fun attachView(nView: AseguradosListView) {
        aListView = nView

        aListView.setCallbacks(aseguradoPicked)
        aListView.loadItemsCallback(getAsegurados, R.layout.asegurado_item_layout)
    }

    fun attachView(nView: AseguradoDetailView) {
        aDetailView = nView


        aDetailView.setCallbacks(navigatoToPolizaList)
    }

    fun attachView(nView: PolizasListView) {
        pListView = nView

        //pListView.setCallbacks(aseguradoPicked)
        pListView.loadItemsCallback(getPolizas, R.layout.poliza_item_layout)
    }

    val getAsegurados: () -> List<Any> = {
        Thread.sleep(3000)
        listOf<Any>(AseguradoBrief(1,"Menem", "0000001"), AseguradoBrief(134,"Chivilcoy", "36000000"), AseguradoBrief(999,"Jesus", "99111999"))
    }

    val getPolizas: () -> List<Any> = {
        //TODO pedir las polizas con respecto al aseguradoBrief
        Thread.sleep(3000)
        listOf<Any>(Poliza("poliza 1"),Poliza("poliza 2"))
    }

    val aseguradoPicked : (AseguradoBrief)->Unit = {
        a: AseguradoBrief ->
        val fragment = AseguradoDetailFragment.newInstance(a)
        //nav.navigateToAseguradoDetalle(currentActivity,fragment)
        (aListView.getOwnerActivity() as AseguradosActivity).attachFragment(fragment)
        attachView(fragment)
    }

    val navigatoToPolizaList : (AseguradoBrief)->Unit = {
        a: AseguradoBrief ->
        val fragment = PolizaListFragment.newInstance(a)
        (aDetailView.getOwnerActivity() as AseguradosActivity).attachFragment(fragment)
        attachView(fragment)
    }

}
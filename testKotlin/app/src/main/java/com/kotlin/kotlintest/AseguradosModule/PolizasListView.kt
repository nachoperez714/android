package com.kotlin.kotlintest.AseguradosModule

/**
 * Created by Codika on 18/12/2018.
 */
interface PolizasListView {
    fun setCallbacks(polizaPicked: (Poliza) -> Unit)
    fun loadItemsCallback(listItems: () -> List<Any>, viewType: Int)
}
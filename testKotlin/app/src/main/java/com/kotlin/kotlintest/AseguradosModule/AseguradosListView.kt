package com.kotlin.kotlintest.AseguradosModule

import android.app.Activity

/**
 * Created by Codika on 14/12/2018.
 */
interface AseguradosListView {
    fun loadItemsCallback(listItems: () -> List<Any>, viewType: Int)
    fun setCallbacks(aseguradoPicked: (AseguradoBrief) -> Unit)
    fun getOwnerActivity(): Activity
}
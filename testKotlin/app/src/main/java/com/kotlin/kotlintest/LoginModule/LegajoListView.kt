package com.kotlin.kotlintest.LoginModule

import android.content.Context

/**
 * Created by Codika on 28/11/2018.
 */

interface LegajoListView {
    fun setCallbacks(onLegajoClick: (Legajo) -> Unit) {}
    fun getViewContext(): Context?
    fun loadItemsCallback(getListItems: () -> List<Any>, viewType: Int)
}


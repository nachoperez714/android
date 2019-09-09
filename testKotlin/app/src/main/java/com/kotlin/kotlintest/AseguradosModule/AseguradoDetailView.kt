package com.kotlin.kotlintest.AseguradosModule

import android.app.Activity

/**
 * Created by Codika on 18/12/2018.
 */
interface AseguradoDetailView {
    fun setCallbacks(navigatoToPolizaList: (AseguradoBrief) -> Unit)
    fun getOwnerActivity(): Activity
}
package com.kotlin.kotlintest.LoginModule

import android.content.Context

/**
 * Created by Codika on 20/11/2018.
 */

interface LoginView{
    fun toast()
    //fun setCallbacks(onClickLogIn: Unit)
    fun setCallbacks(onClickLogIn: (String, String) -> Unit, onClickForgotPass: () -> Unit) {}
    fun startLoading()
    fun finishLoading()
    fun alert(title : String, message : String)
    fun getViewContext(): Context?
}

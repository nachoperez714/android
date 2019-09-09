package com.kotlin.kotlintest.LoginModule

import android.os.AsyncTask
import android.widget.Toast
import com.github.kittinunf.result.Result
import com.kotlin.kotlintest.Navigator
import com.kotlin.kotlintest.R
import java.lang.Thread.sleep
import java.util.regex.Pattern

/**
 * Created by Codika on 20/11/2018.
 */

class LoginController {

    lateinit var onLoginSuccess: () -> Unit
    lateinit var onLegajoPicked: (User, Legajo) -> Unit


    lateinit var navigator: Navigator
    lateinit var view : LoginView
    lateinit var legajoView: LegajoListView
    lateinit var user : User


    companion object Factory {
        fun create(): LoginController = LoginController()
    }

     fun attachView(nView: LoginView) {
        view = nView
        //toast()
         if(view is LoginFragment)
         view.setCallbacks(onClickLogIn,onClickForgotPass)
    }

    fun attachView(nView: LegajoListView) {
        legajoView = nView
        //toast()
        //Callbacks
        legajoView.setCallbacks(onLegajoClick)
        legajoView.loadItemsCallback(getListItems, R.layout.legajo_list_item)
        //}.execute()
    }

    val getListItems: () -> List<Any> = {
        Thread.sleep(3000)
        List(30,{
            index ->
            Legajo(index)
        })
    }

    val onLegajoClick: (Legajo) -> Unit = {
        l: Legajo ->
        //Toast.makeText(legajoView.getViewContext(),"Legajo: "+ l.numLegajo,Toast.LENGTH_LONG).show()
        onLegajoPicked(user,l)
    }

    val onClickForgotPass: ()->Unit = {
        Toast.makeText(view.getViewContext(),"FORGOT PASSWORD", Toast.LENGTH_LONG).show()
    }


    val onClickLogIn: (String, String) -> Unit = {
        user: String, pass: String ->

        if(isEmailValid(user)){
            view.startLoading()
            YourTask(OnTaskCompleted).execute(user,pass)
        } else{
            if(pass.isEmpty()){
                view.alert("Contraseña vacia", "Por favor ingrese contraseña")
            }else {
                view.alert("Email invalido", "Ingrese un Email valido")
            }
        }

    }



    fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }

    val OnTaskCompleted : () -> Unit = {
        view.finishLoading()
        //Toast.makeText(view as Context, "ERROR", Toast.LENGTH_LONG).show()
        onLoginSuccess()
    }




    fun toast(){
        view.toast()
    }

    inner class YourTask(private val onTaskCompleted: ()->Unit) : AsyncTask<Any, Any, Any>() {

        override fun doInBackground(vararg params: Any?): Result<User, Exception> {
            //pega servicio
            return FakeAuthService().login(params[0] as String, params[1] as String)

        }

        // required methods

        override fun onPostExecute(o: Any) {
            // your stuff
            user = (o as Result<User,Exception>).get()
            onTaskCompleted()
        }
    }

    fun setCallbacks(loginSuccess: () -> Unit, legajoPicked: (User, Legajo) ->Unit) {
        onLoginSuccess = loginSuccess
        onLegajoPicked = legajoPicked

    }

}


class User(val pass: String, val user: String, var legajo: Int = 0) {
}

interface AuthService {
    fun login(email: String, pass: String) : Result<User,Exception>
}

class FakeAuthService : AuthService {
    override fun login(user: String, pass: String) : Result<User,Exception> {
        sleep(3000L)
        val user = User(user, pass)
        //return Result.Success(user)

        return Result.of(user)
        }
    }

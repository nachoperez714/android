package com.kotlin.kotlintest.LoginModule

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kotlin.kotlintest.R


import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment(), LoginView {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity!!.setTitle("Login")
        init()
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        /*if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }*/
    }

    override fun onDetach() {
        super.onDetach()
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {


        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }

    override fun getViewContext(): Context? {
        return context
    }

    override fun alert(title: String, message: String) {
        val builder = AlertDialog.Builder(context)

        // Set the alert dialog title
        builder.setTitle(title)

        // Display a message on alert dialog
        builder.setMessage(message)


        // Display a neutral button on alert dialog
        builder.setNeutralButton("OK"){_,_ ->
        }

        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }

    override fun finishLoading() {
           login_progress.visibility = View.GONE //To change body of created functions use File | Settings | File Templates.
    }

    override fun startLoading() {
         login_progress.visibility = View.VISIBLE
    }

    lateinit var onClickLogIn : (String, String) -> Unit
    lateinit var onClickForgotPass : () -> Unit

    override fun setCallbacks(onClickLogIn: (String, String) -> Unit, onClickForgotPass: () -> Unit) {
        this.onClickLogIn = onClickLogIn
        this.onClickForgotPass = onClickForgotPass

    }

    fun init() {
        sign_in_button.setOnClickListener({
            onClickLogIn(user.text.toString(),password.text.toString())
        })
        //sign_in_button.setOnClickListener({Toast.makeText(this,"HOLA HOLA",Toast.LENGTH_LONG).show() })
        btn_forgot_password.setOnClickListener({
            onClickForgotPass()
        })
    }

    override fun toast() {
        Toast.makeText(context,"HOLA HOLA", Toast.LENGTH_LONG)
        //  btn_forgot_password.setText("HOLA HOLA")
    }
}// Required empty public constructor

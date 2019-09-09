package com.kotlin.kotlintest.dummy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.gonanimationlib.Animate
import com.gonanimationlib.Effect
import com.kotlin.kotlintest.MyApplication
import com.kotlin.kotlintest.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Glide.with(this).load(R.drawable.sp_jojo).into(jojo)
        Glide.with(this).load(R.drawable.zawarudo_dio).into(dio)
        Animate.with(Effect.TRANSLATE_FROM_BOTTOM).during(3000).startAnimation(jojo)
        Animate.with(Effect.TRANSLATE_FROM_UP).during(3000).startAnimation(dio)



      Timer().schedule(4000){
            //val intent = Intent(this@SplashActivity,MainActivity::class.java)
            //startActivity(intent)
            //finish()
          navigateToHome()
        }

    }

    private fun navigateToHome() {
        val app : MyApplication
        app = application as MyApplication
        //app.navigatoToHome()
    }

}

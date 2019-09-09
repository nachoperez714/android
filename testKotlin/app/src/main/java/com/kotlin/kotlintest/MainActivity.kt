package com.kotlin.kotlintest

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main2.*
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Button
import java.io.FileNotFoundException
import android.util.TypedValue
import android.view.ViewGroup
import com.kotlin.kotlintest.dummy.photoEditActivity


class MainActivity : AppCompatActivity() {

    val button : Button? = null


    fun View.margin(left: Float? = null, top: Float? = null, right: Float? = null, bottom: Float? = null) {
        layoutParams<ViewGroup.MarginLayoutParams> {
            left?.run { leftMargin = dpToPx(this) }
            top?.run { topMargin = dpToPx(this) }
            right?.run { rightMargin = dpToPx(this) }
            bottom?.run { bottomMargin = dpToPx(this) }
        }
    }

    inline fun <reified T : ViewGroup.LayoutParams> View.layoutParams(block: T.() -> Unit) {
        if (layoutParams is T) block(layoutParams as T)
    }

    fun View.dpToPx(dp: Float): Int = context.dpToPx(dp)
    fun Context.dpToPx(dp: Float): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       /* setContentView(R.layout.activity_main2)

        val layout = findViewById<ConstraintLayout>(R.id.layout) as ConstraintLayout
        val button = Button(this)
        button.id = R.id.button
        button.layoutParams = ConstraintLayout.LayoutParams(0, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        button.margin(right=200f)
        button.setText(R.string.btn_text)
        button.setBackgroundColor(resources.getColor(R.color.black))
        button.setPadding(5,5,5,5)
        layout.addView(button)

        ivTitle.layoutParams = ConstraintLayout.LayoutParams(0, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        //LayoutHelper.setXForViewInParent(button,150F)
        //LayoutHelper.setYForViewInParent(button,150F)
        //LayoutHelper.setViewBelowOtherView(view=button,otherView=ivTitle)
        //LayoutHelper.setViewOverOtherView(button,ivTitle,10)
        //LayoutHelper.setViewToTheRightOfOtherView(button,ivTitle,10)
        //LayoutHelper.setViewToTheLeftOfOtherView(button,ivTitle,10)
        //LayoutHelper.setViewInCenter(button,ivTitle)
        //LayoutHelper.setViewInCenterH(view=button,otherView=ivTitle)
        //LayoutHelper.setViewInCenterV(button,ivTitle,layout)
        //LayoutHelper.setViewInXY(button,200f,0f)
        LayoutHelper.setBottomOfParent(button)
        //LayoutHelper.setTopOfParent(ivTitle)
        LayoutHelper.setStartOfParent(button)
        LayoutHelper.setEndOfParent(ivTitle)
        LayoutHelper.setBottomOfParent(ivTitle)

        val constraintSet = ConstraintSet()
        constraintSet.clone(layout)
        constraintSet.createHorizontalChain(layout.id,ConstraintSet.LEFT,layout.id,ConstraintSet.RIGHT,intArrayOf(button.id, ivTitle.id), floatArrayOf(0.5f,0.5f),ConstraintSet.CHAIN_SPREAD)
        constraintSet.applyTo(layout)





        //Animate.with(Effect.BUBBLE_APPEAR_LEFT).during(1000).onEnd { Animate.with(Effect.ALPHA).value(1F).during(500).startAnimation(button) }.startAnimation(ivTitle)
        button.setOnClickListener(View.OnClickListener { (arg) ->

            val constraintSet = ConstraintSet()
            constraintSet.clone(layout)
            constraintSet.connect(button.id, ConstraintSet.BOTTOM, layout.id, ConstraintSet.BOTTOM, 0)
            constraintSet.connect(button.id, ConstraintSet.RIGHT, layout.id, ConstraintSet.RIGHT, 0)
            constraintSet.applyTo(layout)
            //navigateToSplash();
/*                val intent = Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, 0)*/
         })*/



    }

    private fun navigateToSplash() {
        val app : MyApplication
        app = application as MyApplication
        //app.navigatoToSplash()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            val targetUri = data.data
            val bitmap: Bitmap
            try {
                bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(targetUri))
                foto.setImageBitmap(bitmap)
                fotoClickHandler(targetUri)
                //startEditActivity(bitmap)
            } catch (e: FileNotFoundException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }
        }
    }

    private fun fotoClickHandler(uri: Uri) {
        foto.isClickable
        foto.setOnClickListener({startEditActivity(uri)})
    }

    private fun startEditActivity(uri: Uri) {
        val intent = Intent(this@MainActivity, photoEditActivity::class.java)
        intent.putExtra("foto",uri)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun tuVieja(a : Int,b : Int): Int {
        return a+b
    }

    fun pepe(a: Int) : ((Int) -> Int) {
        return {b: Int -> a+b}
    }
}


private operator fun Any.component1() {}

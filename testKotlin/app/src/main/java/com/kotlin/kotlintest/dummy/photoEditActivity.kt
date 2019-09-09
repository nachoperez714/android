package com.kotlin.kotlintest.dummy

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.gonanimationlib.Animate
import com.gonanimationlib.Effect
import ja.burhanrashid52.photoeditor.PhotoEditor
import kotlinx.android.synthetic.main.activity_photo_edit.*


class photoEditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_photo_edit)


        val uri = intent.extras["foto"]
        val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(uri as Uri?))

        editable.source.setImageBitmap(bitmap)
        val editor = PhotoEditor.Builder(this,editable).build()

    }
}

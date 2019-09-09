package com.kotlin.kotlintest.LoginModule

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.kotlin.kotlintest.R

/**
 * Created by Codika on 20/12/2018.
 */
class KeyValueCellView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0,
        defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    init {
        LayoutInflater.from(context)
                .inflate(R.layout.key_value_cell, this, true)
    }


}
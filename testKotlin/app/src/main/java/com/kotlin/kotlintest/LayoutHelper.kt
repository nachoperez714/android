package com.kotlin.kotlintest

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.view.View

/**
 * Created by Codika on 23/10/2018.
 */

object LayoutHelper {


    fun setXForViewInParent(view: View, x: Float) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(view.parent as ConstraintLayout)
        constraintSet.setTranslationX(view.id, x)
        constraintSet.applyTo(view.parent as ConstraintLayout)
    }

    fun setYForViewInParent(view: View, y: Float) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(view.parent as ConstraintLayout)
        constraintSet.setTranslationY(view.id, y)
        constraintSet.applyTo(view.parent as ConstraintLayout)
    }

    fun setViewInXY(view: View, x: Float, y: Float) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(view.parent as ConstraintLayout)
        constraintSet.setTranslation(view.id,x,y)
        constraintSet.applyTo(view.parent as ConstraintLayout)
    }

    fun setViewBelowOtherView(view: View, otherView: View, margin: Int = 0) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(view.parent as ConstraintLayout)
        constraintSet.connect(view.id, ConstraintSet.TOP, otherView.id, ConstraintSet.BOTTOM, margin)
        constraintSet.applyTo(view.parent as ConstraintLayout)
    }


    fun setViewOverOtherView(view: View, otherView: View, margin: Int = 0) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(view.parent as ConstraintLayout)
        constraintSet.connect(view.id, ConstraintSet.BOTTOM, otherView.id, ConstraintSet.TOP, margin)
        constraintSet.applyTo(view.parent as ConstraintLayout)
    }

    fun setViewToTheRightOfOtherView(view: View, otherView: View, margin: Int = 0) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(view.parent as ConstraintLayout)
        constraintSet.connect(view.id, ConstraintSet.LEFT, otherView.id, ConstraintSet.RIGHT, margin)
        constraintSet.applyTo(view.parent as ConstraintLayout)
    }

    fun setViewToTheLeftOfOtherView(view: View, otherView: View, margin: Int = 0) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(view.parent as ConstraintLayout)
        constraintSet.connect(view.id, ConstraintSet.RIGHT, otherView.id, ConstraintSet.LEFT, margin)
        constraintSet.applyTo(view.parent as ConstraintLayout)
    }

    fun setViewInCenter(view: View, otherView: View) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(view.parent as ConstraintLayout)
        constraintSet.centerHorizontally(view.id,otherView.id)
        constraintSet.centerVertically(view.id,otherView.id)
        constraintSet.applyTo(view.parent as ConstraintLayout)
    }

    fun setViewInCenterH( view: View, otherView: View) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(view.parent as ConstraintLayout)
        constraintSet.centerHorizontally(view.id,otherView.id)
        constraintSet.applyTo(view.parent as ConstraintLayout)
    }

    fun setViewInCenterV(view: View, otherView: View) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(view.parent as ConstraintLayout)
        constraintSet.centerVertically(view.id,otherView.id)
        constraintSet.applyTo(view.parent as ConstraintLayout)
    }

    fun setBottomOfParent(view: View,margin: Int = 0) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(view.parent as ConstraintLayout)
        val parent = view.parent as ConstraintLayout
        constraintSet.connect(view.id,ConstraintSet.BOTTOM, parent.id,ConstraintSet.BOTTOM,margin)
        constraintSet.applyTo(view.parent as ConstraintLayout)
    }

    fun setTopOfParent(view: View,margin: Int = 0) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(view.parent as ConstraintLayout)
        val parent = view.parent as ConstraintLayout
        constraintSet.connect(view.id,ConstraintSet.TOP, parent.id,ConstraintSet.TOP,margin)
        constraintSet.applyTo(view.parent as ConstraintLayout)
    }

    fun setStartOfParent(view: View,margin: Int = 0) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(view.parent as ConstraintLayout)
        val parent = view.parent as ConstraintLayout
        constraintSet.connect(view.id,ConstraintSet.LEFT, parent.id,ConstraintSet.LEFT,margin)
        constraintSet.applyTo(view.parent as ConstraintLayout)
    }

    fun setEndOfParent(view: View,margin: Int = 0) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(view.parent as ConstraintLayout)
        val parent = view.parent as ConstraintLayout
        constraintSet.connect(view.id,ConstraintSet.RIGHT, parent.id,ConstraintSet.RIGHT,margin)
        constraintSet.applyTo(view.parent as ConstraintLayout)
    }

}

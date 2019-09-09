package com.gonanimationlib;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.gonanimationlib.interpolators.SpringInterpolator;

import java.util.ArrayList;
import java.util.List;

import static android.view.MotionEvent.INVALID_POINTER_ID;

/**
 * Created by leandro on 9/4/18.
 */

public enum Effect {

    ALPHA {
        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {
            if (value < 0) value = 0;
            ObjectAnimator anim = ObjectAnimator.ofFloat(view, View.ALPHA, value);
            anim.setDuration(duration);
            anim.setStartDelay(delay);
            anim.setInterpolator(interpolator);
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    listener.onAnimationStart(null);
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    listener.onAnimationEnd(null);
                }

                @Override
                public void onAnimationCancel(Animator animator) {}

                @Override
                public void onAnimationRepeat(Animator animator) {
                    listener.onAnimationRepeat(null);
                }
            });
            anim.start();
        }
    },

    COLOR {
        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {
            int color = Color.WHITE;
            Drawable background = view.getBackground();
            if (background instanceof ColorDrawable)
                color = ((ColorDrawable) background).getColor();

            ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), color, (int) value);
            colorAnimation.setDuration(duration);
            colorAnimation.setStartDelay(delay);
            colorAnimation.addUpdateListener(valueAnim -> view.setBackgroundColor((int) valueAnim.getAnimatedValue()));
            colorAnimation.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    listener.onAnimationStart(null);
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    listener.onAnimationEnd(null);
                }

                @Override
                public void onAnimationCancel(Animator animator) {}

                @Override
                public void onAnimationRepeat(Animator animator) {
                    listener.onAnimationRepeat(null);
                }
            });
            colorAnimation.start();
        }
    },

    // region bubbles

    BUBBLE {
        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {
            if (value <= 0) value = 0.96f;

            AnimatorSet animationSet = new AnimatorSet();
            animationSet.setInterpolator(new AccelerateDecelerateInterpolator());

            view.setLayerType(View.LAYER_TYPE_HARDWARE, null);

            final PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, value);
            final PropertyValuesHolder scaleXDone = PropertyValuesHolder.ofFloat(View.SCALE_X, 1);
            final PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, value);
            final PropertyValuesHolder scaleYDone = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1);

            animationSet.playSequentially(ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY),
                                        ObjectAnimator.ofPropertyValuesHolder(view, scaleXDone, scaleYDone));
            animationSet.setDuration(duration);
            animationSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    view.setLayerType(View.LAYER_TYPE_NONE, null);
                    listener.onAnimationEnd(null);
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                    listener.onAnimationRepeat(null);
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    listener.onAnimationStart(null);
                }
            });

            animationSet.setStartDelay(delay);
            animationSet.start();
        }
    },

    BUBBLE_APPEAR_CENTER {
        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {
            this.bubbleAppear(duration, delay, repeat, view, new SpringInterpolator(0.3, 7), R.anim.bubble_appear_center, listener);
        }
    },

    BUBBLE_APPEAR_BOTTOM {
        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {
            this.bubbleAppear(duration, delay, repeat, view, new SpringInterpolator(0.3, 7), R.anim.bubble_appear_bottom, listener);
        }
    },

    BUBBLE_DESAPPEAR_BOTTOM {
        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {
            this.bubbleAppear(duration, delay, repeat, view, new AccelerateDecelerateInterpolator(), R.anim.bubble_desappear_bottom, listener);
        }
    },

    BUBBLE_APPEAR_LEFT {
        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {
            this.bubbleAppear(duration, delay, repeat, view, new SpringInterpolator(0.3, 7), R.anim.bubble_appear_left, listener);
        }
    },

    BUBBLE_DESAPPEAR_LEFT {
        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {
            this.bubbleAppear(duration, delay, repeat, view, new AccelerateDecelerateInterpolator(), R.anim.bubble_desappear_left, listener);
        }
    },

    BUBBLE_APPEAR_RIGTH {
        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {
            this.bubbleAppear(duration, delay, repeat, view, new SpringInterpolator(0.3, 7), R.anim.bubble_appear_right, listener);
        }
    },

    BUBBLE_DESAPPEAR_RIGTH {
        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {
            this.bubbleAppear(duration, delay, repeat, view, new AccelerateDecelerateInterpolator(), R.anim.bubble_desappear_right, listener);
        }
    },

    BUBBLE_APPEAR_TOP {
        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {
            this.bubbleAppear(duration, delay, repeat, view, new SpringInterpolator(0.3, 7), R.anim.bubble_appear_top, listener);
        }
    },

    BUBBLE_DESAPPEAR_TOP {
        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {
            this.bubbleAppear(duration, delay, repeat, view, new AccelerateDecelerateInterpolator(), R.anim.bubble_desappear_top, listener);
        }
    },

    BUBBLE_DESAPPEAR_CENTER {
        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {
            this.bubbleAppear(duration, delay, repeat, view, new AccelerateDecelerateInterpolator(), R.anim.bubble_desappear_center, listener);
        }
    },

    // endregion

    //region measure

    HEIGHT {
        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {
            measureChange(duration, delay, (int) value, view, true, listener);
        }
    },

    WIDTH {
        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {
            measureChange(duration, delay, (int) value, view, false, listener);
        }
    },

    // endregion

    // region rotations

    ROTATE_INFINITE {
        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {
            rotate(duration, value < 0 ? 360 : -360, delay, Animation.INFINITE, view, listener);
        }
    },

    ROTATE {
        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {
            rotate(duration, value, delay, repeat, view, listener);
        }
    },

    // endregion

    // region translation

    TRANSLATE_FROM_UP_LEFT {
        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {
            translate(duration, delay, repeat, view, interpolator, listener,
                    Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_SELF, 0,
                    Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_SELF, 0);
        }
    },

    TRANSLATE_FROM_UP {
        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {
            translate(duration, delay, repeat, view, interpolator, listener,
                    Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_SELF, 0,
                    Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_SELF, 0);
        }
    },

    TRANSLATE_FROM_UP_RIGTH {
        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {
            translate(duration, delay, repeat, view, interpolator, listener,
                    Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_SELF, 0,
                    Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_SELF, 0);
        }
    },

    TRANSLATE_FROM_LEFT {
        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {
            translate(duration, delay, repeat, view, interpolator, listener,
                    Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_SELF, 0,
                    Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_SELF, 0);
        }
    },

    TRANSLATE_FROM_RIGTH {
        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {
            translate(duration, delay, repeat, view, interpolator, listener,
                    Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_SELF, 0,
                    Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_SELF, 0);
        }
    },

    TRANSLATE_FROM_BOTTOM_LEFT {
        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {
            translate(duration, delay, repeat, view, new SpringInterpolator(), listener,
                    Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_SELF, 0,
                    Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_SELF, 0);
        }
    },

    TRANSLATE_FROM_BOTTOM {
        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {
            translate(duration, delay, repeat, view, interpolator, listener,
                    Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_SELF, 0,
                    Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_SELF, 0);
        }
    },

    TRANSLATE_FROM_BOTTOM_RIGTH {
        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {
            translate(duration, delay, repeat, view, interpolator, listener,
                    Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_SELF, 0,
                    Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_SELF, 0);
        }
    },

    TRANSLATE {
        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {

            final PropertyValuesHolder translationX = PropertyValuesHolder.ofFloat(View.X, (value - view.getWidth()/2));
            final PropertyValuesHolder translationY = PropertyValuesHolder.ofFloat(View.Y, (secondaryValue - view.getHeight()/2));
            view.setLayerType(View.LAYER_TYPE_HARDWARE, null);

            AnimatorSet animationSet = new AnimatorSet();
            animationSet.playTogether(ObjectAnimator.ofPropertyValuesHolder(view, translationX, translationY));
            animationSet.setDuration(duration);
            animationSet.setInterpolator(interpolator);
            animationSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    view.setLayerType(View.LAYER_TYPE_NONE, null);
                }
            });
            animationSet.start();
        }
    },
    // endregion

    ZUMB {
        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {

            Animation stretch = new ScaleAnimation(1, 1.2f, 1, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            stretch.setDuration(200);
            stretch.setInterpolator(new AnticipateInterpolator(4));


            Animation rotateInitMid = new RotateAnimation(0, 6, Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            rotateInitMid.setInterpolator(new CycleInterpolator(3));
            rotateInitMid.setDuration(400);
            rotateInitMid.setStartOffset(200);


            Animation back = new ScaleAnimation(1.2f, 1, 1.2f, 1, Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            back.setDuration(100);
            back.setStartOffset(600);
            back.setInterpolator(new LinearInterpolator());


            AnimationSet set = new AnimationSet(false);
            set.addAnimation(stretch);
            set.addAnimation(rotateInitMid);
            set.addAnimation(back);

            set.setAnimationListener(listener);
            set.setFillAfter(true);
            view.startAnimation(set);
        }
    },

    QUIT_DRAGGABLE {
        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {
            view.setOnTouchListener(null);
        }
    },

    DRAGGABLE_X_Y {

        private int mActivePointerId = INVALID_POINTER_ID;

        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {

            view.setOnTouchListener((v, ev) -> {
                switch (ev.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mActivePointerId = ev.getPointerId(0);
                        break;
                    case MotionEvent.ACTION_MOVE:

                        if (mActivePointerId == INVALID_POINTER_ID) return false;

                        int pointerIndexMove = ev.findPointerIndex(mActivePointerId);
                        float x = ev.getX(pointerIndexMove);
                        float y = ev.getY(pointerIndexMove);

                        view.setX(v.getX() - v.getWidth()/2 + x);
                        view.setY(v.getY() - v.getHeight()/2 + y);
                        break;
                    case MotionEvent.ACTION_POINTER_UP:

                        final int pointerIndexUp = ev.getActionIndex();
                        final int pointerId = ev.getPointerId(pointerIndexUp);

                        if (pointerId == mActivePointerId) {
                            mActivePointerId = INVALID_POINTER_ID;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        mActivePointerId = INVALID_POINTER_ID;
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        mActivePointerId = INVALID_POINTER_ID;
                        break;
                }
                v.performClick();
                return true;
            });
        }
    },

    DRAGGABLE {

        private int mActivePointerId = INVALID_POINTER_ID;
        private float mLastTouchX;
        private float mLastTouchY;

        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {

            view.setOnTouchListener((v, ev) -> {
                switch (ev.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        int pointerIndex = ev.getActionIndex();
                        float x = ev.getX(pointerIndex);
                        float y = ev.getY(pointerIndex);

                        mLastTouchX = x;
                        mLastTouchY = y;

                        mActivePointerId = ev.getPointerId(0);

                        view.setScaleX(1.2f);
                        view.setScaleY(1.2f);

                        break;

                    case MotionEvent.ACTION_MOVE:
                        int pointerIndexMove = ev.findPointerIndex(mActivePointerId);

                        float xMove = ev.getX(pointerIndexMove);
                        float yMove = ev.getY(pointerIndexMove);

                        float dx = xMove - mLastTouchX;
                        float dy = yMove - mLastTouchY;

                        view.setX(v.getX() + dx);
                        view.setY(v.getY() + dy);

                        mLastTouchX = xMove;
                        mLastTouchY = yMove;

                        break;

                    case MotionEvent.ACTION_UP:
                        mActivePointerId = INVALID_POINTER_ID;

                        view.setScaleX(1);
                        view.setScaleY(1);

                        break;

                    case MotionEvent.ACTION_CANCEL:
                        mActivePointerId = INVALID_POINTER_ID;
                        break;

                    case MotionEvent.ACTION_POINTER_UP:

                        final int pointerIndexUp = ev.getActionIndex();
                        final int pointerId = ev.getPointerId(pointerIndexUp);

                        if (pointerId == mActivePointerId) {
                            final int newPointerIndex = pointerIndexUp == 0 ? 1 : 0;
                            mLastTouchX = ev.getX(newPointerIndex);
                            mLastTouchY = ev.getY(newPointerIndex);
                            mActivePointerId = ev.getPointerId(newPointerIndex);
                        }
                        break;
                }
                v.performClick();
                return true;
            });
        }
    },

    SCALE {
        @Override
        public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {
            if (value < 0) value = 1;
            if (secondaryValue < 0) secondaryValue = 1;

            Animation stretch = new ScaleAnimation(secondaryValue, value, secondaryValue, value, Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            stretch.setDuration(duration);
            stretch.setInterpolator(interpolator);
            stretch.setFillAfter(true);
            stretch.setAnimationListener(listener);
            view.startAnimation(stretch);
        }
    },

    ;


    public void animate(long duration, int delay, int repeat, float value, float secondaryValue, View view, Interpolator interpolator, Animation.AnimationListener listener) {}


    // just for translations
    protected final void translate (long duration, int delay, int repeat, View view, Interpolator interpolator, Animation.AnimationListener listener,
            int fromXType, float fromXValue, int toXType, float toXValue, int fromYType, float fromYValue, int toYType, float toYValue) {

        Animation translate = new TranslateAnimation(fromXType, fromXValue, toXType, toXValue, fromYType, fromYValue, toYType, toYValue);
        translate.setStartOffset(delay);
        translate.setDuration(duration);
        translate.setRepeatCount(repeat);
        translate.setInterpolator(interpolator);
        translate.setAnimationListener(listener);
        view.startAnimation(translate);

    }

    // just for rotations
    protected final void rotate(long duration, float value, int delay, int repeat, View view, Animation.AnimationListener listener) {
        if (duration == 1) duration = 50;

        Animation rotate = new RotateAnimation(0, value, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setDuration(duration);
        rotate.setRepeatCount(repeat);
        rotate.setFillAfter(true);
        rotate.setStartOffset(delay);
        rotate.setAnimationListener(listener);
        view.startAnimation(rotate);
    }

    // just for BubbleAppearings
    protected final void bubbleAppear(long duration, int delay, int repeat, View view, Interpolator interpolator, int animRes, Animation.AnimationListener listener) {
        view.setVisibility(View.VISIBLE);

        final Animation bubble = android.view.animation.AnimationUtils.loadAnimation(view.getContext(), animRes);
        bubble.setInterpolator(interpolator);
        bubble.setDuration(duration);
        bubble.setStartOffset(delay);
        bubble.setRepeatCount(repeat);
        bubble.setAnimationListener(listener);
        view.startAnimation(bubble);
    }

    // just for Measure animations
    protected final void measureChange(long duration, int delay, int value, View view, boolean isHeight, Animation.AnimationListener listener) {

        if (value <= 0) return;

        if (isHeight && view.getMeasuredHeight() == value) return;
        if (!isHeight && view.getMeasuredWidth() == value) return;

        ValueAnimator anim = ValueAnimator.ofInt(isHeight ? view.getMeasuredHeight() : view.getMeasuredWidth(), value);
        anim.addUpdateListener(valueAnimator -> {
            int val = (Integer) valueAnimator.getAnimatedValue();
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (isHeight) layoutParams.height = val;
            else layoutParams.width = val;
            view.setLayoutParams(layoutParams); });
        anim.setStartDelay(delay);
        anim.setDuration(duration);
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                listener.onAnimationStart(null);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                listener.onAnimationEnd(null);
            }

            @Override
            public void onAnimationCancel(Animator animator) {}

            @Override
            public void onAnimationRepeat(Animator animator) {
                listener.onAnimationRepeat(null);
            }
        });
        anim.start();
    }
}

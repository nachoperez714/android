package com.gonanimationlib;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.gonanimationlib.interfaces.Listener;

/**
 * Created by leandro on 9/4/18.
 */

public final class AnimationComp {

    // Effect kind to run
    private Effect effect;

    // Basic parameters
    private long duration = Animate.DURATION_SHORT;
    private int delay = 0;
    private float mainValue = -1;
    private float secondaryValue = -1;
    private int repeat = 0;
    private Interpolator interpolator = new LinearInterpolator();

    // Listeners for animation
    private Listener onStart;
    private Listener onEnd;
    private Listener onRepeat;

    AnimationComp(Effect effect) {
        this.effect = effect;
    }

    public void startAnimation(View view) {
        effect.animate(duration, delay, repeat, mainValue, secondaryValue, view,
                interpolator, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        if (onStart != null) onStart.call();
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if (onEnd != null) onEnd.call();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        if (onRepeat != null) onRepeat.call();
                    }
                });
    }


    //region setters
    public AnimationComp during(long during) {
        if (during == 0) during += 1;
        this.duration = during;
        return this;
    }

    public AnimationComp delay(int delay) {
        if (delay < 0) delay = 0;
        this.delay = delay;
        return this;
    }

    public AnimationComp repeat(int repeat, Listener callback) {
        if (repeat < 0) repeat = 0;
        this.repeat = repeat;
        this.onRepeat = callback;
        return this;
    }

    public AnimationComp value(float value) {
        this.mainValue = value;
        return this;
    }

    public AnimationComp secondaryValue(float value) {
        this.secondaryValue = value;
        return this;
    }

    public AnimationComp interpolator(@NonNull Interpolator interpolator) {
        this.interpolator = interpolator;
        return this;
    }

    public AnimationComp onStart(Listener callback) {
        this.onStart = callback;
        return this;
    }

    public AnimationComp onEnd(Listener callback) {
        this.onEnd = callback;
        return this;
    }

    public AnimationComp onRepeat(Listener callback) {
        this.onEnd = callback;
        return this;
    }

    //endregion
}

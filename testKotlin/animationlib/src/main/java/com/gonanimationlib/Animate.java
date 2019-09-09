package com.gonanimationlib;

import android.content.Context;
import android.support.v4.content.ContextCompat;

/**
 * Created by leandro on 6/4/18.
 */

public final class Animate {

    public static AnimationComp with (Effect effect) {
        return new AnimationComp(effect);
    }

    public static final int DURATION_V_SHORT = 150;
    public static final int DURATION_SHORT = 300;
    public static final int DURATION_MEDIUM = 500;
    public static final int DURATION_LARGE = 700;
    public static final int DURATION_V_LARGE = 900;

    public static class Util {

        public static int DpToPx (Context context, int dp) {
            return (int) (dp * context.getResources().getDisplayMetrics().density);
        }

        public static int GetColor(Context context, int color) {
            try {
                return ContextCompat.getColor(context, color);
            } catch (Exception ex) {
                return -1;
            }
        }
    }
}

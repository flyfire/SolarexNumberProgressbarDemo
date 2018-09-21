package com.solarexsoft.solarexnumberprogressbar;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 10:16/2018/9/21
 *    Desc:
 * </pre>
 */
public class ViewUtils {
    public static int dp2px(Context context, float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context
                .getResources().getDisplayMetrics());
    }

    public static int measure(int measureSpec, int defaultSize) {
        int mode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);

        int result = defaultSize;
        if (mode == View.MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = Math.min(specSize, defaultSize);
        }
        return result;
    }
}

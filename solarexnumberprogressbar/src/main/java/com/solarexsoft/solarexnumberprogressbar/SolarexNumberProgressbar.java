package com.solarexsoft.solarexnumberprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 10:02/2018/9/21
 *    Desc:
 * </pre>
 */
public class SolarexNumberProgressbar extends View {
    private static final int DEFAULT_MAX = 100;
    private static final int DEFAULT_PROGRESS = 0;
    private static final int DEFAULT_PROGRESSCOLOR = Color.parseColor("#3385EE");
    private static final int DEFAULT_UNFINISHCOLOR = Color.parseColor("#E1E4E7");

    private static int DEFAULT_WIDTH;
    private static int DEFAULT_HEIGHT;

    private Paint mProgressColorPaint;
    private Paint mTextPaint;
    private Paint mUnfinishColorPaint;

    private int mMax, mProgress, mProgressColor, mUnfinishColor;

    public SolarexNumberProgressbar(Context context) {
        this(context, null);
    }

    public SolarexNumberProgressbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SolarexNumberProgressbar(Context context, @Nullable AttributeSet attrs, int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable
                .SolarexNumberProgressbar);
        if (typedArray != null) {
            mMax = typedArray.getInteger(R.styleable.SolarexNumberProgressbar_max, DEFAULT_MAX);
            mProgress = typedArray.getInteger(R.styleable.SolarexNumberProgressbar_progress,
                    DEFAULT_PROGRESS);

        }
    }
}

package com.solarexsoft.solarexnumberprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
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
    private static final String TAG = SolarexNumberProgressbar.class.getSimpleName();

    private static final int DEFAULT_MAX = 100;
    private static final int DEFAULT_PROGRESS = 0;
    private static final int DEFAULT_PROGRESSCOLOR = Color.parseColor("#3385EE");
    private static final int DEFAULT_UNFINISHCOLOR = Color.parseColor("#E1E4E7");
    private static final int DEFAULT_TEXTCOLOR = Color.parseColor("#ffffff");

    private static int DEFAULT_WIDTH;
    private static int DEFAULT_HEIGHT;
    private static int DEFAULT_TEXTSIZE;

    private Paint mProgressColorPaint;
    private Paint mTextPaint;
    private Paint mUnfinishColorPaint;
    private Paint mCirclePaint;

    private int mMax, mProgress, mProgressColor, mUnfinishColor, mTextColor;
    private float mTextSize;
    private float mTextBaseLine;
    private int mHalfTopRectWidth;
    private int mOffset;

    private int mWidth, mHeight, mProgressWidth;
    private RectF mTopRectF;
    private RectF mUnfinishRectFL, mUnfinishRectFR;
    private RectF mProgressRectF;

    private int mTopRectWidth, mTopRectHeight;
    private float mTopRectLeft;

    private int mProgressRectHeight;
    private int mProgressRectTop;

    private String mProgressStr;
    private int mProgressStrLeft;

    private int mCircleStrokeWidth;
    private int mCircleRadius;
    private int mCircleY;

    private float mTextWidth;

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

        DEFAULT_WIDTH = ViewUtils.dp2px(context, 250);
        DEFAULT_HEIGHT = ViewUtils.dp2px(context, 40f);
        DEFAULT_TEXTSIZE = ViewUtils.dp2px(context, 10);

        if (typedArray != null) {
            mMax = typedArray.getInteger(R.styleable.SolarexNumberProgressbar_max, DEFAULT_MAX);
            mProgress = typedArray.getInteger(R.styleable.SolarexNumberProgressbar_progress,
                    DEFAULT_PROGRESS);
            mProgressColor = typedArray.getColor(R.styleable
                    .SolarexNumberProgressbar_progresscolor, DEFAULT_PROGRESSCOLOR);
            mUnfinishColor = typedArray.getColor(R.styleable
                    .SolarexNumberProgressbar_unfinishedcolor, DEFAULT_UNFINISHCOLOR);
            mTextColor = typedArray.getColor(R.styleable.SolarexNumberProgressbar_textcolor,
                    DEFAULT_TEXTCOLOR);
            mTextSize = typedArray.getDimension(R.styleable.SolarexNumberProgressbar_textsize,
                    DEFAULT_TEXTSIZE);
            typedArray.recycle();
        }


        mProgressColorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mProgressColorPaint.setColor(mProgressColor);

        mUnfinishColorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mUnfinishColorPaint.setColor(mUnfinishColor);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        float dy = (fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.descent;
        mTextBaseLine = ViewUtils.dp2px(context, 7.5f) + dy;

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setColor(mProgressColor);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCircleStrokeWidth = ViewUtils.dp2px(context, 3);
        mCirclePaint.setStrokeWidth(mCircleStrokeWidth);
        mCircleRadius = ViewUtils.dp2px(context, 5);

        mHalfTopRectWidth = ViewUtils.dp2px(context, 15);
        mOffset = ViewUtils.dp2px(context, 30);
        mTopRectWidth = ViewUtils.dp2px(context, 30);
        mTopRectHeight = ViewUtils.dp2px(context, 15);

        mProgressRectTop = ViewUtils.dp2px(context, 27.5f);
        mProgressRectHeight = ViewUtils.dp2px(context, 4);

        mCircleY = mProgressRectTop + mProgressRectHeight / 2;

        setProgress(mProgress);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mProgressWidth = mWidth - mOffset;
        mTopRectLeft = mProgressWidth * mProgress * 1.0f / mMax;

        float topRectRight = mTopRectLeft + mTopRectWidth;
        float topRectBottom = mTopRectHeight;

        mTopRectF = new RectF(mTopRectLeft, 0, topRectRight, topRectBottom);

        float unfinishRectTop = mProgressRectTop;
        float unfinishRectBottom = mProgressRectTop + mProgressRectHeight;
        float unfinishRectLLeft = mHalfTopRectWidth + mCircleRadius;
        float unfinishRectLRight = mHalfTopRectWidth + mTopRectLeft - mCircleRadius;
        float unfinishRectRLeft = mHalfTopRectWidth + mTopRectLeft + mCircleRadius;
        float unfinishRectRRight = mWidth - mHalfTopRectWidth - mCircleRadius;

        mUnfinishRectFL = new RectF(unfinishRectLLeft, unfinishRectTop, unfinishRectLRight,
                unfinishRectBottom);
        mUnfinishRectFR = new RectF(unfinishRectRLeft, unfinishRectTop, unfinishRectRRight,
                unfinishRectBottom);

        float progressRectLeft = mHalfTopRectWidth;
        float progressRectTop = mProgressRectTop;
        float progressRectRight = mHalfTopRectWidth + mTopRectLeft - mCircleRadius;
        float progressRectBottom = mProgressRectTop + mProgressRectHeight;

        mProgressRectF = new RectF(progressRectLeft, progressRectTop, progressRectRight,
                progressRectBottom);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = ViewUtils.measure(widthMeasureSpec, DEFAULT_WIDTH);
        int height = ViewUtils.measure(heightMeasureSpec, DEFAULT_HEIGHT);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mTopRectF.set(mTopRectLeft, 0, mTopRectLeft + mTopRectWidth, mTopRectHeight);
        mProgressRectF.set(mHalfTopRectWidth, mProgressRectTop, mHalfTopRectWidth + mTopRectLeft
                        - mCircleRadius,
                mProgressRectTop + mProgressRectHeight);
        mProgressStrLeft = (int) (mTopRectLeft + mHalfTopRectWidth - mTextWidth / 2.0f);
        canvas.drawRoundRect(mTopRectF, 4, 4, mProgressColorPaint);
        canvas.drawText(mProgressStr, mProgressStrLeft, mTextBaseLine, mTextPaint);
        canvas.drawRoundRect(mUnfinishRectFL, 4, 4, mUnfinishColorPaint);
        canvas.drawRoundRect(mUnfinishRectFR, 4, 4, mUnfinishColorPaint);
        canvas.drawRoundRect(mProgressRectF, 4, 4, mProgressColorPaint);
        canvas.drawCircle(mHalfTopRectWidth + mTopRectLeft, mCircleY, mCircleRadius, mCirclePaint);
    }

    public void setProgress(int progress) {
        mProgress = progress;

        mTopRectLeft = mProgressWidth * mProgress * 1.0f / mMax;

        mProgressStr = progress + "%";
        mTextWidth = mTextPaint.measureText(mProgressStr);
        postInvalidate();
    }
}

package com.gcssloop.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.RemoteViews;

@RemoteViews.RemoteView
public class GradientRecyclerView extends RecyclerView {

    private final static String TAG = "GradientRecyclerView";

    private Paint mPaint;
    private int height;
    private int width;

    private int mShaderWidth = 100;

    public GradientRecyclerView(Context context) {
        super(context, null);
    }

    public GradientRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GradientRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != 0 && h != 0) {
            height = h;
            width = w;
            PagerConfig.Logi(TAG + "height:" + height + ",width:" + width);
            LinearGradient linearGradient = new LinearGradient(width - mShaderWidth, 0, width, 0,
                    new int[]{0xff000000, 0x00000000},
                    new float[]{0, 1}, Shader.TileMode.MIRROR);
            mPaint.setShader(linearGradient);
        }
    }

    @Override
    public void draw(Canvas c) {
        c.saveLayer(0, 0, width, height, null, Canvas.ALL_SAVE_FLAG);
        super.draw(c);
        c.drawRect(width - mShaderWidth, 0, width, height, mPaint);
        c.restore();
    }
}

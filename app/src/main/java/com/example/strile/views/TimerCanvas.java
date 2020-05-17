package com.example.strile.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.strile.R;

public class TimerCanvas extends View {

    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private long totalTime = 1000;
    private long currentTime;

    public TimerCanvas(Context context) {
        super(context);
        setBaseParams();
    }

    public TimerCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBaseParams();
    }

    public TimerCanvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBaseParams();
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
        invalidate();
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
        invalidate();
    }

    private void setBaseParams() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int longInterval = 5000;
        final int shortInterval = 1000;

        final int shortWidth = getResources().getDimensionPixelOffset(R.dimen.width_timer_animation_short_line);
        final int longWidth = getResources().getDimensionPixelOffset(R.dimen.width_timer_animation_long_line);
        final int shortHeight = getResources().getDimensionPixelOffset(R.dimen.height_timer_animation_short_line);
        final int longHeight = getResources().getDimensionPixelOffset(R.dimen.height_timer_animation_long_line);
        final int marginBetween = getResources().getDimensionPixelOffset(R.dimen.margin_between_timer_animation_line);

        paint.setColor(getContext().getColor(R.color.colorLightGray));

        canvas.drawColor(getContext().getColor(R.color.colorBackground));

        int leftLong = (getWidth() - longWidth) / 2;
        int leftShort = (getWidth() - shortWidth) / 2;

        int totalHeightMargins = (int) (totalTime / shortInterval * marginBetween);
        int totalHeightLongLines = (int) (totalTime / longInterval * longHeight);
        int totalHeightShortLines = (int) ((totalTime / shortInterval - totalTime / longInterval) * shortHeight);
        int totalHeight = totalHeightLongLines + totalHeightMargins + totalHeightShortLines;

        int positionVisibleZone = (int) (totalHeight - currentTime * totalHeight / totalTime);
        int heightVisibleZone = getHeight();

        int time = 0;
        for (int totalTop = 0; totalTop < positionVisibleZone + heightVisibleZone; time += shortInterval) {
            if (time % longInterval == 0) totalTop += longHeight;
            else totalTop += shortHeight;

            totalTop += marginBetween;

            if (totalTop >= positionVisibleZone) {
                int x = totalTop - positionVisibleZone;
                int alpha = (int) (255 * (1 - parabolaY(x, heightVisibleZone) / parabolaY(0, heightVisibleZone)));
                paint.setAlpha(alpha);

                int top =  (totalTop - positionVisibleZone);
                if (time % longInterval == 0) {
                    int right = leftLong + longWidth;
                    int buttom =  (totalTop - positionVisibleZone + longHeight);
                    canvas.drawRoundRect(leftLong, top, right, buttom, 100, 100, paint);
                } else {
                    int right = leftShort + shortWidth;
                    int buttom =  (totalTop - positionVisibleZone + shortHeight);
                    canvas.drawRoundRect(leftShort, top, right, buttom, 100, 100, paint);
                }
            }

        }

    }

    private float parabolaY(int x, int h) {
        return (float) (Math.pow(x, 2) - h * x + Math.pow(h, 2) / 4);
    }
}

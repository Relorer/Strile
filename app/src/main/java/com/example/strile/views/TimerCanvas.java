package com.example.strile.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.strile.R;

public class TimerCanvas extends SurfaceView implements SurfaceHolder.Callback {
    private DrawThread drawThread;

    private long totalTime;
    private long currentTime;

    public TimerCanvas(Context context) {
        super(context);
        getHolder().addCallback(this);
        getHolder().setFormat(PixelFormat.TRANSPARENT);
    }

    public TimerCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        getHolder().setFormat(PixelFormat.TRANSPARENT);
    }

    public TimerCanvas(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getHolder().addCallback(this);
        getHolder().setFormat(PixelFormat.TRANSPARENT);
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThread = new DrawThread(getHolder());
        drawThread.setRunning(true);
        drawThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        drawThread.setRunning(false);
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException ignored) {
            }
        }
    }

    class DrawThread extends Thread {
        private boolean running = false;
        private final SurfaceHolder surfaceHolder;

        DrawThread(SurfaceHolder surfaceHolder) {
            this.surfaceHolder = surfaceHolder;
        }

        void setRunning(boolean running) {
            this.running = running;
        }

        @Override
        public void run() {
            Canvas canvas;

            final int longInterval = 5000;
            final int shortInterval = 1000;

            final int shortWidth = getResources().getDimensionPixelOffset(R.dimen.width_timer_animation_short_line);
            final int longWidth = getResources().getDimensionPixelOffset(R.dimen.width_timer_animation_long_line);
            final int shortHeight = getResources().getDimensionPixelOffset(R.dimen.height_timer_animation_short_line);
            final int longHeight = getResources().getDimensionPixelOffset(R.dimen.height_timer_animation_long_line);
            final int marginBetween = getResources().getDimensionPixelOffset(R.dimen.margin_between_timer_animation_line);

            Paint paint = new Paint();
            paint.setColor(getContext().getColor(R.color.colorLightGray));

            while (running) {
                canvas = null;
                try {
                    canvas = surfaceHolder.lockCanvas(null);
                    synchronized (surfaceHolder) {
                        canvas.drawColor(getContext().getColor(R.color.colorBackground));

                        int leftLong = (canvas.getWidth() - longWidth) / 2;
                        int leftShort = (canvas.getWidth() - shortWidth) / 2;

                        int totalHeightMargins = (int) (totalTime / shortInterval * marginBetween);
                        int totalHeightLongLines = (int) (totalTime / longInterval * longHeight);
                        int totalHeightShortLines = (int) ((totalTime / shortInterval - totalTime / longInterval) * shortHeight);
                        int totalHeight = totalHeightLongLines + totalHeightMargins + totalHeightShortLines;

                        int positionVisibleZone = (int) (totalHeight - currentTime * totalHeight / totalTime);
                        int heightVisibleZone = canvas.getHeight();

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
                }
                catch (Exception ignore) {}
                finally {
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }

        private float parabolaY(int x, int h) {
            return (float) (Math.pow(x, 2) - h * x + Math.pow(h, 2) / 4);
        }
    }
}

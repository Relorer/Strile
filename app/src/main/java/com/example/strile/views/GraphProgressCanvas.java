package com.example.strile.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.strile.R;

public class GraphProgressCanvas extends View {

    private final int offsetDotted = getResources().getDimensionPixelOffset(R.dimen.offsetDottedGraph);
    private final int widthDotted = getResources().getDimensionPixelOffset(R.dimen.widthDottedGraph);
    private final int baseMargin = getResources().getDimensionPixelOffset(R.dimen.margin_horizontal_progress_bar);
    private final int textSize = getResources().getDimensionPixelSize(R.dimen.font_size_text_secondary);
    private final int strokeWidthGrid = getResources().getDimensionPixelOffset(R.dimen.strokeWidthGridGraph);
    private final int strokeWidthLineGraph = getResources().getDimensionPixelOffset(R.dimen.strokeWidthLineGraph);
    private final int radiusCircle = getResources().getDimensionPixelOffset(R.dimen.radiusCircleGraph);
    private final int countDivisionsY = 3;
    private final Paint fontPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint gridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint graphPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private int max = 0;
    private int[] points = {};
    private float[] ptsGraph = {};

    public GraphProgressCanvas(Context context) {
        super(context);
        setBaseParams();
    }

    public GraphProgressCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBaseParams();
    }

    public GraphProgressCanvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBaseParams();
    }

    private void setBaseParams() {
        fontPaint.setTextSize(textSize);
        fontPaint.setColor(getContext().getColor(R.color.colorLightGray));
        gridPaint.setColor(getContext().getColor(R.color.colorLightGray));
        gridPaint.setStrokeWidth(strokeWidthGrid);
        graphPaint.setColor(getContext().getColor(R.color.colorAccent));
        graphPaint.setStrokeWidth(strokeWidthLineGraph);
    }

    public void setMax(int max) {
        this.max = max;
        invalidate();
    }

    public void setPoints(int[] points) {
        this.points = points;
        if (points.length > 1)
            ptsGraph = new float[points.length * 2 - 4];
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(getContext().getColor(R.color.colorPrimary));

        if (points.length == 0 || ptsGraph.length == 0) return;

        float heightText = Math.abs(fontPaint.getFontMetrics().top);
        float leftMarginGrid = baseMargin + fontPaint.measureText(String.format("%d.0", max)) + baseMargin * 0.3f;
        float heightGrid = getHeight() - 2 * heightText;
        float widthGrid = getWidth() - leftMarginGrid - baseMargin - fontPaint.measureText(String.valueOf(points[points.length - 2])) / 2;

        canvas.drawLine(leftMarginGrid, heightText / 2, leftMarginGrid, (heightText / 2) + heightGrid, gridPaint);
        canvas.drawLine(leftMarginGrid, (heightText / 2) + heightGrid, leftMarginGrid + widthGrid, (heightText / 2) + heightGrid, gridPaint);

        for (int i = 0; i < countDivisionsY; i++) {
            float topMargin = (heightText / 2) + i * heightGrid / countDivisionsY;
            float[] pts = generatePointsDottedLine(leftMarginGrid, topMargin, (int) widthGrid);
            canvas.drawLines(pts, gridPaint);

            double lineVisibleNum = i == 0 ? (double) max : (((double) max / countDivisionsY) * (countDivisionsY - i));

            @SuppressLint("DefaultLocale")
            String num = String.format("%.1f", lineVisibleNum);
            if (num.equals("0")) num = "";
            float xNum = leftMarginGrid - fontPaint.measureText(num) - baseMargin * 0.3f;
            float yNum = topMargin + heightText * 0.3f;
            canvas.drawText(num, xNum, yNum, fontPaint);
        }

        for (int i = 0, j = 0; i < points.length; i += 2, j += 2) {
            String num = String.valueOf(points[i]);
            float leftMargin = leftMarginGrid + (i >> 1) * (widthGrid / ((points.length >> 1) - 1));

            ptsGraph[j] = leftMargin;
            ptsGraph[j + 1] = (heightText / 2) + heightGrid - heightGrid / max * points[i + 1];
            if (i != 0 && i != points.length - 2) {
                ptsGraph[j + 2] = leftMargin;
                ptsGraph[j + 3] = (heightText / 2) + heightGrid - heightGrid / max * points[i + 1];
                j += 2;
            }
            canvas.drawCircle(ptsGraph[j], ptsGraph[j + 1], radiusCircle, graphPaint);

            float leftMarginText = leftMargin - fontPaint.measureText(num) / 2;
            canvas.drawText(num, leftMarginText, (heightText / 2) + heightGrid + heightText + baseMargin * 0.15f, fontPaint);
        }
        canvas.drawLines(ptsGraph, graphPaint);

    }

    private float[] generatePointsDottedLine(float x1, float y1, int length) {
        int countDotsDottedLine = (length / (offsetDotted + widthDotted) * 4) + 4;
        float[] pts = new float[countDotsDottedLine];
        int currentLength = 0;
        for (int i = 0; i < countDotsDottedLine - 4; i += 4) {
            pts[i] = x1 + currentLength;
            pts[i + 1] = y1;
            currentLength += widthDotted;
            pts[i + 2] = x1 + currentLength;
            pts[i + 3] = y1;
            currentLength += offsetDotted;
        }
        pts[countDotsDottedLine - 4] = x1 + currentLength;
        pts[countDotsDottedLine - 3] = y1;
        currentLength += Math.min(widthDotted, length - currentLength);
        pts[countDotsDottedLine - 2] = x1 + currentLength;
        pts[countDotsDottedLine - 1] = y1;
        return pts;
    }
}
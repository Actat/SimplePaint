package com.example.actat.simplepaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class CanvasView extends View {

    private Paint paint;

    private ArrayList<Path> pathlist;
    private Path drawingPath;

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init_paint();
    }

    public CanvasView(Context context) {
        super(context);

        init_paint();
    }

    void init_paint() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Path path : pathlist) {
            canvas.drawPath(path, paint);
        }

        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawingPath = new Path();
                drawingPath.moveTo(event.getX(), event.getY());
                pathlist.add(drawingPath);
                break;
            case MotionEvent.ACTION_UP:
                drawingPath.lineTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                drawingPath.lineTo(event.getX(), event.getY());
                break;
        }
        return true;
    }

    public void reset() {
        pathlist.clear();
    }

}

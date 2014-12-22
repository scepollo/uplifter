package com.uplifter.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.uplifter.R;
import com.uplifter.model.PositivityModel;

public class PercentGraph extends View {
    private static final int [] COLOURS = { R.color.yellow, R.color.orange, R.color.grey1, R.color.grey2, R.color.grey3 };

    private final float [] _percentages = new float [5];
    private Paint _paint;
    private Paint _bgpaint;
    private RectF _rect;

    public PercentGraph(final Context context) {
        super(context);
        init(context);
    }

    public PercentGraph(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PercentGraph(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(final Context context) {
        final PositivityModel moodScore = UplifterData.getInstance().getMoodScore(context);
        final int size = moodScore.getSize();
        if(size > 0) {
            _percentages[0] = ((float) moodScore.getVeryHappy()) / ((float) size);
            _percentages[1] = ((float) moodScore.getHappy()) / ((float) size);
            _percentages[2] = ((float) moodScore.getSoso()) / ((float) size);
            _percentages[3] = ((float) moodScore.getNotSoGreat()) / ((float) size);
            _percentages[4] = ((float) moodScore.getVeryUnhappy()) / ((float) size);
        }
        _paint = new Paint();
        _paint.setAntiAlias(true);
        _paint.setStyle(Paint.Style.FILL);
        _bgpaint = new Paint();
        _bgpaint.setColor(getContext().getResources().getColor(R.color.yellow));
        _bgpaint.setAntiAlias(true);
        _bgpaint.setStyle(Paint.Style.FILL);
        _rect = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //draw background circle anyway
        int left = 0;
        int width = getWidth();
        int top = 0;
        float startAngle = -90;
        _rect.set(left, top, left+width, top + width); 
        canvas.drawArc(_rect, startAngle, 360, true, _bgpaint);
        for(int i = 0; i < _percentages.length; ++i) {
            _paint.setColor(getContext().getResources().getColor(COLOURS[i]));
            if(_percentages[i] > 0) {
                final float sweepAngle = 360 * _percentages[i];
                canvas.drawArc(_rect, startAngle, sweepAngle, true, _paint);
                startAngle += sweepAngle;
            }
        }
    }
}
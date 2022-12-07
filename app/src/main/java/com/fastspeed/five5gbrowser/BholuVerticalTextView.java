package com.fastspeed.five5gbrowser;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatTextView;

public class BholuVerticalTextView extends AppCompatTextView
{
    final boolean topDownBholu;

    public BholuVerticalTextView(Context context, AttributeSet attrs )
    {
        super( context, attrs );
        final int gravity = getGravity();
        if ( Gravity.isVertical(gravity)
                && ( gravity & Gravity.VERTICAL_GRAVITY_MASK )
                == Gravity.BOTTOM )
        {
            setGravity(
                    ( gravity & Gravity.HORIZONTAL_GRAVITY_MASK )
                            | Gravity.TOP );
            topDownBholu = false;
        }
        else
        {
            topDownBholu = true;
        }
    }

    @Override
    protected void onMeasure( int widthMeasureSpec,
                              int heightMeasureSpec )
    {
        super.onMeasure( heightMeasureSpec,
                widthMeasureSpec );
        setMeasuredDimension( getMeasuredHeight(),
                getMeasuredWidth() );
    }

    @Override
    protected void onDraw( Canvas canvas )
    {
        TextPaint textPaintBholu = getPaint();
        textPaintBholu.setColor( getCurrentTextColor() );
        textPaintBholu.drawableState = getDrawableState();

        canvas.save();

        if (topDownBholu)
        {
            canvas.translate( getWidth(), 0 );
            canvas.rotate( 90 );
        }
        else
        {
            canvas.translate( 0, getHeight() );
            canvas.rotate( -90 );
        }

        canvas.translate( getCompoundPaddingLeft(),
                getExtendedPaddingTop() );

        getLayout().draw( canvas );
        canvas.restore();
    }
}
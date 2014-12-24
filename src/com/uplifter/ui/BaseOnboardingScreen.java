package com.uplifter.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

public class BaseOnboardingScreen extends FragmentActivity implements GestureDetector.OnGestureListener {
    private Activity _this;
    private Class _prev;
    private Class _next;
    private GestureDetectorCompat _detector; 

    private static final String DEBUG_TAG = "Onboarding: ";
    private static final int TRANSITION_LENGTH = 250;

    protected void setThisPrevNext(final Activity myThis, final Class prev, final Class next) {
        _this = myThis;
        _prev = prev;
        _next = next;
    }

    // Called when the activity is first created. 
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Instantiate the gesture detector with the
        // application context and an implementation of
        // GestureDetector.OnGestureListener
        _detector = new GestureDetectorCompat(this,this);
    }

    private final void swipe(final int swipeDirection) {
        /* New Handler to start the Menu-Activity 
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(swipeDirection > 0 && _prev != null) {
                    final Intent mainIntent = new Intent(_this, _prev);
                    _this.startActivity(mainIntent);
                    _this.finish();
                } else if(swipeDirection < 0 && _next != null) {
                    final Intent mainIntent = new Intent(_this, _next);
                    _this.startActivity(mainIntent);
                    _this.finish();
                }
            }
        }, TRANSITION_LENGTH);
    }


    @Override 
    public boolean onTouchEvent(final MotionEvent event) {
        _detector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(final MotionEvent event) {
        Log.d(DEBUG_TAG,"onDown: " + event.toString());
        return true;
    }

    @Override
    public boolean onFling(final MotionEvent event1, final MotionEvent event2,
            final float velocityX, final float velocityY) {
        Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());
        swipe(velocityX < 0 ? -1 : 1);
        return true;
    }

    @Override
    public void onLongPress(final MotionEvent event) {
        Log.d(DEBUG_TAG, "onLongPress: " + event.toString()); 
    }

    @Override
    public boolean onScroll(final MotionEvent e1, final MotionEvent e2, final float distanceX,
            final float distanceY) {
        Log.d(DEBUG_TAG, "onScroll: " + e1.toString()+e2.toString());
        return true;
    }

    @Override
    public void onShowPress(final MotionEvent event) {
        Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
    }

    @Override
    public boolean onSingleTapUp(final MotionEvent arg0) {
        return false;
    }


    protected final void applyBold(final int [] bold) {
        for(final int i: bold) {
            ((TextView) findViewById(i)).setTypeface(Typeface.DEFAULT_BOLD);
        }
    }

    protected final void applyItalics(final int [] italics) {
        for(final int i: italics) {
            final TextView v1 = (TextView) findViewById(i);
            v1.setTypeface(v1.getTypeface(), Typeface.ITALIC);
        }
    }
}

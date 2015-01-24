package com.uplifter.ui;

import android.app.ActionBar;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.uplifter.UplifterApplication;
import com.uplifter.UplifterApplication.TrackerName;

public class UplifterActivity extends FragmentActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Tracker t = ((UplifterApplication) getApplication()).getTracker(TrackerName.APP_TRACKER);
        t.setScreenName(getScreenName());
        t.send(new HitBuilders.AppViewBuilder().build());

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        final ActionBar a = getActionBar();
        if(a != null) {
            a.setDisplayShowHomeEnabled(false);
            a.setDisplayShowTitleEnabled(false);
            a.setDisplayUseLogoEnabled(false);
        }
    }

    protected String getScreenName() {
        return getClass().getName();
    }

    protected final void setBackgroundDrawable(final int id, final Drawable d) {
        if (Build.VERSION.SDK_INT >= 16) {
            findViewById(id).setBackground(d);
        } else {
            findViewById(id).setBackgroundDrawable(d);
        }
    }

    protected final void applyBold(final int [] bold) {
        for(final int i: bold) {
            applyBold((TextView) findViewById(i));
        }
    }

    protected final void applyBold(final TextView tv) {
//        tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
    }

    protected final void applyItalics(final int [] italics) {
        for(final int i: italics) {
            applyItalics((TextView) findViewById(i));
        }
    }

    protected final void applyItalics(final TextView tv) {
        tv.setTypeface(tv.getTypeface(), Typeface.ITALIC);
    }
}

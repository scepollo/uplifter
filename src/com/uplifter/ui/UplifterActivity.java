package com.uplifter.ui;

import android.app.ActionBar;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

public class UplifterActivity extends FragmentActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar a = getActionBar();
        if(a != null) {
            a.setDisplayShowHomeEnabled(false);
            a.setDisplayShowTitleEnabled(false);
            a.setDisplayUseLogoEnabled(false);
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

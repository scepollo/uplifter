package com.uplifter.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.uplifter.util.FontManager;

public class UplifterTextView extends TextView {

    public UplifterTextView(final Context context) {
        super(context);
        setTypeface(FontManager.getTypeface(context, FontManager.DEFAULT_FONT));
    }

    public UplifterTextView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        setTypeface(FontManager.getTypeface(context, FontManager.DEFAULT_FONT));
    }

    public UplifterTextView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        setTypeface(FontManager.getTypeface(context, FontManager.DEFAULT_FONT));
    }
}
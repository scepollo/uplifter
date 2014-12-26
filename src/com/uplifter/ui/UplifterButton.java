package com.uplifter.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.uplifter.util.FontManager;

public class UplifterButton extends Button {
    public UplifterButton(final Context context) {
        super(context);
        setTypeface(FontManager.getTypeface(context, FontManager.DEFAULT_FONT));
    }

    public UplifterButton(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        setTypeface(FontManager.getTypeface(context, FontManager.DEFAULT_FONT));
    }

    public UplifterButton(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        setTypeface(FontManager.getTypeface(context, FontManager.DEFAULT_FONT));
    }
}
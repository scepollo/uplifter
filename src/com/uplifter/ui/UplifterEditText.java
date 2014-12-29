package com.uplifter.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.uplifter.util.FontManager;

public class UplifterEditText extends EditText {

    public UplifterEditText(final Context context) {
        super(context);
        init(context);
    }

    public UplifterEditText(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public UplifterEditText(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private final void init(final Context context) {
        setTypeface(FontManager.getTypeface(context, FontManager.DEFAULT_FONT));
        setLineSpacing(2f, 1.1f);
    }
}
package com.uplifter.util;

import android.content.Context;
import android.graphics.Typeface;

public class FontManager {
    public static final int NAUTILUS_FONT = 0;
    public static final int DEFAULT_FONT = 1;

    private static final String [] FONT_PATH = {
        "fonts/Nautilus.otf",
        "fonts/AvenirLTStd-Book.otf"
    };
    private static final Typeface [] FONTS = new Typeface[FONT_PATH.length];

    private static boolean fontsLoaded = false;

    /**
     * Returns a loaded custom font based on it's identifier. 
     * 
     * @param context - the current context
     * @param fontIdentifier = the identifier of the requested font
     * 
     * @return Typeface object of the requested font.
     */
    public static Typeface getTypeface(final Context context, final int fontIdentifier) {
        if (!fontsLoaded) {
            loadFonts(context);
        }
        return FONTS[fontIdentifier];
    }

    private static void loadFonts(final Context context) {
        for (int i = 0, ii = FONT_PATH.length; i < ii; i++) {
            FONTS[i] = Typeface.createFromAsset(context.getAssets(), FONT_PATH[i]);
        }
        fontsLoaded = true;
    }
}

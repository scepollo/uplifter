package com.uplifter.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.uplifter.R;
import com.uplifter.util.FontManager;
import com.uplifter.util.UplifterData;

public class SplashScreen extends Activity {
    private final int SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        UplifterData.getInstance().init(this);
        setContentView(R.layout.splash_screen);
        ((TextView) findViewById(R.id.splash_textview)).setTypeface(FontManager.getTypeface(this, FontManager.NAUTILUS_FONT));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(UplifterData.getInstance().isAlreadyOnboarded()) {
                    ScreenController.getInstance().loadFirstScreen(SplashScreen.this);
                } else {
                    ScreenController.getInstance().loadOnboardingScreen(SplashScreen.this);
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
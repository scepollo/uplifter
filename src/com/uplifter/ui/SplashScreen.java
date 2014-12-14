package com.uplifter.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.uplifter.R;
import com.uplifter.util.FontManager;
import com.uplifter.util.PersistData;

public class SplashScreen extends Activity {
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        PersistData.init(this);
        setContentView(R.layout.splash_screen);
        ((TextView) findViewById(R.id.splash_title)).setTypeface(FontManager.getTypeface(this, FontManager.NAUTILUS_FONT));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(SplashScreen.this,
                    PersistData.isAlreadyOnboarded() ? TodaysTrainingScreen.class : OnboardingScreen1.class);
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
package com.uplifter.ui;

import com.uplifter.R;
import com.uplifter.R.layout;
import com.uplifter.util.PersistData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity {
    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        PersistData.init(this);
        setContentView(R.layout.splash_screen);

        /* New Handler to start the Menu-Activity 
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                final Intent mainIntent = new Intent(SplashScreen.this,
                    PersistData.isAlreadyOnboarded() ? TodaysTrainingScreen.class : OnboardingScreen1.class);
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
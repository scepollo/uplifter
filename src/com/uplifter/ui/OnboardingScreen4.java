package com.uplifter.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.uplifter.R;

public class OnboardingScreen4 extends BaseOnboardingScreen {
    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setThisPrevNext(this, OnboardingScreen3.class, null);
        setContentView(R.layout.onboarding_screen_4);
    }

    public void sendMessage(final View view) {
        final Intent mainIntent = new Intent(OnboardingScreen4.this, TodaysTrainingScreen.class);
        OnboardingScreen4.this.startActivity(mainIntent);
        OnboardingScreen4.this.finish();
    }
}
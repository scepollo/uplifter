package com.uplifter.ui;

import android.os.Bundle;

import com.uplifter.R;

public class OnboardingScreen2 extends BaseOnboardingScreen {
    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setThisPrevNext(this, OnboardingScreen1.class, OnboardingScreen3.class);
        setContentView(R.layout.onboarding_screen_2);
    }
}
package com.uplifter.ui;

import android.os.Bundle;

import com.uplifter.R;

public class OnboardingScreen1 extends BaseOnboardingScreen {
    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setThisPrevNext(this, null, OnboardingScreen2.class);
        setContentView(R.layout.onboarding_screen_1);
    }
}
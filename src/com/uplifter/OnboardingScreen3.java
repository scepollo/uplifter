package com.uplifter;

import android.os.Bundle;

public class OnboardingScreen3 extends BaseOnboardingScreen {
    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setThisPrevNext(this, OnboardingScreen2.class, OnboardingScreen4.class);
        setContentView(R.layout.onboarding_screen_3);
    }
}
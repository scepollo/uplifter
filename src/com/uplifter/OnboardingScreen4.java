package com.uplifter;

import android.os.Bundle;

public class OnboardingScreen4 extends BaseOnboardingScreen {
    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setThisPrevNext(this, OnboardingScreen3.class, null);
        setContentView(R.layout.onboarding_screen_4);
    }
}
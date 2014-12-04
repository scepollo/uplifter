package com.uplifter;

import android.os.Bundle;

public class OnboardingScreen1 extends BaseOnboardingScreen {
    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setThisPrevNext(this, null, OnboardingScreen2.class);
        setContentView(R.layout.onboarding_screen_1);
    }
}
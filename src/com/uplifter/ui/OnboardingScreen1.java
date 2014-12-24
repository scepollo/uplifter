package com.uplifter.ui;

import android.os.Bundle;

import com.uplifter.R;

public class OnboardingScreen1 extends BaseOnboardingScreen {
    @Override
    public void onCreate(final Bundle b) {
        super.onCreate(b);
        setThisPrevNext(this, null, OnboardingScreen2.class);
        setContentView(R.layout.onboarding_screen_1);
        applyBold(new int [] {R.id.onboarding_1_1});
    }
}
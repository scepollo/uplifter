package com.uplifter.ui;

import com.uplifter.R;
import com.uplifter.R.layout;

import android.os.Bundle;

public class OnboardingScreen3 extends BaseOnboardingScreen {
    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setThisPrevNext(this, OnboardingScreen2.class, OnboardingScreen4.class);
        setContentView(R.layout.onboarding_screen_3);
    }
}
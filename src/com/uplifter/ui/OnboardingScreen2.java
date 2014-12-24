package com.uplifter.ui;

import android.os.Bundle;

import com.uplifter.R;

public class OnboardingScreen2 extends BaseOnboardingScreen {
    private static final int [] ITALICS = { R.id.onboarding_2_2_1, R.id.onboarding_2_3_2, R.id.onboarding_2_4_1 };
    private static final int [] BOLD = { R.id.onboarding_2_1, R.id.onboarding_2_2_2, R.id.onboarding_2_3_1, R.id.onboarding_2_4_2 };

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setThisPrevNext(this, OnboardingScreen1.class, OnboardingScreen3.class);
        setContentView(R.layout.onboarding_screen_2);
        applyBold(BOLD);
        applyItalics(ITALICS);
    }
}
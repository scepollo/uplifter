package com.uplifter.ui;

import android.os.Bundle;
import android.view.View;

import com.uplifter.R;

public class OnboardingScreen4 extends BaseOnboardingScreen {
    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setThisPrevNext(this, OnboardingScreen3.class, null);
        setContentView(R.layout.onboarding_screen_4);
        applyBold(new int [] { R.id.onboarding_4_1 });
    }

    public void sendMessage(final View view) {
        ScreenController.getInstance().loadFirstScreen(this);
    }
}
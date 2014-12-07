package com.uplifter.ui;

import android.os.Bundle;

import com.uplifter.R;
import com.uplifter.util.PersistData;

public class TodaysTrainingScreen extends BaseOnboardingScreen {
    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        PersistData.setAlreadyOnboarded(true);
        setThisPrevNext(this, OnboardingScreen3.class, null);
        setContentView(R.layout.todays_training_screen);
    }
}
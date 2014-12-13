package com.uplifter.ui;

import android.os.Bundle;

import com.uplifter.R;
import com.uplifter.model.TrainingModel;
import com.uplifter.util.PersistData;
import com.uplifter.util.UplifterData;

public class TodaysTrainingScreen extends BaseOnboardingScreen {
    private TrainingModel[] _training;

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        PersistData.setAlreadyOnboarded(true);
        setThisPrevNext(this, OnboardingScreen3.class, null);
        setContentView(R.layout.todays_training_screen);
        _training = UplifterData.loadTraining(this);
    }
}
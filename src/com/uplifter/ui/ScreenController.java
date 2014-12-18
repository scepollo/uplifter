package com.uplifter.ui;

import android.app.Activity;
import android.content.Intent;

import com.uplifter.util.UplifterData;

public class ScreenController {
    private static ScreenController _instance;
    private int _currentTrainingIndex;

    private ScreenController() {
    }

    public final synchronized static ScreenController getInstance() {
        if(_instance == null) {
            _instance = new ScreenController();
        }
        return _instance;
    }

    public final void loadOnboardingScreen(final Activity activity) {
        switchScreen(activity, OnboardingScreen1.class);
    }

    public final void loadFirstScreen(final Activity activity) {
        switchScreen(activity, UplifterData.trainingAlreadyDone() ? LogMoodScreen.class : TodaysTrainingScreen.class);
    }

    public final void loadNextTrainingScreen(final Activity currentActivity) {
        if(++_currentTrainingIndex < UplifterData.DAILY_QUESTION_COUNT) {
            switchScreen(currentActivity, TrainingScreen.class);
        } else {
            UplifterData.setTrainingAlreadyDone();
            switchScreen(currentActivity, PositivitySuperstarScreen.class);
        }
    }

    public final void loadPrevTrainingScreen(final Activity currentActivity) {
        if(_currentTrainingIndex > 0) {
            --_currentTrainingIndex;
            switchScreen(currentActivity, TrainingScreen.class);
        }
    }

    public final void loadFirstTrainingScreen(final Activity currentActivity) {
        _currentTrainingIndex = 0;
        switchScreen(currentActivity, TrainingScreen.class);
    }

    public final int getCurrentTrainingIndex() {
        return _currentTrainingIndex;
    }

    private static final void switchScreen(final Activity current, final Class next) {
        final Intent mainIntent = new Intent(current, next);
        current.startActivity(mainIntent);
        current.finish();
    }

    public final void loadQuoteScreen(final Activity current) {
        switchScreen(current, QuoteScreen.class);
    }
}

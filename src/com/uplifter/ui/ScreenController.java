package com.uplifter.ui;

import android.app.Activity;
import android.content.Intent;

import com.uplifter.util.UplifterData;

public class ScreenController {
    private static ScreenController _instance;
    private int _currentTrainingIndex;

    private ScreenController() {
    }

    public synchronized static ScreenController getInstance() {
        if(_instance == null) {
            _instance = new ScreenController();
        }
        return _instance;
    }

    public void loadFirstScreen(final Activity activity) {
        final Class firstScreen;
        if(UplifterData.trainingAlreadyDone()) {
            firstScreen = LogMoodScreen.class;
        } else {
            firstScreen = TodaysTrainingScreen.class;
        }
        switchScreen(activity, firstScreen);
    }

    public void loadNextTrainingScreen(final Activity currentActivity) {
        if(++_currentTrainingIndex < UplifterData.DAILY_QUESTION_COUNT) {
            switchScreen(currentActivity, TrainingScreen.class);
        } else {
            UplifterData.setTrainingAlreadyDone();
            switchScreen(currentActivity, PositivitySuperstarScreen.class);
        }
    }

    public void loadPrevTrainingScreen(final Activity currentActivity) {
        if(_currentTrainingIndex > 0) {
            --_currentTrainingIndex;
            switchScreen(currentActivity, TrainingScreen.class);
        }
    }

    public void loadFirstTrainingScreen(final Activity currentActivity) {
        _currentTrainingIndex = 0;
        switchScreen(currentActivity, TrainingScreen.class);
    }

    public int getCurrentTrainingIndex() {
        return _currentTrainingIndex;
    }

    private static final void switchScreen(final Activity current, final Class next) {
        final Intent mainIntent = new Intent(current, next);
        current.startActivity(mainIntent);
        current.finish();
    }

    public void loadQuoteScreen(final Activity current) {
        switchScreen(current, QuoteScreen.class);
    }
}

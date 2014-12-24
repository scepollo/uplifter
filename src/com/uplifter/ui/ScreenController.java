package com.uplifter.ui;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;

import com.uplifter.R;
import com.uplifter.util.UplifterData;

public class ScreenController {
    private static final Map<Integer, Class> ACTIVITY_MAP = new HashMap<Integer, Class>();
    static {
        ACTIVITY_MAP.put(R.id.training, TodaysTrainingScreen.class);
        ACTIVITY_MAP.put(R.id.log_your_mood, LogMoodScreen.class);
        ACTIVITY_MAP.put(R.id.tracking, MoodHistoryScreen.class);
        ACTIVITY_MAP.put(R.id.progress, TrainingHistoryScreen.class);
        ACTIVITY_MAP.put(R.id.tips, TipsScreen.class);
        ACTIVITY_MAP.put(R.id.settings, SettingsScreen.class);
        ACTIVITY_MAP.put(R.id.about, AboutScreen.class);
    }

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
        switchScreen(activity, UplifterData.getInstance().trainingAlreadyDone() ? QuoteScreen.class : TodaysTrainingScreen.class);
    }

    public final void loadNextTrainingScreen(final Activity currentActivity, final String [] answers) {
        final UplifterData ulData = UplifterData.getInstance();
        ulData.setTrainingData(_currentTrainingIndex, answers);
        if(++_currentTrainingIndex < UplifterData.DAILY_QUESTION_COUNT) {
            switchScreen(currentActivity, TrainingScreen.class);
        } else {
            ulData.setTrainingAlreadyDone();
            switchScreen(currentActivity, PositivitySuperstarScreen.class);
        }
    }

    public final void loadPrevTrainingScreen(final Activity currentActivity, final String [] answers) {
        if(_currentTrainingIndex > 0) {
            UplifterData.getInstance().setTrainingData(_currentTrainingIndex, answers);
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

    public final boolean switchActionBarScreen(final Activity current, final int id) {
        if(ACTIVITY_MAP.containsKey(id)) {
            Class clazz = ACTIVITY_MAP.get(id);
            final Class currentClazz = current.getClass();
            if(current.getClass() != clazz) {
                if(clazz == TodaysTrainingScreen.class && UplifterData.getInstance().trainingAlreadyDone()) {
                    if(currentClazz == QuoteScreen.class) {
                        return false;
                    }
                    clazz = QuoteScreen.class;
                }
                switchScreen(current, clazz);
                return true;
            }
        }
        return false;
    }

    private static final void switchScreen(final Activity current, final Class next) {
        final Intent mainIntent = new Intent(current, next);
        current.startActivity(mainIntent);
        current.finish();
    }

    public final void loadQuoteScreen(final Activity current) {
        switchScreen(current, QuoteScreen.class);
    }

    public void loadMoodHistoryScreen(final Activity current) {
        switchScreen(current, MoodHistoryScreen.class);
    }
}

package com.uplifter.util;

import java.util.Map;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PersistData {
    private static final String DEFAULT_ALARM_TIME = "8:00";
    private static final String COMMA = ",";
    private static final String UPLIFTER_PREFS = "UPLIFTER_PREFS";
    private static final String ALREADY_ONBOARDED_KEY = "ALREADY_ONBOARDED_KEY";
    private static final String ALARM_DATE_TIME_KEY = "ALARM_DATE_TIME_KEY";
    private static final String NOTIFICATIONS_KEY = "NOTIFICATIONS_KEY";
    private static final String YESTERDAYS_QUESTIONS_KEY = "YESTERDAYS_QUESTIONS_KEY";

    private static String _alarmDateTime;
    private static boolean _notifications;
    private static boolean _alreadyOnboarded;
    private static int [] _yesterdaysQuestions;

    private static boolean _init;
    private static Editor _editor;

    public static final void init(final Activity activity) {
        if(!_init) {
            _init = true;
            final SharedPreferences settings = activity.getSharedPreferences(UPLIFTER_PREFS, 0);
            _editor = settings.edit();
            final Map<String,?> map = settings.getAll();
            if(map.containsKey(ALREADY_ONBOARDED_KEY)) {
                _alreadyOnboarded = (Boolean) map.get(ALREADY_ONBOARDED_KEY);
            }
            if(map.containsKey(ALARM_DATE_TIME_KEY)) {
                _alarmDateTime = (String) map.get(ALARM_DATE_TIME_KEY);
            } else {
                _alarmDateTime = DEFAULT_ALARM_TIME;
            }
            if(map.containsKey(NOTIFICATIONS_KEY)) {
                _notifications = (Boolean) map.get(NOTIFICATIONS_KEY);
            }
            if(map.containsKey(YESTERDAYS_QUESTIONS_KEY)) {
                _yesterdaysQuestions = parseIntArray((String) map.get(YESTERDAYS_QUESTIONS_KEY));
            } else {
                // This assumes that the questions are indexed from 1.
                _yesterdaysQuestions = new int [0];
            }
        }
    }

    private static final int [] parseIntArray(final String list) {
        final String [] array = list.split(COMMA);
        final int [] intArray = new int [array.length];
        for(int i = 0; i < array.length; ++i) {
            try {
                intArray[i] = Integer.parseInt(array[i]);
            } catch(final NumberFormatException e) {
            }
        }
        return intArray;
    }

    public static final boolean isAlreadyOnboarded() {
        return _alreadyOnboarded;
    }

    public static final void setAlreadyOnboarded(final boolean alreadyOnboarded) {
        _alreadyOnboarded = alreadyOnboarded;
        writePersistBoolean(ALREADY_ONBOARDED_KEY, alreadyOnboarded);
    }

    public static final void setAlarmDateTime(final String alarmDateTime) {
        _alarmDateTime = alarmDateTime;
        writePersistString(ALARM_DATE_TIME_KEY, alarmDateTime);
    }

    public static final String getAlarmDateTime() {
        return _alarmDateTime;
    }

    public static final void setNotifications(final boolean notifications) {
        _notifications = notifications;
        writePersistBoolean(NOTIFICATIONS_KEY, notifications);
    }

    public static final boolean getNotifications() {
        return _notifications;
    }

    public static final void setYesterdaysQuestions(final int [] yesterdaysQuestions) {
        _yesterdaysQuestions = yesterdaysQuestions;
        writePersistString(YESTERDAYS_QUESTIONS_KEY,
            "" + yesterdaysQuestions[0] + ',' + yesterdaysQuestions[1] + ',' + yesterdaysQuestions[2]);
    }

    public static final int [] getYesterdaysTrainingIndex() {
        return _yesterdaysQuestions;
    }

    private static final void writePersistBoolean(final String key, final boolean data) {
        _editor.putBoolean(key, data);
        _editor.commit();
    }

    private static final void writePersistString(final String key, final String data) {
        _editor.putString(key, data);
        _editor.commit();
    }

    public static void setTodaysTrainingData(final int [] todaysTrainingIndex, final String [][] trainingData) {
        setYesterdaysQuestions(todaysTrainingIndex);
        // TODO:  Store training data -- use JSON and mySQL
    }
}

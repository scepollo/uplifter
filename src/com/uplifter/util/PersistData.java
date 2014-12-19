package com.uplifter.util;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.uplifter.model.DailyAnswerModel;
import com.uplifter.ui.UplifterUtil;

public class PersistData {
    private static final String DEFAULT_ALARM_TIME = "8:00";
    private static final String COMMA = ",";
    private static final String UPLIFTER_PREFS = "UPLIFTER_PREFS";
    private static final String ALREADY_ONBOARDED_KEY = "ALREADY_ONBOARDED_KEY";
    private static final String ALARM_DATE_TIME_KEY = "ALARM_DATE_TIME_KEY";
    private static final String NOTIFICATIONS_KEY = "NOTIFICATIONS_KEY";
    private static final String YESTERDAYS_QUESTIONS_KEY = "YESTERDAYS_QUESTIONS_KEY";
    private static final String DAILY_ANSWERS_KEY = "DAILY_ANSWERS_KEY";
    private static final String DAILY_ANSWERS_JSON_KEY = "dailyAnswers";

    private static String _alarmDateTime;
    private static boolean _notifications;
    private static boolean _alreadyOnboarded;
    private static int [] _yesterdaysQuestions;

    private static boolean _init;
    private static Editor _editor;
    private static Map<String, DailyAnswerModel> _trainingData = new HashMap<String, DailyAnswerModel>();

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
            if(map.containsKey(DAILY_ANSWERS_KEY)) {
                try {
                    final JSONArray array = new JSONObject((String) map.get(DAILY_ANSWERS_KEY)).getJSONArray(DAILY_ANSWERS_JSON_KEY);
                    for(int i = 0, ii = array.length(); i < ii; ++i) {
                        final DailyAnswerModel model = new DailyAnswerModel(array.getJSONObject(i));
                        _trainingData.put(model.getDate(), model);
                    }
                } catch (final JSONException e) {
                }
                UplifterData.getInstance().setTrainingAlreadyDone(_trainingData.containsKey(UplifterUtil.getTodaysDateString()));
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

    public static final Map<String, DailyAnswerModel> getTrainingData() {
        return _trainingData;
    }

    public static void setTrainingData(final int [] todaysTrainingIndex, final Map<String, DailyAnswerModel> trainingData) {
        _trainingData = trainingData;
        setYesterdaysQuestions(todaysTrainingIndex);
        final JSONObject json = new JSONObject();
        final JSONArray array = new JSONArray();

        for(final DailyAnswerModel model: trainingData.values()) {
            array.put(model.getJSONObject());
        }
        try {
            json.put(DAILY_ANSWERS_JSON_KEY, array);
        } catch (final JSONException e) {
        }

        writePersistString(DAILY_ANSWERS_KEY, json.toString());
    }
}

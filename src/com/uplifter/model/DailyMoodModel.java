package com.uplifter.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.uplifter.ui.UplifterUtil;

public class DailyMoodModel extends BaseModel {
    private static final String MOOD_KEY = "mood";
    private static final String DATE_KEY = "date";
    private static final String DATE_MILLIS_KEY = "dateMillis";

    private String _date;
    private long _dateInMillis;
    private int _mood;

    public DailyMoodModel(final JSONObject json) {
        if(json.has(DATE_KEY)) {
            try {
                _date = json.getString(DATE_KEY);
            } catch (final JSONException e) {
            }
        }
        if(json.has(DATE_MILLIS_KEY)) {
            try {
                _dateInMillis = json.getLong(DATE_MILLIS_KEY);
            } catch (final JSONException e) {
            }
        }
        if(json.has(MOOD_KEY)) {
            try {
                _mood = json.getInt(MOOD_KEY);
            } catch (final JSONException e) {
            }
        }
    }

    public DailyMoodModel(final int mood) {
        _date = UplifterUtil.getTodaysDateString();
        _dateInMillis = System.currentTimeMillis();
        _mood = mood;
    }

    public final String getDate() {
        return _date;
    }

    public final long getDateInMillis() {
        return _dateInMillis;
    }

    public final int getMood() {
        return _mood;
    }

    public final String toString() {
        final StringBuffer buff = new StringBuffer();
        buff.append("DailyMoodModel\nDate: ").append(_date);
        buff.append("\nDateInMillis: ").append(_dateInMillis);
        buff.append("\nMood: ").append(_mood);
        return buff.toString();
    }

    public final JSONObject getJSONObject() {
        final JSONObject json = new JSONObject();
        try {
            json.put(DATE_KEY, _date);
        } catch (final JSONException e) {
        }
        try {
            json.put(DATE_MILLIS_KEY, _dateInMillis);
        } catch (final JSONException e) {
        }
        try {
            json.put(MOOD_KEY, _mood);
        } catch (final JSONException e) {
        }
        return json;
    }
}

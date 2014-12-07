package com.uplifter.util;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PersistData {
    private static final String UPLIFTER_PREFS = "UPLIFTER_PREFS";
    private static final String ALREADY_ONBOARDED = "ALREADY_ONBOARDED";
    private static final String ALARM_DATE_TIME_KEY = "ALARM_DATE_TIME_KEY";
    private static final String NOTIFICATIONS_KEY = "NOTIFICATIONS_KEY";

    private static long _alarmDateTime;
    private static boolean _notifications;
    private static boolean _alreadyOnboarded;

    private static boolean _init;
    private static Editor _editor;

    public static final void init(final Activity activity) {
        if(!_init) {
            _init = true;
            final SharedPreferences settings = activity.getSharedPreferences(UPLIFTER_PREFS, 0);
            _editor = settings.edit();
            _alreadyOnboarded = settings.getBoolean(ALREADY_ONBOARDED, false);
            _alarmDateTime = settings.getLong(ALARM_DATE_TIME_KEY, 0L);
            _notifications = settings.getBoolean(NOTIFICATIONS_KEY, false);
        }
    }

    public static final boolean isAlreadyOnboarded() {
        return _alreadyOnboarded;
    }

    public static final void setAlreadyOnboarded(final boolean alreadyOnboarded) {
        _alreadyOnboarded = alreadyOnboarded;
        writePersistBoolean(NOTIFICATIONS_KEY, alreadyOnboarded);
    }

    public static final void setAlarmDateTime(final long alarmDateTime) {
        _alarmDateTime = alarmDateTime;
        writePersistLong(ALARM_DATE_TIME_KEY, alarmDateTime);
    }

    public static final long getAlarmDateTime() {
        return _alarmDateTime;
    }

    public static final void setNotifications(final boolean notifications) {
        _notifications = notifications;
        writePersistBoolean(NOTIFICATIONS_KEY, notifications);
    }

    public static final boolean getNotifications() {
        return _notifications;
    }

    private static final void writePersistBoolean(final String key, final boolean data) {
        _editor.putBoolean(key, data);
        _editor.commit();
    }

    private static final void writePersistLong(final String key, final long data) {
        _editor.putLong(key, data);
        _editor.commit();
    }
}

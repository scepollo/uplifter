package com.uplifter.util;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.uplifter.model.DateComparableModel;

public class UplifterUtil {
    private static final long ONE_DAY = 1000 * 60 * 60 * 24;

    public static final String getTodaysDateString() {
        return DateFormat.getDateInstance(DateFormat.LONG).format(new Date());
    }

    public static final void cancelNotification(final Context context) {
    }

    public static final void setNotification(final Context context, boolean checked) {
        final Intent myIntent = new Intent(context, UplifterReceiver.class);
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, myIntent,0);
        final AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if(checked) {
            final Calendar calendar = Calendar.getInstance();
            final String alarmString = UplifterData.getInstance().getAlarmDateTime();
            final int indexOfColon = alarmString.indexOf(':');
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(alarmString.substring(0, indexOfColon)));
            calendar.set(Calendar.MINUTE, Integer.parseInt(alarmString.substring(indexOfColon + 1)));

            final long alarmTimeMillis = calendar.getTimeInMillis();
            final long currentTimeMillis = System.currentTimeMillis();
            final long firstTime = (alarmTimeMillis > currentTimeMillis) ? alarmTimeMillis : alarmTimeMillis + ONE_DAY;
            System.out.println("ALARM DATE-TIME: " + alarmTimeMillis);
            System.out.println("CURRENT DATE-TIME: " + currentTimeMillis);
            System.out.println("FIRST TIME: " + firstTime);

            alarmManager.setRepeating(AlarmManager.RTC, firstTime, ONE_DAY, pendingIntent);
        } else {
            alarmManager.cancel(pendingIntent);
        }
    }

    public static final int numberOfConsecutiveDays(final DateComparableModel [] items) {
        if(items == null || items.length == 0) {
            return 0;
        }
        Arrays.sort(items);
        int count = 1;
        long oldTime = new Date(items[0].getDate()).getTime();
        for(int i = 1; i < items.length; ++i) {
            final long newTime = new Date(items[i].getDate()).getTime();
            if(oldTime - newTime == ONE_DAY) {
                ++count;
                oldTime = newTime;
            } else {
                break;
            }
        }
        return count;
    }
}

package com.uplifter.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.uplifter.R;
import com.uplifter.ui.SplashScreen;

public class UplifterAlarmService extends Service {
    private NotificationManager mManager;

    @Override
    public IBinder onBind(final Intent arg0) {
        return null;
    }

    @SuppressWarnings("static-access")
    @Override
    public void onStart(final Intent intent, final int startId) {
        super.onStart(intent, startId);

        final Context appContext = getApplicationContext();
        mManager = (NotificationManager) appContext.getSystemService(appContext.NOTIFICATION_SERVICE);
        final Intent intent1 = new Intent(appContext, SplashScreen.class);

        final Notification notification = new Notification(R.drawable.appicon, "This is a test message!", System.currentTimeMillis());
        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        final PendingIntent pendingNotificationIntent = PendingIntent.getActivity(appContext, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.setLatestEventInfo(appContext, "AlarmManagerDemo", "Start your day positively", pendingNotificationIntent);

        mManager.notify(0, notification);
    }
}
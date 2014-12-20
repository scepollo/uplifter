package com.uplifter.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class UplifterReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, final Intent intent) {
        context.startService(new Intent(context, UplifterAlarmService.class));
    }
}
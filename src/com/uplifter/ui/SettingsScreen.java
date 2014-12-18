package com.uplifter.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TimePicker;

import com.uplifter.R;
import com.uplifter.util.PersistData;

public class SettingsScreen extends BaseOnboardingScreen {
    private static final String AM = "AM";
    private static final String COLON = ":";
    private static final String PM = "PM";
    private static final String TIME_PICKER = "timePicker";

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.settings_screen);
        ((Switch) findViewById(R.id.onboard_notification_switch)).setChecked(PersistData.getNotifications());
        setTimeUI();
    }

    @SuppressLint("NewApi")
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if(!ScreenController.getInstance().switchActionBarScreen(this, item.getItemId())) {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void switchClicked(final View switcher) {
        PersistData.setNotifications(((Switch) switcher).isChecked());
    }

    public void showTimePickerDialog(final View v) {
        new TimePickerFragment().show(getFragmentManager(), TIME_PICKER);
    }

    private final void setTimeUI() {
        final String time = PersistData.getAlarmDateTime();
        final int indexOfColon = time.indexOf(':');
        final int hour = Integer.parseInt(time.substring(0, indexOfColon));
        final String timeString = (hour == 0 ? 12 : (hour > 12 ? hour -12 : hour)) + time.substring(indexOfColon) +
              ' ' + (hour >= 12 ? PM : AM);
        ((Button) findViewById(R.id.onboard_notification_times)).setText(timeString);
    }

    public static class TimePickerFragment extends DialogFragment implements
            TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(final Bundle savedInstanceState) {
            final String time = PersistData.getAlarmDateTime();
            final int indexOfColon = time.indexOf(':');
            final int hour = Integer.parseInt(time.substring(0, indexOfColon));
            final int minute = Integer.parseInt(time.substring(indexOfColon + 1));

            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(final TimePicker view, final int hourOfDay, final int minute) {
            PersistData.setAlarmDateTime(hourOfDay + COLON + minute);
            ((SettingsScreen) getActivity()).setTimeUI();
        }
    }
}
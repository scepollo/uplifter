package com.uplifter.ui;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TimePicker;

import com.uplifter.R;
import com.uplifter.util.PersistData;
import com.uplifter.util.UplifterData;

public class OnboardingScreen3 extends BaseOnboardingScreen {
    private static final String AM = "AM";
    private static final String COLON = ":";
    private static final String PM = "PM";
    private static final String TIME_PICKER = "timePicker";

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setThisPrevNext(this, OnboardingScreen2.class, OnboardingScreen4.class);
        setContentView(R.layout.onboarding_screen_3);
        ((Switch) findViewById(R.id.onboard_notification_switch)).setChecked(UplifterData.getInstance().getNotifications());
        setTimeUI();
    }

    public void switchClicked(final View switcher) {
        UplifterData.getInstance().setNotifications(this, ((Switch) switcher).isChecked());
    }

    public void showTimePickerDialog(final View v) {
        new TimePickerFragment().show(getFragmentManager(), TIME_PICKER);
    }

    private final void setTimeUI() {
        final String time = UplifterData.getInstance().getAlarmDateTime();
        final int indexOfColon = time.indexOf(':');
        final int hour = Integer.parseInt(time.substring(0, indexOfColon));
        final String timeString = (hour == 0 ? 12 : (hour > 12 ? hour -12 : hour)) + time.substring(indexOfColon) +
              ' ' + (hour >= 12 ? PM : AM);
        ((Button) findViewById(R.id.onboard_notification_times)).setText(timeString);
    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(final Bundle savedInstanceState) {
            final String time = UplifterData.getInstance().getAlarmDateTime();
            final int indexOfColon = time.indexOf(':');
            final int hour = Integer.parseInt(time.substring(0, indexOfColon));
            final int minute = Integer.parseInt(time.substring(indexOfColon + 1));

            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(final TimePicker view, final int hourOfDay, final int minute) {
            final OnboardingScreen3 s = (OnboardingScreen3) getActivity();
            UplifterData.getInstance().setAlarmDateTime(s, hourOfDay + COLON + minute);
            s.setTimeUI();
        }
    }
}
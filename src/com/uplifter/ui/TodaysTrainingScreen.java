package com.uplifter.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.uplifter.R;
import com.uplifter.util.UplifterData;

public class TodaysTrainingScreen extends ActionActivity {
    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        UplifterData.getInstance().setAlreadyOnboarded(true);
        setContentView(R.layout.todays_training_screen);
        ((ListView) findViewById(R.id.list)).setAdapter(new TodaysTrainingAdapter(this, UplifterData.getInstance().getTrainingForToday(this)));
    }

    public void sendMessage(final View view) {
        ScreenController.getInstance().loadFirstTrainingScreen(this);
    }
}
package com.uplifter.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.uplifter.R;
import com.uplifter.model.TrainingModel;
import com.uplifter.util.PersistData;
import com.uplifter.util.UplifterData;

public class TodaysTrainingScreen extends Activity {
    private static final int [] TEXT_ID = {R.id.q1, R.id.q2, R.id.q3};

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        PersistData.setAlreadyOnboarded(true);
        setContentView(R.layout.todays_training_screen);
        final TrainingModel[] training = UplifterData.getTodaysTraining(this);
        for(int i = 0, ii = training.length; i < ii; ++i) {
            final TextView text = (TextView) findViewById(TEXT_ID[i]);
            text.setText(training[i].getTitle());
        }
        // TODO: set the three category icons in the UI
    }

    public void sendMessage(final View view) {
        ScreenController.getInstance().loadFirstTrainingScreen(this);
    }
}
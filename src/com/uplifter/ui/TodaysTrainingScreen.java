package com.uplifter.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.uplifter.R;
import com.uplifter.model.TrainingModel;
import com.uplifter.util.PersistData;
import com.uplifter.util.UplifterData;

public class TodaysTrainingScreen extends UplifterActivity {
    private static final int [] TITLE_ID = {R.id.q1, R.id.q2, R.id.q3};
    private static final int [] SUBTITLE_ID = {R.id.s1, R.id.s2, R.id.s3};

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        PersistData.setAlreadyOnboarded(true);
        setContentView(R.layout.todays_training_screen);
        final TrainingModel[] training = UplifterData.getInstance().getTrainingForToday(this);
        for(int i = 0, ii = training.length; i < ii; ++i) {
            ((TextView) findViewById(TITLE_ID[i])).setText(training[i].getTitle().toUpperCase());
            ((TextView) findViewById(SUBTITLE_ID[i])).setText(training[i].getSubtitle());
        }
    }

    public void sendMessage(final View view) {
        ScreenController.getInstance().loadFirstTrainingScreen(this);
    }
}
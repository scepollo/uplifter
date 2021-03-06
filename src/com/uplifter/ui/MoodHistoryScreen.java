package com.uplifter.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.uplifter.R;
import com.uplifter.model.PositivityModel;
import com.uplifter.util.UplifterData;

public class MoodHistoryScreen extends ActionActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_history_screen);
        final PositivityModel moodScore = UplifterData.getInstance().getMoodScore(this);
        ((TextView) findViewById(R.id.score)).setText("" + moodScore.getOverallMood());
        setPercentage(R.id.positive, R.string.positive, moodScore.getPositivePercentage());
        setPercentage(R.id.neutral, R.string.neutral, moodScore.getNeutralPercentage());
        setPercentage(R.id.negative, R.string.negative, moodScore.getNegativePercentage());
    }

    private final void setPercentage(final int viewId, final int stringId, final int percentage) {
        ((TextView) findViewById(viewId)).setText("You were " + getResources().getString(stringId) + ' ' + percentage + "% of the time");
    }
}

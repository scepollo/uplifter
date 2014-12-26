package com.uplifter.ui;

import android.os.Bundle;
import android.view.View;

import com.uplifter.R;
import com.uplifter.util.UplifterData;

public class LogMoodScreen extends ActionActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_mood_screen);
        applyBold(new int [] { R.id.mood1, R.id.mood2, R.id.mood3, R.id.mood4, R.id.mood5 });
    }

    private final void logMood(final int mood) {
        UplifterData.getInstance().logMood(mood);
        ScreenController.getInstance().loadMoodHistoryScreen(this);
    }

    public final void logMood(final View view) {
        if(view.equals(findViewById(R.id.mood1))) {
            logMood(1);
        } else if(view.equals(findViewById(R.id.mood2))) {
            logMood(2);
        } else if(view.equals(findViewById(R.id.mood3))) {
            logMood(3);
        } else if(view.equals(findViewById(R.id.mood4))) {
            logMood(4);
        } else if(view.equals(findViewById(R.id.mood5))) {
            logMood(5);
        }
    }
}

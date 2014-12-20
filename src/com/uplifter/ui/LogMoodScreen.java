package com.uplifter.ui;

import android.os.Bundle;
import android.view.View;

import com.uplifter.R;
import com.uplifter.util.UplifterData;

public class LogMoodScreen extends UplifterActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_mood_screen);
    }

    private final void logMood(final int mood) {
        UplifterData.getInstance().logMood(mood);
        // TODO:  Figure out to where to transition
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

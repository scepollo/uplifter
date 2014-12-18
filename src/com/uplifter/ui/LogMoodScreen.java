package com.uplifter.ui;

import android.os.Bundle;
import android.view.View;

import com.uplifter.R;

public class LogMoodScreen extends UplifterActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_mood_screen);
    }

    private final void logMood(final int mood) {
        // TODO:  log the mood
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

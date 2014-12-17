package com.uplifter.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.uplifter.R;

public class LogMoodScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_mood_screen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.log_mood_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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

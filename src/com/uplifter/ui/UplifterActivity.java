package com.uplifter.ui;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.uplifter.R;

public class UplifterActivity extends Activity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActionBar a = getActionBar();
        a.setDisplayShowHomeEnabled(false);
        a.setDisplayShowTitleEnabled(false);
        a.setDisplayUseLogoEnabled(false);
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
}

package com.uplifter.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.uplifter.R;

public class UplifterActivity extends Activity {
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

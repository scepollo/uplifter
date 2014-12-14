package com.uplifter.ui;

import com.uplifter.R;
import com.uplifter.R.id;
import com.uplifter.R.layout;
import com.uplifter.R.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class PositivitySuperstarScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.positivity_superstar_screen);
    }

    public void sendMessage(final View view) {
        ScreenController.getInstance().loadQuoteScreen(this);
    }
}

package com.uplifter.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.uplifter.R;

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

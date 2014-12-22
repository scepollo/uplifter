package com.uplifter.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.uplifter.R;
import com.uplifter.model.DailyAnswerModel;
import com.uplifter.util.UplifterData;
import com.uplifter.util.UplifterUtil;

public class PositivitySuperstarScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActionBar a = getActionBar();
        a.setDisplayShowHomeEnabled(false);
        a.setDisplayShowTitleEnabled(false);
        a.setDisplayUseLogoEnabled(false);

        setContentView(R.layout.positivity_superstar_screen);
        final DailyAnswerModel[] h = UplifterData.getInstance().getTrainingHistory();
        ((TextView) findViewById(R.id.streak)).setText(UplifterUtil.numberOfConsecutiveDays(h) + "-day streak");
        ((TextView) findViewById(R.id.total_days)).setText(h.length + " total day" + (h.length == 1 ? "" : "s"));
    }

    public void sendMessage(final View view) {
        ScreenController.getInstance().loadQuoteScreen(this);
    }
}

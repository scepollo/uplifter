package com.uplifter.ui;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.uplifter.R;
import com.uplifter.util.UplifterData;

public class TrainingHistoryScreen extends ActionActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_screen);
        ((ListView) findViewById(R.id.list)).setAdapter(new TrainingHistoryAdapter(this, UplifterData.getInstance().getTrainingHistory()));
        final Resources res = getResources();
        final TextView title = (TextView) findViewById(R.id.title);
        title.setText(res.getString(R.string.history_title));
        title.setBackgroundColor(res.getColor(R.color.orange));
        title.setTextColor(res.getColor(R.color.white));
    }
}

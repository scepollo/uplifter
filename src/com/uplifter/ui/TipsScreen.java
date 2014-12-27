package com.uplifter.ui;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.uplifter.R;
import com.uplifter.util.UplifterData;

public class TipsScreen extends ActionActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_screen);
        ((TextView) findViewById(R.id.title)).setText(getResources().getString(R.string.happiness_tips_title));
        ((ListView) findViewById(R.id.list)).setAdapter(new TipsAdapter(this, UplifterData.getInstance().getTips(this)));
    }
}

package com.uplifter.ui;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableLayout;
import android.widget.TextView;

import com.uplifter.R;
import com.uplifter.model.TipModel;
import com.uplifter.util.UplifterData;

public class TipsScreen extends UplifterActivity {
    private static final int [] COLOURS = {R.color.green, R.color.purple, R.color.pink, R.color.yellow};

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tips_screen);
        final LinearLayout view = (LinearLayout) findViewById(R.id.tips_screen);
        final TipModel [] tips = UplifterData.getInstance().getTips(this);
        final Resources res = getResources();

        for(int i = 0, ii = tips.length; i < ii; ++i) {
            final LinearLayout l = new LinearLayout(this);
            l.setOrientation(LinearLayout.VERTICAL);
            final LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            l.setLayoutParams(linearParams);
            l.setBackgroundColor(res.getColor(COLOURS[i % COLOURS.length]));

            final TextView title = new TextView(this);
            title.setLayoutParams(new TableLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT, 1f));
            title.setText(tips[i].getTitle());
            title.setTextSize(16);
            title.setTextColor(res.getColor(R.color.white));
            title.setPadding(50, 50, 50, 10);
            title.setTypeface(Typeface.DEFAULT_BOLD);
            title.setGravity(Gravity.LEFT | Gravity.CENTER);
            l.addView(title);

            final TextView body = new TextView(this);
            body.setLayoutParams(new TableLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT, 1f));
            body.setText(tips[i].getTip());
            body.setTextSize(12);
            body.setTextColor(res.getColor(R.color.white));
            body.setPadding(50, 10, 50, 50);
            body.setTypeface(Typeface.DEFAULT);
            body.setGravity(Gravity.LEFT | Gravity.CENTER);
            l.addView(body);

            view.addView(l);
        }
    }
}

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
    private static final int SIDE_PADDING = 125;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_screen);
        final LinearLayout view = (LinearLayout) findViewById(R.id.list);
        final Resources res = getResources();
        ((TextView) findViewById(R.id.title)).setText(res.getString(R.string.happiness_tips));
        final TipModel [] tips = UplifterData.getInstance().getTips(this);

        for(int i = 0, ii = tips.length; i < ii; ++i) {
            final LinearLayout l = new LinearLayout(this);
            l.setOrientation(LinearLayout.VERTICAL);
            final LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            l.setLayoutParams(linearParams);
            l.setBackgroundColor(res.getColor(COLOURS[i % COLOURS.length]));

            final TextView title = new UplifterTextView(this);
            title.setLayoutParams(new TableLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT, 1f));
            title.setText(tips[i].getTitle());
            title.setTextSize(20);
            title.setTextColor(res.getColor(R.color.white));
            title.setPadding(SIDE_PADDING, 50, SIDE_PADDING, 10);
            title.setTypeface(Typeface.DEFAULT_BOLD);
            title.setGravity(Gravity.LEFT | Gravity.CENTER);
            l.addView(title);

            final TextView body = new UplifterTextView(this);
            body.setLayoutParams(new TableLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT, 1f));
            body.setText(tips[i].getTip());
            body.setTextSize(16);
            body.setTextColor(res.getColor(R.color.white));
            body.setPadding(SIDE_PADDING, 10, SIDE_PADDING, 65);
            body.setTypeface(Typeface.DEFAULT);
            body.setGravity(Gravity.LEFT | Gravity.CENTER);
            l.addView(body);

            view.addView(l);
        }
    }
}

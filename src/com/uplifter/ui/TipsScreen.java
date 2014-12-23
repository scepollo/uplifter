package com.uplifter.ui;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableLayout;
import android.widget.TextView;

import com.uplifter.R;
import com.uplifter.model.TipModel;
import com.uplifter.util.UplifterData;

public class TipsScreen extends UplifterActivity {
    private static final int [] COLOURS = {R.color.green, R.color.purple, R.color.pink, R.color.yellow};
    private int WHITE;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WHITE = getResources().getColor(R.color.white);
        setContentView(R.layout.list_screen);
        final LinearLayout view = (LinearLayout) findViewById(R.id.list);
        final Resources res = getResources();
        ((TextView) findViewById(R.id.title)).setText(res.getString(R.string.happiness_tips));
        final TipModel [] tips = UplifterData.getInstance().getTips(this);

        for(int i = 0, ii = tips.length; i < ii; ++i) {
            view.addView(getTip(tips[i], res.getColor(COLOURS[i % COLOURS.length])));
        }
    }

    private View getTip(final TipModel tip, final int background) {
        final LinearLayout outer = new LinearLayout(this);
        outer.setOrientation(LinearLayout.HORIZONTAL);
        outer.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        outer.setBackgroundColor(background);

        final TextView left = new TextView(this);
        left.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 30f));
        outer.addView(left);

        final LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);
        l.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 7f));

        final TextView topPadding = new UplifterTextView(this);
        topPadding.setLayoutParams(new TableLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT, 1f));
        topPadding.setTextSize(20);
        l.addView(topPadding);

        final TextView title = new UplifterTextView(this);
        title.setLayoutParams(new TableLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT, 1f));
        title.setText(tip.getTitle());
        title.setTextSize(20);
        title.setTextColor(WHITE);
        title.setTypeface(Typeface.DEFAULT_BOLD);
        title.setGravity(Gravity.LEFT | Gravity.CENTER);
        l.addView(title);

        final TextView body = new UplifterTextView(this);
        body.setLayoutParams(new TableLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT, 1f));
        body.setText(tip.getTip());
        body.setTextSize(16);
        body.setTextColor(WHITE);
        body.setTypeface(Typeface.DEFAULT);
        body.setGravity(Gravity.LEFT | Gravity.CENTER);
        l.addView(body);

        final TextView bottomPadding = new UplifterTextView(this);
        bottomPadding.setLayoutParams(new TableLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT, 1f));
        bottomPadding.setTextSize(30);
        l.addView(bottomPadding);

        outer.addView(l);

        final TextView right = new TextView(this);
        right.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 30f));
        outer.addView(right);

        return outer;
    }
}

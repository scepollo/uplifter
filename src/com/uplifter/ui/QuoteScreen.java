package com.uplifter.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.uplifter.R;
import com.uplifter.model.QuoteModel;
import com.uplifter.util.UplifterData;

public class QuoteScreen extends ActionActivity {
    private static final String DASH = "- ";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quote_screen);
        final QuoteModel quote = UplifterData.getInstance().getQuote(this);
        ((TextView) findViewById(R.id.quote_text)).setText(quote.getQuote().toUpperCase());
        ((TextView) findViewById(R.id.quote_author)).setText(DASH + quote.getName() + '\u00A0');
        applyItalics(new int [] { R.id.quote_author, R.id.come_back_tomorrow });
    }
}

package com.uplifter.model;

import org.json.JSONException;
import org.json.JSONObject;

public class QuoteModel extends BaseModel {
    private static final String NAME_KEY = "name";
    private static final String QUOTE_KEY = "quote";

    private String _quote;
    private String _name;

    public QuoteModel(final JSONObject json) {
        if(json == null) {
            return;
        }
        try {
            _quote = json.getString(QUOTE_KEY);
        } catch (final JSONException e) {
            _quote = "";
        }
        try {
            _name = json.getString(NAME_KEY);
        } catch (final JSONException e) {
            _name = "";
        }
    }

    public final String getQuote() {
        return _quote;
    }

    public final void setQuote(final String quote) {
        _quote = quote;
    }

    public final String getName() {
        return _name;
    }

    public final void setName(final String name) {
        _name = name;
    }

    public final String toString() {
        return '\t' + _quote + ' ' + '[' + _name + ']';
    }
}

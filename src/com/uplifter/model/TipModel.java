package com.uplifter.model;

import org.json.JSONException;
import org.json.JSONObject;

public class TipModel extends BaseModel {
    private static final String TITLE_KEY = "title";
    private static final String TIP_KEY = "tip";

    private String _tip;
    private String _title;

    TipModel(final JSONObject json) {
        if(json == null) {
            return;
        }
        try {
            _tip = json.getString(TIP_KEY);
        } catch (final JSONException e) {
            _tip = "";
        }
        try {
            _title = json.getString(TITLE_KEY);
        } catch (final JSONException e) {
            _title = "";
        }
    }

    public final String getTip() {
        return _tip;
    }

    public final void setTip(final String tip) {
        _tip = tip;
    }

    public final String getTitle() {
        return _title;
    }

    public final void setTitle(final String title) {
        _title = title;
    }

    public final String toString() {
        return '\t' + _tip + ' ' + '[' + _title + ']';
    }
}

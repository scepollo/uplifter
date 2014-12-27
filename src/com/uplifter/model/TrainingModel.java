package com.uplifter.model;

import org.json.JSONException;
import org.json.JSONObject;

public class TrainingModel extends BaseModel {
    private static final String INDEX_KEY = "index";
    private static final String QUESTION_KEY = "question";
    private static final String SUBTITLE_KEY = "subtitle";
    private static final String TITLE_KEY = "title";

    private String _title;
    private String _subtitle;
    private String _question;
    private int _index;

    TrainingModel(final JSONObject json) {
        if(json.has(TITLE_KEY)) {
            try {
                _title = json.getString(TITLE_KEY);
            } catch (final JSONException e) {
                _title = "";
            }
        }
        if(json.has(SUBTITLE_KEY)) {
            try {
                _subtitle = json.getString(SUBTITLE_KEY);
            } catch (final JSONException e) {
                _subtitle = "";
            }
        }
        if(json.has(INDEX_KEY)) {
            try {
                _index = json.getInt(INDEX_KEY);
            } catch (final JSONException e) {
            }
        }
        if(json.has(QUESTION_KEY)) {
            try {
                _question = json.getString(QUESTION_KEY);
            } catch (final JSONException e) {
                _question = "";
            }
        }
    }

    public final String getTitle() {
        return _title;
    }

    public final String getSubtitle() {
        return _subtitle;
    }

    public final int getIndex() {
        return _index;
    }

    public final String getQuestion() {
        return _question;
    }

    public final String toString() {
        final StringBuffer buff = new StringBuffer();
        buff.append("TrainingModel\nTitle: ").append(_title);
        buff.append("\nSubtitle: ").append(_subtitle);
        buff.append("\nIndex: ").append(_index);
        buff.append("\nQuestion").append(_question);
        return buff.toString();
    }
}

package com.uplifter.model;

import org.json.JSONException;
import org.json.JSONObject;

public class AnswerModel extends BaseModel {
    private static final String ANSWER_KEY = "answer";
    private static final String INDEX_KEY = "index";

    private int _index;
    private String _answer;

    public AnswerModel(final JSONObject json) {
        if(json.has(INDEX_KEY)) {
            try {
                _index = json.getInt(INDEX_KEY);
            } catch (final JSONException e) {
            }
        }
        if(json.has(ANSWER_KEY)) {
            try {
                _answer = json.getString(ANSWER_KEY);
            } catch (final JSONException e) {
                _answer = "";
            }
        }
    }

    public AnswerModel(final int index, final String answer) {
        _index = index;
        _answer = answer;
    }

    public final int getIndex() {
        return _index;
    }

    public final String getAnswer() {
        return _answer;
    }

    public final String toString() {
        final StringBuffer buff = new StringBuffer();
        buff.append("AnswerModel\nIndex: " + _index);
        buff.append("\nAnswer: " + _answer);
        return buff.toString();
    }

    public final JSONObject getJSONObject() {
        final JSONObject json = new JSONObject();
        try {
            json.put(INDEX_KEY, _index);
        } catch (final JSONException e) {
        }
        try {
            json.put(ANSWER_KEY, _answer);
        } catch (final JSONException e) {
        }
        return json;
    }

    public void setAnswer(final String answer) {
        _answer = answer;
    }
}

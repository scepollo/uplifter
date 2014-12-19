package com.uplifter.model;

import org.json.JSONException;
import org.json.JSONObject;

public class AnswerModel extends BaseModel {
    private static final String ANSWERS_KEY = "answers";
    private static final String INDEX_KEY = "index";

    private int _index;
    private String [] _answers;

    public AnswerModel(final JSONObject json) {
        if(json.has(INDEX_KEY)) {
            try {
                _index = json.getInt(INDEX_KEY);
            } catch (final JSONException e) {
            }
        }
        if(json.has(ANSWERS_KEY)) {
            try {
                _answers = getStringArray(json.getJSONArray(ANSWERS_KEY));
            } catch (final JSONException e) {
                _answers = new String[0];
            }
        }
    }

    public AnswerModel(final int index, final String [] answers) {
        _index = index;
        _answers = answers;
    }

    public final int getIndex() {
        return _index;
    }

    public final String[] getAnswers() {
        return _answers;
    }

    public final String toString() {
        final StringBuffer buff = new StringBuffer();
        buff.append("AnswerModel\nIndex: " + _index);
        if (_answers != null) {
            for (final String answer : _answers) {
                buff.append('\n').append('\t').append(answer);
            }
        }
        return buff.toString();
    }
}

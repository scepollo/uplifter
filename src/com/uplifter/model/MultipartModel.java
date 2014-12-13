package com.uplifter.model;

import org.json.JSONException;
import org.json.JSONObject;

public class MultipartModel extends BaseModel {
    private static final String NUMBER_KEY = "number";
    private static final String QUESTION_KEY = "question";

    private String _question;
    private int _number;

    MultipartModel(final JSONObject json) {
        try {
            _question = json.getString(QUESTION_KEY);
        } catch (final JSONException e) {
            _question = "";
        }
        try {
            _number = json.getInt(NUMBER_KEY);
        } catch (final JSONException e) {
        }
    }

    public final String getQuestion() {
        return _question;
    }

    public final void setQuestion(final String question) {
        _question = question;
    }

    public final int getNumber() {
        return _number;
    }

    public final void setNumber(final int number) {
        _number = number;
    }

    public final String toString() {
        return '\t' + _question + ' ' + '[' + _number + ']';
    }
}

package com.uplifter.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DailyAnswerModel extends BaseModel {
    private static final String ANSWER_MODELS_KEY = "answerModels";
    private static final String DATE_KEY = "date";

    private String _date;
    private AnswerModel [] _answerModels;

    public DailyAnswerModel(final JSONObject json) {
        if(json.has(DATE_KEY)) {
            try {
                _date = json.getString(DATE_KEY);
            } catch (final JSONException e) {
            }
        }
        if(json.has(ANSWER_MODELS_KEY)) {
            try {
                final JSONArray answerJSON = json.getJSONArray(ANSWER_MODELS_KEY);
                _answerModels = new AnswerModel [answerJSON.length()];
                for(int i = 0; i < _answerModels.length; ++i) {
                    _answerModels[i] = new AnswerModel(answerJSON.getJSONObject(i));
                }
            } catch (final JSONException e) {
                _answerModels = new AnswerModel [0];
            }
        }
    }

    public DailyAnswerModel(final String date, final AnswerModel [] answerModels) {
        _date = date;
        _answerModels = answerModels;
    }

    public final String getDate() {
        return _date;
    }

    public final AnswerModel [] getAnswers() {
        return _answerModels;
    }

    public final String toString() {
        final StringBuffer buff = new StringBuffer();
        buff.append("DailyAnswerModel\nDate: " + _date);
        if (_answerModels != null) {
            for (final AnswerModel answer : _answerModels) {
                buff.append('\n').append('\t').append(answer);
            }
        }
        return buff.toString();
    }

    public final JSONObject getJSONObject() {
        final JSONObject json = new JSONObject();
        try {
            json.put(DATE_KEY, _date);
        } catch (final JSONException e) {
        }
        final JSONArray array = new JSONArray();
        for(final AnswerModel answerModel: _answerModels) {
            array.put(answerModel.getJSONObject());
        }
        try {
            json.put(ANSWER_MODELS_KEY, array);
        } catch (final JSONException e) {
        }
        return json;
    }
}

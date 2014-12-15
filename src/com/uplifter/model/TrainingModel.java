package com.uplifter.model;

import org.json.JSONException;
import org.json.JSONObject;

public class TrainingModel extends BaseModel {
    private static final String INDEX_KEY = "index";
    private static final String MULTIPART_KEY = "multipart";
    private static final String QUESTIONS_KEY = "questions";
    private static final String TITLE_KEY = "title";
    private static final String SUBTITLE_KEY = "subtitle";

    private String _title;
    private String _subtitle;
    private String[] _questions;
    private MultipartModel _multipart;
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
        if(json.has(QUESTIONS_KEY)) {
            try {
                _questions = getStringArray(json.getJSONArray(QUESTIONS_KEY));
            } catch (final JSONException e) {
                _questions = new String[0];
            }
        }
        if(json.has(MULTIPART_KEY)) {
            try {
                _multipart = new MultipartModel(json.getJSONObject(MULTIPART_KEY));
            } catch (final JSONException e) {
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

    public final String[] getQuestions() {
        return _questions;
    }

    public final MultipartModel getMultipart() {
        return _multipart;
    }

    public final String toString() {
        final StringBuffer buff = new StringBuffer();
        buff.append("TrainingModel\nTitle: " + _title);
        buff.append("\nIndex: " + _index);
        if (_multipart != null) {
            buff.append('\n').append(_multipart);
        }
        if (_questions != null) {
            for (final String question : _questions) {
                buff.append('\n').append('\t').append(question);
            }
        }
        return buff.toString();
    }
}

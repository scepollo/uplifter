package com.uplifter.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseModel {
    protected final String[] getStringArray(final JSONArray array) {
        final int size = array.length();
        final String[] stringArray = new String[size];
        for (int i = 0; i < size; ++i) {
            try {
                stringArray[i] = (String) array.get(i);
            } catch (final JSONException e) {
            }
        }
        return stringArray;
    }

    protected final void putStringArray(final JSONObject json, final String key, final String[] stringArray) {
        final JSONArray array = new JSONArray();
        for (final String s : stringArray) {
            array.put(s);
        }
        try {
            json.put(key, array);
        } catch (final JSONException e) {
        }
    }
}

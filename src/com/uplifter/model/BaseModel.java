package com.uplifter.model;

import org.json.JSONArray;
import org.json.JSONException;

public abstract class BaseModel {
    protected final String [] getStringArray(final JSONArray array) {
        final int size = array.length();
        final String [] stringArray = new String [size];
        for(int i = 0; i < size; ++i) {
            try {
                stringArray[i] = (String) array.get(i);
            } catch (final JSONException e) {
            }
        }
        return stringArray;
    }
}

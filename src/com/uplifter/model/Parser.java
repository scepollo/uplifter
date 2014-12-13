package com.uplifter.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Parser {
    private static final String TRAINING_KEY = "training";

    public static TrainingModel[] parseTraining(final String json) {
        if(json.length() > 0) {
            try {
                final JSONObject jsonObject = new JSONObject(json);
                final JSONArray array = (JSONArray) jsonObject.getJSONArray(TRAINING_KEY);
                final int size = array.length();
                final TrainingModel [] training = new TrainingModel [size];
                for(int i = 0; i < size; ++i) {
                    training[i] = new TrainingModel((JSONObject) array.get(i));
                }
                return training;
            } catch (final JSONException e) {
                e.printStackTrace();
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
        return new TrainingModel [0];
    }
}

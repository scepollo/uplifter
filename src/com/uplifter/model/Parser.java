package com.uplifter.model;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Parser {
    private static final String TRAINING_KEY = "training";

    public static Map<Integer, TrainingModel> parseTraining(final String json) {
        if(json.length() > 0) {
            try {
                final JSONObject jsonObject = new JSONObject(json);
                final JSONArray array = (JSONArray) jsonObject.getJSONArray(TRAINING_KEY);
                final int size = array.length();
                final Map<Integer, TrainingModel> training = new HashMap<Integer, TrainingModel>(size);
                for(int i = 0; i < size; ++i) {
                    final TrainingModel model = new TrainingModel((JSONObject) array.get(i));
                    training.put(model.getIndex(), model);
                }
                return training;
            } catch (final JSONException e) {
                e.printStackTrace();
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
        return new HashMap<Integer, TrainingModel>(0);
    }
}

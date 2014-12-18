package com.uplifter.model;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Parser {
    private static final String QUOTES_KEY = "quotes";
    private static final String TIPS_KEY = "tips";
    private static final String TRAINING_KEY = "training";

    public static final Map<Integer, TrainingModel> parseTraining(final String json) {
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

    public static final QuoteModel [] parseQuotes(final String json) {
        if(json.length() > 0) {
            try {
                final JSONObject jsonObject = new JSONObject(json);
                final JSONArray array = (JSONArray) jsonObject.getJSONArray(QUOTES_KEY);
                final int size = array.length();
                final QuoteModel [] quotes = new QuoteModel [size];
                for(int i = 0; i < size; ++i) {
                    quotes[i] = new QuoteModel((JSONObject) array.get(i));
                }
                return quotes;
            } catch (final JSONException e) {
                e.printStackTrace();
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
        return new QuoteModel[0];
    }

    public static final TipModel [] parseTips(final String json) {
        if(json.length() > 0) {
            try {
                final JSONObject jsonObject = new JSONObject(json);
                final JSONArray array = (JSONArray) jsonObject.getJSONArray(TIPS_KEY);
                final int size = array.length();
                final TipModel [] tips = new TipModel [size];
                for(int i = 0; i < size; ++i) {
                    tips[i] = new TipModel((JSONObject) array.get(i));
                }
                return tips;
            } catch (final JSONException e) {
                e.printStackTrace();
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
        return new TipModel[0];
    }
}

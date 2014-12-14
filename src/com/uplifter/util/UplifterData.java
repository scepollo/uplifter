package com.uplifter.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

import android.content.Context;

import com.uplifter.model.Parser;
import com.uplifter.model.TrainingModel;

public class UplifterData {
    private static final String UTF_8 = "UTF-8";
    private static final String QUOTES = "quotes.json";
    private static final String TRAINING = "training.json";
    private static final int DAILY_QUESTION_COUNT = 3;

    private static Map<Integer, TrainingModel> _training;
    private static TrainingModel [] _todaysTraining;
    private static int [] _todaysTrainingIndex;

    public static Map<Integer, TrainingModel> loadTraining(final Context context) {
        if(_training == null) {
            _training = Parser.parseTraining(loadFileFromAsset(context, TRAINING));
        }
        return _training;
    }

    public static TrainingModel [] getTodaysTraining(final Context context) {
        if(_todaysTraining == null) {
            _todaysTraining = new TrainingModel [3];
            _todaysTrainingIndex = new int [3];
            // get list of previous day's questions
            final int [] yesterdaysTrainingIndex = PersistData.getYesterdaysTrainingIndex();
            final Map<Integer, TrainingModel> training = loadTraining(context);
            // randomly choose three questions without repetition
            for(int i = 0; i < DAILY_QUESTION_COUNT; ++i) {
                _todaysTrainingIndex[i] = getRandomInt(_todaysTrainingIndex, yesterdaysTrainingIndex, 1, training.size());
                _todaysTraining[i] = training.get(_todaysTrainingIndex[i]);
                System.out.println("_todaysTrainingIndex[" + i + "] = " + _todaysTrainingIndex[i]);
            }
        }
        return _todaysTraining;
    }

    private static int getRandomInt(final int [] alreadyHave, final int [] cannotDuplicate, final int minValue, final int range) {
        final Random r = new Random();
        final ArrayList<Integer> newArrayList = new ArrayList<Integer>();

        for(final int val: alreadyHave) {
            if(val > 0) {
                newArrayList.add(val);
            }
        }
        for(final int val: cannotDuplicate) {
            if(val > 0) {
                newArrayList.add(val);
            }
        }
        final Object [] newList = (Object []) newArrayList.toArray();
        Arrays.sort(newList);
        for(int j = 0, jj = newList.length; j < jj; ++j) {
            System.out.println("newList[" + j + "] = " + newList[j]);
        }
        int val = (r.nextInt(range - newList.length) + minValue);
        for(int j = 0, jj = newList.length; j < jj; ++j) {
            if(val < (Integer) newList[j]) {
                break;
            } else if(val == (Integer) newList[j]) {
                ++val;
            }
        }
        return val;
    }

    public static final int [] getTodaysTrainingIndex() {
        return _todaysTrainingIndex;
    }

    public static String loadQuote(final Context context) {
        final String quotes = loadFileFromAsset(context, QUOTES);
        if(quotes.length() > 0) {
            // TODO:  parse
        }
        return "";
    }

    private static final String loadFileFromAsset(final Context context, final String filename) {
        try {
            final InputStream is = context.getAssets().open(filename);
            final int size = is.available();
            final byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, UTF_8);
        } catch (final IOException ex) {
            return "";
        }
    }
}
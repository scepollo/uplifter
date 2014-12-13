package com.uplifter.util;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;

import com.uplifter.model.Parser;
import com.uplifter.model.TrainingModel;

public class UplifterData {
    private static final String UTF_8 = "UTF-8";
    private static final String QUOTES = "quotes.json";
    private static final String TRAINING = "training.json";

    private static TrainingModel [] _training;

    public static TrainingModel [] loadTraining(final Context context) {
        if(_training == null) {
            _training = Parser.parseTraining(loadFileFromAsset(context, TRAINING));
        }
        return _training;
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

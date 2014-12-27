package com.uplifter.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

import android.app.Activity;
import android.content.Context;

import com.uplifter.model.AnswerModel;
import com.uplifter.model.DailyAnswerModel;
import com.uplifter.model.DailyMoodModel;
import com.uplifter.model.Parser;
import com.uplifter.model.PositivityModel;
import com.uplifter.model.QuoteModel;
import com.uplifter.model.TipModel;
import com.uplifter.model.TrainingModel;
import com.uplifter.ui.ScreenController;

public class UplifterData {
    private static final String UTF_8 = "UTF-8";
    private static final String QUOTES = "quotes.json";
    private static final String TIPS = "tips.json";
    private static final String TRAINING = "training.json";
    public static final int DAILY_QUESTION_COUNT = 3;
    private static final int[] DEFAULT_TRAINING_INDEX = { 5, 3, 9 };;
    private static UplifterData _instance;

    private Map<Integer, TrainingModel> _training;
    private TrainingModel [] _todaysTraining;
    private int [] _todaysTrainingIndex;
    private boolean _trainingAlreadyDone;
    private final AnswerModel [] _trainingData = new AnswerModel [3];
    private QuoteModel [] _quotes;
    private TipModel [] _tips;
    private QuoteModel _quoteOfTheDay;

    public static synchronized final UplifterData getInstance() {
        if(_instance == null) {
            _instance = new UplifterData();
        }
        return _instance;
    }

    private UplifterData() {
    }

    private Map<Integer, TrainingModel> loadTraining(final Context context) {
        if(_training == null) {
            _training = Parser.parseTraining(loadFileFromAsset(context, TRAINING));
        }
        return _training;
    }

    public Map<Integer, TrainingModel> getTraining(final Context context) {
        return loadTraining(context);
    }

    public TrainingModel [] getTrainingForToday(final Context context) {
        if(_todaysTraining == null) {
            // get list of previous day's questions
            final Map<Integer, TrainingModel> training = loadTraining(context);
            final int [] yesterdaysTrainingIndex = PersistData.getYesterdaysTrainingIndex();
            if(yesterdaysTrainingIndex.length == 0) {
                // Use the same training questions for the first time each user uses the app
                _todaysTrainingIndex = DEFAULT_TRAINING_INDEX;
            } else {
                _todaysTrainingIndex = new int [3];
                for(int i = 0; i < DAILY_QUESTION_COUNT; ++i) {
                    // Randomly choose three questions without repetition
                    _todaysTrainingIndex[i] = getRandomInt(_todaysTrainingIndex, yesterdaysTrainingIndex, 1, training.size());
                }
            }
            _todaysTraining = new TrainingModel [3];
            for(int i = 0; i < DAILY_QUESTION_COUNT; ++i) {
                _todaysTraining[i] = training.get(_todaysTrainingIndex[i]);
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

    public final int [] getTodaysTrainingIndex() {
        return _todaysTrainingIndex;
    }

    private final QuoteModel [] loadQuotes(final Context context) {
        if(_quotes == null) {
            _quotes = Parser.parseQuotes(loadFileFromAsset(context, QUOTES));
        }
        return _quotes;
    }

    public final QuoteModel getQuote(final Context context) {
        if(_quoteOfTheDay == null) {
            final QuoteModel [] quotes = loadQuotes(context);
            if(quotes.length > 0) {
                _quoteOfTheDay =  quotes[new Random().nextInt(quotes.length)];
            } else {
                _quoteOfTheDay = new QuoteModel(null);
            }
        }
        return _quoteOfTheDay;
    }

    private final TipModel [] loadTips(final Context context) {
        if(_tips == null) {
            _tips = Parser.parseTips(loadFileFromAsset(context, TIPS));
        }
        return _tips;
    }

    public final TipModel [] getTips(final Context context) {
        return loadTips(context);
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

    public final boolean trainingAlreadyDone() {
        return _trainingAlreadyDone;
    }

    public void setTrainingAlreadyDone(final boolean done) {
        _trainingAlreadyDone = done;
    }

    public void setTrainingAlreadyDone() {
        _trainingAlreadyDone = true;
        PersistData.setTrainingData(_todaysTrainingIndex, new DailyAnswerModel(_trainingData));
    }

    public String [] getCurrentScreenTrainingData() {
        final int index = ScreenController.getInstance().getCurrentTrainingIndex();
        if(_trainingData[index] == null) {
            return null;
        }
        return _trainingData[index].getAnswers();
    }

    public DailyAnswerModel[] getTrainingHistory() {
        return PersistData.getTrainingData().values().toArray(new DailyAnswerModel [0]);
    }

    public void setTrainingData(final int index, final String [] data) {
        if(_trainingData[index] == null) {
            _trainingData[index] = new AnswerModel(_todaysTrainingIndex[index], data);
        } else {
            _trainingData[index].setAnswers(data);
        }
    }

    public void logMood(final int mood) {
        PersistData.setMoodData(new DailyMoodModel(mood));
    }

    public void setAlarmDateTime(final Context context, final String dateTime) {
        PersistData.setAlarmDateTime(dateTime);
        UplifterUtil.setNotification(context, PersistData.getNotifications());
    }

    public void setNotifications(final Context context, final boolean checked) {
        PersistData.setNotifications(checked);
        UplifterUtil.setNotification(context, checked);
    }

    public String getAlarmDateTime() {
        return PersistData.getAlarmDateTime();
    }

    public boolean getNotifications() {
        return PersistData.getNotifications();
    }

    public void init(final Activity activity) {
        PersistData.init(activity);
    }

    public boolean isAlreadyOnboarded() {
        return PersistData.isAlreadyOnboarded();
    }

    public void setAlreadyOnboarded(final boolean b) {
        PersistData.setAlreadyOnboarded(b);
    }

    public PositivityModel getMoodScore(final Context context) {
        return new PositivityModel(context);
    }

    public Map<Long, DailyMoodModel> getMoodData() {
        return PersistData.getMoodData();
    }
}

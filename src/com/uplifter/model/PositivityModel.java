package com.uplifter.model;

import java.util.Collection;
import java.util.Iterator;

import android.content.Context;

import com.uplifter.util.UplifterData;

public class PositivityModel extends BaseModel {
    private int _overallMood;
    private int _positivePercentage;
    private int _neutralPercentage;
    private int _negativePercentage;

    public PositivityModel(final Context context) {
        int sum = 0;
        final Collection<DailyMoodModel> moodValues = UplifterData.getInstance().getMoodData().values();
        final Iterator<DailyMoodModel> moodIterator = moodValues.iterator();
        int p = 0, n = 0;
        while(moodIterator.hasNext()) {
            final DailyMoodModel mood = moodIterator.next();
            final int moodVal = mood.getMood();
            sum += moodVal;
            switch(moodVal) {
            case 5:
            case 4:
                ++p;
                break;
            case 2:
            case 1:
                ++n;
                break;
            default:
                break;
            }
        }
        final int size = moodValues.size();
        _overallMood = (20 * sum) / size;
        _positivePercentage = (100 * p) / size;
        _negativePercentage = (100 * n) / size;
        _neutralPercentage = 100 - _positivePercentage - _negativePercentage;
    }

    public final int getOverallMood() {
        return _overallMood;
    }

    public final int getPositivePercentage() {
        return _positivePercentage;
    }

    public final int getNeutralPercentage() {
        return _neutralPercentage;
    }

    public final int getNegativePercentage() {
        return _negativePercentage;
    }
}

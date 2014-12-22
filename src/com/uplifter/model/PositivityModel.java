package com.uplifter.model;

import java.util.Collection;
import java.util.Iterator;

import android.content.Context;

import com.uplifter.util.UplifterData;

public class PositivityModel extends BaseModel {
    private int _overallMood;
    private int _size;
    private int _veryHappy;
    private int _happy;
    private int _soso;
    private int _notSoGreat;
    private int _veryUnhappy;
    private int _positivePercentage;
    private int _neutralPercentage;
    private int _negativePercentage;

    public PositivityModel(final Context context) {
        int sum = 0;
        final Collection<DailyMoodModel> moodValues = UplifterData.getInstance().getMoodData().values();
        final Iterator<DailyMoodModel> moodIterator = moodValues.iterator();
        while(moodIterator.hasNext()) {
            final DailyMoodModel mood = moodIterator.next();
            final int moodVal = mood.getMood();
            sum += moodVal;
            switch(moodVal) {
            case 5:
                ++_veryHappy;
                break;
            case 4:
                ++_happy;
                break;
            case 3:
                ++_soso;
                break;
            case 2:
                ++_notSoGreat;
                break;
            case 1:
                ++_veryUnhappy;
                break;
            default:
                break;
            }
        }
        _size = moodValues.size();
        if(_size > 0) {
            _overallMood = (20 * sum) / _size;
            _positivePercentage = (100 * (_veryHappy + _happy)) / _size;
            _negativePercentage = (100 * (_notSoGreat + _veryUnhappy)) / _size;
            _neutralPercentage = 100 - _positivePercentage - _negativePercentage;
        }
    }

    public final int getOverallMood() {
        return _overallMood;
    }

    public final int getSize() {
        return _size;
    }

    public final int getVeryHappy() {
        return _veryHappy;
    }

    public final int getHappy() {
        return _happy;
    }

    public final int getSoso() {
        return _soso;
    }

    public final int getNotSoGreat() {
        return _notSoGreat;
    }

    public final int getVeryUnhappy() {
        return _veryUnhappy;
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

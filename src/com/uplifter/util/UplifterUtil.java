package com.uplifter.util;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;

import com.uplifter.model.DateComparableModel;

public class UplifterUtil {
    public static final String getTodaysDateString() {
        return DateFormat.getDateInstance(DateFormat.LONG).format(new Date());
    }

    public static final int numberOfConsecutiveDays(final DateComparableModel [] items) {
        if(items == null || items.length == 0) {
            return 0;
        }
        Arrays.sort(items);
        int count = 1;
        long oldTime = new Date(items[0].getDate()).getTime();
        for(int i = 1; i < items.length; ++i) {
            final long newTime = new Date(items[i].getDate()).getTime();
            if(oldTime - newTime == 86400000) {
                ++count;
                oldTime = newTime;
            } else {
                break;
            }
        }
        return count;
    }
}

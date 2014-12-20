package com.uplifter.util;

import java.text.DateFormat;
import java.util.Date;

public class UplifterUtil {
    public static final String getTodaysDateString() {
        return DateFormat.getDateInstance(DateFormat.LONG).format(new Date());
    }
}

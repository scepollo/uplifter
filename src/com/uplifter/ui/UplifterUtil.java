package com.uplifter.ui;

import java.text.DateFormat;
import java.util.Date;

public class UplifterUtil {
    public static final String getTodaysDateString() {
        return DateFormat.getDateInstance(DateFormat.SHORT).format(new Date());
    }
}

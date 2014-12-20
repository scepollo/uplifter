package com.uplifter.model;

public abstract class DateComparableModel implements Comparable {
    public abstract long getDateInMillis();
    public abstract String getDate();

    @Override
    public int compareTo(final Object o) {
        if(o instanceof DailyAnswerModel) {
            final long otherDateInMillis = ((DailyAnswerModel) o).getDateInMillis();
            final long dateInMillis = getDateInMillis();
            if(dateInMillis > otherDateInMillis) {
                return -1;
            } else if(dateInMillis < otherDateInMillis) {
                return 1;
            }
        }
        return 0;
    }

}

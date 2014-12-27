package com.uplifter.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.uplifter.R;
import com.uplifter.model.TrainingModel;

public class TodaysTrainingAdapter extends ArrayAdapter<TrainingModel> {
    private final Context _context;
    private final TrainingModel [] _questions;

    public TodaysTrainingAdapter(final Context context, final TrainingModel[] questions) {
        super(context, R.layout.todays_training_layout, questions);
        _context = context;
        _questions = questions;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.todays_training_layout, parent, false);
        ((TextView) rowView.findViewById(R.id.question)).setText(_questions[position].getTitle().toUpperCase());
        ((TextView) rowView.findViewById(R.id.subtitle)).setText(_questions[position].getSubtitle());
        return rowView;
    }
}

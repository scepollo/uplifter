package com.uplifter.ui;

import java.util.Arrays;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.uplifter.R;
import com.uplifter.model.AnswerModel;
import com.uplifter.model.DailyAnswerModel;
import com.uplifter.model.TrainingModel;
import com.uplifter.util.UplifterData;

public class TrainingHistoryAdapter extends ArrayAdapter<DailyAnswerModel> {
    private static final int [] Q = { R.id.q1, R.id.q2, R.id.q3 };
    private static final int [] A = { R.id.a1, R.id.a2, R.id.a3 };

    private final Context _context;
    private final DailyAnswerModel[] _answers;

    public TrainingHistoryAdapter(final Context context, final DailyAnswerModel [] answers) {
        super(context, R.layout.training_history_layout, answers);
        _context = context;
        Arrays.sort(answers);
        _answers = answers;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.training_history_layout, parent, false);
        ((TextView) rowView.findViewById(R.id.date)).setText(_answers[position].getDate());
        final Map<Integer, TrainingModel> training = UplifterData.getInstance().getTraining(_context);
        final AnswerModel [] answers = _answers[position].getAnswers();

        for(int i = 0; i < answers.length; ++i) {
            ((TextView) rowView.findViewById(Q[i])).setText(training.get(answers[i].getIndex()).getQuestion());
            ((TextView) rowView.findViewById(A[i])).setText(answers[i].getAnswer());
        }

        return rowView;
    }
}

package com.uplifter.ui;

import java.util.Map;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableLayout;
import android.widget.TextView;

import com.uplifter.R;
import com.uplifter.model.AnswerModel;
import com.uplifter.model.DailyAnswerModel;
import com.uplifter.model.MultipartModel;
import com.uplifter.model.TrainingModel;
import com.uplifter.util.UplifterData;

public class HistoryScreen extends UplifterActivity {
    private static final int [] COLOURS = {R.color.green, R.color.purple, R.color.pink, R.color.yellow};
    private static final int SIDE_PADDING = 75;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_screen);
        final LinearLayout view = (LinearLayout) findViewById(R.id.list);
        final Resources res = getResources();
        ((TextView) findViewById(R.id.title)).setText(res.getString(R.string.history));
        // TODO:  Sort the daily answers
        final DailyAnswerModel [] dailyAnswers = UplifterData.getInstance().getTrainingHistory();
        final Map<Integer, TrainingModel> training = UplifterData.getInstance().getTraining(this);

        for(int i = 0, ii = dailyAnswers.length; i < ii; ++i) {
            final LinearLayout l = new LinearLayout(this);
            l.setOrientation(LinearLayout.VERTICAL);
            l.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            l.setBackgroundColor(res.getColor(COLOURS[i % COLOURS.length]));
            l.setPadding(SIDE_PADDING, 20, SIDE_PADDING, 50);

            populateDate(dailyAnswers[i].getDate(), l);

            for(final AnswerModel answer: dailyAnswers[i].getAnswers()) {
                final LinearLayout ll = new LinearLayout(this);
                ll.setOrientation(LinearLayout.VERTICAL);
                ll.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
                ll.setPadding(0, 20, 0, 0);
                final TrainingModel trainingModel = training.get(answer.getIndex()) ;
                if(trainingModel.getMultipart() != null) {
                    populateMultipartLayout(trainingModel, answer, ll);
                } else if(trainingModel.getQuestions() != null) {
                    populateQuestionsLayout(trainingModel, answer, ll);
                }
                l.addView(ll);
            }

            view.addView(l);
        }
    }

    private void populateDate(final String dateString, final LinearLayout l) {
        final TextView date = new TextView(this);
        date.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
        date.setText(dateString);
        date.setTextSize(12);
        date.setTextColor(getResources().getColor(R.color.white));
        date.setPadding(0, 30, 0, 0);
        date.setTypeface(Typeface.DEFAULT);
        date.setGravity(Gravity.CENTER);
        l.addView(date);
    }

    private void populateQuestionsLayout(final TrainingModel trainingModel, final AnswerModel answer, final LinearLayout l) {
        final String [] questions = trainingModel.getQuestions();
        final String [] answers = answer.getAnswers();

        for(int i = 0; i < questions.length; ++i) {
            populateQuestion(questions[i], l);
            populateAnswer(answers[i], l);
        }
    }

    private void populateMultipartLayout(final TrainingModel trainingModel, final AnswerModel answer, final LinearLayout l) {
        final MultipartModel t = trainingModel.getMultipart();
        populateQuestion(t.getQuestion(), l);
        final String [] answers = answer.getAnswers();
        for(int i = 0; i < answers.length; ++i) {
            populateAnswer(answers[i], l);
        }
    }

    private void populateAnswer(final String a, LinearLayout l) {
        getTextView(a, l);
    }

    private void populateQuestion(final String q, final LinearLayout l) {
        final TextView text = getTextView(q, l);
        text.setTypeface(Typeface.DEFAULT_BOLD);
    }

    private TextView getTextView(final String s, final LinearLayout l) {
        final TextView text = new TextView(this);
        text.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
        text.setText(s);
        text.setTextSize(12);
        text.setTextColor(getResources().getColor(R.color.white));
        text.setPadding(0, 3, 0, 0);
        text.setTypeface(Typeface.DEFAULT);
        text.setGravity(Gravity.LEFT | Gravity.CENTER);
        l.addView(text);
        return text;
    }
}

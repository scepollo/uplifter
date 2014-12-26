package com.uplifter.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uplifter.R;
import com.uplifter.model.MultipartModel;
import com.uplifter.model.TrainingModel;
import com.uplifter.util.UplifterData;

public class TrainingScreen extends UplifterActivity {
    private static final int [] COLOURS = { R.color.blue, R.color.green, R.color.yellow };
    private EditText[] _editFields;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_screen);
        final int index = ScreenController.getInstance().getCurrentTrainingIndex();
        ((View) findViewById(R.id.training_screen_header)).setBackgroundColor(getResources().getColor(COLOURS[index]));
        enableLayouts(UplifterData.getInstance().getTrainingForToday(this)[index]);
    }

    private void enableLayouts(final TrainingModel trainingModel) {
        final TextView tv = (TextView) findViewById(R.id.title);
        tv.setText(trainingModel.getTitle());
        applyBold(tv);

        if(trainingModel.getMultipart() != null) {
            populateMultipartLayout(trainingModel);
        } else if(trainingModel.getQuestions() != null) {
            populateQuestionsLayout(trainingModel);
        }
        final String [] data = UplifterData.getInstance().getCurrentScreenTrainingData();
        if(data != null) {
            for(int i = 0; i < data.length; ++i) {
                _editFields[i].setText(data[i]);
            }
        }
    }

    private final TextView getTextView(final String s) {
        final TextView tv = new UplifterTextView(this);
        tv.setText(s);
        tv.setPadding(0, 20, 0, 20);
        tv.setTextSize(16);
        applyBold(tv);
        return tv;
    }

    private final EditText getEditText() {
        final EditText et = new UplifterEditText(this);
        et.setPadding(0, 20, 0, 20);
        et.setTextSize(16);
        return et;
    }

    private final void populateMultipartLayout(final TrainingModel trainingModel) {
        final LinearLayout ll = (LinearLayout) findViewById(R.id.training_screen_body);
        final MultipartModel model = trainingModel.getMultipart();
        ll.addView(getTextView(model.getQuestion()));

        _editFields = new EditText [model.getNumber()];
        for(int i = 0; i < _editFields.length; ++i) {
            _editFields[i] = getEditText();
            ll.addView(_editFields[i]);
        }
    }

    private final void populateQuestionsLayout(final TrainingModel trainingModel) {
        final LinearLayout ll = (LinearLayout) findViewById(R.id.training_screen_body);
        final String [] questions = trainingModel.getQuestions();

        _editFields = new EditText [questions.length];
        for(int i = 0; i < _editFields.length; ++i) {
            ll.addView(getTextView(questions[i]));
            _editFields[i] = getEditText();
            ll.addView(_editFields[i]);
        }
    }

    public void goBack(final View view) {
        ScreenController.getInstance().loadPrevTrainingScreen(this, getTrainingData());
    }

    public void goForward(final View view) {
        if(verifyEditFields()) {
            ScreenController.getInstance().loadNextTrainingScreen(this, getTrainingData());
        }
    }

    private final String [] getTrainingData() {
        final String [] data = new String [_editFields.length];
        for(int i = 0, ii = data.length; i < ii; ++i) {
            data[i] = _editFields[i].getText().toString().trim();
        }
        return data;
    }

    private boolean verifyEditFields() {
        for(final EditText e: _editFields) {
            if(e.getText().toString().trim().length() == 0) {
                return false;
            }
        }
        return true;
    }
}
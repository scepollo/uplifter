package com.uplifter.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uplifter.R;
import com.uplifter.model.MultipartModel;
import com.uplifter.model.TrainingModel;
import com.uplifter.util.UplifterData;

public class TrainingScreen extends Activity {
    private static final int [] ID = { R.id.dot1, R.id.dot2, R.id.dot3 };

    private EditText[] _editFields;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActionBar a = getActionBar();
        a.setDisplayShowHomeEnabled(false);
        a.setDisplayShowTitleEnabled(false);
        a.setDisplayUseLogoEnabled(false);

        setContentView(R.layout.training_screen);
        final int index = ScreenController.getInstance().getCurrentTrainingIndex();
        ((ImageView) findViewById(ID[index])).setBackgroundResource(R.drawable.darkgrey_dot);
        enableLayouts(UplifterData.getInstance().getTrainingForToday(this)[index]);
    }

    private void enableLayouts(final TrainingModel trainingModel) {
        final LinearLayout ll = (LinearLayout) findViewById(R.id.training_screen_body);
        ll.setOrientation(LinearLayout.VERTICAL);

        final TextView tv = new TextView(this);
        tv.setText(trainingModel.getTitle());
        tv.setTextColor(getResources().getColor(R.color.white));
        ll.addView(tv);

        if(trainingModel.getMultipart() != null) {
            populateMultipartLayout(trainingModel, ll);
        } else if(trainingModel.getQuestions() != null) {
            populateQuestionsLayout(trainingModel, ll);
        }
        final String [] data = UplifterData.getInstance().getCurrentScreenTrainingData();
        if(data != null) {
            for(int i = 0; i < data.length; ++i) {
                _editFields[i].setText(data[i]);
            }
        }
    }

    private final void populateMultipartLayout(final TrainingModel trainingModel, final LinearLayout ll) {
        final MultipartModel model = trainingModel.getMultipart();
        final Resources res = getResources();

        final TextView tv = new TextView(this);
        tv.setText(model.getQuestion());
        tv.setTextColor(res.getColor(R.color.white));
        ll.addView(tv);

        _editFields = new EditText [model.getNumber()];
        for(int i = 0; i < _editFields.length; ++i) {
            _editFields[i] = new EditText(this);
            _editFields[i].setTextColor(res.getColor(R.color.white));
            ll.addView(_editFields[i]);
        }
    }

    private final void populateQuestionsLayout(final TrainingModel trainingModel, final LinearLayout ll) {
        final String [] questions = trainingModel.getQuestions();
        final Resources res = getResources();

        _editFields = new EditText [questions.length];
        for(int i = 0; i < _editFields.length; ++i) {
            final TextView tv = new TextView(this);
            tv.setText(questions[i]);
            tv.setTextColor(res.getColor(R.color.white));
            ll.addView(tv);
            _editFields[i] = new EditText(this);
            _editFields[i].setTextColor(res.getColor(R.color.white));
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

package com.uplifter.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uplifter.R;
import com.uplifter.model.TrainingModel;
import com.uplifter.util.UplifterData;

public class TrainingScreen extends UplifterActivity {
    private static final int [] COLOURS = { R.color.blue, R.color.green, R.color.yellow };
    private EditText _editField;

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

        populateQuestionsLayout(trainingModel);
        final String data = UplifterData.getInstance().getCurrentScreenTrainingData();
        if(data != null) {
            _editField.setText(data);
        }
    }

    private final TextView getTextView(final String s) {
        final TextView tv = new UplifterTextView(this);
        tv.setText(s);
        tv.setPadding(0, 20, 0, 20);
        tv.setTextSize(20);
        applyBold(tv);
        return tv;
    }

    private final EditText getEditText() {
        final EditText et = new UplifterEditText(this);
        et.setPadding(0, 20, 0, 20);
        et.setTextSize(16);
        return et;
    }

    private final void populateQuestionsLayout(final TrainingModel trainingModel) {
        final LinearLayout ll = (LinearLayout) findViewById(R.id.training_screen_body);
        final String question = trainingModel.getQuestion();

        ll.addView(getTextView(question));
        _editField = getEditText();
        ll.addView(_editField);
    }

    public void goBack(final View view) {
        ScreenController.getInstance().loadPrevTrainingScreen(this, getTrainingData());
    }

    public void goForward(final View view) {
        if(verifyEditFields()) {
            ScreenController.getInstance().loadNextTrainingScreen(this, getTrainingData());
        }
    }

    private final String getTrainingData() {
        return _editField.getText().toString().trim();
    }

    private boolean verifyEditFields() {
        return getTrainingData().length() > 0;
    }
}
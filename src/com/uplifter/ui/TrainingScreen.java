package com.uplifter.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
        if(index == 0) {
            ((ImageView) findViewById(R.id.back_arrow)).setVisibility(View.INVISIBLE);
        }
        ((View) findViewById(R.id.training_screen_header)).setBackgroundColor(getResources().getColor(COLOURS[index]));
        enableLayouts(UplifterData.getInstance().getTrainingForToday(this)[index]);
    }

    private void enableLayouts(final TrainingModel trainingModel) {
        ((TextView) findViewById(R.id.title)).setText(trainingModel.getTitle());
        ((TextView) findViewById(R.id.question)).setText(trainingModel.getQuestion());
        _editField = (EditText) findViewById(R.id.answer);

        final String data = UplifterData.getInstance().getCurrentScreenTrainingData();
        if(data != null) {
            _editField.setText(data);
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

    private final String getTrainingData() {
        return _editField.getText().toString().trim();
    }

    private boolean verifyEditFields() {
        return getTrainingData().length() > 0;
    }
}
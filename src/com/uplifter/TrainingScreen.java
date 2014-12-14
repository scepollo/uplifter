package com.uplifter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uplifter.model.MultipartModel;
import com.uplifter.model.TrainingModel;
import com.uplifter.ui.ScreenController;
import com.uplifter.util.UplifterData;

public class TrainingScreen extends Activity {
    private EditText[] _editFields;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_screen);
        enableLayouts(UplifterData.getTodaysTraining(this)[ScreenController.getInstance().getCurrentTrainingIndex()]);
    }

    private void enableLayouts(final TrainingModel trainingModel) {
        final LinearLayout ll = (LinearLayout) findViewById(R.id.training_screen_body);
        ll.setOrientation(LinearLayout.VERTICAL);

        final TextView tv = new TextView(this);
        tv.setText(trainingModel.getTitle());
        ll.addView(tv);

        if(trainingModel.getMultipart() != null) {
            populateMultipartLayout(trainingModel, ll);
        } else if(trainingModel.getQuestions() != null) {
            populateQuestionsLayout(trainingModel, ll);
        }
    }

    private final void populateMultipartLayout(final TrainingModel trainingModel, final LinearLayout ll) {
        final MultipartModel model = trainingModel.getMultipart();

        final TextView tv = new TextView(this);
        tv.setText(model.getQuestion());
        ll.addView(tv);

        _editFields = new EditText [model.getNumber()];
        for(int i = 0; i < _editFields.length; ++i) {
            _editFields[i] = new EditText(this);
            ll.addView(_editFields[i]);
        }
    }

    private final void populateQuestionsLayout(final TrainingModel trainingModel, final LinearLayout ll) {
        final String [] questions = trainingModel.getQuestions();

        _editFields = new EditText [questions.length];
        for(int i = 0; i < _editFields.length; ++i) {
            final TextView tv = new TextView(this);
            tv.setText(questions[i]);
            ll.addView(tv);
            _editFields[i] = new EditText(this);
            ll.addView(_editFields[i]);
        }
    }

    public void goBack(final View view) {
        ScreenController.getInstance().loadPrevTrainingScreen(this);
    }

    public void goForward(final View view) {
        if(verifyEditFields()) {
            saveTrainingData();
            ScreenController.getInstance().loadNextTrainingScreen(this);
        }
    }

    private void saveTrainingData() {
        final String [] data = new String [_editFields.length];
        for(int i = 0, ii = data.length; i < ii; ++i) {
            data[i] = _editFields[i].getText().toString().trim();
        }
        UplifterData.setTrainingData(data);
    }

    private boolean verifyEditFields() {
        for(final EditText e: _editFields) {
            if(e.getText().toString().trim().length() == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.training_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

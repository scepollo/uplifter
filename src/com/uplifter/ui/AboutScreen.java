package com.uplifter.ui;

import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.widget.TextView;

import com.uplifter.R;

public class AboutScreen extends UplifterActivity {
    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.about_screen);
        try {
            ((TextView) findViewById(R.id.version)).setText(getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName);
        } catch (final NameNotFoundException e) {
        }
    }
}
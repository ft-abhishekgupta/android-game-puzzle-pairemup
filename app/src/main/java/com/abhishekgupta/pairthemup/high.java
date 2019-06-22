package com.abhishekgupta.pairthemup;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class high extends AppCompatActivity {

    TextView t1, t2, t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high);
        t1 = (TextView) findViewById(R.id.textView4);
        t2 = (TextView) findViewById(R.id.textView5);
        t3 = (TextView) findViewById(R.id.textView6);
        SharedPreferences score = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int s1 = score.getInt("level" + 1, -1);
        int s2 = score.getInt("level" + 2, -1);
        int s3 = score.getInt("level" + 3, -1);
        String d1 = s1 == -1 ? "Easy - Not set yet." : "Easy - " + s1 + " seconds";
        String d2 = s2 == -1 ? "Normal - Not set yet." : "Normal - " + s2 + " seconds";
        String d3 = s3 == -1 ? "Hard - Not set yet." : "Hard - " + s3 + " seconds";
        t1.setText(d1);
        t2.setText(d2);
        t3.setText(d3);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    public void mback(View view) {
        finish();
    }
}

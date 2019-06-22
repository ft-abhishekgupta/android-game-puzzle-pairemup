package com.abhishekgupta.pairthemup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import java.util.Random;

public class game extends AppCompatActivity implements View.OnClickListener {

    public static int l = 1;
    int size = 20;
    Button b[];
    int s[];
    static char s2[];
    MediaPlayer bkk, click, wrong, right, over;
    int flag = 0, flag2 = 0, flagmute = 1, p = -1, i, c = 0, a = 0, score = 250;
    Chronometer chronometer;
    CountDownTimer c3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (l == 1) {
            setContentView(R.layout.level1);
            size = 20;
            s = new int[] { 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10 };
            s2 = new char[] { '0', '0', '1', '1', '2', '2', '3', '3', '4', '4', '5', '5', '6', '6', '7', '7', '8', '8',
                    '9', '9' };
        } else if (l == 2) {
            setContentView(R.layout.level2);
            size = 30;
            s = new int[] { 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12, 13, 13, 14,
                    14, 15, 15 };
            s2 = new char[] { '0', '0', '1', '1', '2', '2', '3', '3', '4', '4', '5', '5', '6', '6', '7', '7', '8', '8',
                    '9', '9', '/', '/', '*', '*', '+', '+', '-', '-', '@', '@' };
        } else {
            setContentView(R.layout.level3);
            size = 40;
            s = new int[] { 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12, 13, 13, 14,
                    14, 15, 15, 16, 16, 17, 17, 18, 18, 19, 19, 20, 20 };
            s2 = new char[] { '0', '0', '1', '1', '2', '2', '3', '3', '4', '4', '5', '5', '6', '6', '7', '7', '8', '8',
                    '9', '9', '/', '/', '*', '*', '+', '+', '-', '-', '@', '@', '#', '#', '$', '$', '%', '%', '&', '&',
                    '=', '=' };
        }
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        click = MediaPlayer.create(this, R.raw.click);
        wrong = MediaPlayer.create(this, R.raw.wrong);
        right = MediaPlayer.create(this, R.raw.right);
        over = MediaPlayer.create(this, R.raw.stop);
        bkk = MediaPlayer.create(this, R.raw.background);
        bkk.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer bkk) {
                bkk.start();
            }
        });
        b = initializeButtons(size);
        for (int ii = 0; ii < size; ii++) {
            b[ii].setText("~");
            b[ii].setOnClickListener(this);
        }
        shuffleArray(s);
        c3 = new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                for (int k = 0; k < size; k++) {
                    b[k].setClickable(false);
                    b[k].setText(s2[k] + "");
                }
            }
            @Override
            public void onFinish() {
                for (int k = 0; k < size; k++) {
                    b[k].setClickable(true);
                    b[k].setText("~");

                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.start();
                }
            }
        };
        c3.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bkk.stop();
        c3.cancel();
    }

    public Button[] initializeButtons(int x) {
        Resources res = getResources();
        Button[] buttons = new Button[x];
        for (int i = 0; i < x; i++) {
            String b = "button" + (i + 1);
            buttons[i] = (Button) findViewById(res.getIdentifier(b, "id", getPackageName()));
        }
        return buttons;
    }

    static void shuffleArray(int[] ar) {
        Random rnd = new Random();
        for (int k = 1; k < 100; k++) {
            for (int i = ar.length - 1; i > 0; i--) {
                int index = rnd.nextInt(i);
                int a = ar[index];
                char b = s2[index];
                ar[index] = ar[i];
                s2[index] = s2[i];
                ar[i] = a;
                s2[i] = b;
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(game.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        try {
            for (int j = 0; j < size; j++) {
                if (v.getId() == b[j].getId() && s[j] != -1 && b[j].getText().equals("~")) {
                    if (p == -1) {
                        try {
                            if (click.isPlaying()) {
                                click.stop();
                                click.release();
                                click = MediaPlayer.create(this, R.raw.click);
                            }
                            click.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        b[j].setText(s2[j] + "");
                        b[j].setTextColor(Color.parseColor("#efb239"));
                        p = j;
                    } else {
                        if ((s2[j] + "").equals(b[p].getText())) {
                            try {
                                if (right.isPlaying()) {
                                    right.stop();
                                    right.release();
                                    right = MediaPlayer.create(this, R.raw.right);
                                }
                                right.start();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            b[j].setText(s2[j] + "");
                            b[j].setTextColor(Color.parseColor("#67c03d"));
                            b[p].setTextColor(Color.parseColor("#67c03d"));
                            s[j] = -1;
                            s[p] = -1;
                            p = -1;
                            a++;
                        } else {
                            try {
                                if (wrong.isPlaying()) {
                                    wrong.stop();
                                    wrong.release();
                                    wrong = MediaPlayer.create(this, R.raw.wrong);
                                }
                                wrong.start();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            final int j2 = j;
                            CountDownTimer c2 = new CountDownTimer(300, 150) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    b[j2].setTextColor(Color.parseColor("#ef4239"));
                                    b[p].setTextColor(Color.parseColor("#ef4239"));
                                    b[j2].setText(s2[j2] + "");
                                    for (int k = 0; k < size; k++) {
                                        b[k].setClickable(false);
                                    }
                                }
                                @Override
                                public void onFinish() {
                                    b[p].setText("~");
                                    b[j2].setText("~");
                                    b[p].setTextColor(Color.parseColor("#ffffff"));
                                    b[j2].setTextColor(Color.parseColor("#ffffff"));
                                    p = -1;
                                    a++;
                                    for (int k = 0; k < size; k++) {
                                        b[k].setClickable(true);
                                    }
                                }
                            };
                            c2.start();
                        }
                    }
                }
            }
            c = 0;
            for (int j = 0; j < size; j++) {
                if (!b[j].getText().equals("~"))
                    c++;
            }
            if (c == size) {
                bkk.start();
                View.OnClickListener mOnClick = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recreate();
                    }
                };
                chronometer.stop();
                String ti = chronometer.getText().toString();
                int time = Integer.parseInt(ti.substring(0, 2)) * 60 + Integer.parseInt(ti.substring(3));
                SharedPreferences score = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                int stored = score.getInt("level" + l, -1);
                if (stored >= time || stored == -1) {
                    SharedPreferences.Editor editor = score.edit();
                    editor.putInt("level" + l, time);
                    editor.commit();
                    Snackbar.make(findViewById(android.R.id.content),
                            "Congrats!!! You took " + time + " seconds and it\'s an Highscore!!",
                            Snackbar.LENGTH_INDEFINITE).setAction("Replay", mOnClick).setActionTextColor(Color.RED)
                            .show();
                } else {
                    Snackbar.make(findViewById(android.R.id.content), "You took " + time + " seconds.",
                            Snackbar.LENGTH_INDEFINITE).setAction("Replay", mOnClick).setActionTextColor(Color.RED)
                            .show();
                }

            }
        } catch (Exception e) {
        }
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

        Intent intent = new Intent(game.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
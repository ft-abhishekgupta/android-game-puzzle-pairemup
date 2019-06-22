package com.abhishekgupta.pairthemup;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MediaPlayer win;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPause() {
        super.onPause();
        win.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        win = MediaPlayer.create(this, R.raw.win);
        win.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer bkk) {
                win.start();
            }
        });
        win.start();
    }

    public void easyg(View view) {
        Intent intent = new Intent(MainActivity.this, game.class);
        game.l = 1;
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void normalg(View view) {
        Intent intent = new Intent(MainActivity.this, game.class);
        game.l = 2;
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void hardg(View view) {
        Intent intent = new Intent(MainActivity.this, game.class);
        game.l = 3;
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void mrate(View view) {
        Toast.makeText(this, "Thankyou", Toast.LENGTH_SHORT).show();
        Uri uri = Uri.parse("market://details?id=" + getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT
                | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
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

    public void highm(View view) {
        Intent intent = new Intent(MainActivity.this, high.class);
        startActivity(intent);
    }

    public void mdev(View view) {
        Intent intent = new Intent(MainActivity.this, devv.class);
        startActivity(intent);
    }

    public void mhow(View view) {
        Intent intent = new Intent(MainActivity.this, how.class);
        startActivity(intent);
    }

    public void mback(View view) {
        System.exit(0);
    }

    public void mshare(View view) {
        final String appPackageName = getApplicationContext().getPackageName();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Check out a cool puzzle game \"Pair\'em Up\" at: https://play.google.com/store/apps/details?id="
                        + appPackageName);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}

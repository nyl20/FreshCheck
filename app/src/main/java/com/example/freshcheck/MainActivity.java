package com.example.freshcheck;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuInflater;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//public class MainActivity extends AppCompatActivity {
//    Button button;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        button = (Button) findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openNewActivity();
//            }
//        });
//    }
//    public void openNewActivity(){
//        Intent intent = new Intent(this, NewActivity.class);
//        startActivity(intent);
//    }
//}

public class MainActivity extends AppCompatActivity {
    Button visuals;
//    Button profileB;
    Button msurvey;
    TextView timerText;
    Button resetB;
    Button stopStartButton;

    boolean timerStarted = false;
    Timer timer;
    TimerTask timerTask;
    Double time = 0.0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toolbar toolBar = (Toolbar) findViewById(R.id.tools);
//        setSupportActionBar(toolBar);

        Button visualButton = (Button) findViewById(R.id.progress);

        visualButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProgress();
            }
        });

//        profileB = findViewById(R.id.profile);
//        profileB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) { openProfile(); }
//        });

        msurvey = findViewById(R.id.survey);
        msurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSurvey();
            }
        });
        timerText = (TextView) findViewById(R.id.timerText);
        stopStartButton = (Button) findViewById(R.id.circle);

        timer = new Timer();

        resetB = findViewById(R.id.reset);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public void openProgress(){
        Intent intent = new Intent(this, Progress.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void openProfile(){
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

    public void openSurvey(){
        Intent intent = new Intent(this, Survey.class);
        startActivity(intent);
    }

    public void resetTapped(View view){
        AlertDialog.Builder resetAlert = new AlertDialog.Builder(this);
        resetAlert.setTitle("Reset Timer");
        resetAlert.setMessage("Are you sure you want to save?");
        resetAlert.setPositiveButton("Save", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                // Pass data to progress
                Intent intent = new Intent(MainActivity.this, Progress.class);
                intent.putExtra("time", time);
                intent.putExtra("try", 5);
                startActivity(intent);

                if(timerTask != null){
                    timerTask.cancel();
                    time = 0.0;
                    timerStarted = false;
                    timerText.setText(formatTime(0,0,0));
                }
                timerText.setVisibility(View.INVISIBLE);
                resetB.setVisibility(View.INVISIBLE);

            }
        });


        resetAlert.setNeutralButton("Cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                // do nothing
            }
        });

        resetAlert.show();

    }

    public void startStopTapped(View view){
        if(timerStarted == false) {
            timerStarted = true;
//            timerText = (TextView) findViewById(R.id.timerText);
            timerText.setVisibility(View.VISIBLE);
            resetB.setVisibility(View.VISIBLE);
            stopStartButton.setText("PAUSE");
            startTimer();
        } else {
            timerStarted = false;
            stopStartButton.setText("START");
            timerTask.cancel();
        }
    }

    private void startTimer(){
        timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        time++;
                        timerText.setText(getTimerText());
                    }

                });
            }
        };
        timer.scheduleAtFixedRate(timerTask,0,1000);
    }

    private String getTimerText(){
        int rounded = (int) Math.round(time);
        int seconds = ((rounded % 86400) % 3600) % 60;
        int minutes = ((rounded % 86400) % 3600) / 60;
        int hours = ((rounded % 86400) / 3600);
        return formatTime(seconds, minutes, hours);
    }

    private String formatTime(int seconds, int minutes, int hours){
        return String.format("%02d",hours)+" : " + String.format("%02d",minutes)+ " : " + String.format("%02d",seconds);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_settings:
                return true;

            case R.id.action_profile:
                Intent intent = new Intent(this, Profile.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
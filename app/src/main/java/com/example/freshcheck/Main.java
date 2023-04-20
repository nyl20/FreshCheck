package com.example.freshcheck;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Main extends AppCompatActivity {
    TextView timerText;
    Button stopStartButton;

    boolean timerStarted = false;
    Timer timer;
    TimerTask timerTask;
    Double time = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerText = (TextView) findViewById(R.id.timerText);
        stopStartButton = (Button) findViewById(R.id.circle);

        timer = new Timer();
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        float savedTimerValue = prefs.getFloat("timerValue", 0.0f);
        time = (double) savedTimerValue;
        timerText.setText(formatTime(0, 0, 0));

    }

    public void resetTapped(View view){
        AlertDialog.Builder resetAlert = new AlertDialog.Builder(this);
        resetAlert.setTitle("Reset Timer");
        resetAlert.setMessage("Are you sure you want to reset the timer?");
        resetAlert.setPositiveButton("Reset", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                if(timerTask != null){
                    timerTask.cancel();
                    double timerValue = time;
                    time = 0.0;
                    timerStarted = false;
                    timerText.setText(formatTime(0,0,0));
                    // Save the timer value in SharedPreferences
                    SharedPreferences.Editor editor = getSharedPreferences("MyPrefs", MODE_PRIVATE).edit();
                    editor.putFloat("timerValue", (float) timerValue);
                    editor.apply();
                }
                timerText.setVisibility(View.INVISIBLE);
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
            changeCircle("STOP", R.color.darkOrange);
//            stopStartButton.setText("STOP");
            startTimer();
        } else {
            timerStarted = false;
            changeCircle("START", R.color.darkGreen);
//            stopStartButton.setText("START");
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

    private void changeCircle(String start, int color){
        stopStartButton.setText(start);
        stopStartButton.setTextColor(ContextCompat.getColor(this, color));
    }

}
package com.example.freshcheck;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.Timer;
import java.util.TimerTask;

public class TimerFragment extends Fragment {
    TextView timerText;
    Button stopStartButton;

    boolean timerStarted = false;
    Timer timer;
    TimerTask timerTask;
    Double time = 0.0;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_timer);
//
//        timerText = (TextView) findViewById(R.id.timerText);
//        stopStartButton = (Button) findViewById(R.id.circle);
//
//        timer = new Timer();
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_timer, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        timerText = (TextView) getView().findViewById(R.id.timerText);
        stopStartButton = (Button) getView().findViewById(R.id.circle);
        timer = new Timer();
    }

    public void resetTapped(){
        AlertDialog.Builder resetAlert = new AlertDialog.Builder(getActivity());
        resetAlert.setTitle("Reset Timer");
        resetAlert.setMessage("Are you sure you want to reset the timer?");
        resetAlert.setPositiveButton("Reset", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                if(timerTask != null){
                    timerTask.cancel();
                    time = 0.0;
                    timerStarted = false;
                    timerText.setText(formatTime(0,0,0));
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

    private void startStopTapped(){
        if(timerStarted == false) {
            timerStarted = true;
            timerText = (TextView) getView().findViewById(R.id.timerText);
            timerText.setVisibility(View.VISIBLE);
            stopStartButton.setText("STOP");
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
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {
                        time++;
                        timerText.setText(getTimerText());
                    }
                                            }

                );
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

}
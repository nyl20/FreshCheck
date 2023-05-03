package com.example.freshcheck;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

public class Profile extends AppCompatActivity {
    private TimePicker goal;
    int hours;
    int minutes;

    TextView ghrs;
    Button setGoals;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("My Profile");

        goal = findViewById(R.id.goal_picker);
        goal.setIs24HourView(true);

        ghrs = findViewById(R.id.ghr);

        hours = 0;
        minutes = 0;

        setGoals = findViewById(R.id.setGoalButton);
        setGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hours = goal.getHour();
                minutes = goal.getMinute();
                ghrs.setText("hour input" + minutes);

                Intent intent = new Intent(Profile.this, Progress.class);
                intent.putExtra("hours", hours);
                intent.putExtra("minutes", minutes);
                startActivity(intent);
            }
        });
        }



    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setGoal() {
        hours = goal.getHour();
        minutes = goal.getMinute();
//        ghrs.setText(""+hours);
//        Intent intent = new Intent(Profile.this, Progress.class);
//        intent.putExtra("hours", hours);
//        intent.putExtra("minutes", minutes);
//        startActivity(intent);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}

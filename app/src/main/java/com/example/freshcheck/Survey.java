package com.example.freshcheck;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;


public class Survey extends AppCompatActivity {
    int mood;

    ImageButton up;
    ImageButton mix;
    ImageButton down;
    LinearLayout buttons;
    TextView confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_survey);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Mood Survey");

        mood = 0;
        buttons = findViewById(R.id.thumbButtons);
        confirm = findViewById(R.id.confirmation);
        up = (ImageButton) findViewById(R.id.good);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mood = 3;
                completeSurvey(view);
            }
        });

        mix = (ImageButton) findViewById(R.id.both);
        mix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mood = 2;
                completeSurvey(view); }
        });

        down = (ImageButton) findViewById(R.id.bad);
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mood = 1;
                completeSurvey(view); }
        });

    }


    public void completeSurvey(View view){
        AlertDialog.Builder resetAlert = new AlertDialog.Builder(this);
        resetAlert.setTitle("Finish Survey");
        resetAlert.setMessage("Are you sure you want to save?");
        resetAlert.setPositiveButton("Save", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                // Pass data to progress
//                Intent intent = new Intent(Survey.this, Progress.class);
//                intent.putExtra("mood", mood);
//                startActivity(intent);

//                if(mood > 0){
//                    mood = 0;
//                }
                buttons.setVisibility(View.INVISIBLE);
                confirm.setVisibility(View.VISIBLE);

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

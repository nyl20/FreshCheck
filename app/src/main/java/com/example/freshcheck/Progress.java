package com.example.freshcheck;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Progress extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager;
    Adapter adapt;

    private ProgressBar progressBar;
    private TextView progressText;
    int current;

    int hoursGoal;
    int minsGoal;

    TextView goalText;
    TextView percentText;

//    BarChart barChart;
//    BarData barData;
//    BarDataSet barDataSet;
//
//    ArrayList barEntriesArrayList;
//
//    double amount;
//    int x;
//
//    TextView timeDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visuals);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("My Progress");


        Intent getGoals = getIntent();
        hoursGoal = getGoals.getIntExtra("hours", 2);
        minsGoal = getGoals.getIntExtra("minutes", 0);
        goalText = findViewById(R.id.goalText);
        goalText.setText("Today's Goal: " + String.format("%02d",hoursGoal)+":" + String.format("%02d",minsGoal));

        progressBar = findViewById(R.id.progress_circle);
        progressText = findViewById(R.id.progress_text);
        percentText = findViewById(R.id.percentText);

        current=50; // current mins completed hard coded to 50 for now
        // need an if statement for if no goal set, hoursGoal is null or something?
        int goalMins = hoursGoal * 60 + minsGoal; // get the goal in minutes from the goal input

        int hoursCurrent = current / 60; // should always round down for hour
        int minsCurrent;
        if (hoursCurrent < 1){
            hoursCurrent = 0; // if no hour passed, then set hour count to 0
            minsCurrent = current;
        } else { // else get the minutes after subtracting hours
            minsCurrent = current - (hoursCurrent * 60);
        }


        float prop = (float) (current)/ (float) (goalMins);
        int percentage = (int) (prop * 100); // percentage of the goal completed
        progressBar.setProgress(percentage);
        if (current < goalMins){ // current progress still less than goal
            progressText.setText("" + hoursCurrent +" hrs " + minsCurrent + " mins");
            percentText.setText("Percent Complete: " + percentage + "%");
        } else { // the goal has been met
            progressText.setText("Congrats you met the goal!");
        }


//        if (goalMins > 0) { // if the goal has been set
//            int percentage = (current/goalMins) * 100; // percentage of the goal completed
//            progressBar.setProgress(percentage);
//            if (current < goalMins){ // current progress still less than goal
//                progressText.setText("" + hoursCurrent +" hrs " + minsCurrent + " mins");
//            } else { // the goal has been met
//                progressText.setText("Congrats you met the goal!");
//            }
//        } else { // the goal has not been set, default will be 2 hours
//            progressText.setText("" + hoursCurrent +" hrs " + minsCurrent + " mins");
//        }



        tabLayout=(TabLayout) findViewById(R.id.tabs);
        viewPager=(ViewPager2) findViewById(R.id.viewPager);
        adapt= new Adapter(getSupportFragmentManager(),getLifecycle());

        adapt.addFragment(new WeekFragment());
        adapt.addFragment(new MonthFragment());
        adapt.addFragment(new YearFragment());

        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager.setAdapter(adapt);

        tabLayout.addTab(tabLayout.newTab().setText("Week"));
        tabLayout.addTab(tabLayout.newTab().setText("Month"));
        tabLayout.addTab(tabLayout.newTab().setText("Year"));
//        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);


//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override public void onTabUnselected(TabLayout.Tab tab){};

            @Override
            public void onTabReselected(TabLayout.Tab tab){

            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

//        // Get time from Intent
//        Intent intent = getIntent();
//        amount = intent.getDoubleExtra("time", 1.0);
//        x = intent.getIntExtra("try", 2);
//        timeDisplay = findViewById(R.id.textTime);
//
//        barChart = findViewById(R.id.chart1);
//        getBarEntries();
//        barDataSet = new BarDataSet(barEntriesArrayList, "progress");
//        barData = new BarData(barDataSet);
//        barChart.setData(barData);
//        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
//        barDataSet.setValueTextColor(Color.BLACK);
//        barDataSet.setValueTextSize(16f);
//        barChart.getDescription().setEnabled(false);
//
//        timeDisplay.setText(amount + "");


    }



    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

//    private void getBarEntries() {
//        barEntriesArrayList = new ArrayList<>();
//        barEntriesArrayList.add(new BarEntry(1f, (float) amount));
//        barEntriesArrayList.add(new BarEntry(2f, x));
//        barEntriesArrayList.add(new BarEntry(3f, 8));
//        barEntriesArrayList.add(new BarEntry(4f, 2));
//        barEntriesArrayList.add(new BarEntry(5f, 4));
//        barEntriesArrayList.add(new BarEntry(6f, 1));
//
//    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}


//import android.support.v7.app.AppCompatActivity;
//        import android.os.Bundle;
//public class NewActivity extends AppCompatActivity {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_new);
//    }
//}
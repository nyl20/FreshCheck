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
    int i;

    int hoursGoal;
    int minsGoal;

    TextView goalText;

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

        i=50;
        progressBar = findViewById(R.id.progress_circle);
        progressBar.setProgress(i);
        progressText = findViewById(R.id.progress_text);
        progressText.setText("" + i);


        Intent getGoals = getIntent();
        hoursGoal = getGoals.getIntExtra("hours", 0);
        minsGoal = getGoals.getIntExtra("minutes", 0);
        goalText = findViewById(R.id.goalText);
        goalText.setText("Today's Goal: " + String.format("%02d",hoursGoal)+":" + String.format("%02d",minsGoal));

        tabLayout=(TabLayout) findViewById(R.id.tabs);
        viewPager=(ViewPager2) findViewById(R.id.viewPager);
        adapt= new Adapter(getSupportFragmentManager(),getLifecycle());

        adapt.addFragment(new DayFragment());
        adapt.addFragment(new WeekFragment());
        adapt.addFragment(new YearFragment());

        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager.setAdapter(adapt);

        tabLayout.addTab(tabLayout.newTab().setText("Day"));
        tabLayout.addTab(tabLayout.newTab().setText("Week"));
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
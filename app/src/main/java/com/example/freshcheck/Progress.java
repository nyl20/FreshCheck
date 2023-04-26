package com.example.freshcheck;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Progress extends AppCompatActivity {

    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;

    ArrayList barEntriesArrayList;

    double amount;
    int x;

    TextView timeDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visuals);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Get time from Intent
        Intent intent = getIntent();
        amount = intent.getDoubleExtra("time", 1.0);
        x = intent.getIntExtra("try", 2);
        timeDisplay = findViewById(R.id.textTime);

        barChart = findViewById(R.id.chart1);
        getBarEntries();
        barDataSet = new BarDataSet(barEntriesArrayList, "progress");
        barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);

        timeDisplay.setText(amount + "");


    }

    private void getBarEntries() {
        barEntriesArrayList = new ArrayList<>();
        barEntriesArrayList.add(new BarEntry(1f, (float) amount));
        barEntriesArrayList.add(new BarEntry(2f, x));
        barEntriesArrayList.add(new BarEntry(3f, 8));
        barEntriesArrayList.add(new BarEntry(4f, 2));
        barEntriesArrayList.add(new BarEntry(5f, 4));
        barEntriesArrayList.add(new BarEntry(6f, 1));

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


//import android.support.v7.app.AppCompatActivity;
//        import android.os.Bundle;
//public class NewActivity extends AppCompatActivity {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_new);
//    }
//}
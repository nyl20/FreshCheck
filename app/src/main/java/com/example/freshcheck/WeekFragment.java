package com.example.freshcheck;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class WeekFragment extends Fragment {
    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;

    ArrayList barEntriesArrayList;
    String [] days = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
    int MAX_X = 7;

    double amount;
    int minutes;
    int x;
    int [] colors;
    Progress progress;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.week_visual, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//         Get time from Intent
        Intent intent = getActivity().getIntent();
        amount = intent.getDoubleExtra("time", 50.0);
        int rounded = (int) Math.round(amount) + 50;
        minutes = ((rounded % 86400) % 3600) / 60;
        x = intent.getIntExtra("try", 2);
        progress = (Progress) getActivity();
        colors = progress.colors;


        barChart = view.findViewById(R.id.weekChart);
        getBarEntries();
        progress.barDesign(barChart, days);
        progress.setLegend(barChart);
        barDataSet = new BarDataSet(barEntriesArrayList, "progress");
        barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(new int[] {colors[3],
                colors[2],
                colors[3],
                colors[0],
                colors[2],
                colors[1],
                colors[0]});
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);





    }

    private void barDesign() {
        barChart.getDescription().setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return days[(int) value];
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        YAxis axisLeft = barChart.getAxisLeft();
        axisLeft.setDrawGridLines(false);
//        axisLeft.setGranularity(10f);
//        axisLeft.setAxisMinimum(0);
//
//        YAxis axisRight = barChart.getAxisRight();
//        axisRight.setGranularity(10f);
//        axisRight.setAxisMinimum(0);
    }



    private void getBarEntries() {
        barEntriesArrayList = new ArrayList<>();
        barEntriesArrayList.add(new BarEntry(0f, (float) 2.8));
        barEntriesArrayList.add(new BarEntry(1f, (float) 1.2));
        barEntriesArrayList.add(new BarEntry(2f, x));
        barEntriesArrayList.add(new BarEntry(3f, (float) 0.5));
        barEntriesArrayList.add(new BarEntry(4f, (float) 2.1));
        barEntriesArrayList.add(new BarEntry(5f, (float) 0.8));
        barEntriesArrayList.add(new BarEntry(6f, (float) minutes));


    }
}

package com.example.freshcheck;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

public class YearFragment extends Fragment {
    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;

    ArrayList barEntriesArrayList;
//    String [] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

    String [] months = {"J", "F", "M", "A", "M", "J", "J", "A", "S", "O", "N", "D"};
    double amount;
    int minutes;
    int x;
    int [] colors;
    Progress progress;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.year_visual, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//         Get time from Intent
        Intent intent = getActivity().getIntent();
        amount = intent.getDoubleExtra("time", 1.0);
        int rounded = (int) Math.round(amount) + 50;
        minutes = ((rounded % 86400) % 3600) / 60;
        x = intent.getIntExtra("try", 2);

        progress = (Progress) getActivity();
        colors = progress.colors;

        barChart = view.findViewById(R.id.yearChart);
        getBarEntries();
        progress.setLegend(barChart);
//        barDesigny();
        barDataSet = new BarDataSet(barEntriesArrayList, "progress");
        barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(new int[] {ContextCompat.getColor(getActivity(), R.color.darkGreen),
                ContextCompat.getColor(getActivity(), R.color.lightGreen),
                ContextCompat.getColor(getActivity(), R.color.darkGreen),
                ContextCompat.getColor(getActivity(), R.color.lightOrange),
                ContextCompat.getColor(getActivity(), R.color.darkOrange),
                ContextCompat.getColor(getActivity(), R.color.lightGreen),
                ContextCompat.getColor(getActivity(), R.color.lightGreen),
                ContextCompat.getColor(getActivity(), R.color.darkGreen),
                ContextCompat.getColor(getActivity(), R.color.lightOrange),
                ContextCompat.getColor(getActivity(), R.color.darkOrange),
                ContextCompat.getColor(getActivity(), R.color.lightGreen),
                ContextCompat.getColor(getActivity(), R.color.darkOrange)});
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(12f);
        barChart.getDescription().setEnabled(false);


    }


    private void barDesigny() {
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return months[(int) value];
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        YAxis axisLeft = barChart.getAxisLeft();
        axisLeft.setDrawGridLines(false);
    }

    private void getBarEntries() {
        barEntriesArrayList = new ArrayList<>();
        barEntriesArrayList.add(new BarEntry(1f, 4));
        barEntriesArrayList.add(new BarEntry(2f, x));
        barEntriesArrayList.add(new BarEntry(3f, 2));
        barEntriesArrayList.add(new BarEntry(4f, 8));
        barEntriesArrayList.add(new BarEntry(5f, 7));
        barEntriesArrayList.add(new BarEntry(6f, 3));
        barEntriesArrayList.add(new BarEntry(7f, 7));
        barEntriesArrayList.add(new BarEntry(8f, 2));
        barEntriesArrayList.add(new BarEntry(9f, 7));
        barEntriesArrayList.add(new BarEntry(10f, 5));
        barEntriesArrayList.add(new BarEntry(11f, 6));
        barEntriesArrayList.add(new BarEntry(12f, (float) minutes));

    }
}

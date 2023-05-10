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

import java.util.ArrayList;

public class MonthFragment extends Fragment {
    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;

    ArrayList barEntriesArrayList;

    double amount;
    int minutes;
    int x;
    int [] colors;
    Progress progress;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.month_visual, container, false);
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

        barChart = view.findViewById(R.id.monthChart);
        getBarEntries();
        progress.setLegend(barChart);
        barDataSet = new BarDataSet(barEntriesArrayList, "progress");
        barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(new int[] {ContextCompat.getColor(getActivity(), R.color.darkGreen),
                ContextCompat.getColor(getActivity(), R.color.lightGreen),
                ContextCompat.getColor(getActivity(), R.color.darkGreen),
                ContextCompat.getColor(getActivity(), R.color.lightOrange),
                ContextCompat.getColor(getActivity(), R.color.darkOrange)});
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);


    }



    private void getBarEntries() {
        barEntriesArrayList = new ArrayList<>();
        barEntriesArrayList.add(new BarEntry(1f, 1));
        barEntriesArrayList.add(new BarEntry(2f, x));
        barEntriesArrayList.add(new BarEntry(3f, 8));
        barEntriesArrayList.add(new BarEntry(4f, (float) minutes));

    }
}

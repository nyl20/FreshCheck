package com.example.freshcheck;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class WeekFragment extends Fragment {
    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;

    ArrayList barEntriesArrayList;

    double amount;
    int x;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.week_visual, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//         Get time from Intent
        Intent intent = getActivity().getIntent();
        amount = intent.getDoubleExtra("time", 1.0);
        x = intent.getIntExtra("try", 2);

        barChart = view.findViewById(R.id.weekChart);
        getBarEntries();
        barDataSet = new BarDataSet(barEntriesArrayList, "progress");
        barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);


    }

    private void getBarEntries() {
        barEntriesArrayList = new ArrayList<>();
        barEntriesArrayList.add(new BarEntry(1f, (float) amount));
        barEntriesArrayList.add(new BarEntry(2f, x));
        barEntriesArrayList.add(new BarEntry(3f, 8));
        barEntriesArrayList.add(new BarEntry(4f, 3));
        barEntriesArrayList.add(new BarEntry(5f, 9));
        barEntriesArrayList.add(new BarEntry(6f, 3));

    }
}

package dev.raghav.civilgate.Reports;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//
//import com.github.mikephil.charting.charts.PieChart;
//import com.github.mikephil.charting.data.Entry;
//import com.github.mikephil.charting.data.PieData;
//import com.github.mikephil.charting.data.PieDataSet;
//import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import dev.raghav.civilgate.R;


public class Score_Card extends Fragment {
  //  PieChartView pieChartView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.advance_level_layout, container, false);

        return view;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}

package dev.raghav.civilgate;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class BookmarkActivity extends AppCompatActivity {

    BarChart chart;
    private ArrayList<BarEntry> categories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);


        BarEntry e = new BarEntry(50,60,70);
        categories.add(e);


        chart = findViewById(R.id.comp_chart);
        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

            }

            @Override
            public void onNothingSelected() {

            }
        });

        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);
        chart.setMaxVisibleValueCount(60);
        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);
        chart.setDrawGridBackground(false);


        Legend legend = chart.getLegend();
        legend.setEnabled(false);

        ArrayList<BarEntry> valueSet1 = new ArrayList<BarEntry>();

        ArrayList<String> ylabels = new ArrayList<>();
        for (int i = 0; i < 6; ++i) {
            BarEntry entry = new BarEntry(i, (i + 1) * 2);
            valueSet1.add(entry);
            ylabels.add(" " + i);
        }

       // List<IBarDataSet> dataSets = new ArrayList<>();
        BarDataSet barDataSet = new BarDataSet(valueSet1, " ");
        barDataSet.setColor(Color.CYAN);
        barDataSet.setDrawValues(true);
       // dataSets.add(barDataSet);


        BarData data = new BarData(ylabels);
        //data.setBarWidth(0.4f);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);
        chart.setData(data);
        //chart.setFitBars(true);
        chart.invalidate();




    }
}

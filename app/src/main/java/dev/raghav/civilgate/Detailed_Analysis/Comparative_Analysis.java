package dev.raghav.civilgate.Detailed_Analysis;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import dev.raghav.civilgate.R;

public class Comparative_Analysis extends Fragment {
    BarChart toppercahrt;
    private BarChart chart;
//    private SeekBar seekBarX, seekBarY;
    private TextView tvX, tvY;

    protected Typeface tfRegular;
    protected Typeface tfLight;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View view = inflater.inflate(R.layout.comparitive_analysis ,container ,false);
//        toppercahrt = view.findViewById(R.id.toppercahrt);
        chart = view.findViewById(R.id.comp_chart);
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
//        chart.getDescription().setEnabled(false);
        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawGridBackground(false);
        // chart.setDrawYLabels(false);



//        AnyChartView anyChartView = view.findViewById(R.id.toppercahrt);
////        anyChartView.setProgressBar(findViewById(R.id.progress_bar));
//
//        Cartesian cartesian = AnyChart.column();
//
//        List<DataEntry> data = new ArrayList<>();
//        data.add(new ValueDataEntry("Rouge", 80));
//        data.add(new ValueDataEntry("Foundation", 90));
//        data.add(new ValueDataEntry("Mascara", 50));
//        data.add(new ValueDataEntry("Lip gloss", 60));
//        data.add(new ValueDataEntry("Lipstick", 40));
//        data.add(new ValueDataEntry("Nail polish", 70));
//        data.add(new ValueDataEntry("Eyebrow pencil", 30));
//        data.add(new ValueDataEntry("Eyeliner", 20));
//        data.add(new ValueDataEntry("Eyeshadows", 90));
//
//        Column column = cartesian.column(data);
//
//        column.tooltip()
//                .titleFormat("{%X}")
//                .position(Position.CENTER_BOTTOM)
//                .anchor(Anchor.CENTER_BOTTOM)
//                .offsetX(0d)
//                .offsetY(5d)
//                .format("{%Value}{groupsSeparator: }");
//
//        cartesian.animation(true);
//        cartesian.title("Top 10 List");
//
//        cartesian.yScale().minimum(0d);
//
//        cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }");
//
//        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
//        cartesian.interactivity().hoverMode(HoverMode.BY_X);
//
//        cartesian.xAxis(0).title("%");
//        cartesian.yAxis(0).title("Participants");
//
//        anyChartView.setChart(cartesian);
//        ArrayList NoOfEmp = new ArrayList();
//
//        NoOfEmp.add(new BarEntry(945f, 0));
//        NoOfEmp.add(new BarEntry(1040f, 1));
//        NoOfEmp.add(new BarEntry(1133f, 2));
//        NoOfEmp.add(new BarEntry(1240f, 3));
//        NoOfEmp.add(new BarEntry(1369f, 4));
//        NoOfEmp.add(new BarEntry(1487f, 5));
//        NoOfEmp.add(new BarEntry(1501f, 6));
//        NoOfEmp.add(new BarEntry(1645f, 7));
//        NoOfEmp.add(new BarEntry(1578f, 8));
//        NoOfEmp.add(new BarEntry(1695f, 9));
//
//        ArrayList year = new ArrayList();
////
//        year.add("2008");
//        year.add("2009");
//        year.add("2010");
//        year.add("2011");
//        year.add("2012");
//        year.add("2013");
//        year.add("2014");
//        year.add("2015");
//        year.add("2016");
//        year.add("2017");
//
//        BarDataSet bardataset = new BarDataSet(NoOfEmp, "No Of Employee");
//        toppercahrt.animateY(5000);
//        BarData data = new BarData(year, bardataset);
//  //    BarData data = new BarData();
//        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
//        toppercahrt.setData(data);
        return view;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}

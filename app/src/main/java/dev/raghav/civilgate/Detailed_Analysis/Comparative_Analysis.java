package dev.raghav.civilgate.Detailed_Analysis;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


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

import dev.raghav.civilgate.Activities.Profile_Activity;
import dev.raghav.civilgate.Api.Api;
import dev.raghav.civilgate.Api.Retro_Urls;
import dev.raghav.civilgate.Const_Files.CompareGraph;
import dev.raghav.civilgate.Const_Files.Percentage;
import dev.raghav.civilgate.R;
import dev.raghav.civilgate.SessionManage.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static dev.raghav.civilgate.Detailed_Analysis.Detailed_Analysis.levelid;

public class Comparative_Analysis extends Fragment {
    BarChart toppercahrt;
    private BarChart barChart;
//    private SeekBar seekBarX, seekBarY;
    private TextView tvX, tvY;
    private ArrayList<BarEntry> categories = new ArrayList<>();
    private List<BarEntry> entries = new ArrayList<>();
    public int Color[] = {android.graphics.Color.GREEN ,android.graphics.Color.YELLOW,android.graphics.Color.RED,android.graphics.Color.BLUE,
            android.graphics.Color.BLACK};
    protected Typeface tfRegular;
    protected Typeface tfLight;
    private SessionManager sessionManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View view = inflater.inflate(R.layout.comparitive_analysis ,container ,false);
//        toppercahrt = view.findViewById(R.id.toppercahrt);
        sessionManager = new SessionManager(getActivity());
        barChart = (BarChart) view.findViewById(R.id.comp_chart);
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(Retro_Urls.The_Base).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api RegApi = RetroLogin.create(Api.class);
        Call<CompareGraph> login_responceCall = RegApi.Toppers_list(levelid,String.valueOf(sessionManager.getCoustId()));
        login_responceCall.enqueue(new Callback<CompareGraph>() {
            @Override
            public void onResponse(Call<CompareGraph> call, Response<CompareGraph> response) {
                Log.d("string" , ""+response.body().getResponce());
//                            Log.d("string" , ""+response.body().getData().getEmail());
                if(response.body().getResponce() ==true)
                {

                    ArrayList<BarEntry> bargroup2 = new ArrayList<>();
                    for(int i=0;i<response.body().getData().size();i++)
                    {
                        for(int j=0;j<response.body().getData().size();j++)
                        {
                            if(response.body().getData().get(i).getRank() > response.body().getData().get(j).getRank())
                            {
                                bargroup2.add(new BarEntry(Integer.valueOf(response.body().getData().get(i).getTotalmark()), 0));
                            }

                        }

//                        bargroup2.add(new BarEntry(10f, 1));
//                        bargroup2.add(new BarEntry(5f, 2));
//                        bargroup2.add(new BarEntry(25f, 3));
//                        bargroup2.add(new BarEntry(4f, 4));

                    }



// creating dataset for Bar Group1
                    //     BarDataSet barDataSet1 = new BarDataSet(bargroup1, "Bar Group 1");

//barDataSet1.setColor(Color.rgb(0, 155, 0));
                    //    barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);

// creating dataset for Bar Group 2
                    BarDataSet barDataSet2 = new BarDataSet(bargroup2, "My Rank");
                    barDataSet2.setColors(Color);

                    ArrayList<String> labels = new ArrayList<String>();
                    labels.add("2016");
                    labels.add("2015");
                    labels.add("2014");
                    labels.add("2013");
                    labels.add("2012");
                    labels.add("2011");


                    BarData data = new BarData(labels,barDataSet2);

                    barChart.setData(data); // set the data and list of labels into chart

//                    Intent intent=new Intent(ShowAllPakages.this,MainActivity.class);
//                    //manager.serverLogin(response.body().getData().getId() , response.body().getData().getName(),response.body().getData().getStatus());
//                    intent.putExtra("respoce", ""+response);
//                    startActivity(intent);
//                    finish();
                }else{
                    Toast.makeText(getActivity(), "Either Email is wrong or Password", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CompareGraph> call, Throwable t) {

                Log.d("cause" , ""+t.getMessage());
                Log.d("cause" , ""+t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Network problem", Toast.LENGTH_SHORT).show();

            }
        });

        // create BarEntry for Bar Group 1
//        ArrayList<BarEntry> bargroup1 = new ArrayList<>();
//        bargroup1.add(new BarEntry(8f, 0));
//        bargroup1.add(new BarEntry(2f, 1));
//        bargroup1.add(new BarEntry(5f, 2));
//        bargroup1.add(new BarEntry(20f, 3));
//        bargroup1.add(new BarEntry(15f, 4));
//        bargroup1.add(new BarEntry(19f, 5));

// create BarEntry for Bar Group 1



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

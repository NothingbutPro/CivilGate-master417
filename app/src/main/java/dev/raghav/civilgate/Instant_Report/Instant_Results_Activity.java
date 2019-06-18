package dev.raghav.civilgate.Instant_Report;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import dev.raghav.civilgate.Api.Api;
import dev.raghav.civilgate.Api.Retro_Urls;
import dev.raghav.civilgate.Other_Parsing_Files.Instant_Report;
import dev.raghav.civilgate.R;
import dev.raghav.civilgate.SessionManage.SessionManager;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static dev.raghav.civilgate.Test_Activities.Main_Test_Activity.queposition;

public class Instant_Results_Activity extends AppCompatActivity {
    PieChart pieChart;
    int coustId;
    String sub_leve_id;
    TextView totcandidate,totalQue,duration,rightmark,negative,left,mytime,unproductive,idleTime,mark ;
    TextView leftans ,correctans ,wrongans ;
    int level_id;
    ArrayList NoOfEmp = new ArrayList();
    int Colors[] = {Color.GREEN , Color.RED ,Color.YELLOW};
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instant__results);
        pieChart = findViewById(R.id.piechart);

        totcandidate = findViewById(R.id.totcandidate);
        rightmark = findViewById(R.id.rightmark);
        negative = findViewById(R.id.negative);
        left = findViewById(R.id.left);
        mytime = findViewById(R.id.mytime);
        unproductive = findViewById(R.id.unproductive);
        idleTime = findViewById(R.id.idleTime);
        duration = findViewById(R.id.duration);
        mark = findViewById(R.id.mark);
        totalQue = findViewById(R.id.totalQue);
        coustId = getIntent().getIntExtra("coustId" ,0);
        sub_leve_id = getIntent().getStringExtra("sub_leve_id");
        level_id = getIntent().getIntExtra("level_id" ,200);
        sessionManager = new SessionManager(this);
        ShowMeResult(coustId , sub_leve_id , level_id);
        leftans = findViewById(R.id.leftans);
        correctans = findViewById(R.id.correctans);
        wrongans = findViewById(R.id.wrongans);
//        NoOfEmp.add(new Entry(945f, 0));
//        NoOfEmp.add(new Entry(1040f, 1));
//        NoOfEmp.add(new Entry(1133f, 2));
//
//        PieDataSet dataSet = new PieDataSet(NoOfEmp, "");
//
//        ArrayList year = new ArrayList();
//
//        year.add("Correct");
//        year.add("Incorrect");
//        year.add("Left");
//
//        PieData data = new PieData(year, dataSet);
//        pieChart.setData(data);
//        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//        pieChart.animateXY(5000, 5000);


    }

    private void ShowMeResult(int coustId, String sub_leve_id, int level_id)
    {
                OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(150, TimeUnit.SECONDS)
                .readTimeout(300,TimeUnit.SECONDS).writeTimeout(200 , TimeUnit.SECONDS).build();
        ProgressDialog ExamprogressDialog;
        ExamprogressDialog = new ProgressDialog(Instant_Results_Activity.this);
        ExamprogressDialog.setMax(100);
        ExamprogressDialog.setTitle("Generating the report");
        ExamprogressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        ExamprogressDialog.setCancelable(false);
        ExamprogressDialog.show();
        Retrofit RetroGEtExam = new Retrofit.Builder()
                .baseUrl(Retro_Urls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api EmamApi = RetroGEtExam.create(Api.class);
        Call<Instant_Report> exam_testCall = EmamApi.EndTest(sessionManager.getCoustId(), sub_leve_id,level_id);
        exam_testCall.enqueue(new Callback<Instant_Report>() {
            @Override
            public void onResponse(Call<Instant_Report> call, Response<Instant_Report> response) {

                //  Toast.makeText(getActivity(), "Test name"+response.body().getData().get(0).getTestName(), Toast.LENGTH_SHORT).show();
                if(response.isSuccessful())
                {
                    ExamprogressDialog.dismiss();
               //    Boolean res = response.body().getCorrect();
               //    Log.e("last responce is" , "  "+res);
//                   int s = response.body().getData().get(0).getMarks();


                    if(response.isSuccessful() )
                    {
                        Log.e("las" , " submited marks"+response.body().getResponce());
                        Log.e("las" , " duration marks"+response.body().getData().getDuration());
                     //   idleTime.setText(String.valueOf(response.body().getData().getIdleTime()));

                       totcandidate.setText(String.valueOf(response.body().getData().getTotcandidate()));
                       totalQue.setText(String.valueOf(response.body().getData().getTotalQue()));
                       duration.setText(String.valueOf(response.body().getData().getDuration()));
                       rightmark.setText(String.valueOf(response.body().getData().getRightmark()));
                        negative.setText(String.valueOf(response.body().getData().getNegative()));
                       left.setText(String.valueOf(response.body().getData().getLeft()));
                       mytime.setText(String.valueOf(response.body().getData().getMytime()));
                        unproductive.setText(String.valueOf(response.body().getData().getUnproductive()));
                        mark.setText(String.valueOf(response.body().getData().getMark()));
                      //  idleTime.setText(String.valueOf(response.body().getData().getIdleTime()));
                        Log.e("leftedddd" , "submited marks"+response.body().getData().getLeft());
                        idleTime.setText(String.valueOf(response.body().getData().getIdleTime()));
                        totcandidate.setText(String.valueOf(response.body().getData().getTotcandidate()));
                        totalQue.setText(String.valueOf(response.body().getData().getTotalQue()));
                        duration.setText(String.valueOf(response.body().getData().getDuration()));
                        rightmark.setText(String.valueOf(response.body().getData().getRightmark()));
                        negative.setText(String.valueOf(response.body().getData().getNegative()));
                        left.setText(String.valueOf(response.body().getData().getLeft()));
                        mytime.setText(String.valueOf(response.body().getData().getMytime()));
                        unproductive.setText(String.valueOf(response.body().getData().getUnproductive()));
                        idleTime.setText(String.valueOf(response.body().getData().getIdleTime()));
                        correctans.setText(String.valueOf(response.body().getData().getCorrect()));
                        leftans.setText(String.valueOf(response.body().getData().getLeft()));
                        wrongans.setText(String.valueOf(response.body().getData().getIncorrect()));
                        NoOfEmp.add(new Entry(response.body().getData().getCorrect(), 0));
                        NoOfEmp.add(new Entry(response.body().getData().getIncorrect(), 1));
                        NoOfEmp.add(new Entry(response.body().getData().getLeft(), 2));

                        PieDataSet dataSet = new PieDataSet(NoOfEmp, "");

                        ArrayList year = new ArrayList();

                        year.add("Correct");
                        year.add("Incorrect");
                        year.add("Left");

                        PieData data = new PieData(year, dataSet);
                        pieChart.setData(data);
                        dataSet.setColors(ColorTemplate.createColors(Colors));
                        pieChart.animateXY(5000, 5000);
 //                       Intent to_result = new Intent(Instant_Results_Activity.this , Instant_Results_Activity.class);
//                       startActivity(to_result);
                        Toast.makeText(Instant_Results_Activity.this, "test successfully submit", Toast.LENGTH_SHORT ).show();

                  //      System.gc();
                   //     finish();

                    }
                    else
                        {


                    }
//
                }

            }

            @Override
            public void onFailure(Call<Instant_Report> call, Throwable t) {
                ExamprogressDialog.dismiss();
                Log.w("MyTag", "requestFailed"+t);
                Log.w("MyTag", "requestFailed"+t.getLocalizedMessage());
                Log.w("MyTag", "requestFailed"+t.getMessage());
                //            Log.w("MyTag", "requestFailed "+ call.clone().isExecuted());

            }
        });
    }

    @Override
    public void onBackPressed() {
        queposition =0;
        super.onBackPressed();
    }
}

package dev.raghav.civilgate.Detailed_Analysis;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import dev.raghav.civilgate.Instant_Report.Instant_Results_Activity;
import dev.raghav.civilgate.Other_Parsing_Files.Instant_Report;
import dev.raghav.civilgate.Other_Parsing_Files.Score_Result;
import dev.raghav.civilgate.R;
import dev.raghav.civilgate.SessionManage.SessionManager;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static dev.raghav.civilgate.Activities.Direct_History.levelid;
import static dev.raghav.civilgate.Activities.Direct_History.sublevelid;
import static dev.raghav.civilgate.Test_Activities.Main_Test_Activity.queposition;

public class Score_Card extends Fragment {
    PieChart pieChart;
    int coustId;
    String sub_leve_id;
    TextView totcandidate,totalQue,duration,rightmark,negative,left,mytime,unproductive,idleTime,mark ;
    TextView leftans ,correctans ,wrongans ;
    int level_id;
    ArrayList NoOfEmp = new ArrayList();
    int Colors[] = {Color.GREEN , Color.RED ,Color.YELLOW};
    SessionManager sessionManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.score_card , container,false);
        pieChart = view.findViewById(R.id.piechart);

        totcandidate = view.findViewById(R.id.totcandidate);
        rightmark = view.findViewById(R.id.rightmark);
        negative = view.findViewById(R.id.negative);
        left = view.findViewById(R.id.left);
        mytime = view.findViewById(R.id.mytime);
        unproductive = view.findViewById(R.id.unproductive);
        idleTime = view.findViewById(R.id.idleTime);
        duration = view.findViewById(R.id.duration);
        mark = view.findViewById(R.id.mark);
        totalQue = view.findViewById(R.id.totalQue);
        coustId = getActivity().getIntent().getIntExtra("coustId" ,0);
        //coustId = getActivity().getIntent().getIntExtra("coustId" ,0);
        sub_leve_id = sublevelid;
       // sub_leve_id = getActivity().getIntent().getStringExtra("sub_leve_id");
        level_id = Integer.valueOf(levelid);
     //   level_id = getActivity().getIntent().getIntExtra("level_id" ,200);
        sessionManager = new SessionManager(getActivity());
        ShowMeResult(coustId , sub_leve_id , level_id);
        leftans = view.findViewById(R.id.leftans);
        correctans = view.findViewById(R.id.correctans);
        wrongans = view.findViewById(R.id.wrongans);
        return view;
//         return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void ShowMeResult(int coustId, String sub_leve_id, int level_id) {
        Log.e("sub_leve_id at score" , "is "+sub_leve_id);
        Log.e("level_id ats core" , "is "+level_id);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(150, TimeUnit.SECONDS)
                .readTimeout(300,TimeUnit.SECONDS).writeTimeout(200 , TimeUnit.SECONDS).build();
        ProgressDialog ExamprogressDialog;
        ExamprogressDialog = new ProgressDialog(getActivity());
        ExamprogressDialog.setMax(100);
        ExamprogressDialog.setTitle("Generating the report");
        ExamprogressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        ExamprogressDialog.setCancelable(false);
        ExamprogressDialog.show();
        Retrofit RetroGEtExam = new Retrofit.Builder()
                .baseUrl(Retro_Urls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api EmamApi = RetroGEtExam.create(Api.class);
        Call<Score_Result> exam_testCall = EmamApi.SCORE_RESULT_CALL(sessionManager.getCoustId(), sub_leve_id,level_id);
        exam_testCall.enqueue(new Callback<Score_Result>() {
            @Override
            public void onResponse(Call<Score_Result> call, Response<Score_Result> response) {

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
                        Log.e("las" , " duration marks"+response.body().getData().get(0));
                        //   idleTime.setText(String.valueOf(response.body().getData().getIdleTime()));

                            totcandidate.setText(String.valueOf(response.body().getData().get(0).getTotcandidate()));
                            totalQue.setText(String.valueOf(response.body().getData().get(0).getTotalQue()));
                            duration.setText(String.valueOf(response.body().getData().get(0).getDuration()));
                            rightmark.setText(String.valueOf(response.body().getData().get(0).getRightmark()));
                            negative.setText(String.valueOf(response.body().getData().get(0).getNegative()));
                            left.setText(String.valueOf(response.body().getData().get(0).getLeft()));
                            mytime.setText(String.valueOf(response.body().getData().get(0).getMytime()));
                            unproductive.setText(String.valueOf(response.body().getData().get(0).getUnproductive()));
                            mark.setText(String.valueOf(response.body().getData().get(0).getMark()));
                            //  idleTime.setText(String.valueOf(response.body().getData().getIdleTime()));
                            Log.e("leftedddd", "submited marks" + response.body().getData().get(0).getLeft());
                            idleTime.setText(String.valueOf(response.body().getData().get(0).getIdleTime()));
                            totcandidate.setText(String.valueOf(response.body().getData().get(0).getTotcandidate()));
                            totalQue.setText(String.valueOf(response.body().getData().get(0).getTotalQue()));
                            duration.setText(String.valueOf(response.body().getData().get(0).getDuration()));
                            rightmark.setText(String.valueOf(response.body().getData().get(0).getRightmark()));
                            negative.setText(String.valueOf(response.body().getData().get(0).getNegative()));
                            left.setText(String.valueOf(response.body().getData().get(0).getLeft()));
                            mytime.setText(String.valueOf(response.body().getData().get(0).getMytime()));
                            unproductive.setText(String.valueOf(response.body().getData().get(0).getUnproductive()));
                            idleTime.setText(String.valueOf(response.body().getData().get(0).getIdleTime()));
                            correctans.setText(String.valueOf(response.body().getData().get(0).getCorrect()));
                            leftans.setText(String.valueOf(response.body().getData().get(0).getLeft()));
                            wrongans.setText(String.valueOf(response.body().getData().get(0).getIncorrect()));
                            NoOfEmp.add(new Entry(Integer.valueOf(response.body().getData().get(0).getCorrect()), 0));
                            NoOfEmp.add(new Entry(Integer.valueOf(response.body().getData().get(0).getIncorrect()), 1));
                            NoOfEmp.add(new Entry(Integer.valueOf(response.body().getData().get(0).getLeft()), 2));

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
                            Toast.makeText(getActivity(), "test successfully submit", Toast.LENGTH_SHORT).show();

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
            public void onFailure(Call<Score_Result> call, Throwable t) {
                ExamprogressDialog.dismiss();
                Log.w("MyTag", "requestFailed"+t);
                Log.w("MyTag", "requestFailed"+t.getLocalizedMessage());
                Log.w("MyTag", "requestFailed"+t.getMessage());
                //            Log.w("MyTag", "requestFailed "+ call.clone().isExecuted());

            }
        });


    }
}

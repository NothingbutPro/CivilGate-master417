package dev.raghav.civilgate.Frag_granades;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import dev.raghav.civilgate.Api.Api;
import dev.raghav.civilgate.Api.Retro_Urls;
import dev.raghav.civilgate.Const_Files.Percentage;
import dev.raghav.civilgate.Const_Files.Tests_Name;
import dev.raghav.civilgate.Dapter.Test_Adapter;
import dev.raghav.civilgate.Other_Parsing_Files.Exam_Test;
import dev.raghav.civilgate.R;
import dev.raghav.civilgate.SessionManage.SessionManager;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Subject_Wise_Level_Test_Fragment extends Fragment {
    SessionManager manager;
    TextView cred_mie;
    Test_Adapter testAdapter;
    RecyclerView tests_recy_sub;

    Thread thread;
    int  px =0;
    int[] p = new  int[20];
    private List<Tests_Name> tests_namessub = new ArrayList<>();
    private TextView questions,groupmarks,grouptesttime,groupexpiry;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View Subjec_wiseView  = inflater.inflate(R.layout.subjectwise_level_layout , container , false);
        manager = new SessionManager(getActivity());
        cred_mie = Subjec_wiseView.findViewById(R.id.cred_mie);
        tests_recy_sub = Subjec_wiseView.findViewById(R.id.tests_recy_sub);
        questions = Subjec_wiseView.findViewById(R.id.questions);
        groupmarks = Subjec_wiseView.findViewById(R.id.groupmarks);
        grouptesttime = Subjec_wiseView.findViewById(R.id.grouptesttime);
        groupexpiry = Subjec_wiseView.findViewById(R.id.groupexpiry);
        getAllLowerLevels();
        Get_Percentage();
            return Subjec_wiseView;
    }
    private void Get_Percentage() {
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(Retro_Urls.The_Base).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api RegApi = RetroLogin.create(Api.class);
        Call<Percentage> login_responceCall = RegApi.PERCENTAGE_CALL(manager.getCoustId());
        login_responceCall.enqueue(new Callback<Percentage>() {
            @Override
            public void onResponse(Call<Percentage> call, Response<Percentage> response) {
                Log.d("string" , ""+response.body().getResponce());
//                            Log.d("string" , ""+response.body().getData().getEmail());
                if(response.body().getResponce())
                {
                    Toast.makeText(getActivity(), "percentage is"+response.body().getData(), Toast.LENGTH_SHORT).show();
                    Log.e("per" , "percentage is"+response.body().getData());
                    cred_mie.setText(cred_mie.getText().toString().concat(" "+response.body().getData()));
//                    Toast.makeText(ShowAllPakages.this, "Login successful", Toast.LENGTH_SHORT).show();


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
            public void onFailure(Call<Percentage> call, Throwable t) {

                Log.d("cause" , ""+t.getMessage());
                Toast.makeText(getActivity(), "Network problem", Toast.LENGTH_SHORT).show();

            }
        });

    }
    private boolean getAllLowerLevels() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(150, TimeUnit.SECONDS)
                .readTimeout(300,TimeUnit.SECONDS).writeTimeout(200 , TimeUnit.SECONDS).build();
        ProgressDialog ExamprogressDialog;
        ExamprogressDialog = new ProgressDialog(getActivity());
        ExamprogressDialog.setMax(100);
        ExamprogressDialog.setTitle("Getting your level information");
        ExamprogressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        ExamprogressDialog.setCancelable(false);
        ExamprogressDialog.show();
        Retrofit RetroGEtExam = new Retrofit.Builder()
                .baseUrl(Retro_Urls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api EmamApi = RetroGEtExam.create(Api.class);
        Call<Exam_Test> exam_testCall = EmamApi.Get_GetExam(5 ,manager.getCoustId());
        exam_testCall.enqueue(new Callback<Exam_Test>() {
            @Override
            public void onResponse(Call<Exam_Test> call, Response<Exam_Test> response) {

                //  Toast.makeText(getActivity(), "Test name"+response.body().getData().get(0).getTestName(), Toast.LENGTH_SHORT).show();
                if(response.isSuccessful())
                {
                    ExamprogressDialog.dismiss();
                    if(response.body().getResponce() == true)
                    {
                        Log.e("elements" , " are "+response.body().getData());
                        p =new int[response.body().getData().size()];
                        questions.setText(questions.getText().toString().concat(" "+response.body().getData().get(0).getTotalQue()));
                        groupmarks.setText(groupmarks.getText().toString().concat(" "+response.body().getData().get(0).getTotalMark()));
                        grouptesttime.setText(grouptesttime.getText().toString().concat(" "+response.body().getData().get(0).getTesttime()).concat(" min"));
                        groupexpiry.setText(groupexpiry.getText().toString().concat(" "+response.body().getData().get(0).getTestEndDate()));
                        for(int i=0;i<response.body().getData().size();i++)
                        {
                            Log.e("id" , ""+response.body().getData().get(i).getId());
                            px= px+1;
//                            p[0] =i;
                            String s = response.body().getData().get(i).getTestName();
                            Log.e("elements" , "are"+response.body().getData().get(0).getTestName());
                            Tests_Name tests_name = new Tests_Name(response.body().getData().get(i).getTestName() ,Integer.valueOf(response.body().getData().get(i).getTesttime()) , response.body().getData().get(i).getSubjectIds(),response.body().getData().get(i).getCatId(),response.body().getData().get(i).getQStatus(),response.body().getData().get(i).getId() );
                            tests_namessub.add(tests_name);
                        }

//                        if(p[0] >=1)
//                        {

                        testAdapter = new Test_Adapter(tests_namessub);
                        //      RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                        llm.setOrientation(LinearLayoutManager.VERTICAL);
                        tests_recy_sub.setLayoutManager(llm);
                        tests_recy_sub.setAdapter(testAdapter);

//                        }

                    }
//
                }

            }

            @Override
            public void onFailure(Call<Exam_Test> call, Throwable t) {
                ExamprogressDialog.dismiss();

                Log.w("MyTag", "requestFailed"+t);
                Log.w("MyTag", "requestFailed"+t.getLocalizedMessage());
                Log.w("MyTag", "requestFailed"+t.getMessage());
                //            Log.w("MyTag", "requestFailed "+ call.clone().isExecuted());

            }
        });

        return true;
    }
    @Override
    public void onDetach() {
        Toast.makeText(getActivity(), "Subject_Wise_Level_Test_Fragment  Its Detached", Toast.LENGTH_SHORT).show();
        tests_namessub.clear();
        try {
            tests_recy_sub.setAdapter(null);
            testAdapter.notifyDataSetChanged();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        super.onDetach();
    }
}

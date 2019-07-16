package dev.raghav.civilgate.Detailed_Analysis;

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
import android.widget.Toast;

import java.util.ArrayList;

import dev.raghav.civilgate.Activities.ShowAllPakages;
import dev.raghav.civilgate.Api.Api;
import dev.raghav.civilgate.Api.Retro_Urls;
import dev.raghav.civilgate.Const_Files.Detailed_Analysis_const;
import dev.raghav.civilgate.Const_Files.Detailed_Analysis_const_data;
import dev.raghav.civilgate.Const_Files.Package;
import dev.raghav.civilgate.Const_Files.Package_Const;
import dev.raghav.civilgate.Dapter.Pakages_Adapter;
import dev.raghav.civilgate.R;
import dev.raghav.civilgate.SessionManage.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static dev.raghav.civilgate.Detailed_Analysis.Detailed_Analysis.levelid;
import static dev.raghav.civilgate.Detailed_Analysis.Detailed_Analysis.sublevelid;

public class Question_Report extends Fragment {
    RecyclerView questionrec;
    Analysis_Recy_Adapter analysis_recy_adapter;
    SessionManager sessionManager;
    ArrayList<Detailed_Analysis_const_data> detailed_analysis_consts = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question_report ,container,false);
        questionrec = view.findViewById(R.id.questionrec);
        sessionManager = new SessionManager(getActivity());
        GetAllSolvedQuestions();
        return view;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void GetAllSolvedQuestions() {
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(Retro_Urls.The_Base).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api RegApi = RetroLogin.create(Api.class);
        Call<Detailed_Analysis_const> login_responceCall = RegApi.DETAILED_ANALYSIS_CONST_CALL(sessionManager.getCoustId() ,levelid ,sublevelid );
        login_responceCall.enqueue(new Callback<Detailed_Analysis_const>() {
            @Override
            public void onResponse(Call<Detailed_Analysis_const> call, Response<Detailed_Analysis_const> response) {
                Log.d("string" , ""+response.body().getResponce());
//                            Log.d("string" , ""+response.body().getData().getEmail());
                if(response.body().getResponce())
                {
                 //   Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();
                    for(int k=0;k< response.body().getData().size() ;k++) {
                        detailed_analysis_consts.add(new Detailed_Analysis_const_data(response.body().getData().get(k).getId() , response.body().getData().get(k).getQueAns() , response.body().getData().get(k).getAns(),response.body().getData().get(k).getMarks(),response.body().getData().get(k).getTime()));
                     //   Log.e("image" , "url"+ response.body().getData().get(k).getPackageImage());
                    }
                    analysis_recy_adapter = new Analysis_Recy_Adapter(detailed_analysis_consts);
                    LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    questionrec.setLayoutManager(llm);
                    questionrec.setAdapter(analysis_recy_adapter);
//                    Intent intent=new Intent(ShowAllPakages.this,MainActivity.class);
//                    //manager.serverLogin(response.body().getData().getId() , response.body().getData().getName(),response.body().getData().getStatus());
//                    intent.putExtra("respoce", ""+response);
//                    startActivity(intent);
//                    finish();
                }else{
                    //    Toast.makeText(ShowAllPakages.this, "Either Email is wrong or Password", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Detailed_Analysis_const> call, Throwable t) {

                Log.d("cause" , ""+t.getCause());
                Log.d("main cause" , ""+t.getLocalizedMessage());
                Log.d("cause" , ""+t.getMessage());
                Toast.makeText(getActivity(), "Network problem", Toast.LENGTH_SHORT).show();

            }
        });

    }
}

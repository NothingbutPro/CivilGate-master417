package dev.raghav.civilgate.Frag_granades;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import dev.raghav.civilgate.Activities.Critical_Thinking;
import dev.raghav.civilgate.Activities.Level_Tab_Activities;
import dev.raghav.civilgate.Activities.Profile_Activity;
import dev.raghav.civilgate.Activities.ShowAllPakages;
import dev.raghav.civilgate.Api.Api;
import dev.raghav.civilgate.Api.Retro_Urls;
import dev.raghav.civilgate.Const_Files.Myoverall;
import dev.raghav.civilgate.Const_Files.MyoverallData;
import dev.raghav.civilgate.Const_Files.Percentage;
import dev.raghav.civilgate.Other_Parsing_Files.Dashboard_Latest_Test;
import dev.raghav.civilgate.Other_Parsing_Files.Get_About;
import dev.raghav.civilgate.R;
import dev.raghav.civilgate.SessionManage.SessionManager;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home extends Fragment {
    ImageView leve_id;
    CardView procard;
    TextView latestnam,latestque,latestmarks;
    com.budiyev.android.circularprogressbar.CircularProgressBar unprogress,attprogress,wrongprogress;
    ProgressBar pro1,pro2,pro3,pro4;
    TextView totaltes , solvet,tesnscore,cedittxt;
    CardView buy_package_icon ,mythink;
    SessionManager sessionManagerl;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.home,container,false);
        leve_id = v.findViewById(R.id.leve_id);
        procard = v.findViewById(R.id.procard);
        totaltes = v.findViewById(R.id.totalnu);
        unprogress = v.findViewById(R.id.unprogress_bar);
        attprogress = v.findViewById(R.id.attprogress);
        wrongprogress = v.findViewById(R.id.wrongprogress);
        latestnam = v.findViewById(R.id.latestname);
        latestque = v.findViewById(R.id.latestque);
        latestmarks = v.findViewById(R.id.totmark);
        pro1 = v.findViewById(R.id.pro1);
        pro2 = v.findViewById(R.id.pro2);
        pro3 = v.findViewById(R.id.pro3);
        pro4 = v.findViewById(R.id.pro4);
        solvet = v.findViewById(R.id.solvetest);
        tesnscore = v.findViewById(R.id.tesnscore);
        sessionManagerl = new SessionManager(getActivity());
        cedittxt = v.findViewById(R.id.cedittxt);
        mythink = v.findViewById(R.id.mythink);
        buy_package_icon = v.findViewById(R.id.buyicon);

        mythink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , Critical_Thinking.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        procard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , Profile_Activity.class);
                startActivity(intent);
            }
        });
        buy_package_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , ShowAllPakages.class);
                startActivity(intent);
            }
        });
        leve_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to_leveltab = new Intent(getActivity() , Level_Tab_Activities.class);
                startActivity(to_leveltab);
            }
        });
        GetallTest();
        GetTheLatestOne();
        return  v;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void GetTheLatestOne() {
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(Retro_Urls.The_Base).addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.e("session is" , ""+sessionManagerl.getCoustId());
        Api RegApi = RetroLogin.create(Api.class);
        Call<Dashboard_Latest_Test> login_responceCall = RegApi.DASHBOARD_LATEST_TEST_CALL(sessionManagerl.getCoustId());
        login_responceCall.enqueue(new Callback<Dashboard_Latest_Test>() {
            @Override
            public void onResponse(Call<Dashboard_Latest_Test> call, Response<Dashboard_Latest_Test> response) {
                Log.d("string" , ""+response.body().getResponce());
//                            Log.d("string" , ""+response.body().getData().getEmail());
                if(response.body().getResponce())
                {
                    Toast.makeText(getActivity(), "percentage is"+response.body().getData(), Toast.LENGTH_SHORT).show();
                    Float correct = Float.valueOf(response.body().getData().get(0).getCorrect());
                    Float wrong = Float.valueOf(response.body().getData().get(0).getIncorrect());
                 //   int attempted = Integer.parseInt(response.body().getData().get(0).getCorrect() + Integer.parseInt(response.body().getData().get(0).getIncorrect()));
                    Float attempted = correct +wrong;
                    Log.e("per" , "percentage is"+attempted);
                    Log.e("per" , "percentage is"+response.body().getData().get(0).getCorrect());
                    Log.e("per" , "percentage is"+response.body().getData().get(0).getIncorrect());
                    Log.e("per" , "percentage is"+response.body().getData().get(0).getTotalQue());
                    unprogress.setProgress(Float.parseFloat(response.body().getData().get(0).getLeft()));
                 //   attprogress.setProgress(100);
                    attprogress.setProgress(attempted);

                     wrongprogress.setProgress(Float.parseFloat(response.body().getData().get(0).getIncorrect()));
                     latestnam.setText("Latest Test Report: "+response.body().getData().get(0).getTestName());
                    latestque.setText("Total Question: "+response.body().getData().get(0).getTotalQue());
                     latestmarks.setText("Total Mark: "+response.body().getData().get(0).getTotalmark());
              //      cred_mie.setText(cred_mie.getText().toString().concat(" "+response.body().getData()));
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
            public void onFailure(Call<Dashboard_Latest_Test> call, Throwable t) {

                Log.d("cause" , ""+t.getMessage());
                Log.d("cause" , ""+t.getCause());
                Toast.makeText(getActivity(), "Network problem", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void GetallTest() {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100,TimeUnit.SECONDS).build();
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(Retro_Urls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api AbloutApi = RetroLogin.create(Api.class);
        Call<Myoverall> get_aboutCall = AbloutApi.MYOVERALL_DATA_CALL(sessionManagerl.getCoustId());
        get_aboutCall.enqueue(new Callback<Myoverall>() {
            @Override
            public void onResponse(Call<Myoverall> call, Response<Myoverall> response) {
                Log.e("total 1" , ""+response.body().getData().getTotaltest());
                Log.e("total 2" , ""+response.body().getData().getSolved());
                Log.e("total 3" , ""+response.body().getData().getScore());
                Log.e("total 4" , ""+response.body().getData().getCredit());
                totaltes.setText(String.valueOf(response.body().getData().getTotaltest()));
                solvet.setText(String.valueOf(response.body().getData().getSolved()));
                if(!String.valueOf(response.body().getData().getScore()).equals("null")) {
                    tesnscore.setText(String.valueOf(response.body().getData().getScore()));
                }else {
                    tesnscore.setText("0");
                }
                if(!String.valueOf(response.body().getData().getCredit()).equals("null"))
                {
                    cedittxt.setText(String.valueOf(response.body().getData().getCredit()));
                }else {
                    cedittxt.setText("0");
                }


                pro1.setProgress(Math.round(Float.parseFloat(response.body().getData().getTotaltest())));
                pro2.setProgress(Math.round(Float.parseFloat(response.body().getData().getSolved())));
                try {
                    pro3.setProgress(Math.round(Float.parseFloat(response.body().getData().getScore())));
                }
                catch (Exception e)
                {
                    pro3.setProgress(0);
                    e.printStackTrace();
                }
                try {
                    pro4.setProgress(Math.round(Float.parseFloat(response.body().getData().getCredit())));
                }catch (Exception e)
                {
                    pro4.setProgress(0);
                    e.printStackTrace();
                }
             //   Toast.makeText(getActivity(), ""+response.body().getData().getDescription(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Myoverall> call, Throwable t) {
                Log.e("error" , ""+t.getMessage());
                Log.e("error" , ""+t.getCause());
                Log.e("error" , ""+t.getStackTrace());
                Log.e("error session" , ""+sessionManagerl.getCoustId());
                Log.e("error2" , ""+t.getLocalizedMessage());
                Toast.makeText(getActivity(), ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}

package dev.raghav.civilgate.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dev.raghav.civilgate.Api.Api;
import dev.raghav.civilgate.Api.Retro_Urls;
import dev.raghav.civilgate.Const_Files.Package;
import dev.raghav.civilgate.Const_Files.Package_Const;
import dev.raghav.civilgate.Dapter.Pakages_Adapter;
import dev.raghav.civilgate.Dapter.Schedule_Dapter;
import dev.raghav.civilgate.Other_Parsing_Files.Schedule;
import dev.raghav.civilgate.Other_Parsing_Files.Schedule_Data;
import dev.raghav.civilgate.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Schedule_Activity extends AppCompatActivity {
    RecyclerView schedurecy;
    Schedule_Dapter schedule_dapter;
    Schedule schedules;
    ArrayList<Schedule_Data> scheduleArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shcedule);
        schedurecy = findViewById(R.id.schedurecy);
        LinearLayoutManager llm = new LinearLayoutManager(Schedule_Activity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        schedurecy.setLayoutManager(llm);
        GetScheduleAct();

    }

    private void GetScheduleAct() {

        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(Retro_Urls.The_Base).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api RegApi = RetroLogin.create(Api.class);
        Call<Schedule> login_responceCall = RegApi.SCHEDULE_CALL();
        login_responceCall.enqueue(new Callback<Schedule>() {
            @Override
            public void onResponse(Call<Schedule> call, Response<Schedule> response) {
                Log.d("string" , ""+response.body().getResponce());
//                            Log.d("string" , ""+response.body().getData().getEmail());
                if(response.body().getResponce())
                {
                    Toast.makeText(Schedule_Activity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    for(int k=0;k< response.body().getData().size() ;k++) {
                        scheduleArrayList.add(new Schedule_Data(response.body().getData().get(k).getId() ,response.body().getData().get(k).getDescription(),response.body().getData().get(k).getImage()  ,response.body().getData().get(k).getDate() ,response.body().getData().get(k).getTitle()));
                        Log.e("image" , "url"+ response.body().getData().get(k).getImage());
                    }

                    schedule_dapter = new Schedule_Dapter(scheduleArrayList);
                    schedurecy.setAdapter(schedule_dapter);
//                    Intent intent=new Intent(ShowAllPakages.this,MainActivity.class);
//                    //manager.serverLogin(response.body().getData().getId() , response.body().getData().getName(),response.body().getData().getStatus());
//                    intent.putExtra("respoce", ""+response);
//                    startActivity(intent);
//                    finish();
                }
                else{
                    Toast.makeText(Schedule_Activity.this, "Either Email is wrong or Password", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Schedule> call, Throwable t) {

                Log.d("cause" , ""+t.getCause());
                Toast.makeText(Schedule_Activity.this, "Network problem", Toast.LENGTH_SHORT).show();

            }
        });

    }
}

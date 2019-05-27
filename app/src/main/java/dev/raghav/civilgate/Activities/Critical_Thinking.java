package dev.raghav.civilgate.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dev.raghav.civilgate.Api.Api;
import dev.raghav.civilgate.Api.Retro_Urls;
import dev.raghav.civilgate.Const_Files.Brain_Questions;
import dev.raghav.civilgate.Const_Files.Percentage;
import dev.raghav.civilgate.R;
import dev.raghav.civilgate.SessionManage.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Critical_Thinking extends AppCompatActivity {
    Button posmath;
    EditText question , ans1 ,ans2,ans3,ans4,correctnass,correctdis;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_critical__thinking);
        posmath = findViewById(R.id.posmath);
        question = findViewById(R.id.quemy);
        ans1 = findViewById(R.id.an1);
        ans2 = findViewById(R.id.an2);
        ans3 = findViewById(R.id.an3);
        ans4 = findViewById(R.id.an4);
        sessionManager = new SessionManager(this);
        correctnass = findViewById(R.id.correctan);
        correctdis = findViewById(R.id.correctdis);
        posmath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ans1.getText().toString().length() !=0 &&ans2.getText().toString().length() !=0 && ans3.getText().toString().length() !=0 && ans4.getText().toString().length() !=0
                && correctnass.getText().toString().length() !=0)
                {
                    Postthethinkingass();
                }else {
                    question.requestFocus();
                    Toast.makeText(Critical_Thinking.this, "All fields are mandatory ", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void Postthethinkingass() {
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(Retro_Urls.The_Base).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api RegApi = RetroLogin.create(Api.class);
        Call<Brain_Questions> login_responceCall = RegApi.StudentQue(sessionManager.getCoustId() ,question.getText().toString() ,ans1.getText().toString() ,ans2.getText().toString() , ans3.getText().toString() , ans4.getText().toString() , correctnass.getText().toString() , correctdis.getText().toString());
        login_responceCall.enqueue(new Callback<Brain_Questions>() {
            @Override
            public void onResponse(Call<Brain_Questions> call, Response<Brain_Questions> response) {
                Log.d("string" , ""+response.body().getResponce());
//                            Log.d("string" , ""+response.body().getData().getEmail());
                if(response.body().getResponce())
                {
                    if(response.body().getResponce() == true)
                    {
                        Intent intent = new Intent(Critical_Thinking.this , MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(Critical_Thinking.this, "Error in posting", Toast.LENGTH_SHORT).show();
                    }
//                    Log.e("per" , "percentage is"+response.body().getData());
//                    Toast.makeText(ShowAllPakages.this, "Login successful", Toast.LENGTH_SHORT).show();


//                    Intent intent=new Intent(ShowAllPakages.this,MainActivity.class);
//                    //manager.serverLogin(response.body().getData().getId() , response.body().getData().getName(),response.body().getData().getStatus());
//                    intent.putExtra("respoce", ""+response);
//                    startActivity(intent);
//                    finish();
                }
                else{
                    Toast.makeText(Critical_Thinking.this, "Can't Post Now", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Brain_Questions> call, Throwable t) {

                Log.d("cause" , ""+t.getMessage());
                Toast.makeText(Critical_Thinking.this, "Network problem "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}

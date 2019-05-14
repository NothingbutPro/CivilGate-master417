package dev.raghav.civilgate.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import dev.raghav.civilgate.Api.Api;
import dev.raghav.civilgate.Const_Files.Package;
import dev.raghav.civilgate.Const_Files.Package_Const;
import dev.raghav.civilgate.Const_Files.Retro_Urls;
import dev.raghav.civilgate.Dapter.Pakages_Adapter;
import dev.raghav.civilgate.Other_Parsing_Files.Credit;
import dev.raghav.civilgate.R;
import dev.raghav.civilgate.SessionManage.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Profile_Activity extends AppCompatActivity {
TextView pronamemn ,credit,email , mobilenumber,addresspx, passout_year;
SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);
        pronamemn = findViewById(R.id.pronamemn);
        credit = findViewById( R.id.credit);
        email = findViewById(R.id.email);
        addresspx = findViewById(R.id.addresspx);
        passout_year = findViewById(R.id.passout_year);
        sessionManager = new SessionManager(Profile_Activity.this);
        mobilenumber = findViewById(R.id.mobilefx);
        GetTheCredits();
    }

    private void GetTheCredits() {
        Toast.makeText(this, "Credentials called", Toast.LENGTH_SHORT).show();
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(Retro_Urls.The_Base).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api RegApi = RetroLogin.create(Api.class);
        Call<Credit> login_responceCall = RegApi.Get_Credits(sessionManager.getCoustId());
        login_responceCall.enqueue(new Callback<Credit>() {
            @Override
            public void onResponse(Call<Credit> call, Response<Credit> response) {
                Log.d("string" , ""+response.body().getResponce());
//                            Log.d("string" , ""+response.body().getData().getEmail());
                if(response.body().getResponce())
                {
//                    Toast.makeText(ShowAllPakages.this, "Login successful", Toast.LENGTH_SHORT).show();
                    for(int k=0;k< response.body().getData().size() ;k++) {
                        credit.setText(response.body().getData().get(k).getCreditPoin());
                       // credit.setText("5000");
                        mobilenumber.setText(mobilenumber.getText().toString().concat(response.body().getData().get(k).getMobile()));
                        email.setText(email.getText().toString().concat(response.body().getData().get(k).getEmail()));
                        pronamemn.setText(response.body().getData().get(k).getName());
                        addresspx.setText(addresspx.getText().toString().concat(response.body().getData().get(k).getAddress()));
                        passout_year.setText(passout_year.getText().toString().concat(response.body().getData().get(k).getPassoutYear()));
                    }

//                    Intent intent=new Intent(ShowAllPakages.this,MainActivity.class);
//                    //manager.serverLogin(response.body().getData().getId() , response.body().getData().getName(),response.body().getData().getStatus());
//                    intent.putExtra("respoce", ""+response);
//                    startActivity(intent);
//                    finish();
                }else{
                    Toast.makeText(Profile_Activity.this, "Either Email is wrong or Password", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Credit> call, Throwable t) {

                Log.d("cause" , ""+t.getCause());
                Toast.makeText(Profile_Activity.this, "Network problem", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

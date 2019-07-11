package dev.raghav.civilgate.Activities;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.numberprogressbar.NumberProgressBar;

import java.util.Timer;

import dev.raghav.civilgate.Api.Api;
import dev.raghav.civilgate.Api.Retro_Urls;
import dev.raghav.civilgate.Const_Files.Percentage;
import dev.raghav.civilgate.Other_Parsing_Files.Credit;
import dev.raghav.civilgate.R;
import dev.raghav.civilgate.SessionManage.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static dev.raghav.civilgate.Activities.MaBookmarks.ixp;

public class Profile_Activity extends AppCompatActivity   {
TextView pronamemn ,credit,email , mobilenumber,addresspx, passout_year;
SessionManager sessionManager;
    ProgressBar proprogres;
    private NumberProgressBar progressBar;
    private Timer timer;
    int i;
    private int progressStatus = 0;
    private Handler handler = new Handler();
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
//        proprogres = findViewById(R.id.proprogres);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setReachedBarColor(Color.BLUE);
        progressBar.setUnreachedBarHeight(Color.BLUE);
        progressBar.setReachedBarHeight(5);
        progressBar.setUnreachedBarHeight(5);
        Toast.makeText(this, "value of i is"+ixp, Toast.LENGTH_SHORT).show();
        i = 0;
        ixp =50;
        Toast.makeText(this, "value after i"+ixp, Toast.LENGTH_SHORT).show();
      //  progressBar.incrementProgressBy(33);
        // Start the lengthy operation in a background thread
//        timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        progressBar.incrementProgressBy(1);
//                    }
//                });
//            }
//        }, 1000, 100);
        GetTheCredits();
        Get_Percentage();
    }

    private void Get_Percentage() {
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(Retro_Urls.The_Base).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api RegApi = RetroLogin.create(Api.class);
        Call<Percentage> login_responceCall = RegApi.PERCENTAGE_CALL(sessionManager.getCoustId());
        login_responceCall.enqueue(new Callback<Percentage>() {
            @Override
            public void onResponse(Call<Percentage> call, Response<Percentage> response) {
                Log.d("string" , ""+response.body().getResponce());
//                            Log.d("string" , ""+response.body().getData().getEmail());
                if(response.body().getResponce())
                {
                    Toast.makeText(Profile_Activity.this, "percentage is"+response.body().getData(), Toast.LENGTH_SHORT).show();
                    Log.e("per" , "percentage is"+response.body().getData());
                    progressBar.incrementProgressBy(Integer.valueOf(String.valueOf(Math.round(response.body().getData()))));
//                    Toast.makeText(ShowAllPakages.this, "Login successful", Toast.LENGTH_SHORT).show();


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
            public void onFailure(Call<Percentage> call, Throwable t) {

                Log.d("cause" , ""+t.getMessage());
                Toast.makeText(Profile_Activity.this, "Network problem", Toast.LENGTH_SHORT).show();

            }
        });

    }


//        ObjectAnimator.ofInt(proprogres, "progress", 79).start();
//        proprogres.startIntro();
//        proprogres.setProgress(25);
//        proprogres.success();




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
//                        progressBar.setProgress(0); // call these two methods before setting progress.
//                        progressBar.setMax(20);
//                        progressBar.setProgress(20);
                        // credit.setText("5000");
                    //    progressBar.incrementProgressBy(50);
                     //   progressBar.setProgress(50);
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

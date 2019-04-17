package dev.raghav.civilgate.Activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import dev.raghav.civilgate.Api.Api;
import dev.raghav.civilgate.Const_Files.Retro_Urls;
import dev.raghav.civilgate.Other_Parsing_Files.Get_About;
import dev.raghav.civilgate.R;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class About_all extends AppCompatActivity {
    ProgressDialog Aboutusdialog;
    TextView about_web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_all);
        Aboutusdialog = new ProgressDialog(this);
        Aboutusdialog.show();
        about_web = findViewById(R.id.about_web);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100,TimeUnit.SECONDS).build();
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(Retro_Urls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api AbloutApi = RetroLogin.create(Api.class);
        Call<Get_About> get_aboutCall = AbloutApi.TellAbout();
        get_aboutCall.enqueue(new Callback<Get_About>() {
            @Override
            public void onResponse(Call<Get_About> call, Response<Get_About> response) {
                Toast.makeText(About_all.this, ""+response.body().getData().getDescription(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Get_About> call, Throwable t) {

            }
        });

    }
}

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
import dev.raghav.civilgate.Const_Files.Package;
import dev.raghav.civilgate.Const_Files.Package_Const;
import dev.raghav.civilgate.Api.Retro_Urls;
import dev.raghav.civilgate.Dapter.Pakages_Adapter;
import dev.raghav.civilgate.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowAllPakages extends AppCompatActivity {
    RecyclerView pakagerecyler;
    private List<Package_Const> packages= new ArrayList<>();
    Pakages_Adapter pakages_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_pakages);
        pakagerecyler = findViewById(R.id.pakagerecyler);
        LinearLayoutManager llm = new LinearLayoutManager(ShowAllPakages.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        pakagerecyler.setLayoutManager(llm);
        ShowAllPakagess();
    }

    private void ShowAllPakagess() {
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(Retro_Urls.The_Base).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api RegApi = RetroLogin.create(Api.class);
        Call<Package> login_responceCall = RegApi.Get_Package();
        login_responceCall.enqueue(new Callback<Package>() {
            @Override
            public void onResponse(Call<Package> call, Response<Package> response) {
                Log.d("string" , ""+response.body().getResponce());
//                            Log.d("string" , ""+response.body().getData().getEmail());
                if(response.body().getResponce())
                {
                    Toast.makeText(ShowAllPakages.this, "Login successful", Toast.LENGTH_SHORT).show();
                    for(int k=0;k< response.body().getData().size() ;k++) {
                        packages.add(new Package_Const(response.body().getData().get(k).getId() ,response.body().getData().get(k).getLevelId(),response.body().getData().get(k).getStatus() ,response.body().getData().get(k).getPackageName() ,response.body().getData().get(k).getPackageMrp() ,response.body().getData().get(k).getPackageType() ,response.body().getData().get(k).getPackageImage() ,response.body().getData().get(k).getDescription() , response.body().getData().get(k).getTesttime() , response.body().getData().get(k).getTotalQue()));
                        Log.e("image" , "url"+ response.body().getData().get(k).getPackageImage());
                    }

                    pakages_adapter = new Pakages_Adapter(packages);
                    pakagerecyler.setAdapter(pakages_adapter);
//                    Intent intent=new Intent(ShowAllPakages.this,MainActivity.class);
//                    //manager.serverLogin(response.body().getData().getId() , response.body().getData().getName(),response.body().getData().getStatus());
//                    intent.putExtra("respoce", ""+response);
//                    startActivity(intent);
//                    finish();
                }else{
                    Toast.makeText(ShowAllPakages.this, "Either Email is wrong or Password", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Package> call, Throwable t) {

                Log.d("cause" , ""+t.getCause());
                Toast.makeText(ShowAllPakages.this, "Network problem", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

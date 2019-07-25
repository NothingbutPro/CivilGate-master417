package dev.raghav.civilgate.Activities;

import android.app.ProgressDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import dev.raghav.civilgate.Api.Api;
import dev.raghav.civilgate.Const_Files.Level_Java;
import dev.raghav.civilgate.Api.Retro_Urls;
import dev.raghav.civilgate.Dapter.HistoryAdapter;
import dev.raghav.civilgate.Other_Parsing_Files.Get_Level;
import dev.raghav.civilgate.R;
import dev.raghav.civilgate.Reports_Adapters.History_Fragments.Basic_History;
import dev.raghav.civilgate.Reports_Fragments.Advanced_Level_Report;
import dev.raghav.civilgate.Reports_Fragments.Daily_Reports;
import dev.raghav.civilgate.Reports_Fragments.Intermidiate_Reports;
import dev.raghav.civilgate.Reports_Fragments.Mock_Test_Reports;
import dev.raghav.civilgate.Reports_Fragments.Subject_WiseReport;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HisHistoryActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Toolbar historytoolbar;
    HistoryAdapter historyAdapter;
    private ProgressDialog progressDialog;
    private ArrayList<Level_Java> level_javas = new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_his_history);

        tabLayout = findViewById(R.id.historytably);
        viewPager = findViewById(R.id.history_pager);
        historytoolbar = findViewById(R.id.historytoolbar);

        historyAdapter = new HistoryAdapter(getSupportFragmentManager() , HisHistoryActivity.this);
        CheckTheHistory();
    }

    private void CheckTheHistory() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(150, TimeUnit.SECONDS)
                .readTimeout(300,TimeUnit.SECONDS).writeTimeout(200 , TimeUnit.SECONDS).build();
        Retrofit LEvelRetrofit = new Retrofit.Builder().client(client).baseUrl(Retro_Urls.The_Base).addConverterFactory(GsonConverterFactory.create()).build();
        Api LevelApi = LEvelRetrofit.create(Api.class);
        Call<Get_Level> get_levelCall = LevelApi.GetLevels();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMax(1000);
        progressDialog.setTitle("Getting Your Data");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        get_levelCall.enqueue(new Callback<Get_Level>() {

            @Override
            public void onResponse(Call<Get_Level> call, Response<Get_Level> response) {
                if(response.body().getResponce().booleanValue() == true)
                {
                    progressDialog.dismiss();
                    Get_Level getLevel = response.body();
                    Log.e("responce" , ""+getLevel.getData().size());
                    int n = getLevel.getData().size();
                    level_javas = new ArrayList<>(n);
                    for(int p=0;p<n;p++)
                    {
                        level_javas.add(new Level_Java( getLevel.getData().get(p).getId(),getLevel.getData().get(p).getLevel()));
                        Log.d("id at"+p,""+level_javas.get(p).getId() +" and "+level_javas.get(p).getLevel()+"\n");
                        Log.d("getLevel id at"+p,""+getLevel.getData().get(p).getId() +"and getLevel"+getLevel.getData().get(p).getLevel());
                    }
//                    for(int i=0;i<n;i++)
//                    {
//                        for(int p=0;p<n;p++) {
//                            int id = level_javas.get(0).getId();
//                            int levid =getLevel.getData().get(i).getId();
                    if (getLevel.getData().get(2).getId() == 7 && level_javas.get(2).getId() == 7) {

                        // level_javas.remove(0, new Level_Java(getLevel.getData().get(i).getId(), getLevel.getData().get(i).getLevel()));
                     //   historyAdapter.addFragment(new Daily_Reports(), level_javas.get(2).getLevel());
                        historyAdapter.addFragment(new Daily_Reports(), "Daily Reports");
                        level_javas.add(2 , new Level_Java(100 , "Intermediate Test"));

                    }
                    if (getLevel.getData().get(1).getId() == 2 && level_javas.get(1).getId() == 2) {

                    //  level_javas.add(1, new Level_Java(getLevel.getData().get(i).getId(), getLevel.getData().get(i).getLevel()));
                   // historyAdapter.addFragment(new Basic_History(), level_javas.get(1).getLevel());
                    historyAdapter.addFragment(new Basic_History(), "Basic Reports");
                    level_javas.add(1 , new Level_Java(100 , "cccccccc"));

                      }
                    if (getLevel.getData().get(5).getId() == 6 && level_javas.get(5).getId() != 6) {

                    //  level_javas.add(2, new Level_Java(getLevel.getData().get(i).getId(), getLevel.getData().get(i).getLevel()));
                    historyAdapter.addFragment(new Subject_WiseReport(), "Subjectwise Report");
                    level_javas.add(5 , new Level_Java(100 , "ppppppp"));

                }  if (getLevel.getData().get(3).getId() == 3 && level_javas.get(3).getId() != 3) {
                    //  level_javas.add(3, new Level_Java(getLevel.getData().get(i).getId(), getLevel.getData().get(i).getLevel()));
                    historyAdapter.addFragment(new Intermidiate_Reports(), "Intermediate Test Report");
                    level_javas.add(3 , new Level_Java(100 , "kkkkkkk"));

                }  if (getLevel.getData().get(0).getId() == 4 && level_javas.get(0).getId() != 100) {
                    //  level_javas.add(4, new Level_Java(getLevel.getData().get(i).getId(), getLevel.getData().get(i).getLevel()));
                    historyAdapter.addFragment(new Advanced_Level_Report(), "Advance Test Reports");
                    level_javas.add(0 , new Level_Java(100 , "lllllllllll"));

                }   if (getLevel.getData().get(4).getId() == 5 && level_javas.get(4).getId() != 5) {
                    //  level_javas.add(4, new Level_Java(getLevel.getData().get(i).getId(), getLevel.getData().get(i).getLevel()));
                    historyAdapter.addFragment(new Mock_Test_Reports(), "Mock Test Reports");
//                                level_javas.remove(4);
                    level_javas.add(4 , new Level_Java(100 , "xxxxxxxxxxx"));
                }

                    viewPager.setAdapter(historyAdapter);
                    tabLayout.setupWithViewPager(viewPager);


                }

            }
            //
            @Override
            public void onFailure(Call<Get_Level> call, Throwable t) {
                progressDialog.dismiss();
                Log.d("cause" , ""+t.getCause());
            }
        });

    }
}

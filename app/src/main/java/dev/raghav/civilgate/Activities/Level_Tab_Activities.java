package dev.raghav.civilgate.Activities;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import dev.raghav.civilgate.Api.Api;
import dev.raghav.civilgate.Const_Files.Level_Java;
import dev.raghav.civilgate.Api.Retro_Urls;
import dev.raghav.civilgate.Dapter.ServiceGenerator;
import dev.raghav.civilgate.Frag_granades.Advanced_Level_Test_Fragment;
import dev.raghav.civilgate.Frag_granades.Basic_Level_Test_Fragment;
import dev.raghav.civilgate.Frag_granades.Daily_Level_Test_Fragment;
import dev.raghav.civilgate.Frag_granades.Intermediate_Level_Test_Fragment;
import dev.raghav.civilgate.Frag_granades.Mock_Level_Test_Fragment;
import dev.raghav.civilgate.Frag_granades.Subject_Wise_Level_Test_Fragment;
import dev.raghav.civilgate.Other_Parsing_Files.Get_Level;
import dev.raghav.civilgate.R;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Level_Tab_Activities extends AppCompatActivity {
     RecyclerView level_resicler;
    private ServiceGenerator serviceGeneratoradapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
     ProgressDialog progressDialog;
     TextView lechng;
     Toolbar toolbar_level;

     //public List<Level_Java> level_javaList = new ArrayList<>();
    private ArrayList<Level_Java> level_javas ;
    private static final Handler mainThreadHandler = new Handler(Looper.getMainLooper());
    //Level_Adapter level_adapter;

    @Override
    protected void onStart() {
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMax(1000);
//        progressDialog.setTitle("please wait");
//        progressDialog.setCancelable(false);
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progressDialog.show();
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level__tab__activities);
//        level_resicler = findViewById(R.id.level_resid);
        lechng = findViewById(R.id.lechng);
        toolbar_level = (Toolbar) findViewById(R.id.toolbar_level);
        toolbar_level.setNavigationIcon(R.drawable.arrow);
        toolbar_level.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   stopActivityTask();
                onBackPressed();
            }
        });

        tabLayout = findViewById(R.id.tably);
        serviceGeneratoradapter = new ServiceGenerator(getSupportFragmentManager() , Level_Tab_Activities.this);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
          //      lechng.setText(serviceGeneratoradapter.getPageTitle(serviceGeneratoradapter.getPageTitleforchange()));
                lechng.setText(tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager = (ViewPager) findViewById(R.id.level_pager);
       if (getAllLevels())
       {
           viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
               @Override
               public void onPageScrolled(int i, float v, int i1) {
             //      Toast.makeText(Level_Tab_Activities.this, "position is"+i, Toast.LENGTH_SHORT).show();
               }

               @Override
               public void onPageSelected(int i) {
            //       Toast.makeText(Level_Tab_Activities.this, "Selected page is"+i, Toast.LENGTH_SHORT).show();
               }

               @Override
               public void onPageScrollStateChanged(int i) {
               //    Toast.makeText(Level_Tab_Activities.this, "Chjanged"+i, Toast.LENGTH_SHORT).show();
               }
           });
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
               tabLayout.getDefaultFocusHighlightEnabled();
           }
       }

//        serviceGeneratoradapter.getPageTitle(serviceGeneratoradapter.)

    }

    private Boolean getAllLevels() {
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
//                            if (getLevel.getData().get(2).getId() == 7 && level_javas.get(2).getId() == 7) {

                               // level_javas.remove(0, new Level_Java(getLevel.getData().get(i).getId(), getLevel.getData().get(i).getLevel()));
                                serviceGeneratoradapter.addFragment(new Daily_Level_Test_Fragment(),"Daily Tests");
                              level_javas.add(2 , new Level_Java(100 , "Intermediate Test"));
//
//                            }  if (getLevel.getData().get(1).getId() == 2 && level_javas.get(1).getId() == 2) {

                              //  level_javas.add(1, new Level_Java(getLevel.getData().get(i).getId(), getLevel.getData().get(i).getLevel()));
                              //  serviceGeneratoradapter.addFragment(new Basic_Level_Test_Fragment(), level_javas.get(1).getLevel());
                                serviceGeneratoradapter.addFragment(new Basic_Level_Test_Fragment(), "Basic Tests");
                                level_javas.add(1 , new Level_Java(100 , "cccccccc"));

//                            }
//                if (getLevel.getData().get(5).getId() == 6 && level_javas.get(5).getId() != 6) {

                              //  level_javas.add(2, new Level_Java(getLevel.getData().get(i).getId(), getLevel.getData().get(i).getLevel()));
                                serviceGeneratoradapter.addFragment(new Subject_Wise_Level_Test_Fragment(), "Subjectwise Tests");
                                level_javas.add(5 , new Level_Java(100 , "ppppppp"));

//                            }  if (getLevel.getData().get(3).getId() == 3 && level_javas.get(3).getId() != 3) {
                              //  level_javas.add(3, new Level_Java(getLevel.getData().get(i).getId(), getLevel.getData().get(i).getLevel()));
                                serviceGeneratoradapter.addFragment(new Intermediate_Level_Test_Fragment(), "Intermediate Tests");
                                level_javas.add(3 , new Level_Java(100 , "kkkkkkk"));

//                            }  if (getLevel.getData().get(0).getId() == 4 && level_javas.get(0).getId() != 100) {
                              //  level_javas.add(4, new Level_Java(getLevel.getData().get(i).getId(), getLevel.getData().get(i).getLevel()));
                                serviceGeneratoradapter.addFragment(new Advanced_Level_Test_Fragment(), "Advanced Tests");
                                level_javas.add(0 , new Level_Java(100 , "lllllllllll"));

//                            }   if (getLevel.getData().get(4).getId() == 5 && level_javas.get(4).getId() != 5) {
                              //  level_javas.add(4, new Level_Java(getLevel.getData().get(i).getId(), getLevel.getData().get(i).getLevel()));
                                serviceGeneratoradapter.addFragment(new Mock_Level_Test_Fragment(), "Mock Test");
//                                level_javas.remove(4);
                                level_javas.add(4 , new Level_Java(100 , "xxxxxxxxxxx"));

//                            }

                    viewPager.setAdapter(serviceGeneratoradapter);
                    tabLayout.setupWithViewPager(viewPager);
                }

            }
//
            @Override
            public void onFailure(Call<Get_Level> call, Throwable t) {
                progressDialog.dismiss();
                Log.d("cause" , ""+t.getMessage());
            }
        });

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

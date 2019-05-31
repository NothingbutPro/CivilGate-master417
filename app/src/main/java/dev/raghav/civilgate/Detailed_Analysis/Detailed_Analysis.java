package dev.raghav.civilgate.Detailed_Analysis;

import android.app.ProgressDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import dev.raghav.civilgate.Activities.ShowAllPakages;
import dev.raghav.civilgate.Api.Api;
import dev.raghav.civilgate.Api.Retro_Urls;
import dev.raghav.civilgate.Const_Files.Package;
import dev.raghav.civilgate.Const_Files.Package_Const;
import dev.raghav.civilgate.Dapter.Pakages_Adapter;
import dev.raghav.civilgate.Dapter.ServiceGenerator;
import dev.raghav.civilgate.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Detailed_Analysis extends AppCompatActivity {
    private ServiceGenerator serviceGeneratoradapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public  static String levelid;
    public static String sublevelid;
    ProgressDialog progressDialog;

    Toolbar toolbar_level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_card);
        viewPager  = findViewById(R.id.report_pager);
        tabLayout = findViewById(R.id.reporttbl);
        levelid   = getIntent().getStringExtra("levelid");
        sublevelid = getIntent().getStringExtra("sublevelid");
//        analysis_adapter = new Analysis_Recy_Adapter()
        serviceGeneratoradapter = new ServiceGenerator(getSupportFragmentManager() , Detailed_Analysis.this);
        Get_All_REports();

    }


    private void Get_All_REports() {
        serviceGeneratoradapter.addFragment(new Score_Card(), "Score Card");
        serviceGeneratoradapter.addFragment(new Question_Report(), "Question Report");
        serviceGeneratoradapter.addFragment(new Comparative_Analysis(), "Comparative Analysis");
        viewPager.setAdapter(serviceGeneratoradapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}

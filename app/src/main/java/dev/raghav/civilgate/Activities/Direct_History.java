package dev.raghav.civilgate.Activities;

import android.app.ProgressDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import dev.raghav.civilgate.Dapter.ServiceGenerator;
import dev.raghav.civilgate.Detailed_Analysis.Comparative_Analysis;
import dev.raghav.civilgate.Detailed_Analysis.Detailed_Analysis;
import dev.raghav.civilgate.Detailed_Analysis.Question_Report;
import dev.raghav.civilgate.Detailed_Analysis.Score_Card;
import dev.raghav.civilgate.Frag_granades.Full_Direct_Solution;
import dev.raghav.civilgate.R;

public class Direct_History  extends AppCompatActivity {
    private ServiceGenerator serviceGeneratoradapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    TextView tootxt;
    public  static String levelid;
    public static String sublevelid;
    ProgressDialog progressDialog;

    Toolbar toolbar_level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_card);
        viewPager  = findViewById(R.id.report_pager);
        tootxt  = findViewById(R.id.tootxt);
        tabLayout = findViewById(R.id.reporttbl);
        levelid   = getIntent().getStringExtra("levelid");
        sublevelid = getIntent().getStringExtra("sublevelid");
//        analysis_adapter = new Analysis_Recy_Adapter()
        serviceGeneratoradapter = new ServiceGenerator(getSupportFragmentManager() , Direct_History.this);
        Get_All_REports();
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tootxt.setText(tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void Get_All_REports() {
        serviceGeneratoradapter.addFragment(new Score_Card(), "Score Card");
        serviceGeneratoradapter.addFragment(new Full_Direct_Solution(), "Full Solution");
//        serviceGeneratoradapter.addFragment(new Comparative_Analysis(), "Comparative Analysis");
        viewPager.setAdapter(serviceGeneratoradapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}

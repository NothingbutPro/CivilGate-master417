package dev.raghav.civilgate.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.raghav.civilgate.Dapter.Analysis_Adapter;
import dev.raghav.civilgate.R;

public class Results_Activity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    Analysis_Adapter analysis_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        analysis_adapter = new Analysis_Adapter(getSupportFragmentManager());
        viewPager.setAdapter(analysis_adapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}

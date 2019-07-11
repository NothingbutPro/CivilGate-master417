package dev.raghav.civilgate.Full_Solution;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import dev.raghav.civilgate.R;

public class Full_Solution_Act extends AppCompatActivity {
    FrameLayout solframe;
    static public String  lel_id , sublel_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full__solution_);
//        solframe = findViewById(R.id.solframeboo);
//        lel_id   = getIntent().getStringExtra("levelid");
//        sublel_id = getIntent().getStringExtra("sublevelid");
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
       // fragmentTransaction.replace(R.id.solframe , new MCQ_Questions()).commit();
    }
}

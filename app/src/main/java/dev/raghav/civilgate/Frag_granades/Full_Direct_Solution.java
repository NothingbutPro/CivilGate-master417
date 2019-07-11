package dev.raghav.civilgate.Frag_granades;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import dev.raghav.civilgate.Full_Solution.MCQ_Questions;
import dev.raghav.civilgate.R;

public class Full_Direct_Solution extends Fragment {
    FrameLayout solfr;
//    static public String  lel_id , sublel_id;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.full_direct_solution,container,false );

        solfr = view.findViewById(R.id.solfr);

       // lel_id   = getActivity().getIntent().getStringExtra("levelid");
      //  sublel_id = getIntent().getStringExtra("sublevelid");
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.solfr , new MCQ_Questions()).commit();
//        return super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }
}

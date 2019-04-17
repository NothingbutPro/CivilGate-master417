package dev.raghav.civilgate.Frag_granades;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dev.raghav.civilgate.R;

public class Subject_Wise_Level_Test_Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View Subjec_wiseView  = inflater.inflate(R.layout.subjectwise_level_layout , container , false);
            return Subjec_wiseView;
    }
}

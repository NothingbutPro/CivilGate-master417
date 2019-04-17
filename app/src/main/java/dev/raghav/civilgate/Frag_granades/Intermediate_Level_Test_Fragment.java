package dev.raghav.civilgate.Frag_granades;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dev.raghav.civilgate.R;

public class Intermediate_Level_Test_Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View IntermediateView  = inflater.inflate(R.layout.intermediate_level_layout , container , false);
        return IntermediateView;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}

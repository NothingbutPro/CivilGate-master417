package dev.raghav.civilgate.Reports_Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dev.raghav.civilgate.R;

public class Basic_Reports extends Fragment {
    RecyclerView basichis;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.basic_reports ,container , false);
        basichis = view.findViewById(R.id.basichis);

        return view;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}

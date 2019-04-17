package dev.raghav.civilgate.Test_Activities.Test_Types;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dev.raghav.civilgate.R;

public class Fill_In_Que_Test extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View FillView = inflater.inflate(R.layout.fill_the_que , container , false);
        return FillView;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}

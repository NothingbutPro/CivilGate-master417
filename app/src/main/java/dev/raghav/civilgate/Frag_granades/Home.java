package dev.raghav.civilgate.Frag_granades;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import dev.raghav.civilgate.Activities.Level_Tab_Activities;
import dev.raghav.civilgate.Activities.Profile_Activity;
import dev.raghav.civilgate.Activities.ShowAllPakages;
import dev.raghav.civilgate.R;

public class Home extends Fragment {
    ImageView leve_id;
    CardView procard;
    CardView buy_package_icon;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.home,container,false);
        leve_id = v.findViewById(R.id.leve_id);
        procard = v.findViewById(R.id.procard);
        buy_package_icon = v.findViewById(R.id.buyicon);
        procard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , Profile_Activity.class);
                startActivity(intent);
            }
        });
        buy_package_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , ShowAllPakages.class);
                startActivity(intent);
            }
        });
        leve_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to_leveltab = new Intent(getActivity() , Level_Tab_Activities.class);
                startActivity(to_leveltab);
            }
        });
        return  v;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}

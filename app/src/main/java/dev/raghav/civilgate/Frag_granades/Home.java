package dev.raghav.civilgate.Frag_granades;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import dev.raghav.civilgate.Activities.Level_Tab_Activities;
import dev.raghav.civilgate.R;

public class Home extends Fragment {
    ImageView leve_id;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.home,container,false);
        leve_id = v.findViewById(R.id.leve_id);
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

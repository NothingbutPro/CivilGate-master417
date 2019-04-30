package dev.raghav.civilgate.Frag_granades;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import dev.raghav.civilgate.R;

public class Mock_Level_Test_Fragment extends Fragment {
    LinearLayout detail;
    TextView viewTopic, hideTopic;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View MocklevelView = inflater.inflate(R.layout.mock_level_layout, container, false);

        detail = (LinearLayout) MocklevelView.findViewById(R.id.detail);
        viewTopic = (TextView) MocklevelView.findViewById(R.id.viewTopic);
        hideTopic = (TextView) MocklevelView.findViewById(R.id.hideTopic);

        viewTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewTopic.setVisibility(View.GONE);
                detail.setVisibility(View.VISIBLE);
                hideTopic.setVisibility(View.VISIBLE);
            }
        });

        hideTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewTopic.setVisibility(View.VISIBLE);
                detail.setVisibility(View.GONE);
                hideTopic.setVisibility(View.GONE);
            }
        });
        return MocklevelView;
    }
}

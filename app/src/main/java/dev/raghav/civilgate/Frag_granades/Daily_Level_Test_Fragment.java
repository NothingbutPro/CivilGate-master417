package dev.raghav.civilgate.Frag_granades;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import dev.raghav.civilgate.SessionManage.SessionManager;
import dev.raghav.civilgate.R;

public class Daily_Level_Test_Fragment extends Fragment {
    SessionManager sessionManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View Daily_LevelView = inflater.inflate(R.layout.daily_level_layout , container , false);
        return Daily_LevelView;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDetach() {
        Toast.makeText(getActivity(), "Daily_Level_Test_Fragment  Its Detached", Toast.LENGTH_SHORT).show();
        super.onDetach();
    }
}

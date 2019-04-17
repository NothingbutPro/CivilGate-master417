package dev.raghav.civilgate.Dapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dev.raghav.civilgate.Const_Files.Level_Java;
import dev.raghav.civilgate.R;

public class Level_Adapter extends RecyclerView.Adapter<Level_Adapter.MyViewHolder> {
    private List<Level_Java> levelAdapterList;
    public Level_Adapter(List<Level_Java> levelAdapterList) {
        this.levelAdapterList = levelAdapterList;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id, level_name;

        public MyViewHolder(View view) {
            super(view);
            id = (TextView) view.findViewById(R.id.level_id);
            level_name = (TextView) view.findViewById(R.id.leve_testname);
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.level, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Level_Java  levelJava = levelAdapterList.get(i);
        myViewHolder.id.setText(String.valueOf(levelJava.getId()) );
        myViewHolder.level_name.setText(levelJava.getLevel());
    }


    @Override
    public int getItemCount() {
        return levelAdapterList.size();
    }
}

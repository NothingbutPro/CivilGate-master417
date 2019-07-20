package dev.raghav.civilgate.Dapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import dev.raghav.civilgate.Const_Files.Package_Const;
import dev.raghav.civilgate.Other_Parsing_Files.Schedule;
import dev.raghav.civilgate.Other_Parsing_Files.Schedule_Data;
import dev.raghav.civilgate.R;
import dev.raghav.civilgate.SessionManage.SessionManager;

public class Schedule_Dapter extends RecyclerView.Adapter<Schedule_Dapter.MyViewHolder> {
    private List<Schedule_Data> packageList;
    SessionManager sessionManager;
    Context context;
    String package_url = "https://gogateexam.com/uploads/package/";

    public Schedule_Dapter(List<Schedule_Data> packages) {
        this.packageList = packages;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title ,file;
        WebView description;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title_of_schedule);
            description = view.findViewById(R.id.descrip);
            file = view.findViewById(R.id.image_of_sch);
//            id = (TextView) view.findViewById(R.id.level_id);
            sessionManager = new SessionManager(view.getContext());
            context  = view.getContext();
        }
    }


    @NonNull
    @Override
    public Schedule_Dapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.schedule_rows, viewGroup, false);

        return new Schedule_Dapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Schedule_Dapter.MyViewHolder myViewHolder, int i) {

        Schedule_Data aPackage = packageList.get(i);
        Log.e("tex at aPackage" , ""+aPackage.getTitle());
        myViewHolder.title.setText(aPackage.getTitle());
        myViewHolder.description.loadData(aPackage.getDescription() , "text/html" ,null);
        myViewHolder.file.setText(aPackage.getImage());


    }

//    private Void Start_the_test(View v, String subject_ids) {
//
//    }


    @Override
    public int getItemCount() {
        return packageList.size();
    }

}
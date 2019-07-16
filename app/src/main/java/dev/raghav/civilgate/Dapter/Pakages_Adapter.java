package dev.raghav.civilgate.Dapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import dev.raghav.civilgate.Const_Files.Package_Const;
import dev.raghav.civilgate.R;
import dev.raghav.civilgate.SessionManage.SessionManager;

public class Pakages_Adapter extends RecyclerView.Adapter<Pakages_Adapter.MyViewHolder> {
    private List<Package_Const> packageList;
    SessionManager sessionManager;
    Context context;
    String package_url = "https://gogateexam.com/uploads/package/";

    public Pakages_Adapter(List<Package_Const> packages) {
        this.packageList = packages;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView pakage_name,package_mrp,pkgdes ,pkgques,pkgtime;
        ImageView pkgimg;
        Button buybtn;

        public MyViewHolder(View view) {
            super(view);
            pakage_name = view.findViewById(R.id.pakage_name);
            package_mrp = view.findViewById(R.id.package_mrp);
            pkgdes = view.findViewById(R.id.pkgdes);
            pkgimg = view.findViewById(R.id.pkgimg);
            pkgtime = view.findViewById(R.id.packtime);
            pkgques = view.findViewById(R.id.totquespack);
            buybtn = view.findViewById(R.id.buybtn);
//            id = (TextView) view.findViewById(R.id.level_id);

            sessionManager = new SessionManager(view.getContext());
            context  = view.getContext();
        }
    }


    @NonNull
    @Override
    public Pakages_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.pakage, viewGroup, false);

        return new Pakages_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Pakages_Adapter.MyViewHolder myViewHolder, int i) {

        Package_Const aPackage = packageList.get(i);
        myViewHolder.pakage_name.setText(String.valueOf(aPackage.getPackage_name()));
        myViewHolder.package_mrp.setText(String.valueOf(aPackage.getPackage_mr()));
        myViewHolder.pkgdes.setText(String.valueOf(aPackage.getPackage_des()));
       myViewHolder.pkgtime.setText(myViewHolder.pkgtime.getText().toString().concat(String.valueOf(aPackage.getPackage_time())));
        myViewHolder.pkgques.setText(myViewHolder.pkgques.getText().toString().concat(String.valueOf(aPackage.getPackage_ques())));
        Log.e("image url at adapter" ,package_url +aPackage.getPackage_image());
        Glide.with(context).load(package_url +aPackage.getPackage_image())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(myViewHolder.pkgimg);
        myViewHolder.buybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new
            }
        });
//        myViewHolder.id.setText(String.valueOf(tests_Name.getTest_start_date()) );
//        Log.e("test at adapter", "name at" + tests_Name.getTest_name());
//        myViewHolder.Test_name.setText(tests_Name.getTest_name());
//        myViewHolder.Test_len.setText(String.valueOf(tests_Name.getTest_len()));
//
//        myViewHolder.Test_start.setText(tests_Name.test_start_date);
//        myViewHolder.Test_finishes.setText(tests_Name.test_end_date);
//        myViewHolder.test_mat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //   new Start_the_test(v, sessionManager.getCoustId(), tests_Name.getSubject_ids());
//                StartTheTest(v ,tests_Name.getTest_name(), tests_Name.getTest_id(),sessionManager.getCoustId(),1,tests_Name.getSub_level_cat_id() ,0,tests_Name.getSubject_ids());
//
//
//
//            }
//        });

    }

//    private Void Start_the_test(View v, String subject_ids) {
//
//    }


    @Override
    public int getItemCount() {
        return packageList.size();
    }

}
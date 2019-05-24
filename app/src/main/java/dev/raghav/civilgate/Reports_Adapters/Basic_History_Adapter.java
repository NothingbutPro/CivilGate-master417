package dev.raghav.civilgate.Reports_Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import dev.raghav.civilgate.R;
import dev.raghav.civilgate.Reports_Adapters.History_Java.Basic_History;
import dev.raghav.civilgate.SessionManage.SessionManager;

public class Basic_History_Adapter extends RecyclerView.Adapter<Basic_History_Adapter.MyViewHolder> {
private List<Basic_History> basic_histories;
        SessionManager sessionManager;
        Context context;
        String package_url = "http://ihisaab.in/lms/uploads/";

public Basic_History_Adapter(List<Basic_History> basic_histories) {
        this.basic_histories = basic_histories;
        }

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView test_name,test_id,pkgtype,tesdate;
    ImageView pkgimg;

    public MyViewHolder(View view) {
        super(view);
        test_name = view.findViewById(R.id.test_namep);

        tesdate = view.findViewById(R.id.tesdate);
//        test_name = view.findViewById(R.id.package_mrp);
//        pkgtype = view.findViewById(R.id.pkgtype);
//        pkgimg = view.findViewById(R.id.pkgimg);

//            id = (TextView) view.findViewById(R.id.level_id);

        sessionManager = new SessionManager(view.getContext());
        context  = view.getContext();
    }
}


    @NonNull
    @Override
    public Basic_History_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.history, viewGroup, false);

        return new Basic_History_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Basic_History_Adapter.MyViewHolder myViewHolder, int i) {

        Basic_History basic_history = basic_histories.get(i);
        myViewHolder.test_name.setText(String.valueOf(basic_history.getTestName()));
        try {
            Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(basic_history.getDate().toString());
            myViewHolder.tesdate.setText(date1.toLocaleString());
        } catch (ParseException e) {
            myViewHolder.tesdate.setText(basic_history.getDate().toString());
            e.printStackTrace();
        }

//        myViewHolder.package_mrp.setText(String.valueOf(basic_histories.getPackage_mr()));
//        myViewHolder.pkgtype.setText(String.valueOf(basic_histories.getPackage_type()));
//        Log.e("image url" ,package_url +basic_histories.getPackage_image());
//        Glide.with(context).load(package_url +basic_histories.getPackage_image())
//                .thumbnail(0.5f)
//                .crossFade()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(myViewHolder.pkgimg);
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
        return basic_histories.size();
    }

}
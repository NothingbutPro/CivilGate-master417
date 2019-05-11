package dev.raghav.civilgate.Dapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.card.MaterialCardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;
import java.util.concurrent.TimeUnit;

import dev.raghav.civilgate.Api.Api;
import dev.raghav.civilgate.Const_Files.Package;
import dev.raghav.civilgate.Const_Files.Package_Const;
import dev.raghav.civilgate.Const_Files.Retro_Urls;
import dev.raghav.civilgate.Const_Files.Tests_Name;
import dev.raghav.civilgate.R;
import dev.raghav.civilgate.SessionManage.SessionManager;
import dev.raghav.civilgate.Test_Activities.Main_Test_Activity;
import dev.raghav.civilgate.Test_Activities.Test_Types.TestStart;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Pakages_Adapter extends RecyclerView.Adapter<Pakages_Adapter.MyViewHolder> {
    private List<Package_Const> packageList;
    SessionManager sessionManager;
    Context context;
    String package_url = "http://ihisaab.in/lms/uploads/";

    public Pakages_Adapter(List<Package_Const> packages) {
        this.packageList = packages;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView pakage_name,package_mrp,pkgtype;
        ImageView pkgimg;

        public MyViewHolder(View view) {
            super(view);
            pakage_name = view.findViewById(R.id.pakage_name);
            package_mrp = view.findViewById(R.id.package_mrp);
            pkgtype = view.findViewById(R.id.pkgtype);
            pkgimg = view.findViewById(R.id.pkgimg);

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
        myViewHolder.pkgtype.setText(String.valueOf(aPackage.getPackage_type()));
        Log.e("image url" ,package_url +aPackage.getPackage_image());
        Glide.with(context).load(package_url +aPackage.getPackage_image())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(myViewHolder.pkgimg);
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
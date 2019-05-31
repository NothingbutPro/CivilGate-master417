package dev.raghav.civilgate.Detailed_Analysis;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.w3c.dom.Text;

import java.util.List;

import dev.raghav.civilgate.Const_Files.Detailed_Analysis_const;
import dev.raghav.civilgate.Const_Files.Detailed_Analysis_const_data;
import dev.raghav.civilgate.Const_Files.Package_Const;
import dev.raghav.civilgate.Dapter.Pakages_Adapter;
import dev.raghav.civilgate.R;
import dev.raghav.civilgate.SessionManage.SessionManager;

public class Analysis_Recy_Adapter  extends RecyclerView.Adapter<Analysis_Recy_Adapter.MyViewHolder> {
    private List<Detailed_Analysis_const_data> detailed_analysis_const_data;
    SessionManager sessionManager;
    Context context;
    String package_url = "http://ihisaab.in/lms/uploads/";

    public Analysis_Recy_Adapter(List<Detailed_Analysis_const_data> detailed_analysis_consts) {
        this.detailed_analysis_const_data = detailed_analysis_consts;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView maans ,theirans , mascore , matime,topass,wtfidid;
        ImageView pkgimg;

        public MyViewHolder(View view) {
            super(view);
            maans = view.findViewById(R.id.maans);
            theirans = view.findViewById(R.id.theirass);
            mascore = view.findViewById(R.id.myscoreass);
            matime = view.findViewById(R.id.matime);
            topass = view.findViewById(R.id.topass);
            wtfidid = view.findViewById(R.id.wtfidid);
//            id = (TextView) view.findViewById(R.id.level_id);

            sessionManager = new SessionManager(view.getContext());
            context  = view.getContext();
        }
    }


    @NonNull
    @Override
    public Analysis_Recy_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.questioon_report, viewGroup, false);

        return new Analysis_Recy_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Analysis_Recy_Adapter.MyViewHolder myViewHolder, int i) {

        Detailed_Analysis_const_data detailed_analysis_const = detailed_analysis_const_data.get(i);
        myViewHolder.maans.setText(String.valueOf(detailed_analysis_const.getAns()));
        myViewHolder.theirans.setText(String.valueOf(detailed_analysis_const.getQueAns()));
        if(detailed_analysis_const.getAns().equals(detailed_analysis_const.getQueAns()))
        {
//            myViewHolder.wtfidid.setBackgroundColor(Color.GREEN);
            myViewHolder.wtfidid.setText("CORRECT");
            myViewHolder.wtfidid.setTextColor(Color.GREEN);
//            SpannableStringBuilder builder = new SpannableStringBuilder();
//            String green = "Correct";
//            SpannableString redSpannable= new SpannableString(green);
//            redSpannable.setSpan(new ForegroundColorSpan(Color.GREEN), 0, green.length(), 0);
//            builder.append(redSpannable);
//            myViewHolder.wtfidid.setText("You answered this question "+green);
        }else {
//            myViewHolder.wtfidid.setBackgroundColor(Color.RED);

            myViewHolder.wtfidid.setText("WRONG");
            myViewHolder.wtfidid.setTextColor(Color.RED);
//            SpannableStringBuilder builder = new SpannableStringBuilder();
//
//            String red = "Wrong";
//            SpannableString redSpannable= new SpannableString(red);
//            redSpannable.setSpan(new ForegroundColorSpan(Color.RED), 0, red.length(), 0);
//            builder.append(redSpannable);
//            myViewHolder.wtfidid.setText("You answered this question "+red);
        }

//        myViewHolder.package_mrp.setText(String.valueOf(detailed_analysis_const.getPackage_mr()));
//        myViewHolder.pkgtype.setText(String.valueOf(detailed_analysis_const.getPackage_type()));
//        Log.e("image url" ,package_url +detailed_analysis_const.getPackage_image());
//        Glide.with(context).load(package_url +detailed_analysis_const.getPackage_image())
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
        return detailed_analysis_const_data.size();
    }

}
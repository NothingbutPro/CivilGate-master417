package dev.raghav.civilgate.Dapter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.card.MaterialCardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.TimeUnit;

import dev.raghav.civilgate.Activities.MainActivity;
import dev.raghav.civilgate.Api.Api;
import dev.raghav.civilgate.Const_Files.Level_Java;
import dev.raghav.civilgate.Const_Files.Retro_Urls;
import dev.raghav.civilgate.Const_Files.Tests_Name;
import dev.raghav.civilgate.MAin_test_collpase;
import dev.raghav.civilgate.Other_Parsing_Files.Exam_Test;
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

public class Test_Adapter  extends RecyclerView.Adapter<Test_Adapter.MyViewHolder> {
    private List<Tests_Name> levelAdapterList;
    SessionManager sessionManager;
    public Test_Adapter(List<Tests_Name> levelAdapterList) {
        this.levelAdapterList = levelAdapterList;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id, Test_name , Test_start , Test_finishes,Test_status,Test_len;
        MaterialCardView test_mat;

        public MyViewHolder(View view) {
            super(view);
//            id = (TextView) view.findViewById(R.id.level_id);
            Test_name = view.findViewById(R.id.testname);
            Test_len = view.findViewById(R.id.testlenth);
            Test_status = view.findViewById(R.id.teststat);
            Test_start = view.findViewById(R.id.test_start_date);
            Test_finishes = view.findViewById(R.id.test_end_date);
            test_mat = view.findViewById(R.id.test_mat);
            sessionManager = new SessionManager(view.getContext());

        }
    }


    @NonNull
    @Override
    public Test_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.exam_test, viewGroup, false);

        return new Test_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Test_Adapter.MyViewHolder myViewHolder, int i) {

        Tests_Name tests_Name = levelAdapterList.get(i);
//        myViewHolder.id.setText(String.valueOf(tests_Name.getTest_start_date()) );
        Log.e("test at adapter" , "name at"+tests_Name.getTest_name());
       myViewHolder.Test_name.setText(tests_Name.getTest_name());
       myViewHolder.Test_len.setText(String.valueOf(tests_Name.getTest_len()));

       myViewHolder.Test_start.setText(tests_Name.test_start_date);
       myViewHolder.Test_finishes.setText(tests_Name.test_end_date);
       myViewHolder.test_mat.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                new Start_the_test(v, sessionManager.getCoustId() , tests_Name.getSubject_ids());

                   Toast.makeText(v.getContext(), "ids are "+tests_Name.getSubject_ids(), Toast.LENGTH_SHORT).show();
                   //  Intent getquestionIntent = new Intent(v.getContext() , Main_Test_Activity.class);
                   Intent getquestionIntent = new Intent(v.getContext() , Main_Test_Activity.class);
                   getquestionIntent.putExtra("sub_id" ,tests_Name.getSubject_ids() );
//               getquestionIntent.putExtra("no_of_que" , tests_Name.)
                   v.getContext().startActivity(getquestionIntent);


           }
       });

    }

//    private Void Start_the_test(View v, String subject_ids) {
//
//    }


    @Override
    public int getItemCount() {
        return levelAdapterList.size();
    }

    private class Start_the_test extends AsyncTask<Void ,Void,String> {
        public Start_the_test(View v, int coustId, String subject_ids) {

        }

        @Override
        protected String doInBackground(Void... voids) {
            return null;
        }
    }
}

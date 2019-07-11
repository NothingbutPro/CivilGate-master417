package dev.raghav.civilgate.Dapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.card.MaterialCardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.TimeUnit;

import dev.raghav.civilgate.Activities.Direct_History;
import dev.raghav.civilgate.Api.Api;
import dev.raghav.civilgate.Api.Retro_Urls;
import dev.raghav.civilgate.Const_Files.Tests_Name;
import dev.raghav.civilgate.Instant_Report.Instant_Results_Activity;
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
        public TextView id, Test_name, Test_start, Test_finishes, Test_status, Test_len;
        Button teststatus;
        MaterialCardView test_mat;

        public MyViewHolder(View view) {
            super(view);
//            id = (TextView) view.findViewById(R.id.level_id);
            Test_name = view.findViewById(R.id.testname);
          //  Test_len = view.findViewById(R.id.testlenth);
//            Test_status = view.findViewById(R.id.teststat);
            teststatus = view.findViewById(R.id.teststatus);
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
        Log.e("test at adapter", "name at" + tests_Name.getTest_start_date());
        Log.e("test at adapter", "time at" + tests_Name.getTest_len());
   //     Log.e("test at adapter", "time at" + tests_Name.);
        myViewHolder.Test_name.setText("Test Name : "+tests_Name.getTest_name());
        try {
            if (tests_Name.getQStatus().equals("2"))
            {
                myViewHolder.teststatus.setText("Report");
            }else {
                myViewHolder.teststatus.setText("Incomplete");
            }
        }catch (Exception e)
        {
            myViewHolder.teststatus.setText("Open");
            e.printStackTrace();
        }
     //   myViewHolder.Test_name.setText("pppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp");
     //   myViewHolder.Test_len.setText(String.valueOf(tests_Name.getTest_len()) +"Mins");
        myViewHolder.Test_start.setText(tests_Name.getTest_start_date());
        myViewHolder.Test_finishes.setText(tests_Name.getTest_end_date());
        myViewHolder.teststatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myViewHolder.teststatus.getText().toString().equals("Report"))
                {
                    Intent intent = new Intent(v.getContext() , Direct_History.class);
                    intent.putExtra("sublevelid" , tests_Name.getSub_level_cat_id());
                    intent.putExtra("levelid" , tests_Name.getTest_id());
                    v.getContext().startActivity(intent);
                }
                else {
                    StartTheTest(v, tests_Name.getTest_name(), tests_Name.getTest_id(), sessionManager.getCoustId(), Integer.valueOf(tests_Name.getTest_id()), tests_Name.getSub_level_cat_id(), 0, tests_Name.getSubject_ids());
                }
            }
        });
      //  myViewHolder.Tes.setText(tests_Name.getTest_end_date());
//        myViewHolder.test_mat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//             //   new Start_the_test(v, sessionManager.getCoustId(), tests_Name.getSubject_ids());
//
//             //   StartTheTest(v ,tests_Name.getTest_name(), tests_Name.getTest_id(),sessionManager.getCoustId(),1,tests_Name.getSub_level_cat_id() ,0,tests_Name.getSubject_ids());
//                if(myViewHolder.teststatus.getText().toString().equals("Report"))
//                {
//                 Intent intent = new Intent(v.getContext() , Instant_Results_Activity.class);
//                 v.getContext().startActivity(intent);
//                }else {
//                    StartTheTest(v, tests_Name.getTest_name(), tests_Name.getTest_id(), sessionManager.getCoustId(), Integer.valueOf(tests_Name.getTest_id()), tests_Name.getSub_level_cat_id(), 0, tests_Name.getSubject_ids());
//                }
//
//
//            }
//        });

    }

    private void StartTheTest(View v, String test_name, String test_id, int coustId, int i, String sub_level_cat_id, int i1, String subject_ids) {
        Log.d("test name" , ""+test_name);
        Log.d("sess const id" , ""+sessionManager.getCoustId());
        Log.d("sess const id" , ""+coustId);
        Log.d("test name" , "1");
        Log.d("sub elvel cat" , ""+sub_level_cat_id);

        Log.d("i1" , ""+i1);
        Log.d("subject ids" , ""+subject_ids);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(150, TimeUnit.SECONDS)
                .readTimeout(300,TimeUnit.SECONDS).writeTimeout(200 , TimeUnit.SECONDS).build();
        ProgressDialog ExamprogressDialog;
        ExamprogressDialog = new ProgressDialog(v.getContext());
        ExamprogressDialog.setMax(100);
        ExamprogressDialog.setTitle("Starting your test");
        ExamprogressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        ExamprogressDialog.setCancelable(false);
        ExamprogressDialog.show();
        Retrofit RetroGEtExam = new Retrofit.Builder()
                .baseUrl(Retro_Urls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api EmamApi = RetroGEtExam.create(Api.class);
        Call<TestStart> exam_testCall = EmamApi.GetTestQuestionCall(coustId,i,sub_level_cat_id,subject_ids);
        exam_testCall.enqueue(new Callback<TestStart>() {
            @Override
            public void onResponse(Call<TestStart> call, Response<TestStart> response) {

                //  Toast.makeText(getActivity(), "Test name"+response.body().getData().get(0).getTestName(), Toast.LENGTH_SHORT).show();
                if(response.isSuccessful())
                {
                    for(int k=0 ; k<response.body().getData().size() ;k++)
                    {
                        Log.i("responce's question " , ""+response.body().getData().get(k).getQue());
                    }
                    ExamprogressDialog.dismiss();
                    if(response.body().getResponce() == true)
                    {
//                        Log.e("elements" , " are "+response.body().getData().get(0).getQue());
//                        Log.e("id elements" , " are "+response.body().getData().get(0).getId());
//                        Log.e("sub elements" , " are "+response.body().getData().get(0).getSubId());
                        Toast.makeText(v.getContext(), "Test Started", Toast.LENGTH_SHORT).show();
                      //  Toast.makeText(v.getContext(), "ids are " + tests_Name.getSubject_ids(), Toast.LENGTH_SHORT).show();
                        //  Intent getquestionIntent = new Intent(v.getContext() , Main_Test_Activity.class);
                        Log.e("sub_leve_id" ,""+sub_level_cat_id);
                        Log.e("level_id" ,""+coustId);
                        Intent getquestionIntent = new Intent(v.getContext(), Main_Test_Activity.class);
                        getquestionIntent.putExtra("sub_id", subject_ids);
                        getquestionIntent.putExtra("sub_leve_id", sub_level_cat_id);
                        getquestionIntent.putExtra("level_id", i);
//               getquestionIntent.putExtra("no_of_que" , tests_Name.)
                        v.getContext().startActivity(getquestionIntent);
                        ((Activity)v.getContext()).finish();
                    }
                    else
                        {
                        Log.e("Que is" , ""+response.body().getData().get(0).getQue());
                        Toast.makeText(v.getContext(), ""+response.body().getData().get(0).getQue(), Toast.LENGTH_SHORT).show();
                    }
//
                }

            }

            @Override
            public void onFailure(Call<TestStart> call, Throwable t) {
                Log.w("MyTag", "requestFailedwa"+t);
                //            Log.w("MyTag", "requestFailed "+ call.clone().isExecuted());

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

}

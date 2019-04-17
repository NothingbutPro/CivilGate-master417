package dev.raghav.civilgate.Frag_granades;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import dev.raghav.civilgate.SessionManage.SessionManager;
import dev.raghav.civilgate.Api.Api;
import dev.raghav.civilgate.Const_Files.Retro_Urls;
import dev.raghav.civilgate.Const_Files.Tests_Name;
import dev.raghav.civilgate.Dapter.Test_Adapter;
import dev.raghav.civilgate.Other_Parsing_Files.Exam_Test;
import dev.raghav.civilgate.R;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Basic_Level_Test_Fragment  extends Fragment {
    private static  long Progess_time_out = 0;
    SessionManager manager;
    Test_Adapter testAdapter;
    RecyclerView tests_recy;
    Thread thread;
     int  px =0;
    int[] p = new  int[20];
    private List<Tests_Name> tests_names= new ArrayList<>();

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View BasicView  = inflater.inflate(R.layout.basic_level_layout , container , false);
        tests_recy = BasicView.findViewById(R.id.tests_recy);
       // lowerServiceGenerator = new LowerServiceGenerator(getActivity().getSupportFragmentManager() , getActivity());
      //  ExamprogressDialog = new ProgressDialog(getActivity());
        manager = new SessionManager(getActivity());

        if (getAllLowerLevels())
        {
            Toast.makeText(getActivity(), "tyfjuyjutuyt true", Toast.LENGTH_SHORT).show();

         //   ExamprogressDialog.dismiss();
        }
        return BasicView;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private boolean getAllLowerLevels() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(150, TimeUnit.SECONDS)
                .readTimeout(300,TimeUnit.SECONDS).writeTimeout(200 , TimeUnit.SECONDS).build();
        ProgressDialog ExamprogressDialog;
        ExamprogressDialog = new ProgressDialog(getActivity());
        ExamprogressDialog.setMax(100);
        ExamprogressDialog.setTitle("Getting your level information");
        ExamprogressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        ExamprogressDialog.setCancelable(false);
        ExamprogressDialog.show();
        Retrofit RetroGEtExam = new Retrofit.Builder()
                .baseUrl(Retro_Urls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api EmamApi = RetroGEtExam.create(Api.class);
        Call<Exam_Test> exam_testCall = EmamApi.Get_GetExam(manager.getCoustId());
        exam_testCall.enqueue(new Callback<Exam_Test>() {
            @Override
            public void onResponse(Call<Exam_Test> call, Response<Exam_Test> response) {

              //  Toast.makeText(getActivity(), "Test name"+response.body().getData().get(0).getTestName(), Toast.LENGTH_SHORT).show();
                if(response.isSuccessful())
                {
                    ExamprogressDialog.dismiss();
//                    if(response.body().getResponce() == true)
//                    {
                        Log.e("elements" , " are "+response.body());
                        p =new int[response.body().getData().size()];
                        for(int i=0;i<response.body().getData().size();i++)
                        {
                            px= px+1;
//                            p[0] =i;
                            String s = response.body().getData().get(i).getTestName();
                            Log.e("elements" , "are"+response.body().getData().get(0).getTestName());
                            Tests_Name tests_name = new Tests_Name(response.body().getData().get(i).getTestName() , response.body().getData().get(i).getTestStartDate() , response.body().getData().get(i).getTestEndDate(),response.body().getData().get(i).getTesttime() , response.body().getData().get(i).getSubjectIds());
                            tests_names.add(tests_name);
                        }

//                        if(p[0] >=1)
//                        {

                            testAdapter = new Test_Adapter(tests_names);
                            //  RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                            llm.setOrientation(LinearLayoutManager.VERTICAL);
                            tests_recy.setLayoutManager(llm);
                            tests_recy.setAdapter( testAdapter );

//                        }

                    }
//
//                }

            }

            @Override
            public void onFailure(Call<Exam_Test> call, Throwable t) {
                Log.w("MyTag", "requestFailed", t);

            }
        });
//            if(px>0)
//            {
//                ExamprogressDialog.dismiss();
//            }
//        tests_recy.setLayoutManager(mLayoutManager);
//        tests_recy.setItemAnimator(new DefaultItemAnimator());
//        tests_recy.setAdapter(testAdapter);
     //   p = null;
        return true;
    }

    @Override
    public void onDetach() {
        tests_names.clear();
        tests_recy.setAdapter(null);
        testAdapter.notifyDataSetChanged();
        super.onDetach();
    }
}

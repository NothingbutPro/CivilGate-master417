package dev.raghav.civilgate.Reports_Adapters.History_Fragments;

import android.app.ProgressDialog;
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

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import dev.raghav.civilgate.Api.Api;
import dev.raghav.civilgate.Api.Retro_Urls;
import dev.raghav.civilgate.R;
import dev.raghav.civilgate.Reports_Adapters.Basic_History_Adapter;
import dev.raghav.civilgate.Reports_Adapters.Parsing_files.HisHistory;
import dev.raghav.civilgate.SessionManage.SessionManager;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Basic_History extends Fragment {
    SessionManager sessionManager;
        RecyclerView basihis;
        Basic_History_Adapter historyAdapter;
        private  ArrayList<dev.raghav.civilgate.Reports_Adapters.History_Java.Basic_History> historyArrayList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View BasicView = inflater.inflate(R.layout.basic_history , container , false);
        basihis = BasicView.findViewById(R.id.basihis);
        sessionManager = new SessionManager(BasicView.getContext());
        Get_Basic_Details();

        return BasicView;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private Boolean Get_Basic_Details() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(150, TimeUnit.SECONDS)
                .readTimeout(300,TimeUnit.SECONDS).writeTimeout(200 , TimeUnit.SECONDS).build();
        Retrofit LEvelRetrofit = new Retrofit.Builder().client(client).baseUrl(Retro_Urls.The_Base).addConverterFactory(GsonConverterFactory.create()).build();
        Api LevelApi = LEvelRetrofit.create(Api.class);
        Call<HisHistory> get_levelCall = LevelApi.Gethistory(sessionManager.getCoustId());
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMax(1000);
        progressDialog.setTitle("Getting Your Data");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        get_levelCall.enqueue(new Callback<HisHistory>() {

            @Override
            public void onResponse(Call<HisHistory> call, Response<HisHistory> response) {
                historyArrayList = new ArrayList<>();
                if(response.body().getResponce().booleanValue() == true)
                {
                    progressDialog.dismiss();
                    HisHistory hisHistory = response.body();
                    Log.e("responce" , ""+hisHistory.getData().size());
                    for(int p =0;p<hisHistory.getData().size();p++)
                    {
                      if(hisHistory.getData().get(p).getLevelname().equals("Basic Level"))
                      {
                          Log.e("level name uis" , ""+hisHistory.getData().get(p).getLevelname());
                          historyArrayList.add(new dev.raghav.civilgate.Reports_Adapters.History_Java.Basic_History(response.body().getData().get(p).getLevelname()
                                  ,response.body().getData().get(p).getTestName(),response.body().getData().get(p).getTId(),
                                  response.body().getData().get(p).getStudentId(),response.body().getData().get(p).getLevelId(),response.body().getData().get(p).getLevelSubId()
                                  ,response.body().getData().get(p).getDate()

                          ));

                      }
                    }

                        historyAdapter = new Basic_History_Adapter(historyArrayList);
                        //      RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                        llm.setOrientation(LinearLayoutManager.VERTICAL);
                        basihis.setLayoutManager(llm);
                        basihis.setAdapter(historyAdapter);

                }

            }
            //
            @Override
            public void onFailure(Call<HisHistory> call, Throwable t) {
                Log.d("cause" , ""+t.getCause());
            }
        });

        return true;

    }
}

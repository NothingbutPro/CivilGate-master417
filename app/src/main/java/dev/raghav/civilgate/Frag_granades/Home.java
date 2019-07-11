package dev.raghav.civilgate.Frag_granades;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import dev.raghav.civilgate.Activities.Critical_Thinking;
import dev.raghav.civilgate.Activities.Level_Tab_Activities;
import dev.raghav.civilgate.Activities.Profile_Activity;
import dev.raghav.civilgate.Activities.ShowAllPakages;
import dev.raghav.civilgate.Api.Api;
import dev.raghav.civilgate.Api.Retro_Urls;
import dev.raghav.civilgate.Const_Files.Myoverall;
import dev.raghav.civilgate.Const_Files.MyoverallData;
import dev.raghav.civilgate.Other_Parsing_Files.Get_About;
import dev.raghav.civilgate.R;
import dev.raghav.civilgate.SessionManage.SessionManager;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home extends Fragment {
    ImageView leve_id;
    CardView procard;
    ProgressBar pro1,pro2,pro3,pro4;
    TextView totaltes , solvet,tesnscore,cedittxt;
    CardView buy_package_icon ,mythink;
    SessionManager sessionManagerl;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.home,container,false);
        leve_id = v.findViewById(R.id.leve_id);
        procard = v.findViewById(R.id.procard);
        totaltes = v.findViewById(R.id.totalnu);
        pro1 = v.findViewById(R.id.pro1);
        pro2 = v.findViewById(R.id.pro2);
        pro3 = v.findViewById(R.id.pro3);
        pro4 = v.findViewById(R.id.pro4);
        solvet = v.findViewById(R.id.solvetest);
        tesnscore = v.findViewById(R.id.tesnscore);
        sessionManagerl = new SessionManager(getActivity());
        cedittxt = v.findViewById(R.id.cedittxt);
        mythink = v.findViewById(R.id.mythink);
        buy_package_icon = v.findViewById(R.id.buyicon);

        mythink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , Critical_Thinking.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        procard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , Profile_Activity.class);
                startActivity(intent);
            }
        });
        buy_package_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , ShowAllPakages.class);
                startActivity(intent);
            }
        });
        leve_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to_leveltab = new Intent(getActivity() , Level_Tab_Activities.class);
                startActivity(to_leveltab);
            }
        });
        GetallTest();
        return  v;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void GetallTest() {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100,TimeUnit.SECONDS).build();
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(Retro_Urls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api AbloutApi = RetroLogin.create(Api.class);
        Call<Myoverall> get_aboutCall = AbloutApi.MYOVERALL_DATA_CALL(sessionManagerl.getCoustId());
        get_aboutCall.enqueue(new Callback<Myoverall>() {
            @Override
            public void onResponse(Call<Myoverall> call, Response<Myoverall> response) {
                Log.e("total 1" , ""+response.body().getData().getTotaltest());
                Log.e("total 2" , ""+response.body().getData().getSolved());
                Log.e("total 3" , ""+response.body().getData().getScore());
                Log.e("total 4" , ""+response.body().getData().getCredit());
                totaltes.setText(String.valueOf(response.body().getData().getTotaltest()));
                solvet.setText(String.valueOf(response.body().getData().getSolved()));
                tesnscore.setText(String.valueOf(response.body().getData().getScore()));
                cedittxt.setText(String.valueOf(response.body().getData().getCredit()));
                pro1.setProgress(Math.round(Float.parseFloat(response.body().getData().getTotaltest())));
                pro2.setProgress(Math.round(Float.parseFloat(response.body().getData().getSolved())));
                pro3.setProgress(Math.round(Float.parseFloat(response.body().getData().getScore())));
                pro4.setProgress(Math.round(Float.parseFloat(response.body().getData().getCredit())));
             //   Toast.makeText(getActivity(), ""+response.body().getData().getDescription(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Myoverall> call, Throwable t) {
                Log.e("error" , ""+t.getMessage());
                Log.e("error" , ""+t.getCause());
                Log.e("error" , ""+t.getStackTrace());
                Log.e("error session" , ""+sessionManagerl.getCoustId());
                Log.e("error2" , ""+t.getLocalizedMessage());
                Toast.makeText(getActivity(), ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}

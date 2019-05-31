package dev.raghav.civilgate.Full_Solution;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import dev.raghav.civilgate.Api.Api;
import dev.raghav.civilgate.Api.Retro_Urls;
import dev.raghav.civilgate.Const_Files.Detailed_Analysis_const;
import dev.raghav.civilgate.Const_Files.Detailed_Analysis_const_data;
import dev.raghav.civilgate.Const_Files.Full_Solutions;
import dev.raghav.civilgate.Const_Files.PostBookMarks;
import dev.raghav.civilgate.Detailed_Analysis.Analysis_Recy_Adapter;
import dev.raghav.civilgate.R;
import dev.raghav.civilgate.SessionManage.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static dev.raghav.civilgate.Full_Solution.Full_Solution_Act.lel_id;
import static dev.raghav.civilgate.Full_Solution.Full_Solution_Act.sublel_id;

public class MCQ_Questions extends Fragment {
   SessionManager sessionManager;
   WebView webque;
   TextView soledit ,ansss1,ansss2,ansss3,ansss4;
   static public int solutioncounter =0;
   ImageView bookmark;
   Full_Solutions full_solutions;
   TextView next ,previous;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mcq_questions , container , false);
        sessionManager = new SessionManager(getActivity());
        webque = view.findViewById(R.id.webque);
        next = view.findViewById(R.id.next);
        previous = view.findViewById(R.id.previous);
        bookmark = view.findViewById(R.id.bookmark);
        ansss1 = view.findViewById(R.id.ansss1);
        ansss2 = view.findViewById(R.id.ansss2);
        ansss3 = view.findViewById(R.id.ansss3);
        ansss4 = view.findViewById(R.id.ansss4);
        GetTheFullSolution();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.solframe , new MCQ_Questions());
                fragmentTransaction.commit();
                fragmentTransaction.addToBackStack(null);
                solutioncounter++;
            }
        }
        );
        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HaveABookMArk(full_solutions.getData().get(solutioncounter).getQueid());
             //   Toast.makeText(getActivity(), " Book Mark Added "+full_solutions.getData().get(solutioncounter).getQueid(), Toast.LENGTH_SHORT).show();
//                for(int p=0;p<full_solutions.getData().size();p++)
//                {
//                 if(full_solutions.getData().get(p).equals(full_solutions.getData().get(solutioncounter).getQue()))
//                 {
//
//                 }
//                }

            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.solframe , new MCQ_Questions());
                fragmentTransaction.commit();
                fragmentTransaction.addToBackStack(null);
                --solutioncounter;
            }
        });
        return view;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void HaveABookMArk(String queid) {
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(Retro_Urls.The_Base).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api RegApi = RetroLogin.create(Api.class);
        Log.w("book markid is" , ""+queid);
        Call<PostBookMarks> login_responceCall = RegApi.PostBookMarks(sessionManager.getCoustId() ,queid );
        login_responceCall.enqueue(new Callback<PostBookMarks>() {
            @Override
            public void onResponse(Call<PostBookMarks> call, Response<PostBookMarks> response) {
                Log.d("string" , ""+response.message());
                if(response.isSuccessful())
                {
                    if(response.body().getResponce() ==true)
                    {
                        Toast.makeText(getActivity(), "Book Mark added successfully", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "Not able to add book mark", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getActivity(), ""+response.errorBody(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostBookMarks> call, Throwable t) {

                Log.d("cause" , ""+t.getCause());
                Log.d("main cause" , ""+t.getLocalizedMessage());
                Log.d("cause" , ""+t.getMessage());
                Toast.makeText(getActivity(), "Network problem", Toast.LENGTH_SHORT).show();

            }
        });




    }

    private void GetTheFullSolution() {
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(Retro_Urls.The_Base).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api RegApi = RetroLogin.create(Api.class);
        Call<Full_Solutions> login_responceCall = RegApi.FULL_SOLUTIONS_CALL(sessionManager.getCoustId() ,lel_id ,sublel_id );
        login_responceCall.enqueue(new Callback<Full_Solutions>() {
            @Override
            public void onResponse(Call<Full_Solutions> call, Response<Full_Solutions> response) {
                Log.d("string" , ""+response.body().getResponce());
//                            Log.d("string" , ""+response.body().getData().getEmail());
                if(response.body().getResponce())
                {
                    //   Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();
                    for(int k=0;k< response.body().getData().size() ;k++) {
                       Log.w("whtas" , ""+response.body().getData().get(k).getQue());
                       if(solutioncounter <response.body().getData().size() &&  solutioncounter==k) {
                           webque.loadData(response.body().getData().get(solutioncounter).getQue().toString(), "text/html", null);
                         int maans = Integer.valueOf(response.body().getData().get(solutioncounter).getAns());
                         int rightans = Integer.valueOf(response.body().getData().get(solutioncounter).getQueAns());

                       }
                        full_solutions = response.body();
                        //      Log.e("image" , "url"+ response.body().getData().get(k).getPackageImage());
                    }

//                    Intent intent=new Intent(ShowAllPakages.this,MainActivity.class);
//                    //manager.serverLogin(response.body().getData().getId() , response.body().getData().getName(),response.body().getData().getStatus());
//                    intent.putExtra("respoce", ""+response);
//                    startActivity(intent);
//                    finish();
                }

                else

                    {
                    //    Toast.makeText(ShowAllPakages.this, "Either Email is wrong or Password", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Full_Solutions> call, Throwable t) {

                Log.d("cause" , ""+t.getCause());
                Log.d("main cause" , ""+t.getLocalizedMessage());
                Log.d("cause" , ""+t.getMessage());
                Toast.makeText(getActivity(), "Network problem", Toast.LENGTH_SHORT).show();

            }
        });




    }

    @Override
    public void onDetach() {
        solutioncounter=0;
        Toast.makeText(getActivity(), "fragment detached "+solutioncounter, Toast.LENGTH_SHORT).show();
        getActivity().finish();
        super.onDetach();
    }
}
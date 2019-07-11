package dev.raghav.civilgate.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import dev.raghav.civilgate.Api.Api;
import dev.raghav.civilgate.Api.Retro_Urls;
import dev.raghav.civilgate.Const_Files.Full_Solution_Data;
import dev.raghav.civilgate.Const_Files.Full_Solutions;
import dev.raghav.civilgate.Dapter.Question_Reviews_Adapter;
import dev.raghav.civilgate.Full_Solution.MCQ_Questions;
import dev.raghav.civilgate.R;
import dev.raghav.civilgate.SessionManage.SessionManager;
import dev.raghav.civilgate.Test_Activities.Dapter.Questions_Adapter;
import dev.raghav.civilgate.Test_Activities.Main_Test_Activity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static dev.raghav.civilgate.Activities.Direct_History.levelid;
import static dev.raghav.civilgate.Activities.Direct_History.sublevelid;
import static dev.raghav.civilgate.Full_Solution.Full_Solution_Act.lel_id;
import static dev.raghav.civilgate.Full_Solution.Full_Solution_Act.sublel_id;

public class All_Reviews_Questions extends AppCompatActivity {
 RecyclerView allquegrid;
 String maque;
 Question_Reviews_Adapter question_reviews_adapter;
 SessionManager sessionManager;
 ArrayList<Full_Solution_Data> full_solutionsArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__reviews__questions);
        allquegrid = findViewById(R.id.allquegrid);
        maque = getIntent().getStringExtra("maque");
        sessionManager = new SessionManager(this);
        GetAllinGRid(maque);
    }

    private void GetAllinGRid(String maque) {
        Log.e("levelid is" , ""+lel_id);
        Log.e("sublel_id is" , ""+sublel_id);
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(Retro_Urls.The_Base).addConverterFactory(GsonConverterFactory.create())
                .build();

        Api RegApi = RetroLogin.create(Api.class);
        Call<Full_Solutions> login_responceCall = RegApi.FULL_SOLUTIONS_CALL(sessionManager.getCoustId() ,levelid ,sublevelid );
        login_responceCall.enqueue(new Callback<Full_Solutions>() {
            @Override
            public void onResponse(Call<Full_Solutions> call, Response<Full_Solutions> response) {
                Log.d("string" , ""+response.body().getResponce());

                for(int x=0;x<response.body().getData().size(); x++)
                {
                    full_solutionsArrayList.add(new Full_Solution_Data(Integer.toString(x) , response.body().getData().get(x).getQueAns() , response.body().getData().get(x).getAns() ,response.body().getData().get(x).getSId() , response.body().getData().get(x).getTestId()));

                }

                GridLayoutManager manager = new GridLayoutManager(All_Reviews_Questions.this, 6, GridLayoutManager.VERTICAL, false);
                allquegrid.setLayoutManager(manager);
//                            Log.d("string" , ""+response.body().getData().getEmail());
                if(response.body().getResponce())
                {
                    if(response.body().getResponce())
                    {
                        question_reviews_adapter = new Question_Reviews_Adapter(All_Reviews_Questions.this, full_solutionsArrayList);
//                quelinrecy.setHasFixedSize(true);
                        allquegrid.setAdapter(question_reviews_adapter); // set the Adapter to RecyclerView
                        //getAllQuestions(student_id);
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
           //     Toast.makeText(getActivity(), "Network problem", Toast.LENGTH_SHORT).show();

            }
        });



    }
}

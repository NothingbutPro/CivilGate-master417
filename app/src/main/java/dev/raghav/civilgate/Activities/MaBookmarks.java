package dev.raghav.civilgate.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import dev.raghav.civilgate.Api.Api;
import dev.raghav.civilgate.Api.Retro_Urls;
import dev.raghav.civilgate.Const_Files.BooktheMarks;
import dev.raghav.civilgate.Const_Files.BooktheMarksData;
import dev.raghav.civilgate.Frag_granades.Boomarks_Fragment;
import dev.raghav.civilgate.Other_Parsing_Files.Get_About;
import dev.raghav.civilgate.R;
import dev.raghav.civilgate.SessionManage.SessionManager;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MaBookmarks extends AppCompatActivity {
    RecyclerView bookrecy;

    SessionManager sessionManager;
    String test_id;
    public static ArrayList<BooktheMarksData> marksArrayList = new ArrayList<>();
    static  public int ixp=10;
    FrameLayout soleedty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_ma_bookmarks);
        setContentView(R.layout.activity_full__solution_);
     //   bookrecy = findViewById(R.id.bookrecy);
        soleedty = findViewById(R.id.solframeboo);
        sessionManager = new SessionManager(this);
        GetAllBookmarks();
    }

    private void GetAllBookmarks() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100,TimeUnit.SECONDS).build();
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(Retro_Urls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api AbloutApi = RetroLogin.create(Api.class);
        Call<BooktheMarks> get_aboutCall = AbloutApi.BOOKTHE_MARKS_CALL(sessionManager.getCoustId() ,test_id);
        get_aboutCall.enqueue(new Callback<BooktheMarks>() {
            @Override
            public void onResponse(Call<BooktheMarks> call, Response<BooktheMarks> response) {
                for(int i=0;i<response.body().getData().size(); i++){
                    Log.e("hi" , "bookamr"+response.body().getData().get(i).getQue());
                    marksArrayList.add(new BooktheMarksData(response.body().getData().get(i).getId(),response.body().getData().get(i).getSubId(),
                            response.body().getData().get(i).getQue(),response.body().getData().get(i).getAns1(),response.body().getData().get(i).getAns2(),
                            response.body().getData().get(i).getAns3(),response.body().getData().get(i).getAns4(),response.body().getData().get(i).getAns3(),response.body().getData().get(i).getQue(),
                            response.body().getData().get(i).getAns3(),response.body().getData().get(i).getFromAns(), response.body().getData().get(i).getToAns(),response.body().getData().get(i).getSolution(),response.body().getData().get(i).getStatus(),response.body().getData().get(i).getVideo(),response.body().getData().get(i).getVideoUrl(),response.body().getData().get(i).getCreatedate(),response.body().getData().get(i).getType()
                            ,response.body().getData().get(i).getTestName()
                            ));

                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.solframeboo , new Boomarks_Fragment()).commit();
//                bookrecy.setLayoutManager(llm);
//                bookrecy.setAdapter( new dev.raghav.civilgate.Dapter.BooktheMarks(marksArrayList));

           //     Toast.makeText(MaBookmarks.this, ""+response.body().getData().getDescription(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<BooktheMarks> call, Throwable t) {

            }
        });
    }
}

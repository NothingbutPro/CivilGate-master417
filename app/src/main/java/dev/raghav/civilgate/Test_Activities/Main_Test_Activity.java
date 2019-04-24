package dev.raghav.civilgate.Test_Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import dev.raghav.civilgate.Api.Api;
import dev.raghav.civilgate.Const_Files.Questions_jJava;
import dev.raghav.civilgate.Const_Files.Retro_Urls;
import dev.raghav.civilgate.Other_Parsing_Files.Submit_Question;
import dev.raghav.civilgate.Other_Parsing_Files.Test_Question;
import dev.raghav.civilgate.R;
import dev.raghav.civilgate.SessionManage.SessionManager;
import dev.raghav.civilgate.Test_Activities.Dapter.Questions_Adapter;
import dev.raghav.civilgate.Test_Activities.Test_Types.Fill_In_Que_Test;
import dev.raghav.civilgate.Test_Activities.Test_Types.Multiple_Que_Test;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main_Test_Activity extends AppCompatActivity {
    Questions_Adapter questions_adapter;
    public static int queposition = 0;
    RecyclerView quelinrecy;
    static int no_of_questions;

    TextView fab2;
    TextView fab;
   public static HashMap<Integer , Questions_jJava> questionsJJavaHashMap = new HashMap<>();
    private long startTime = 0L;

    private Handler customHandler = new Handler();
    SessionManager sessionManager;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    // BottomSheetBehavior variable
    private BottomSheetBehavior bottomSheetBehavior;
    NestedScrollView nestedScrollView;
    public static LinkedList<Questions_jJava> questionsJJavaLinkedList = new LinkedList<>();
    String student_id;
   // public static int Ansposition =0;
    List<Questions_jJava> questions_jJavaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapse);
        sessionManager = new SessionManager(this);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nestedScrollView = findViewById(R.id.nestedScrollView);
        bottomSheetBehavior = BottomSheetBehavior.from(nestedScrollView);
        fab = findViewById(R.id.fab);
        fab2 = findViewById(R.id.fab2);

        //---------------------------Timer------------------

        new CountDownTimer(1800000, 1000) {

            public void onTick(long millisUntilFinished) {
                String text = String.format(Locale.getDefault(), "%02d : %02d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60);
                fab2.setText(text);
                //    textView.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                //  fab.setText("done!");
            }
        }.start();
        //-------------------------------------------------

       /* startTime = SystemClock.uptimeMillis();
        customHandler.postDelayed(updateTimerThread, 0);*/

       //--------------------------------------------------

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        student_id = getIntent().getStringExtra("sub_id");
        Toast.makeText(this, "student naME IS" + student_id, Toast.LENGTH_SHORT).show();
        quelinrecy = findViewById(R.id.gridlay);
        bottomSheetBehavior.setHideable(true);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        GridLayoutManager manager = new GridLayoutManager(this, 9, GridLayoutManager.VERTICAL, false);
        quelinrecy.setLayoutManager(manager);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_COLLAPSED) {
                    fab.setText("Open Question Tab");
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                } else {
                    fab.setText("Close Question Tab");
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }

            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   buildDialog(R.style.DialogAnimation, "Left - Right Animation!");
            }
        });

        getAllQuestions(student_id);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //Remove this line to disable peek
        bottomSheetBehavior.setPeekHeight(200);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                String state = "";

                switch (newState) {
                    case BottomSheetBehavior.STATE_DRAGGING: {
                        state = "DRAGGING";
                        break;
                    }
                    case BottomSheetBehavior.STATE_SETTLING: {
                        state = "SETTLING";
                        break;
                    }
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        state = "EXPANDED";
                        break;
                    }
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        state = "COLLAPSED";
                        break;
                    }
                    case BottomSheetBehavior.STATE_HIDDEN: {
                        state = "HIDDEN";
                        break;
                    }
                }

                Toast.makeText(Main_Test_Activity.this, "Bottom Sheet State Changed to: " + state, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }


    private void getAllQuestions(String student_id) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS).writeTimeout(100, TimeUnit.SECONDS).build();
        Retrofit QueRetrofit = new Retrofit.Builder().client(client)
                .baseUrl(Retro_Urls.The_Base).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api QueApi = QueRetrofit.create(Api.class);
        Call<Test_Question> testQuestionCall = QueApi.GetQuestion(student_id);
        testQuestionCall.enqueue(new Callback<Test_Question>() {
            @Override
            public void onResponse(Call<Test_Question> call, Response<Test_Question> response) {
                no_of_questions = response.body().getData().size();
                String whatque = response.body().getData().get(0).getQue();
                for (int k = 0; k < no_of_questions; k++) {
                    questions_jJavaList.add(new Questions_jJava(Integer.valueOf(response.body().getData().get(k).getId()), response.body().getData().get(k).getType(), response.body().getData().get(k).getQue()));
//
//
                    // questions_jJavaList.add(new Questions_jJava(questions_jJavaList.get(k).getId() ,questions_jJavaList.get(k).getType()));
                    Log.e("no of questiobn", "" + questions_jJavaList.get(k).getType());

                    if (response.body().getData().get(k).getType() == 1) {
                        questionsJJavaLinkedList.add(new Questions_jJava(response.body().getData().get(k).getId(), response.body().getData().get(k).getSubId()
                                , response.body().getData().get(k).getMinusmark(), response.body().getData().get(k).getMarks(), response.body().getData().get(k).getSolution(),
                                response.body().getData().get(k).getStatus(), response.body().getData().get(k).getCreatedate(), response.body().getData().get(k).getVideo()
                                , response.body().getData().get(k).getQue(), response.body().getData().get(k).getAns1(), response.body().getData().get(k).getAns2(), response.body().getData().get(k).getAns3()
                                , response.body().getData().get(k).getAns4(), response.body().getData().get(k).getAns(), response.body().getData().get(k).getVideoUrl()));
                    }
                    else
                        {
                        questionsJJavaLinkedList.add(new Questions_jJava(response.body().getData().get(k).getId(), response.body().getData().get(k).getSubId(),
                                response.body().getData().get(k).getMarks(), response.body().getData().get(k).getMarks(), response.body().getData().get(k).getSolution(),
                                response.body().getData().get(k).getStatus(), response.body().getData().get(k).getCreatedate(), response.body().getData().get(k).getVideo()
                                , response.body().getData().get(k).getQue(), response.body().getData().get(k).getVideoUrl()));
                    }
                }
                questions_adapter = new Questions_Adapter(Main_Test_Activity.this, questions_jJavaList);
//                quelinrecy.setHasFixedSize(true);
                quelinrecy.setAdapter(questions_adapter); // set the Adapter to RecyclerView
                //getAllQuestions(student_id);
                //    questions_adapter = new Questions_Adapter(Main_Test_Activity.this ,questions_jJavaList);
                Log.e("no of questiobn", "jsdajksd " + questionsJJavaLinkedList.get(0).getType());
                if (questions_jJavaList.get(0).getType() == 1)
                {

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container_dik, new Multiple_Que_Test()).commit();

                }
                else
                    {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container_dik, new Fill_In_Que_Test()).commit();
                }
                //quelinrecy.setAdapter(questions_adapter);

            }

            @Override
            public void onFailure(Call<Test_Question> call, Throwable t) {
                getAllQuestions(student_id);
                Log.d("cause", "" + t.getCause());
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_forward:
//                    if()
                    int next = ++queposition;
                    int frontback = next - 1;
                  //  int nextquery = ++Ansposition;
                    try {
                        if (questions_jJavaList.get(next).getType() == 1) {

                      //      Submit_The_Query();
                            if(questionsJJavaHashMap.get(frontback).getWritten_ans().equals("Not answered"))
                            {
                                Submit_The_Query(questions_jJavaList.get(frontback).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(frontback).getTIme_taken(),2 , questionsJJavaHashMap.get(frontback).getWritten_ans());
                            }else {
                                Submit_The_Query(questions_jJavaList.get(frontback).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(frontback).getTIme_taken(),1 , questionsJJavaHashMap.get(frontback).getWritten_ans());
                            }
                            Log.d("ans is" , ""+questionsJJavaHashMap.get(frontback).getWritten_ans().length());
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.container_dik, new Multiple_Que_Test()).commit();
                        } else {
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.container_dik, new Fill_In_Que_Test()).commit();
                        }
                    } catch (Exception e) {
                        queposition--;
                        Toast.makeText(Main_Test_Activity.this, "Can't go forward", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    //      mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_save:
                    int saveornot = queposition;
                    Toast.makeText(Main_Test_Activity.this, "that's que", Toast.LENGTH_SHORT).show();
                    questionsJJavaHashMap.remove(queposition);
                    questionsJJavaHashMap.put(queposition , new Questions_jJava("Mark as review" , "0.00"));
                    if(questionsJJavaHashMap.get(saveornot).getWritten_ans().equals("Not answered"))
                    {
                        Submit_The_Query(questions_jJavaList.get(saveornot).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(saveornot).getTIme_taken(),3 , questionsJJavaHashMap.get(saveornot).getWritten_ans());
                    }else {
                        Submit_The_Query(questions_jJavaList.get(saveornot).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(saveornot).getTIme_taken(),4 , questionsJJavaHashMap.get(saveornot).getWritten_ans());
                    }
                  //  Submit_The_Query();
//                    Log.e("written ans" , "is "+questionsJJavaHashMap.get(--queposition).getWritten_ans());
//                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_back:
                    int back = --queposition;
                    int getbackhash = back +1;
                   // int backquery = --Ansposition;
                    try {
                        if (questions_jJavaList.get(back).getType() == 1 ) {
                            Log.d("ans is" , ""+questionsJJavaHashMap.get(getbackhash).getWritten_ans());
                            if(questionsJJavaHashMap.get(getbackhash).getWritten_ans().equals("Not answered"))
                            {
                                Submit_The_Query(questions_jJavaList.get(getbackhash).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(getbackhash).getTIme_taken(),2 , questionsJJavaHashMap.get(getbackhash).getWritten_ans());
                            }else {
                                Submit_The_Query(questions_jJavaList.get(getbackhash).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(getbackhash).getTIme_taken(),1 , questionsJJavaHashMap.get(getbackhash).getWritten_ans());
                            }
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.container_dik, new Multiple_Que_Test()).commit();
                        } else {
                            Submit_The_Query(questions_jJavaList.get(queposition).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(queposition).getTIme_taken(), 2, questionsJJavaHashMap.get(queposition).getWritten_ans());
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.container_dik, new Fill_In_Que_Test()).commit();
                        }
                    } catch (Exception e) {
                        ++queposition;
                        Toast.makeText(Main_Test_Activity.this, "No back possible", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    //                 mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    private void Submit_The_Query(int que_id, int coustId, String tIme_taken, int q_status, String written_ans) {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(150, TimeUnit.SECONDS)
                .readTimeout(300,TimeUnit.SECONDS).writeTimeout(200 , TimeUnit.SECONDS).build();
        ProgressDialog ExamprogressDialog;
        ExamprogressDialog = new ProgressDialog(Main_Test_Activity.this);
        ExamprogressDialog.setMax(100);
        ExamprogressDialog.setTitle("Submitting Your Ans");
        ExamprogressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        ExamprogressDialog.setCancelable(false);
        ExamprogressDialog.show();
        Retrofit RetroGEtExam = new Retrofit.Builder()
                .baseUrl(Retro_Urls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api EmamApi = RetroGEtExam.create(Api.class);
        Call<Submit_Question> exam_testCall = EmamApi.SubmitQuery(que_id,coustId,tIme_taken,q_status,written_ans);
        exam_testCall.enqueue(new Callback<Submit_Question>() {
            @Override
            public void onResponse(Call<Submit_Question> call, Response<Submit_Question> response) {

                //  Toast.makeText(getActivity(), "Test name"+response.body().getData().get(0).getTestName(), Toast.LENGTH_SHORT).show();
                if(response.isSuccessful())
                {
                    ExamprogressDialog.dismiss();
                    if(response.body().getResponce() == true)
                    {
                        Log.e("Question" , " submited "+response.body().getData());
                        Toast.makeText(Main_Test_Activity.this, "successfully submited", Toast.LENGTH_SHORT).show();

                    }
//
                }

            }

            @Override
            public void onFailure(Call<Submit_Question> call, Throwable t) {
                Log.w("MyTag", "requestFailed"+t);
                //            Log.w("MyTag", "requestFailed "+ call.clone().isExecuted());

            }
        });

    }

    //------------------------------------------------
    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            fab2.setText("" + mins + ":"
                    + String.format("%02d", secs)/* + ":"
                    + String.format("%03d", milliseconds)*/);
            customHandler.postDelayed(this, 0);
        }

    };


}

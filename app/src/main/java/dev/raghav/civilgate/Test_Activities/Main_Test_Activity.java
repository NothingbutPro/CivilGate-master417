package dev.raghav.civilgate.Test_Activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
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
import dev.raghav.civilgate.Api.Retro_Urls;
import dev.raghav.civilgate.Instant_Report.Instant_Results_Activity;
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

    public static Questions_Adapter questions_adapter;
    public static int queposition = 0;
    RecyclerView quelinrecy;
    static int no_of_questions;
    TextView fab23;
    BottomNavigationView navigation;
    AlertDialog.Builder builder;
    TextView fab2;
    TextView fab;
    Boolean res = false;
    public  int level_id;
   public  String sub_leve_id;
   public static HashMap<Integer , Questions_jJava> questionsJJavaHashMap = new HashMap<>();
    private long startTime = 0L;
    View view;
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
    private boolean bx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapse);


        sessionManager = new SessionManager(this);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        view = getWindow().getDecorView();
        nestedScrollView = findViewById(R.id.nestedScrollView);
        bottomSheetBehavior = BottomSheetBehavior.from(nestedScrollView);
        fab = findViewById(R.id.fab);
        fab2 = findViewById(R.id.fab2);
        fab23 = findViewById(R.id.fab23);
        sub_leve_id =getIntent().getStringExtra("sub_leve_id" );
        level_id =getIntent().getIntExtra("level_id" ,50);
        Log.d("the sub leve" ,""+level_id);
        fab23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                alertDialogBuilder.setMessage("Are you sure, you want to submit the test");
                        alertDialogBuilder.setPositiveButton("Submit Test",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        Toast.makeText(Main_Test_Activity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                                        int nextsubmit = queposition;
                                        // int frontbacksubmit = nextsubmit - 1;
                                        //  int nextquery = ++Ansposition;
                                        Log.e("at Naviga" , "tion submit");
                                        try {
                                            if (questions_jJavaList.get(nextsubmit).getType() == 1) {

                                                //      Submit_The_Query();
                                                if(questionsJJavaHashMap.get(nextsubmit).getWritten_ans().equals("0"))
                                                {

                                                    quelinrecy.findViewHolderForLayoutPosition(2).itemView.setBackgroundColor(Color.GREEN);
                                                    //  quelinrecy.findViewHolderForAdapterPosition(1).itemView.setBackgroundColor(Color.GREEN);
                                                    Submit_The_Query_Final(questions_jJavaList.get(nextsubmit).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(nextsubmit).getTIme_taken(),2 , questionsJJavaHashMap.get(nextsubmit).getWritten_ans());
                                                }else {
                                                    quelinrecy.findViewHolderForLayoutPosition(2).itemView.setBackgroundColor(Color.GREEN);
//                                quelinrecy.findViewHolderForAdapterPosition(2).itemView.setBackgroundColor(Color.GREEN);
                                                    Submit_The_Query_Final(questions_jJavaList.get(nextsubmit).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(nextsubmit).getTIme_taken(),1 , questionsJJavaHashMap.get(nextsubmit).getWritten_ans());
                                                }
                                                Log.d("ans at map is" , ""+questionsJJavaHashMap.get(nextsubmit).getWritten_ans());
                                                Log.d("id at map is" , ""+questionsJJavaHashMap.get(nextsubmit).getId());
                                                Log.d("sub at map is" , ""+questionsJJavaHashMap.get(nextsubmit).getSub_id());
                                                FragmentManager fragmentManager = getSupportFragmentManager();
                                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                                fragmentTransaction.replace(R.id.container_dik, new Multiple_Que_Test()).commit();
                                            } else {
                                                FragmentManager fragmentManager = getSupportFragmentManager();
                                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                              //  fragmentTransaction.replace(R.id.container_dik, new Fill_In_Que_Test()).commit();
                                                fragmentTransaction.replace(R.id.container_dik, new Multiple_Que_Test()).commit();
                                            }
                                        } catch (Exception e) {
                                            queposition--;
                                            if(questionsJJavaHashMap.get(nextsubmit).getWritten_ans().equals("0"))
                                            {

                                                Submit_The_Query_Final(questions_jJavaList.get(nextsubmit).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(nextsubmit).getTIme_taken(),2 , questionsJJavaHashMap.get(nextsubmit).getWritten_ans());
                                            }else {
                                                Submit_The_Query_Final(questions_jJavaList.get(nextsubmit).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(nextsubmit).getTIme_taken(),1 , questionsJJavaHashMap.get(nextsubmit).getWritten_ans());
                                            }
                                            Toast.makeText(Main_Test_Activity.this, "Can't go forward", Toast.LENGTH_SHORT).show();
                                            e.printStackTrace();
                                        }
                                    }
                                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
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
        GridLayoutManager manager = new GridLayoutManager(this, 6, GridLayoutManager.VERTICAL, false);
        quelinrecy.setLayoutManager(manager);
        fab.setOnClickListener(v -> {
            if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_COLLAPSED) {
                fab.setText("Open Question Tab");
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            } else {
                fab.setText("Close Question Tab");
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }

        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   buildDialog(R.style.DialogAnimation, "Left - Right Animation!");
            }
        }
        );

        getAllQuestions(student_id);
         navigation = (BottomNavigationView) findViewById(R.id.navigation);
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
        Log.e("student id" , ""+student_id);
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
                   // fragmentTransaction.replace(R.id.container_dik, new Fill_In_Que_Test()).commit();
                    fragmentTransaction.replace(R.id.container_dik, new Multiple_Que_Test()).commit();

                }
                //quelinrecy.setAdapter(questions_adapter);

            }

            @Override
            public void onFailure(Call<Test_Question> call, Throwable t) {
                //getAllQuestions(student_id);
                Log.e("at local" , ""+t.getLocalizedMessage());
                Log.e("at local" , ""+t.getStackTrace());
                Log.d("cause", "" + t.getMessage());
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_submit:
                    int nextsubmit = queposition;
                   // int frontbacksubmit = nextsubmit - 1;
                    //  int nextquery = ++Ansposition;
                    Toast.makeText(Main_Test_Activity.this, "that's que" +queposition, Toast.LENGTH_SHORT).show();
                    Log.e("at Naviga" , "tion submit");
                    try {
                        if (questions_jJavaList.get(nextsubmit).getType() == 1) {

                            //      Submit_The_Query();
                            if(questionsJJavaHashMap.get(nextsubmit).getWritten_ans().equals("0"))
                            {

                                quelinrecy.findViewHolderForLayoutPosition(2).itemView.setBackgroundColor(Color.GREEN);
                              //  quelinrecy.findViewHolderForAdapterPosition(1).itemView.setBackgroundColor(Color.GREEN);
                                Submit_The_Query_Final(questions_jJavaList.get(nextsubmit).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(nextsubmit).getTIme_taken(),2 , questionsJJavaHashMap.get(nextsubmit).getWritten_ans());
                            }else {
                                quelinrecy.findViewHolderForLayoutPosition(2).itemView.setBackgroundColor(Color.GREEN);
//                                quelinrecy.findViewHolderForAdapterPosition(2).itemView.setBackgroundColor(Color.GREEN);
                                Submit_The_Query_Final(questions_jJavaList.get(nextsubmit).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(nextsubmit).getTIme_taken(),1 , questionsJJavaHashMap.get(nextsubmit).getWritten_ans());
                            }
                            Log.d("ans at map is" , ""+questionsJJavaHashMap.get(nextsubmit).getWritten_ans());
                            Log.d("id at map is" , ""+questionsJJavaHashMap.get(nextsubmit).getId());
                            Log.d("sub at map is" , ""+questionsJJavaHashMap.get(nextsubmit).getSub_id());
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.container_dik, new Multiple_Que_Test()).commit();
                        } else {
                            if(questionsJJavaHashMap.get(nextsubmit).getWritten_ans().length() ==0)
                            {
                                quelinrecy.findViewHolderForLayoutPosition(2).itemView.setBackgroundColor(Color.GREEN);
                                //  quelinrecy.findViewHolderForAdapterPosition(1).itemView.setBackgroundColor(Color.GREEN);
                                Submit_The_Query_Final(questions_jJavaList.get(nextsubmit).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(nextsubmit).getTIme_taken(),2 , questionsJJavaHashMap.get(nextsubmit).getWritten_ans());
                            }else {
                                quelinrecy.findViewHolderForLayoutPosition(2).itemView.setBackgroundColor(Color.GREEN);
//                                quelinrecy.findViewHolderForAdapterPosition(2).itemView.setBackgroundColor(Color.GREEN);
                                Submit_The_Query_Final(questions_jJavaList.get(nextsubmit).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(nextsubmit).getTIme_taken(),1 , questionsJJavaHashMap.get(nextsubmit).getWritten_ans());
                            }
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                           // fragmentTransaction.replace(R.id.container_dik, new Fill_In_Que_Test()).commit();
                            fragmentTransaction.replace(R.id.container_dik, new Multiple_Que_Test()).commit();
                        }
                    } catch (Exception e) {
                        queposition--;
                        if(questionsJJavaHashMap.get(nextsubmit).getWritten_ans().equals("0"))
                        {

                            Submit_The_Query_Final(questions_jJavaList.get(nextsubmit).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(nextsubmit).getTIme_taken(),2 , questionsJJavaHashMap.get(nextsubmit).getWritten_ans());
                        }else {
                            Submit_The_Query_Final(questions_jJavaList.get(nextsubmit).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(nextsubmit).getTIme_taken(),1 , questionsJJavaHashMap.get(nextsubmit).getWritten_ans());
                        }
                        Toast.makeText(Main_Test_Activity.this, "Can't go forward", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    return true;

                case R.id.navigation_forwardsdfsdf:
//                    if()
                    int next = ++queposition;
                    int frontback = next - 1;
                    Toast.makeText(Main_Test_Activity.this, "that's que" +queposition, Toast.LENGTH_SHORT).show();
                  //  int nextquery = ++Ansposition;
                    try {
                        if (questions_jJavaList.get(next).getType() == 1) {
                            Log.d("ans is" , ""+questionsJJavaHashMap.get(frontback).getWritten_ans());
                      //      Submit_The_Query();
                            Log.e("Position" , ""+frontback);
                            if(questionsJJavaHashMap.get(frontback).getWritten_ans().equals("0"))
                            {
                                quelinrecy.findViewHolderForLayoutPosition(frontback).itemView.setBackgroundColor(Color.GREEN);
                              //  quelinrecy.findViewHolderForAdapterPosition(1).itemView.setBackgroundColor(Color.GREEN);
                               Submit_The_Query(questions_jJavaList.get(frontback).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(frontback).getTIme_taken(),2 , questionsJJavaHashMap.get(frontback).getWritten_ans());
                                    FragmentManager fragmentManager = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                    fragmentTransaction.replace(R.id.container_dik, new Multiple_Que_Test()).commit();

                            }
                            else {

                                quelinrecy.findViewHolderForLayoutPosition(frontback).itemView.setBackgroundColor(Color.GREEN);
                              //  quelinrecy.findViewHolderForAdapterPosition(2).itemView.setBackgroundColor(Color.GREEN);
                              Submit_The_Query(questions_jJavaList.get(frontback).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(frontback).getTIme_taken(),1 , questionsJJavaHashMap.get(frontback).getWritten_ans());

                                  Log.d("ans is" , ""+questionsJJavaHashMap.get(frontback).getWritten_ans());
                                  FragmentManager fragmentManager = getSupportFragmentManager();
                                  FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                  fragmentTransaction.replace(R.id.container_dik, new Multiple_Que_Test()).commit();

                            }

                        } else {
                            Log.d("else ans is" , ""+questionsJJavaHashMap.get(frontback).getWritten_ans());
                            //      Submit_The_Query();
                            Log.e("Position" , ""+frontback);
                            if(questionsJJavaHashMap.get(frontback).getWritten_ans().length() ==0)
                            {
                                quelinrecy.findViewHolderForLayoutPosition(frontback).itemView.setBackgroundColor(Color.GREEN);
                                //  quelinrecy.findViewHolderForAdapterPosition(1).itemView.setBackgroundColor(Color.GREEN);
                                Submit_The_Query(questions_jJavaList.get(frontback).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(frontback).getTIme_taken(),2 , questionsJJavaHashMap.get(frontback).getWritten_ans());


                                FragmentManager fragmentManager = getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.container_dik, new Multiple_Que_Test()).commit();

                            } else {

                                quelinrecy.findViewHolderForLayoutPosition(frontback).itemView.setBackgroundColor(Color.GREEN);
                                //  quelinrecy.findViewHolderForAdapterPosition(2).itemView.setBackgroundColor(Color.GREEN);
                                Submit_The_Query(questions_jJavaList.get(frontback).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(frontback).getTIme_taken(),1 , questionsJJavaHashMap.get(frontback).getWritten_ans());

                                Log.d("else ans is" , ""+questionsJJavaHashMap.get(frontback).getWritten_ans());
                                FragmentManager fragmentManager = getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                             //   fragmentTransaction.replace(R.id.container_dik, new Fill_In_Que_Test()).commit();
                                fragmentTransaction.replace(R.id.container_dik, new Multiple_Que_Test()).commit();
                            }
//                            FragmentManager fragmentManager = getSupportFragmentManager();
//                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                            fragmentTransaction.replace(R.id.container_dik, new Fill_In_Que_Test()).commit();
                        }
                        try {
                            if (questions_jJavaList.get(next+1) == null)
                            {

                            }
                        }
                        catch (Exception e)
                        {
                            View menuItem = navigation.findViewById(R.id.navigation_forwardsdfsdf);
                            View menuItem2 = navigation.findViewById(R.id.navigation_back);
                            View menuItem3 = navigation.findViewById(R.id.navigation_save);
                            View menuItem4 = navigation.findViewById(R.id.navigation_submit);
                            View menuItem5 = navigation.findViewById(R.id.navigation_save_lastsdfgdf);
                            menuItem.setVisibility(View.GONE);
                            menuItem3.setVisibility(View.GONE);
                            menuItem4.setVisibility(View.VISIBLE);
                            menuItem5.setVisibility(View.VISIBLE);
//                            queposition--;
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        queposition--;
//                        if(questionsJJavaHashMap.get(frontback).getWritten_ans() ==0)
//                        {
//                            quelinrecy.findViewHolderForLayoutPosition(frontback).itemView.setBackgroundColor(Color.GREEN);
//                           if( Submit_The_Query(questions_jJavaList.get(frontback).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(frontback).getTIme_taken(),2 , questionsJJavaHashMap.get(frontback).getWritten_ans()))
//                           {
//
//                           }
//
//                        }
//                        else
//                            {
//                                quelinrecy.findViewHolderForLayoutPosition(frontback).itemView.setBackgroundColor(Color.GREEN);
//                           if( Submit_The_Query(questions_jJavaList.get(frontback).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(frontback).getTIme_taken(),1 , questionsJJavaHashMap.get(frontback).getWritten_ans()))
//                           {
//
//                           }
//
//                        }
                        Toast.makeText(Main_Test_Activity.this, "Can't go forward", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    //      mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_save_lastsdfgdf:
                    Log.w("nav last savcev " , "navigation_save_lastsdfgdf   sdfsdfsdfsd");
                    int finalsaveornot = queposition;
                    int finalyessave = finalsaveornot-1;
                    Toast.makeText(Main_Test_Activity.this, "that's que" +queposition, Toast.LENGTH_SHORT).show();
                    Log.e("at Naviga" , "tion submit");
                    try {
                        if (questions_jJavaList.get(finalsaveornot).getType() == 1) {

                            //      Submit_The_Query();
                            if(questionsJJavaHashMap.get(finalsaveornot).getWritten_ans().equals("0"))
                            {

                                quelinrecy.findViewHolderForLayoutPosition(2).itemView.setBackgroundColor(Color.GREEN);
                                //  quelinrecy.findViewHolderForAdapterPosition(1).itemView.setBackgroundColor(Color.GREEN);
                                Submit_The_Query_Final(questions_jJavaList.get(finalsaveornot).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(finalsaveornot).getTIme_taken(),2 , questionsJJavaHashMap.get(finalsaveornot).getWritten_ans());
                            }else {
                                quelinrecy.findViewHolderForLayoutPosition(2).itemView.setBackgroundColor(Color.GREEN);
//                                quelinrecy.findViewHolderForAdapterPosition(2).itemView.setBackgroundColor(Color.GREEN);
                                Submit_The_Query_Final(questions_jJavaList.get(finalsaveornot).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(finalsaveornot).getTIme_taken(),1 , questionsJJavaHashMap.get(finalsaveornot).getWritten_ans());
                            }
                            Log.d("ans at map is" , ""+questionsJJavaHashMap.get(finalsaveornot).getWritten_ans());
                            Log.d("id at map is" , ""+questionsJJavaHashMap.get(finalsaveornot).getId());
                            Log.d("sub at map is" , ""+questionsJJavaHashMap.get(finalsaveornot).getSub_id());
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.container_dik, new Multiple_Que_Test()).commit();
                        } else {
                            if(questionsJJavaHashMap.get(finalsaveornot).getWritten_ans().length() ==0)
                            {
                                quelinrecy.findViewHolderForLayoutPosition(2).itemView.setBackgroundColor(Color.GREEN);
                                //  quelinrecy.findViewHolderForAdapterPosition(1).itemView.setBackgroundColor(Color.GREEN);
                                Submit_The_Query_Final(questions_jJavaList.get(finalsaveornot).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(finalsaveornot).getTIme_taken(),2 , questionsJJavaHashMap.get(finalsaveornot).getWritten_ans());
                            }else {
                                quelinrecy.findViewHolderForLayoutPosition(2).itemView.setBackgroundColor(Color.GREEN);
//                                quelinrecy.findViewHolderForAdapterPosition(2).itemView.setBackgroundColor(Color.GREEN);
                                Submit_The_Query_Final(questions_jJavaList.get(finalsaveornot).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(finalsaveornot).getTIme_taken(),1 , questionsJJavaHashMap.get(finalsaveornot).getWritten_ans());
                            }
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                           // fragmentTransaction.replace(R.id.container_dik, new Fill_In_Que_Test()).commit();
                            fragmentTransaction.replace(R.id.container_dik, new Multiple_Que_Test()).commit();
                        }
                    } catch (Exception e) {
                        queposition--;
                        if(questionsJJavaHashMap.get(finalsaveornot).getWritten_ans().equals("0"))
                        {
                            Submit_The_Query_Final(questions_jJavaList.get(finalsaveornot).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(finalsaveornot).getTIme_taken(),2 , questionsJJavaHashMap.get(finalsaveornot).getWritten_ans());
                        }else {
                            Submit_The_Query_Final(questions_jJavaList.get(finalsaveornot).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(finalsaveornot).getTIme_taken(),1 , questionsJJavaHashMap.get(finalsaveornot).getWritten_ans());
                        }
                        Toast.makeText(Main_Test_Activity.this, "Can't go forward", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }


                    return true;
                case R.id.navigation_save :
                    int saveornot = ++queposition;
                    int yessave = saveornot-1;
                    Toast.makeText(Main_Test_Activity.this, "that's que" +queposition, Toast.LENGTH_SHORT).show();
                    questionsJJavaHashMap.remove(queposition);
                    questionsJJavaHashMap.put(queposition , new Questions_jJava(String.valueOf(5) , 0));

                    Log.e("Position" , ""+queposition);
//                    try {
//                        if (questions_jJavaList.get(saveornot+1) == null)
//                        {
//
//                        }
//                    }
//                    catch (Exception e)
//                    {
//                        View menuItem = navigation.findViewById(R.id.navigation_forwardsdfsdf);
//                        View menuItem2 = navigation.findViewById(R.id.navigation_back);
//                        View menuItem3 = navigation.findViewById(R.id.navigation_save);
//                        View menuItem4 = navigation.findViewById(R.id.navigation_submit);
//                        menuItem.setVisibility(View.GONE);
//                        menuItem4.setVisibility(View.VISIBLE);
//                        e.printStackTrace();
//                    }
                    Log.d("saveornot" , ""+questions_jJavaList.get(yessave).getQue());
                    Log.d("saveornot" , ""+questions_jJavaList.get(yessave).getType());
                    try {
                        if (questions_jJavaList.get(yessave).getType() == 1) {

                            if (questionsJJavaHashMap.get(yessave).getWritten_ans().equals("0")) {
//                            ++queposition;
                                quelinrecy.findViewHolderForLayoutPosition(yessave).itemView.setBackgroundColor(Color.GREEN);
                                Submit_The_Query(questions_jJavaList.get(yessave).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(yessave).getTIme_taken(), 3, questionsJJavaHashMap.get(yessave).getWritten_ans());
                                Log.d("ans is", "" + questionsJJavaHashMap.get(yessave).getWritten_ans());
                                FragmentManager fragmentManager = getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.container_dik, new Multiple_Que_Test()).commit();


                            } else {
                                quelinrecy.findViewHolderForLayoutPosition(yessave).itemView.setBackgroundColor(Color.GREEN);
//                            ++queposition;
                                Submit_The_Query(questions_jJavaList.get(yessave).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(yessave).getTIme_taken(), 4, questionsJJavaHashMap.get(yessave).getWritten_ans());
                                Log.d("ans is", "" + questionsJJavaHashMap.get(yessave).getWritten_ans());
                                FragmentManager fragmentManager = getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                           //     fragmentTransaction.replace(R.id.container_dik, new Fill_In_Que_Test()).commit();
                                fragmentTransaction.replace(R.id.container_dik, new Multiple_Que_Test()).commit();

                            }
//                        try {
//
//                            if (questions_jJavaList.get(saveornot+2) == null)
//                            {
//
//                            }
//                        }
//                        catch (Exception e)
//                        {
//
//                            e.printStackTrace();
//
////                            View menuItem = navigation.findViewById(R.id.navigation_forwardsdfsdf);
////                            View menuItem2 = navigation.findViewById(R.id.navigation_back);
////                            View menuItem3 = navigation.findViewById(R.id.navigation_save);
////                            View menuItem4 = navigation.findViewById(R.id.navigation_submit);
////                            menuItem.setVisibility(View.GONE);
////                            menuItem4.setVisibility(View.VISIBLE);
//////                            queposition--;
////                            e.printStackTrace();
//                        }

                        }
                    }
                    catch (Exception e)
                    {

                        e.printStackTrace();
                        if (questionsJJavaHashMap.get(yessave).getWritten_ans().equals("0")) {
//                            ++queposition;
                            quelinrecy.findViewHolderForLayoutPosition(yessave).itemView.setBackgroundColor(Color.GREEN);
                            Submit_The_Query(questions_jJavaList.get(yessave).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(yessave).getTIme_taken(), 3, questionsJJavaHashMap.get(yessave).getWritten_ans());
                            Log.d("ans is", "" + questionsJJavaHashMap.get(yessave).getWritten_ans());
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.container_dik, new Multiple_Que_Test()).commit();


                        } else {

                            quelinrecy.findViewHolderForLayoutPosition(yessave).itemView.setBackgroundColor(Color.GREEN);
//                            ++queposition;
                            Submit_The_Query(questions_jJavaList.get(yessave).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(yessave).getTIme_taken(), 4, questionsJJavaHashMap.get(yessave).getWritten_ans());
                            Log.d("ans is", "" + questionsJJavaHashMap.get(yessave).getWritten_ans());
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.container_dik, new Multiple_Que_Test()).commit();

                        }
                    }

                    try{
                        if (questions_jJavaList.get(saveornot+1) == null)
                        {

                        }
                    }catch (Exception ex)
                    {
                        Log.e("we" ,"moved forward");
                        ex.printStackTrace();
                        View menuItem = navigation.findViewById(R.id.navigation_forwardsdfsdf);
                        View menuItem2 = navigation.findViewById(R.id.navigation_back);
                        View menuItem3 = navigation.findViewById(R.id.navigation_save);
                        View menuItem4 = navigation.findViewById(R.id.navigation_submit);
                        View menuItem5 = navigation.findViewById(R.id.navigation_save_lastsdfgdf);
                        menuItem.setVisibility(View.GONE);
                        menuItem3.setVisibility(View.GONE);
                        menuItem4.setVisibility(View.VISIBLE);
                        menuItem5.setVisibility(View.VISIBLE);

//                        queposition--;
                        ex.printStackTrace();
                    }
                  //  Submit_The_Query();
//                    Log.e("written ans" , "is "+questionsJJavaHashMap.get(--queposition).getWritten_ans());
//                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_back:
                    int back = --queposition;
                    int getbackhash = back +1;
                    Toast.makeText(Main_Test_Activity.this, "that's que" +queposition, Toast.LENGTH_SHORT).show();
                   // int backquery = --Ansposition;
                    try {
                        if (questions_jJavaList.get(back).getType() == 1 ) {
                            Log.d("ans is" , ""+questionsJJavaHashMap.get(getbackhash).getWritten_ans());
                            if(questionsJJavaHashMap.get(getbackhash).getWritten_ans().equals("0"))
                            {
                             //   quelinrecy.findViewHolderForLayoutPosition(2).itemView.setBackgroundColor(Color.GREEN);
                              if  (Submit_The_Query(questions_jJavaList.get(getbackhash).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(getbackhash).getTIme_taken(),2 , questionsJJavaHashMap.get(getbackhash).getWritten_ans()))
                              {

                              }
                            }else {
                             //   quelinrecy.findViewHolderForLayoutPosition(2).itemView.setBackgroundColor(Color.GREEN);
                                if(Submit_The_Query(questions_jJavaList.get(getbackhash).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(getbackhash).getTIme_taken(),1 , questionsJJavaHashMap.get(getbackhash).getWritten_ans()))
                                {

                                }
                            }
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.container_dik, new Multiple_Que_Test()).commit();

                        } else {
                            Submit_The_Query(questions_jJavaList.get(queposition).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(queposition).getTIme_taken(), 2, questionsJJavaHashMap.get(queposition).getWritten_ans());
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                       //     fragmentTransaction.replace(R.id.container_dik, new Fill_In_Que_Test()).commit();
                            fragmentTransaction.replace(R.id.container_dik, new Multiple_Que_Test()).commit();
                        }
                    } catch (Exception e) {
                        ++queposition;
//                        if(questionsJJavaHashMap.get(getbackhash).getWritten_ans() ==0)
//                        {
//
//                            Submit_The_Query(questions_jJavaList.get(getbackhash).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(getbackhash).getTIme_taken(),2 , questionsJJavaHashMap.get(getbackhash).getWritten_ans());
//                        }else {
//                            Submit_The_Query(questions_jJavaList.get(getbackhash).getId(), sessionManager.getCoustId(), questionsJJavaHashMap.get(getbackhash).getTIme_taken(),1 , questionsJJavaHashMap.get(getbackhash).getWritten_ans());
//                        }
                        Toast.makeText(Main_Test_Activity.this, "No back possible", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    //                 mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    private void Submit_The_Query_Final(int que_id, int coustId, int tIme_taken, int q_status, String written_ans)
    {
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
        Call<Submit_Question> exam_testCall = EmamApi.SubmitQuery(level_id ,sub_leve_id,que_id,coustId,tIme_taken,q_status, String.valueOf(written_ans));

        exam_testCall.enqueue(new Callback<Submit_Question>() {
            @Override
            public void onResponse(Call<Submit_Question> call, Response<Submit_Question> response) {

                //  Toast.makeText(getActivity(), "Test name"+response.body().getData().get(0).getTestName(), Toast.LENGTH_SHORT).show();
                if(response.isSuccessful())
                {

                    if(response.body().getResponce() == true)
                    {
                        Log.e("level_id" , ""+sessionManager.getCoustId());
                        Log.e("level_sub_id" , ""+sub_leve_id);
                        Log.e("level_id" , ""+level_id);
                        Log.e("last Question" , " submited "+response.body().getData().getLevelId());
                        Toast.makeText(Main_Test_Activity.this, "Test successfully submitted ", Toast.LENGTH_SHORT).show();
                        Log.e("at session",""+sessionManager.getCoustId());
                        Log.e("at cousst(sub id)",""+sub_leve_id);
                        Log.e("at level",""+level_id);
                        ExamprogressDialog.dismiss();
                        Sumbit_the_set(sessionManager.getCoustId(),sub_leve_id,level_id);
                        System.gc();
                        queposition =0;
                    //                        finish();

                    }
//
                }

            }

            @Override
            public void onFailure(Call<Submit_Question> call, Throwable t) {
                Log.w("MyTag", "requestFailed"+t.getMessage());
                Log.w("MyTag", "requestFailed"+t.getLocalizedMessage());
                ExamprogressDialog.dismiss();
                //            Log.w("MyTag", "requestFailed "+ call.clone().isExecuted());

            }
        });

    }

    private void Sumbit_the_set(int coustId, String sub_leve_id, int level_id) {
        Intent intent = new Intent(Main_Test_Activity.this , Instant_Results_Activity.class);
        questions_jJavaList.clear();
        questionsJJavaHashMap.clear();
        intent.putExtra("coustId" ,coustId );
        intent.putExtra("sub_leve_id" ,sub_leve_id );
        intent.putExtra("level_id" ,level_id );
        startActivity(intent);
        finish();

    }

    private Boolean Submit_The_Query(int que_id, int coustId, int tIme_taken, int q_status, String written_ans) {

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
        Call<Submit_Question> exam_testCall = EmamApi.SubmitQuery(level_id , sub_leve_id,que_id,coustId,tIme_taken,q_status, String.valueOf(written_ans));
        exam_testCall.enqueue(new Callback<Submit_Question>() {
            @Override
            public void onResponse(Call<Submit_Question> call, Response<Submit_Question> response) {

                //  Toast.makeText(getActivity(), "Test name"+response.body().getData().get(0).getTestName(), Toast.LENGTH_SHORT).show();
                res = response.body().getResponce();
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

                Log.w("MyTag", "requestFailed"+t.getMessage());
                Log.w("Tag", "request "+t.getLocalizedMessage());
                ExamprogressDialog.dismiss();
                //            Log.w("MyTag", "requestFailed "+ call.clone().isExecuted());

            }
        });
        return res;
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

    @Override
    public void onBackPressed() {
        builder = new AlertDialog.Builder(this);
        ShowBackDialog();
//        queposition =0;
//        Toast.makeText(this, "backf pressed", Toast.LENGTH_SHORT).show();
//        Thread.currentThread().interrupt();
////        Thread.currentThread().stop();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.getFragments().clear();
////        questionsJJavaHashMap =null;
////        questionsJJavaLinkedList = null;
//        finish();
            if(bx) {
                super.onBackPressed();
            }else {
                Toast.makeText(this, "Proceeds", Toast.LENGTH_SHORT).show();
            }

    }

    private Boolean ShowBackDialog() {

        //Uncomment the below code to Set the message and title from the strings.xml file
        builder.setMessage("Exam Alert!") .setTitle("You are backing up");

        //Setting message manually and performing action on button click
        builder.setMessage("Are you sure want to quit")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        queposition =0;
                        Toast.makeText(getApplicationContext(), "back pressed", Toast.LENGTH_SHORT).show();
                        Thread.currentThread().interrupt();
//        Thread.currentThread().stop();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.getFragments().clear();
//        questionsJJavaHashMap =null;
//        questionsJJavaLinkedList = null;
                        bx = true;

                        finish();


//                        Toast.makeText(getApplicationContext(),"you choose yes action for alertbox",
//                                Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(),"you choose no action for alertbox",
                                Toast.LENGTH_SHORT).show();
                        bx = false;
                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("You are backing up");
        alert.show();
        return bx;
    }
}


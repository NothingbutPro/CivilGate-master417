package dev.raghav.civilgate.Full_Solution;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import dev.raghav.civilgate.Activities.About_All;
import dev.raghav.civilgate.Activities.All_Reviews_Questions;
import dev.raghav.civilgate.Api.Api;
import dev.raghav.civilgate.Api.Retro_Urls;
import dev.raghav.civilgate.Const_Files.Detailed_Analysis_const;
import dev.raghav.civilgate.Const_Files.Detailed_Analysis_const_data;
import dev.raghav.civilgate.Const_Files.Full_Solution_Data;
import dev.raghav.civilgate.Const_Files.Full_Solutions;
import dev.raghav.civilgate.Const_Files.PostBookMarks;
import dev.raghav.civilgate.Detailed_Analysis.Analysis_Recy_Adapter;
import dev.raghav.civilgate.Other_Parsing_Files.Get_About;
import dev.raghav.civilgate.R;
import dev.raghav.civilgate.SessionManage.SessionManager;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static dev.raghav.civilgate.Activities.Direct_History.levelid;
import static dev.raghav.civilgate.Activities.Direct_History.sublevelid;
import static dev.raghav.civilgate.Full_Solution.Full_Solution_Act.lel_id;
import static dev.raghav.civilgate.Full_Solution.Full_Solution_Act.sublel_id;

public class MCQ_Questions extends Fragment {
   SessionManager sessionManager;
   WebView webque;
   ImageView opt1 ,opt2 ,opt3,opt4;
   TextView  optxt1 ,optxt2 ,optxt3 ,optxt4 ;
   TextView allques;
   TextView soledit, ansss1,ansss2,ansss3,ansss4;
   static public int solutioncounter =0;
   ImageView bookmark;
   int nx,pre;
   TextView iwrote,actualwrote;
   LinearLayout doubsol;
   Full_Solutions full_solutions;
   String ques_at;
   TextView next ,previous;
    int rightans;
    String rightansstr;
    CardView multiqueli , fillinwque;
    private String maanstr;
    //ArrayList<Full_Solution_Data> full_solutionsArrayList = new ArrayList<>();

    private int maans;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mcq_questions , container , false);
        sessionManager = new SessionManager(getActivity());
        webque = view.findViewById(R.id.webque);
        next = view.findViewById(R.id.next);
        previous = view.findViewById(R.id.previous);
        doubsol = view.findViewById(R.id.doubsol);
        bookmark = view.findViewById(R.id.bookmark);
        multiqueli = view.findViewById(R.id.multisolve);
        fillinwque = view.findViewById(R.id.fillcard);
        actualwrote = view.findViewById(R.id.shouldwrote);
        iwrote = view.findViewById(R.id.iwrote);
        ansss1 = view.findViewById(R.id.ansss1);
        ansss2 = view.findViewById(R.id.ansss2);
        ansss3 = view.findViewById(R.id.ansss3);
        ansss4 = view.findViewById(R.id.ansss4);
        soledit = view.findViewById(R.id.soledit);
        allques = view.findViewById(R.id.allques);
        optxt1 = view.findViewById(R.id.optxt1);
        optxt2 = view.findViewById(R.id.optxt2);
        optxt3 = view.findViewById(R.id.optxt3);
        optxt4 = view.findViewById(R.id.optxt4);
        opt1 = view.findViewById(R.id.opt1);
        opt2 = view.findViewById(R.id.opt2);
        opt3 = view.findViewById(R.id.opt3);
        opt4 = view.findViewById(R.id.opt4);

        GetTheFullSolution();
        doubsol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create custom dialog object
                final Dialog dialog = new Dialog(getActivity());
                // Include dialog.xml file
                dialog.setContentView(R.layout.anydoubt);
                // Set dialog title
                dialog.setTitle("Custom Dialog");

                // set values for custom dialog components - text, image and button
                TextView Ques = (TextView) dialog.findViewById(R.id.ques_no);
                Ques.setText("Ques "+solutioncounter);
                TextView ques_doubt = dialog.findViewById(R.id.ques_doubt);
                EditText madoubt = dialog.findViewById(R.id.madoubt);
                ques_doubt.setText(""+ques_at);

                dialog.show();

                Button askydbt = (Button) dialog.findViewById(R.id.askydbt);
                askydbt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String Askysting = madoubt.getText().toString();
                        AsyITAway(Askysting);
                    }
                });
                // if decline button is clicked, close the custom dialog
                askydbt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close dialog
                        dialog.dismiss();
                    }
                });
            }
        });
        allques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , All_Reviews_Questions.class);
                intent.putExtra("maque" , full_solutions.getData().get(solutioncounter).getQueid());
                startActivity(intent);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iwrote.setText("");
                actualwrote.setText("");
                optxt1.setText("");
                optxt2.setText("");
                optxt3.setText("");
                optxt4.setText("");
                opt1.setImageDrawable(null);
                opt2.setImageDrawable(null);
                opt3.setImageDrawable(null);
                opt4.setImageDrawable(null);
                nx =1;
                solutioncounter++;
                GetTheFullSolution();
                nx =0;

            }
        }
        );
        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HaveABookMArk(full_solutions.getData().get(solutioncounter).getQueid() , full_solutions.getData().get(solutioncounter).getTestId() , full_solutions.getData().get(solutioncounter).getSId());
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
                if(!(solutioncounter <=0 )) {
                    iwrote.setText("");
                    actualwrote.setText("");
                    optxt1.setText("");
                    optxt2.setText("");
                    optxt3.setText("");
                    optxt4.setText("");
                    opt1.setImageDrawable(null);
                    opt2.setImageDrawable(null);
                    opt3.setImageDrawable(null);
                    opt4.setImageDrawable(null);
                    pre =1;
                    --solutioncounter;
                    GetTheFullSolution();
                    pre =0;
                }else {
                    Toast.makeText(getActivity(), "No Backward possible", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void AsyITAway(String askysting) {
//        OkHttpClient client = new OkHttpClient.Builder()
//                .connectTimeout(100, TimeUnit.SECONDS)
//                .readTimeout(100,TimeUnit.SECONDS).build();
//        Retrofit RetroLogin = new Retrofit.Builder()
//                .baseUrl(Retro_Urls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
//                .build();
//        Api AbloutApi = RetroLogin.create(Api.class);
//        Call<Get_About> get_aboutCall = AbloutApi.TellAbout();
//        get_aboutCall.enqueue(new Callback<Get_About>() {
//            @Override
//            public void onResponse(Call<Get_About> call, Response<Get_About> response) {
//                Toast.makeText(About_All.this, ""+response.body().getData().getDescription(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<Get_About> call, Throwable t) {
//
//            }
//        });


    }

    private void HaveABookMArk(String queid, String testId, String sId) {
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(Retro_Urls.The_Base).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api RegApi = RetroLogin.create(Api.class);
        Log.w("book markid is" , ""+queid);
        Call<PostBookMarks> login_responceCall = RegApi.PostBookMarks(sessionManager.getCoustId() ,queid ,testId,sId  );
        login_responceCall.enqueue(new Callback<PostBookMarks>() {
            @Override
            public void onResponse(Call<PostBookMarks> call, Response<PostBookMarks> response) {
                Log.d("string" , ""+response.message());
                if(response.isSuccessful())
                {
                    if(response.body().getResponce() == true)
                    {
                        Toast.makeText(getActivity(), "Book Mark added successfully", Toast.LENGTH_SHORT).show();
                    }
                    else{

                        Toast.makeText(getActivity(), "Not able to add book mark", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    {
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
        Log.e("levelid is" , ""+levelid);
        Log.e("sublel_id is" , ""+sublevelid);
        Call<Full_Solutions> login_responceCall = RegApi.FULL_SOLUTIONS_CALL(sessionManager.getCoustId() ,levelid ,sublevelid );
        login_responceCall.enqueue(new Callback<Full_Solutions>() {
            @Override
            public void onResponse(Call<Full_Solutions> call, Response<Full_Solutions> response) {
                Log.d("string" , ""+response.body().getResponce());
                //full_solutionsArrayList = response.body().getData();
//                            Log.d("string" , ""+response.body().getData().getEmail());
                if(response.body().getResponce())
                {
                    if(response.body().getResponce())
                    {
                        Log.e("we" , "get some");
                        full_solutions = response.body();
                    //   Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();
                    for(int k=0;k< response.body().getData().size() ;k++) {
                       Log.w("whtas" , ""+response.body().getData().get(k).getQue());
                       Log.e("has sid" , ""+response.body().getData().get(k).getSId());
                       Log.e("has stid" , ""+response.body().getData().get(k).getTestId());
                       if(solutioncounter <response.body().getData().size() &&  solutioncounter==k) {
                           if( nx ==1)
                           {
                                Log.e("first" , "Part");
                               if(solutioncounter >=0) {
                                   FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                   FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                   fragmentTransaction.replace(R.id.solfr, new MCQ_Questions());
                                   fragmentTransaction.commit();
                                   fragmentTransaction.addToBackStack(null);

                               }
                               else
                                   {
                                   Toast.makeText(getActivity(), "No forward possible", Toast.LENGTH_SHORT).show();
                               }
                           }
                           else
                               if(pre ==1)
                           {
                               Log.e("first" , "second Part");


                               FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                               FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                               fragmentTransaction.replace(R.id.solfr, new MCQ_Questions());
                               fragmentTransaction.commit();
                               fragmentTransaction.addToBackStack(null);

                           }

                           webque.loadData(response.body().getData().get(solutioncounter).getQue().toString(), "text/html", null);
                           
                           try {
                               Log.e("first" , "try Part");

                                try {
                                    maans = Integer.valueOf(response.body().getData().get(solutioncounter).getAns());
                                    rightans = Integer.valueOf(response.body().getData().get(solutioncounter).getQueAns());
                                }catch (Exception e)
                                {
                                    e.printStackTrace();
                                }
                               Log.w("maans" , ""+maans);
                               Log.w("rightans" , ""+rightans);
                               if(response.body().getData().get(k).getType().equals("2"))
                               {
                                   fillinwque.setVisibility(View.VISIBLE);
                                   multiqueli.setVisibility(View.GONE);
                                   iwrote.setText(iwrote.getText().toString().concat(" "+response.body().getData().get(k).getQueAns()));
                                   actualwrote.setText(actualwrote.getText().toString().concat(" "+response.body().getData().get(k).getAns()));

                               }else {
                                   multiqueli.setVisibility(View.VISIBLE);
                                   fillinwque.setVisibility(View.GONE);
                                   ansss1.setText(String.valueOf(response.body().getData().get(solutioncounter).getAns1()));
                                   ansss2.setText(String.valueOf(response.body().getData().get(solutioncounter).getAns2()));
                                   ansss3.setText(String.valueOf(response.body().getData().get(solutioncounter).getAns3()));
                                   ansss4.setText(String.valueOf(response.body().getData().get(solutioncounter).getAns4()));
                               }
                               if(maans==rightans)
                               {
                                   if(maans ==1)
                                   {
                                       optxt1.setText("Your Answer");
                                       opt1.setImageResource(R.drawable.ic_correct);
                                   }
                                   if(maans ==2)
                                   {
                                       optxt2.setText("Your Answer");
                                       opt2.setImageResource(R.drawable.ic_correct);
                                   }
                                   if(maans ==3)
                                   {
                                       optxt3.setText("Your Answer");
                                       opt3.setImageResource(R.drawable.ic_correct);
                                   }
                                   if(maans ==4)
                                   {
                                       optxt4.setText("Your Answer");
                                       opt4.setImageResource(R.drawable.ic_correct);
                                   }
                                   if(maans ==0)
                                   {


//                                   opt1.setImageResource(R.drawable.ic_correct);
                                   }
                               }
                               else {
                                   if(rightans ==1)
                                   {
                                       optxt1.setText("Actual Ans");
                                       opt1.setImageResource(R.drawable.ic_correct);
                                   }
                                   if(rightans ==2)
                                   {
                                       optxt2.setText("Actual Ans");
                                       opt3.setImageResource(R.drawable.ic_correct);
                                   }
                                   if(rightans ==3)
                                   {
                                       optxt3.setText("Actual Ans");
                                       opt3.setImageResource(R.drawable.ic_correct);
                                   }
                                   if(rightans ==4)
                                   {
                                       optxt4.setText("Actual Ans");
                                       opt4.setImageResource(R.drawable.ic_correct);
                                   }
                                   if(rightans ==0)
                                   {

//                                   opt1.setImageResource(R.drawable.ic_correct);
                                   }
                               }
                               Log.e("solution  is", "" + solutioncounter);
                               String soul = Html.escapeHtml(response.body().getData().get(solutioncounter).getSolution());
                               soledit.setText(String.valueOf(soul));
                           }
                           catch (Exception e)
                           {
                               Log.e("log catch first" , "Part");
                               maanstr = response.body().getData().get(solutioncounter).getAns();
                               rightansstr = response.body().getData().get(solutioncounter).getQueAns();
                               ansss1.setVisibility(View.GONE);
                               ansss2.setVisibility(View.GONE);
                               ansss3.setVisibility(View.GONE);
                               ansss4.setVisibility(View.GONE);
                               if(maanstr.equals(rightansstr) )
                               {
                                   String soul = Html.escapeHtml(response.body().getData().get(solutioncounter).getSolution());
                                   soledit.setText(String.valueOf(soul));
                                   soledit.setTextColor(Color.GREEN);
//                                   if(maanstr.equals("1"))
//                                   {
//                                       optxt1.setText("Your Answer");
//                                       opt1.setImageResource(R.drawable.ic_correct);
//                                   }
//                                   if(maanstr ==2)
//                                   {
//                                       optxt2.setText("Your Answer");
//                                       opt2.setImageResource(R.drawable.ic_correct);
//                                   }
//                                   if(maans ==3)
//                                   {
//                                       optxt3.setText("Your Answer");
//                                       opt3.setImageResource(R.drawable.ic_correct);
//                                   }
//                                   if(maanstr ==4)
//                                   {
//                                       optxt4.setText("Your Answer");
//                                       opt4.setImageResource(R.drawable.ic_correct);
//                                   }
//                                   if(maanstr ==0)
//                                   {
//
//
////                                   opt1.setImageResource(R.drawable.ic_correct);
//                                   }
                               }else {
                                   String soul = Html.escapeHtml(response.body().getData().get(solutioncounter).getSolution());
                                   soledit.setText(String.valueOf(soul));
                                   soledit.setTextColor(Color.RED);
                                   if(rightans ==1)
                                   {
                                       optxt1.setText("Actual Ans");
                                       opt1.setImageResource(R.drawable.ic_correct);
                                   }
                                   if(rightans ==2)
                                   {
                                       optxt2.setText("Actual Ans");
                                       opt3.setImageResource(R.drawable.ic_correct);
                                   }
                                   if(rightans ==3)
                                   {
                                       optxt3.setText("Actual Ans");
                                       opt3.setImageResource(R.drawable.ic_correct);
                                   }
                                   if(rightans ==4)
                                   {
                                       optxt4.setText("Actual Ans");
                                       opt4.setImageResource(R.drawable.ic_correct);
                                   }
                                   if(rightans ==0)
                                   {

//                                   opt1.setImageResource(R.drawable.ic_correct);
                                   }
                               }
                               Log.e("solution  is", "" + solutioncounter);

                              e.printStackTrace();
                           }
                         //  int rightans = String.valueOf()Integer.valueOf(response.body().getData().get(solutioncounter).getQueAns());
                           // soledit.loadData(response.body().getData().get(solutioncounter).getSolution() , "text/html" , null);
                       }
                       else {
                           Log.e("we " , "are Part");
                           if(solutioncounter +1 >response.body().getData().size())
                           {
                               solutioncounter = response.body().getData().size()-1;
                               Toast.makeText(getActivity(), "No forward possible ", Toast.LENGTH_SHORT).show();
                               Log.e("dfsd" , "dxdxdx");
                           }

                       }
                       }

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

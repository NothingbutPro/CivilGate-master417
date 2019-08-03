package dev.raghav.civilgate.Frag_granades;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import dev.raghav.civilgate.Activities.About_All;
import dev.raghav.civilgate.Activities.All_Reviews_Questions;
import dev.raghav.civilgate.Api.Api;
import dev.raghav.civilgate.Api.Retro_Urls;
import dev.raghav.civilgate.Const_Files.Bookmark_ids;
import dev.raghav.civilgate.Const_Files.BooktheMarks;
import dev.raghav.civilgate.Const_Files.Full_Solutions;
import dev.raghav.civilgate.Other_Parsing_Files.Get_About;
import dev.raghav.civilgate.Other_Parsing_Files.RemoveBookmarks;
import dev.raghav.civilgate.R;
import dev.raghav.civilgate.SessionManage.SessionManager;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Boomarks_Fragment extends Fragment {

    SessionManager sessionManager;
    LinearLayout anydbt;
    WebView webquebk;
    ImageView opt1 ,opt2 ,opt3,opt4;
    TextView optxt1 ,optxt2 ,optxt3 ,optxt4 ;
    TextView allques;
    TextView soleditbk, ansss1bk,ansss2bk,ansss3bk,ansss4bk,queno;
    static public int solutioncounter =0;
    ImageView unbookmark;
    int nx,pre;
    Call<BooktheMarks> get_aboutCall;
    int checklst =0;
    ArrayAdapter<String> BookaArrayAdapter;
    Full_Solutions full_solutions;
    TextView next ,previous;
    ArrayList<Bookmark_ids> integerstest_ids = new ArrayList<>();
    ArrayList<String> integidsonly = new ArrayList<>();
    Spinner bookmarksid;
    AlertDialog.Builder builder;
    private String k1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
       // View view = inflater.inflate(R.layout.mcq_questions , container , false);
        View view = inflater.inflate(R.layout.bookfragslayout , container , false);
        sessionManager = new SessionManager(getActivity());
        webquebk = view.findViewById(R.id.webquebk);
        next = view.findViewById(R.id.nextbx);
        anydbt = view.findViewById(R.id.anydbt);
        queno = view.findViewById(R.id.queno);
        previous = view.findViewById(R.id.previousbx);
        bookmarksid = view.findViewById(R.id.bookmarksid);
        unbookmark = view.findViewById(R.id.ubbookmark);
        ansss1bk = view.findViewById(R.id.ansss1bk);
        ansss2bk = view.findViewById(R.id.ansss2bk);
        ansss3bk = view.findViewById(R.id.ansss3bk);
        ansss4bk = view.findViewById(R.id.ansss4bk);
        soleditbk = view.findViewById(R.id.soleditbk);
        allques = view.findViewById(R.id.allques);
        optxt1 = view.findViewById(R.id.optxt1);
        optxt2 = view.findViewById(R.id.optxt2);
        optxt3 = view.findViewById(R.id.optxt3);
        optxt4 = view.findViewById(R.id.optxt4);
        opt1 = view.findViewById(R.id.opt1);
        opt2 = view.findViewById(R.id.opt2);
        opt3 = view.findViewById(R.id.opt3);
        opt4 = view.findViewById(R.id.opt4);
   //     Toast.makeText(getActivity(), "hiiiiiiiiiii", Toast.LENGTH_SHORT).show();
        GetTheFullSolution(0);
    /*    anydbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create custom dialog object
                final Dialog dialog = new Dialog(getActivity());
                // Include dialog.xml file
                dialog.setContentView(R.layout.anydoubt);
                // Set dialog title
                dialog.setTitle("Custom Dialog");

                // set values for custom dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.textDialog);
                text.setText("Custom dialog Android example.");
                ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
                image.setImageResource(R.drawable.arrow);

                dialog.show();

                Button declineButton = (Button) dialog.findViewById(R.id.declineButton);
                // if decline button is clicked, close the custom dialog
                declineButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close dialog
                        dialog.dismiss();
                    }
                });

            }
        });*/
        unbookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(100, TimeUnit.SECONDS)
                        .readTimeout(100,TimeUnit.SECONDS).build();
                Retrofit RetroLogin = new Retrofit.Builder()
                        .baseUrl(Retro_Urls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
                        .build();
                Log.e("hi id is" , ""+integerstest_ids.get(solutioncounter).getBookmarks_id());
                Toast.makeText(getActivity(), "boomark id"+integerstest_ids.get(solutioncounter).getBookmarks_id(), Toast.LENGTH_SHORT).show();
                Api AbloutApi = RetroLogin.create(Api.class);
                Call<RemoveBookmarks> get_aboutCall = AbloutApi.REMOVE_BOOKMARKS_CALL(integerstest_ids.get(solutioncounter).getBookmarks_id());
                get_aboutCall.enqueue(new Callback<RemoveBookmarks>() {
                    @Override
                    public void onResponse(Call<RemoveBookmarks> call, Response<RemoveBookmarks> response) {
                        Toast.makeText(getActivity(), ""+response.body().getData(), Toast.LENGTH_SHORT).show();
                        if(response.body().getResponce() == true)
                        {
                            GetTheFullSolution(integerstest_ids.get(++solutioncounter).getTestid());
                        }
                    }

                    @Override
                    public void onFailure(Call<RemoveBookmarks> call, Throwable t) {

                    }
                });
            }
        });
        bookmarksid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0) {
                    solutioncounter =0;
                    ansss1bk.setText("");
                    ansss2bk.setText("");
                    ansss3bk.setText("");
                    ansss4bk.setText("");
                    soleditbk.setText("");
                    optxt1.setText("");
                    optxt2.setText("");
                    optxt3.setText("");
                    optxt4.setText("");
                    opt1.setImageDrawable(null);
                    opt2.setImageDrawable(null);
                    opt3.setImageDrawable(null);
                    opt4.setImageDrawable(null);
                    GetTheFullSolution(integerstest_ids.get(position).getTestid());
//                    GetTheFullSolution(0);
                }

         //       GetTheFullSolution(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
                                        integidsonly.clear();
                                        integerstest_ids.clear();
                                        ansss1bk.setText("");
                                        ansss2bk.setText("");
                                        ansss3bk.setText("");
                                        ansss4bk.setText("");
                                        soleditbk.setText("");
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
                                        GetTheFullSolution(0);
                                        nx =0;

                                    }
                                }
        );
//        bookmark.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//             //   HaveABookMArk(full_solutions.getData().get(solutioncounter).getQueid() , full_solutions.getData().get(solutioncounter).getTestId() , full_solutions.getData().get(solutioncounter).getSId());
//                //   Toast.makeText(getActivity(), " Book Mark Added "+full_solutions.getData().get(solutioncounter).getQueid(), Toast.LENGTH_SHORT).show();
////                for(int p=0;p<full_solutions.getData().size();p++)
////                {
////                 if(full_solutions.getData().get(p).equals(full_solutions.getData().get(solutioncounter).getQue()))
////                 {
////
////                 }
////                }
//
//            }
//        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(solutioncounter <=0 )) {
                    integidsonly.clear();
                    integerstest_ids.clear();
                    ansss1bk.setText("");
                    ansss2bk.setText("");
                    ansss3bk.setText("");
                    ansss4bk.setText("");
                    soleditbk.setText("");
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
                    GetTheFullSolution(0);
                    pre =0;
                }else {
                    Toast.makeText(getActivity(), "No Backward possible", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }



    private void GetTheFullSolution(int testid) {

//        integerstest_ids.clear();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100,TimeUnit.SECONDS).build();
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(Retro_Urls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api AbloutApi = RetroLogin.create(Api.class);
        if(testid ==0) {
       get_aboutCall = AbloutApi.BOOKTHE_MARKS_CALL(sessionManager.getCoustId() , String.valueOf(testid));
        }else {
            get_aboutCall = AbloutApi.BOOKTHE_MARKS_CALL(sessionManager.getCoustId() ,String.valueOf(testid));
        }
        get_aboutCall.enqueue(new Callback<BooktheMarks>() {
            @Override
            public void onResponse(Call<BooktheMarks> call, Response<BooktheMarks> response) {
                for(int i=0;i<response.body().getData().size(); i++){
                    if(testid ==0) {
                     //   Toast.makeText(getActivity(), " "+response.body().getData().get(i).getTestName(), Toast.LENGTH_SHORT).show();
                        integerstest_ids.add(new Bookmark_ids(Integer.parseInt(response.body().getData().get(i).getTest_id()), response.body().getData().get(i).getTestName() ,response.body().getData().get(i).getBookmarkId()));
                    }
                    if(solutioncounter>=0 ){
//                    Log.e("hi" , "bookamr"+response.body().getData().get(solutioncounter).getQue());
             //       Log.e("hi" , "bookamr"+response.body().getData().get(solutioncounter).getTest_id());

                    if(response.body().getData().get(i).getType().equals("1")) {

                       // integerstest_ids.add(Integer.parseInt(response.body().getData().get(i).getTest_id()));
                        if(solutioncounter <response.body().getData().size() &&  solutioncounter==i) {
                                webquebk.loadData(response.body().getData().get(solutioncounter).getQue().toString(), "text/html", null);
                                ansss1bk.setText(ansss1bk.getText().toString().concat("" + response.body().getData().get(solutioncounter).getAns1()));
                                ansss2bk.setText(ansss2bk.getText().toString().concat("" + response.body().getData().get(solutioncounter).getAns2()));
                                ansss3bk.setText(ansss3bk.getText().toString().concat("" + response.body().getData().get(solutioncounter).getAns3()));
                                ansss4bk.setText(ansss4bk.getText().toString().concat("" + response.body().getData().get(solutioncounter).getAns4()));
                                soleditbk.setText(response.body().getData().get(solutioncounter).getSolution());
                                queno.setText(String.valueOf(solutioncounter));
                            int maans = Integer.valueOf(response.body().getData().get(solutioncounter).getAns());
                            if(maans ==1)
                            {

                                opt1.setImageResource(R.drawable.ic_correct);
                            }
                            if(maans ==2)
                            {

                                opt2.setImageResource(R.drawable.ic_correct);
                            }
                            if(maans ==3)
                            {

                                opt3.setImageResource(R.drawable.ic_correct);
                            }
                            if(maans ==4)
                            {
                                opt4.setImageResource(R.drawable.ic_correct);
                            }

                        }else {
                            if(solutioncounter <=0) {
                          //      Toast.makeText(getActivity(), "No Backward possible", Toast.LENGTH_SHORT).show();
                            }else if(solutioncounter == response.body().getData().size()){
                            //    Toast.makeText(getActivity(), "No Forward possible", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    }
//                    marksArrayList.add(new BooktheMarksData(response.body().getData().get(i).getId(),response.body().getData().get(i).getSubId(),
//                            response.body().getData().get(i).getQue(),response.body().getData().get(i).getAns1(),response.body().getData().get(i).getAns2(),
//                            response.body().getData().get(i).getAns3(),response.body().getData().get(i).getAns4(),response.body().getData().get(i).getAns3(),response.body().getData().get(i).getQue(),
//                            response.body().getData().get(i).getAns3(),response.body().getData().get(i).getFromAns(), response.body().getData().get(i).getToAns(),response.body().getData().get(i).getSolution(),response.body().getData().get(i).getStatus(),response.body().getData().get(i).getVideo(),response.body().getData().get(i).getVideoUrl(),response.body().getData().get(i).getCreatedate(),response.body().getData().get(i).getType()
//                            ,response.body().getData().get(i).getTestName()
//                    ));

                }
                if(testid ==0) {
                    for(int k=0;k<response.body().getData().size(); ++k) {
//                        for(int j=0;j<=k;j++) {
                            String j1 = response.body().getData().get(k).getTestName();
                            if(k !=0)
                            {
                                 k1 = response.body().getData().get(k-1).getTestName();
                            }else {
                                 k1 ="xyz";
                                integidsonly.add(response.body().getData().get(k).getTestName());
                            }
                            if(k==0) {
                                integidsonly.add(response.body().getData().get(k).getTestName());

                            }else if(!j1.equals(k1))
                            {
                                integidsonly.add(response.body().getData().get(k).getTestName());

                            }else {
                                try {
                                    integidsonly.remove(k);
                                }catch (Exception e)
                                {
                                    e.printStackTrace();
                                    Toast.makeText(getActivity(), "last i guess", Toast.LENGTH_SHORT).show();
                                }

                            }


//                            if(j==k)
//                            {
//                                integidsonly.add(response.body().getData().get(j).getTestName());
//                            }
//                            if(!k1.equals(j1)) {
//
//                                integidsonly.add(response.body().getData().get(j).getTestName());
//
//                            }else {
//                                try {
//                                    if (integidsonly.get(j).length() != 0) {
//                                        integidsonly.remove(k);
//                                    }
//                                }catch (Exception e)
//                                {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }

                    }
//                    if(testid ==0) {
//                        for(int k=0;k<integidsonly.size(); k++) {
//                            for(int j=0;j<integidsonly.size(); j++) {
//
//                                if(!response.body().getData().get(j).getTestName().equals(response.body().getData().get(k).getTestName())) {
//
//                                    integidsonly.add(response.body().getData().get(k).getTestName());
//                                }else {
//                                integidsonly.remove(j);
//                                }
//                            }
//                            }
//
//                        }
                    BookaArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, integidsonly );
                    bookmarksid.setAdapter(BookaArrayAdapter);
                }
                //bookmarksid.setDr(android.R.layout.simple_spinner_dropdown_item);

//                if(solutioncounter>=0) {
//                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                   // FragmentManager fragmentManager = getFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.solframeboo, new Boomarks_Fragment()).commit();
//                }
//                bookrecy.setLayoutManager(llm);
//                bookrecy.setAdapter( new dev.raghav.civilgate.Dapter.BooktheMarks(marksArrayList));

                //     Toast.makeText(MaBookmarks.this, ""+response.body().getData().getDescription(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<BooktheMarks> call, Throwable t) {

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

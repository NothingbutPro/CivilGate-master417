package dev.raghav.civilgate.Test_Activities.Test_Types;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import dev.raghav.civilgate.Const_Files.Questions_jJava;
import dev.raghav.civilgate.R;

import static android.os.Looper.getMainLooper;
import static dev.raghav.civilgate.Test_Activities.Main_Test_Activity.queposition;
import static dev.raghav.civilgate.Test_Activities.Main_Test_Activity.questionsJJavaHashMap;
import static dev.raghav.civilgate.Test_Activities.Main_Test_Activity.questionsJJavaLinkedList;

public class Multiple_Que_Test extends Fragment {
    TextView que_txt ,que_no;
    RadioGroup radio_grp;
    RadioButton ans1, ans2, ans3, ans4;
    SpannableString spannableStringque;
    View Itemview;
    LinearLayout textnas;
    CardView card1;
    TextView clock;
    AutoCompleteTextView ansinput;
    private long startTime = 0L;
    private Handler customHandler = new Handler();
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    int TimeTaken =0;
    int clicks;
    SimpleDateFormat  oldmin;
    int countmin =0;
    int mins;
    int secs;
    Timestamp tsTemp;
    TextView clear_all;
     Handler someHandler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View MultipleView = inflater.inflate(R.layout.multiple_que, container, false);

        que_txt = MultipleView.findViewById(R.id.que_txtview);
        radio_grp = MultipleView.findViewById(R.id.radio_grp);
        clear_all = MultipleView.findViewById(R.id.clear_all);
        que_no = MultipleView.findViewById(R.id.que_no);
        ansinput = MultipleView.findViewById(R.id.ansinput);
        ans1 = MultipleView.findViewById(R.id.ans1);
        ans2 = MultipleView.findViewById(R.id.ans2);
        ans3 = MultipleView.findViewById(R.id.ans3);
        ans4 = MultipleView.findViewById(R.id.ans4);
        clock = MultipleView.findViewById(R.id.cock);
        oldmin = new SimpleDateFormat("ddMMyyyyhhmmss");
        Log.e("recent min" , ""+oldmin.getCalendar().getTime().getSeconds());
        TimeTaken = oldmin.getCalendar().getTime().getMinutes();
        Log.e("recent time" , ""+TimeTaken);


        TimeTaken = 0;
        try {
            spannableStringque = SpannableString.valueOf(questionsJJavaLinkedList.get(queposition).getQue());
            que_txt.setText(Html.fromHtml(questionsJJavaLinkedList.get(queposition).getQue()));
            ans1.setText(questionsJJavaLinkedList.get(queposition).getAns_1());
            ans2.setText(questionsJJavaLinkedList.get(queposition).getAns_2());
            ans3.setText(questionsJJavaLinkedList.get(queposition).getAns_3());
            ans4.setText(questionsJJavaLinkedList.get(queposition).getAns_4());
            que_no.setText("Que "+questionsJJavaLinkedList.get(queposition).getPosition());
            if(questionsJJavaLinkedList.get(queposition).getAns_1().length() !=0)
            {

                ansinput.setVisibility(View.GONE);
            }else {

            }


        }catch (Exception e)
        {
            radio_grp.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "screwed ", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
         someHandler = new Handler(getMainLooper());
//        TimeTaken =0;
        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
//                timeInMilliseconds = System.uptimeMillis() - startTime;

                updatedTime = timeSwapBuff + timeInMilliseconds;


                 secs = (int) (updatedTime / 1000);
                int mins = secs / 60;
                secs = secs % 60;
                int milliseconds = (int) (updatedTime % 1000);
                clock.setText("" + mins + ":"
                        +secs/* + ":"
                    + String.format("%03d", milliseconds)*/);
//                tvClock.setText(new SimpleDateFormat("HH:mm", Locale.US).format(new Date()));
               // Log.e("time is" ,""+new SimpleDateFormat("HH:mm", Locale.US).format(new Date()));
            //    Log.e("secs  is" ,""+secs);
                if(secs ==59)
                {
                    countmin++;
                    secs=secs+1;
                    TimeTaken = (countmin*secs);
//                    ++TimeTaken;
//                    ++countmin;
                    Log.e("time issssss" ,""+(TimeTaken));

//                    secs =(countmin*secs);
                }else{
                    if(TimeTaken==0)
                    {
                        ++TimeTaken;
                    //    Log.e("at zero" , ""+TimeTaken);
                    }else {
                        ++TimeTaken;
                   //     Log.e("time taken" , ""+TimeTaken);
                    }

//                    if(countmin<1) {
//                        Log.e("time waaaas", "" + TimeTaken);
//                        Log.e("countmin is", "" + countmin);
//                        TimeTaken = secs;
//                    }else {
//                        TimeTaken = TimeTaken + secs;
//                        Log.e("time taken", "" + TimeTaken);
//                        Log.e("secs is", "" + secs);
//
//                    }
                }
                someHandler.postDelayed(this, 1000);
            }
        }, 10);
//        radio_grp.findViewById(R.id.ans1);
//        radio_grp.findViewById(R.id.ans2);
//        radio_grp.findViewById(R.id.ans3);
//        radio_grp.findViewById(R.id.ans3);


//        Runnable updateTimerThread = new Runnable() {
//
//            public void run() {
//
//                timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
////                timeInMilliseconds = System.uptimeMillis() - startTime;
//
//                updatedTime = timeSwapBuff + timeInMilliseconds;
//
//
//                    int secs = (int) (updatedTime / 1000);
//                    int mins = secs / 60;
//                    secs = secs % 60;
//                    int milliseconds = (int) (updatedTime % 1000);
//                    clock.setText("" + mins + ":"
//                            +secs/* + ":"
//                    + String.format("%03d", milliseconds)*/);
//
//                    TimeTaken = secs;
////                        long start = System.currentTimeMillis();
////                        long runTime = System.currentTimeMillis() - start;
////                        Log.e("System time" , ""+runTime);
////                            clock.setText("" + mins + ":"
////                                    + String.format("%02d", secs)/* + ":"
////                    + String.format("%03d", milliseconds)*/);
//                    //    TimeTaken = String.format("%02d, secs);
//                    //    Log.e("updatedTime is" , ""+updatedTime);
//
////                        if(secs ==59)
////                        {
////                            TimeTaken = secs +1;
////                            Log.d("before 59" , "time is "+TimeTaken);
////                        }else {
////
////                            TimeTaken = TimeTaken+secs;
////                            Log.d("after 59" , "time is "+TimeTaken);
////                        }
//
//
//                    //  Log.d("writtebn xfsadf", "sdf" + questionsJJavaHashMap.get(queposition).getWritten_ans());
////                            questionsJJavaHashMap.remove(queposition);
////                            questionsJJavaHashMap.put(queposition, new Questions_jJava("Not answered", TimeTaken));
//                    //      Log.d("writtebn xfsadf", "sdf" + questionsJJavaHashMap.get(queposition).getWritten_ans());
//
//
//
//
//
//
//
//                // Toast.makeText(getActivity(), ""+String.format("%02d", secs), Toast.LENGTH_SHORT).show();
//                customHandler.postDelayed(this, 0);
//            }
//
//        };


        ans1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if(isChecked ==true)
                    {

//                    TimeTaken =  (new Timestamp( (System.currentTimeMillis())/1000).getSeconds()) -( tsTemp.getSeconds()) ;
//                    TimeTaken = new Timestamp( (System.currentTimeMillis())/1000).getSeconds();
//                        SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
//                        String format = s.format(new Date());
//                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyyhhmms");
//                        Log.e("multiple" , "old man is" +oldmin.format(new Date().getTime()));
//                        Date d2 = oldmin.getCalendar().getTime();
//                        Date d3 = s.getCalendar().getTime();
//
//                        date2 = Calendar.getInstance().getTime();
//
//
//                        //   long difference = date1.getTime() - date2.getTime();
//                        int lowsec= date1.getSeconds();
//                        int highsec = date2.getSeconds();
//                        Log.e("full date1 " , ""+date1);
//                        Log.e("full date2" , ""+date2);
//                        Log.e("date1 hours" , ""+date1.getHours());
//                        Log.e("date2 hours " , ""+date2.getHours());
//                        Log.e("date1 hours" , ""+date1.getMinutes());
//                        Log.e("date2 hours " , ""+date2.getMinutes());
//                        Log.e("date1 hours" , ""+date1.getSeconds());
//                        Log.e("date2 hours " , ""+date2.getSeconds());
//                        if(date1.getHours() <=date2.getHours())
//                        {
//                            days = date2.getHours() - date1.getHours();
//                            if(date1.getMinutes() <= date2.getMinutes())
//                            {
//                                min = date2.getMinutes() - date1.getMinutes();
//                                Log.e("date2 min " , ""+min);
//                                if(lowsec<=highsec)
//                                {
//                                    if(min !=0)
//                                    {
//                                        int orgmin = date2.getSeconds()-date1.getSeconds();
//                                        TimeTaken = min *orgmin;
//                                        Log.e("=======Hours"," :: "+(min *orgmin));
//                                    }else{
//                                        int orgmin = date2.getSeconds()- date1.getSeconds();
//                                        TimeTaken = orgmin;
//                                        Log.e("=======Seconds"," :: "+orgmin);
//                                    }
//
//
//                                }
//                                else
//                                {
//                                    if(min !=0)
//                                    {
//                                        int orgmin = date1.getSeconds()-date2.getSeconds();
//                                        TimeTaken = min *orgmin;
//                                        Log.e("=======Hours"," :: "+(min *orgmin));
//                                    }else{
//                                        int orgmin = date1.getSeconds()- date2.getSeconds() ;
//                                        TimeTaken = orgmin;
//
//                                        Log.e("=======Seconds"," :: "+orgmin);
//                                    }
//                                }
////                        min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
//                            } else
//                            {
//                                min = date1.getMinutes() - date2.getMinutes();
//                                Log.e("date2 min " , ""+min);
//                                if(lowsec>=highsec)
//                                {
//                                    if(min !=0)
//                                    {
//
//                                        int orgmin = date2.getSeconds()-date1.getSeconds();
//                                        TimeTaken = min *orgmin;
//                                        Log.e("=======Hours"," :: "+(min *orgmin));
//                                    }else{
//                                        int orgmin = date2.getSeconds()- date1.getSeconds() ;
//                                        TimeTaken = orgmin;
//                                        Log.e("=======Seconds"," :: "+orgmin);
//                                    }
//
//
//                                }else {
//
//                                    if(min !=0)
//                                    {
//                                        int orgmin = date1.getSeconds()-date2.getSeconds();
//                                        TimeTaken = min *orgmin;
//                                        Log.e("=======Hours"," :: "+(min *orgmin));
//                                    }else{
//                                        int orgmin = date1.getSeconds()- date2.getSeconds() ;
//                                        TimeTaken = orgmin;
//                                        Log.e("=======Seconds"," :: "+orgmin);
//                                    }
//                                }
////                        min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
//                            }
//                        }
                        questionsJJavaHashMap.remove(queposition);
                        Log.e("Time taken" , " is" +TimeTaken);
                        questionsJJavaHashMap.put(queposition , new Questions_jJava(String.valueOf(1) , TimeTaken ,queposition));
                        Log.e("writtebn xfsadf" , "sdf "+questionsJJavaHashMap.get(queposition).getWritten_ans());
                     //   Log.d("Level id is" , ""+questionsJJavaHashMap.get(queposition).getId());
                       // Log.d("Level id is" , ""+questionsJJavaHashMap.get(queposition).getSub_id());
                        Log.d("in link Sub Level id is" , ""+questionsJJavaLinkedList.get(queposition).getSub_id());
                        Log.d("in link Level id is " , ""+questionsJJavaLinkedList.get(queposition).getId());
                        //  questionsJJavaHashMap.put(queposition , new Questions_jJava(ans1.getText().toString() , ))
                        ans2.setChecked(false);
                        ans3.setChecked(false);
                        ans4.setChecked(false);
                        if(clicks !=0)
                        {
                            Toast.makeText(getActivity(), "clicked", Toast.LENGTH_SHORT).show();
                            ans1.setChecked(false);
                            clicks =1;
                        }


                    }
                }


        });

        ansinput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                questionsJJavaHashMap.remove(queposition);
                questionsJJavaHashMap.put(queposition , new Questions_jJava(s.toString(), TimeTaken,queposition));
                Log.d("writtebn xfsadf" , "sdf "+questionsJJavaHashMap.get(queposition).getWritten_ans());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        ans2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked ==true)
                {
//                    questionsJJavaHashMap.put(queposition , new Questions_jJava())
//
//                    TimeTaken = new Timestamp( (System.currentTimeMillis())/1000).getSeconds();
//                    int lowsec= date1.getSeconds();
//                    int highsec = date2.getSeconds();
//                    Log.e("full date1 " , ""+date1);
//                    Log.e("full date2" , ""+date2);
//                    Log.e("date1 hours" , ""+date1.getHours());
//                    Log.e("date2 hours " , ""+date2.getHours());
//                    Log.e("date1 hours" , ""+date1.getMinutes());
//                    Log.e("date2 hours " , ""+date2.getMinutes());
//                    Log.e("date1 hours" , ""+date1.getSeconds());
//                    Log.e("date2 hours " , ""+date2.getSeconds());
//                    if(date1.getHours() <=date2.getHours())
//                    {
//                        days = date2.getHours() - date1.getHours();
//                        if(date1.getMinutes() <= date2.getMinutes())
//                        {
//                            min = date2.getMinutes() - date1.getMinutes();
//                            Log.e("date2 min " , ""+min);
//                            if(lowsec<=highsec)
//                            {
//                                if(min !=0)
//                                {
//                                    int orgmin = date2.getSeconds()-date1.getSeconds();
//                                    TimeTaken = min *orgmin;
//                                    Log.e("=======Hours"," :: "+min *orgmin);
//                                }else{
//                                    int orgmin = date2.getSeconds()- date1.getSeconds();
//                                    TimeTaken = orgmin;
//                                    Log.e("=======Seconds"," :: "+orgmin);
//                                }
//
//
//                            }else {
//
//                                if(min !=0)
//                                {
//                                    int orgmin = date1.getSeconds()-date2.getSeconds();
//                                    TimeTaken = min *orgmin;
//                                    Log.e("=======Hours"," :: "+(min *orgmin));
//                                }else{
//                                    int orgmin = date1.getSeconds()- date2.getSeconds() ;
//                                    TimeTaken =orgmin;
//                                    Log.e("=======Seconds"," :: "+orgmin);
//                                }
//                            }
////                        min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
//                        } else
//                        {
//                            min = date1.getMinutes() - date2.getMinutes();
//                            Log.e("date2 min " , ""+min);
//                            if(lowsec>=highsec)
//                            {
//                                if(min !=0)
//                                {
//                                    int orgmin = date2.getSeconds()-date1.getSeconds();
//                                    TimeTaken = min *orgmin;
//                                    Log.e("=======Hours"," :: "+(min *orgmin));
//                                }else{
//                                    int orgmin = date2.getSeconds()- date1.getSeconds() ;
//                                    TimeTaken = orgmin;
//                                    Log.e("=======Seconds"," :: "+orgmin);
//                                }
//
//
//                            }else {
//
//                                if(min !=0)
//                                {
//                                    int orgmin = date1.getSeconds()-date2.getSeconds();
//                                    TimeTaken = min *orgmin;
//                                    Log.e("=======Hours"," :: "+(min *orgmin));
//                                }else{
//                                    int orgmin = date1.getSeconds()- date2.getSeconds() ;
//                                    TimeTaken =orgmin;
//                                    Log.e("=======Seconds"," :: "+orgmin);
//                                }
//                            }
////                        min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
//                        }
//
//
//
//                    }
                    questionsJJavaHashMap.remove(queposition);
                    questionsJJavaHashMap.put(queposition , new Questions_jJava(String.valueOf(2) , TimeTaken ,queposition));
                    Log.d("writtebn xfsadf" , "sdf"+questionsJJavaHashMap.get(queposition).getWritten_ans());
                    ans1.setChecked(false);
                    ans3.setChecked(false);
                    ans4.setChecked(false);
                    if(clicks !=0)
                    {
                        Toast.makeText(getActivity(), " clicked", Toast.LENGTH_SHORT).show();
                        ans2.setChecked(false);
                        clicks =1;
                    }
                }
            }
        });   ans3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked ==true)
                {

//                    int lowsec= date1.getSeconds();
//                    int highsec = date2.getSeconds();
//                    Log.e("full date1 " , ""+date1);
//                    Log.e("full date2" , ""+date2);
//                    Log.e("date1 hours" , ""+date1.getHours());
//                    Log.e("date2 hours " , ""+date2.getHours());
//                    Log.e("date1 hours" , ""+date1.getMinutes());
//                    Log.e("date2 hours " , ""+date2.getMinutes());
//                    Log.e("date1 hours" , ""+date1.getSeconds());
//                    Log.e("date2 hours " , ""+date2.getSeconds());
//                    if(date1.getHours() <=date2.getHours())
//                    {
//                        days = date2.getHours() - date1.getHours();
//                        if(date1.getMinutes() <= date2.getMinutes())
//                        {
//                            min = date2.getMinutes() - date1.getMinutes();
//                            Log.e("date2 min " , ""+min);
//                            if(lowsec<=highsec)
//                            {
//                                if(min !=0)
//                                {
//                                    int orgmin = date2.getSeconds()-date1.getSeconds();
//                                    TimeTaken = min *orgmin;
//                                    Log.e("=======Hours"," :: "+min *orgmin);
//                                }else{
//                                    int orgmin = date2.getSeconds()- date1.getSeconds() ;
//                                    TimeTaken = orgmin;
//                                    Log.e("=======Seconds"," :: "+orgmin);
//                                }
//
//
//                            }else {
//
//                                if(min !=0)
//                                {
//                                    int orgmin = date1.getSeconds()-date2.getSeconds();
//                                    TimeTaken = min *orgmin;
//                                    Log.e("=======Hours"," :: "+(min *orgmin));
//                                }else{
//                                    int orgmin = date1.getSeconds()- date2.getSeconds() ;
//                                    TimeTaken = orgmin;
//                                    Log.e("=======Seconds"," :: "+orgmin);
//                                }
//                            }
////                        min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
//                        } else
//                        {
//                            min = date1.getMinutes() - date2.getMinutes();
//                            Log.e("date2 min " , ""+min);
//                            if(lowsec>=highsec)
//                            {
//                                if(min !=0)
//                                {
//                                    int orgmin = date2.getSeconds()-date1.getSeconds();
//                                    TimeTaken = min *orgmin;
//                                    Log.e("=======Hours"," :: "+(min *orgmin));
//                                }else{
//                                    int orgmin = date2.getSeconds()- date1.getSeconds() ;
//                                    TimeTaken = orgmin;
//                                    Log.e("=======Seconds"," :: "+orgmin);
//                                }
//
//
//                            }else {
//
//                                if(min !=0)
//                                {
//                                    int orgmin = date1.getSeconds()-date2.getSeconds();
//                                    TimeTaken = min *orgmin;
//                                    Log.e("=======Hours"," :: "+(min *orgmin));
//                                }else{
//                                    int orgmin = date1.getSeconds()- date2.getSeconds() ;
//                                    TimeTaken = orgmin;
//                                    Log.e("=======Seconds"," :: "+orgmin);
//                                }
//                            }
////                        min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
//                        }
//
//
//
//                    }
                    questionsJJavaHashMap.remove(queposition);
                    questionsJJavaHashMap.put(queposition , new Questions_jJava(String.valueOf(3) , TimeTaken,queposition));
                    Log.d("writtebn xfsadf" , "sdf"+questionsJJavaHashMap.get(queposition).getWritten_ans());
                    ans4.setChecked(false);
                    ans2.setChecked(false);
                    ans1.setChecked(false);
                    if(clicks !=0)
                    {
                        Toast.makeText(getActivity(), " clicked", Toast.LENGTH_SHORT).show();
                        ans3.setChecked(false);
                        clicks =1;
                    }
                }
            }
        });
        ans4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked ==true)
                {

//                    int lowsec= date1.getSeconds();
//                    int highsec = date2.getSeconds();
//                    Log.e("full date1 " , ""+date1);
//                    Log.e("full date2" , ""+date2);
//                    Log.e("date1 hours" , ""+date1.getHours());
//                    Log.e("date2 hours " , ""+date2.getHours());
//                    Log.e("date1 hours" , ""+date1.getMinutes());
//                    Log.e("date2 hours " , ""+date2.getMinutes());
//                    Log.e("date1 hours" , ""+date1.getSeconds());
//                    Log.e("date2 hours " , ""+date2.getSeconds());
//                    if(date1.getHours() <=date2.getHours())
//                    {
//                        days = date2.getHours() - date1.getHours();
//                        if(date1.getMinutes() <= date2.getMinutes())
//                        {
//                            min = date2.getMinutes() - date1.getMinutes();
//                            Log.e("date2 min " , ""+min);
//                            if(lowsec<=highsec)
//                            {
//                                if(min !=0)
//                                {
//                                    int orgmin = date2.getSeconds()-date1.getSeconds();
//                                    TimeTaken = min *orgmin;
//                                    Log.e("=======Hours"," :: "+min *orgmin);
//                                }else{
//                                    int orgmin = date2.getSeconds()- date1.getSeconds() ;
//                                    TimeTaken =orgmin;
//                                    Log.e("=======Seconds"," :: "+orgmin);
//                                }
//
//
//                            }else {
//
//                                if(min !=0)
//                                {
//                                    int orgmin = date1.getSeconds()-date2.getSeconds();
//                                    TimeTaken = min *orgmin;
//                                    Log.e("=======Hours"," :: "+(min *orgmin));
//                                }else{
//                                    int orgmin = date1.getSeconds()- date2.getSeconds() ;
//                                    TimeTaken = orgmin;
//                                    Log.e("=======Seconds"," :: "+orgmin);
//                                }
//                            }
////                        min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
//                        } else
//                        {
//                            min = date1.getMinutes() - date2.getMinutes();
//                            Log.e("date2 min " , ""+min);
//                            if(lowsec>=highsec)
//                            {
//                                if(min !=0)
//                                {
//                                    int orgmin = date2.getSeconds()-date1.getSeconds();
//                                    TimeTaken = min *orgmin;
//                                    Log.e("=======Hours"," :: "+(min *orgmin));
//                                }else{
//                                    int orgmin = date2.getSeconds()- date1.getSeconds() ;
//                                    TimeTaken = orgmin;
//                                    Log.e("=======Seconds"," :: "+orgmin);
//                                }
//
//
//                            }else {
//
//                                if(min !=0)
//                                {
//                                    int orgmin = date1.getSeconds()-date2.getSeconds();
//                                    TimeTaken = min *orgmin;
//                                    Log.e("=======Hours"," :: "+(min *orgmin));
//                                }else{
//                                    int orgmin = date1.getSeconds()- date2.getSeconds() ;
//                                    TimeTaken = orgmin;
//                                    Log.e("=======Seconds"," :: "+orgmin);
//                                }
//                            }
////                        min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
//                        }
//
//
//
//                    }
                    questionsJJavaHashMap.remove(queposition);
                    questionsJJavaHashMap.put(queposition , new Questions_jJava(String.valueOf(4) , TimeTaken,queposition));
                    Log.d("writtebn xfsadf" , "sdf"+questionsJJavaHashMap.get(queposition).getWritten_ans());
                    ans2.setChecked(false);
                    ans3.setChecked(false);
                    ans1.setChecked(false);
                    if(clicks !=0)
                    {
                        Toast.makeText(getActivity(), " clicked", Toast.LENGTH_SHORT).show();
                        ans4.setChecked(false);
                        clicks =1;
                    }
                }



            }
        });


        clear_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans1.setChecked(false);
                ans2.setChecked(false);
                ans3.setChecked(false);
                ans4.setChecked(false);
            }
        });

        startTime = SystemClock.uptimeMillis();
      //  customHandler.postDelayed(updateTimerThread, 0);






        return MultipleView;
//        return super.onCreateView(inflater, container, savedInstanceState);


    }

    @Override
    public void onAttachFragment(Fragment childFragment) {

        super.onAttachFragment(childFragment);
    }

    @Override
    public void onDetach() {
        Thread.currentThread().interrupt();

        Toast.makeText(getActivity(), "thread destroyed", Toast.LENGTH_SHORT).show();
        secs=0;
        TimeTaken =0;
        someHandler.removeCallbacksAndMessages(null);
        super.onDetach();
    }





}

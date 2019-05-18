package dev.raghav.civilgate.Test_Activities.Test_Types;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import dev.raghav.civilgate.Const_Files.Questions_jJava;
import dev.raghav.civilgate.R;

import static dev.raghav.civilgate.Test_Activities.Main_Test_Activity.queposition;
import static dev.raghav.civilgate.Test_Activities.Main_Test_Activity.questionsJJavaHashMap;
import static dev.raghav.civilgate.Test_Activities.Main_Test_Activity.questionsJJavaLinkedList;

public class Fill_In_Que_Test extends Fragment {
    AutoCompleteTextView ansinput;
    TextView fillque;
    TextView clock;
    private long startTime = 0L;
    private Handler customHandler = new Handler();
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    int TimeTaken ;
    int clicks;
    int oldmin;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View FillView = inflater.inflate(R.layout.fill_the_que , container , false);
        fillque = FillView.findViewById(R.id.fillque);
        ansinput = FillView.findViewById(R.id.ansinput);
        fillque.setText(Html.fromHtml(questionsJJavaLinkedList.get(queposition).getQue()));
        clock = FillView.findViewById(R.id.cock);

        Runnable updateTimerThread = new Runnable() {

            public void run() {

                timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
//                timeInMilliseconds = System.uptimeMillis() - startTime;

                updatedTime = timeSwapBuff + timeInMilliseconds;
                try {

                    int secs = (int) (updatedTime / 1000);
                    int mins = secs / 60;
                    secs = secs % 60;
                    oldmin = mins;
                    int milliseconds = (int) (updatedTime % 1000);
                    clock.setText("" + mins + ":"
                            +secs/* + ":"

                    + String.format("%03d", milliseconds)*/);

                    TimeTaken = secs;
//                        long start = System.currentTimeMillis();
//                        long runTime = System.currentTimeMillis() - start;
//                        Log.e("System time" , ""+runTime);
//                            clock.setText("" + mins + ":"
//                                    + String.format("%02d", secs)/* + ":"
//                    + String.format("%03d", milliseconds)*/);
                    //    TimeTaken = String.format("%02d, secs);
                    //    Log.e("updatedTime is" , ""+updatedTime);
                    if(secs ==0 && mins ==0 )
                    {
                        questionsJJavaHashMap.remove(queposition);
                        // Log.d("secs" , ""+secs);
//                        Toast.makeText(getActivity(), "secs is "+secs, Toast.LENGTH_SHORT).show();
                        //  questionsJJavaHashMap.put(queposition, new Questions_jJava(0, TimeTaken));
                        questionsJJavaHashMap.put(queposition, new Questions_jJava(String.valueOf(0), secs));
                    }
//                        if(secs ==59)
//                        {
//                            TimeTaken = secs +1;
//                            Log.d("before 59" , "time is "+TimeTaken);
//                        }else {
//
//                            TimeTaken = TimeTaken+secs;
//                            Log.d("after 59" , "time is "+TimeTaken);
//                        }


                    //  Log.d("writtebn xfsadf", "sdf" + questionsJJavaHashMap.get(queposition).getWritten_ans());
//                            questionsJJavaHashMap.remove(queposition);
//                            questionsJJavaHashMap.put(queposition, new Questions_jJava("Not answered", TimeTaken));
                    //      Log.d("writtebn xfsadf", "sdf" + questionsJJavaHashMap.get(queposition).getWritten_ans());


                }catch (Exception e)
                {
                    int secs = (int) (updatedTime / 1000);
                    int mins = secs / 60;
                    secs = secs % 60;
                    int milliseconds = (int) (updatedTime % 1000);
                    clock.setText("" + mins + ":"
                            + String.format("%02d", secs)/* + ":"
                    + String.format("%03d", milliseconds)*/);
//                        TimeTaken = String.format("%02d", secs);
                    TimeTaken = secs;
                    //  questionsJJavaHashMap.remove(queposition);
                    //  questionsJJavaHashMap.put(queposition, new Questions_jJava(0, TimeTaken));
                    //   questionsJJavaHashMap.put(queposition, new Questions_jJava(0, secs));
                    Log.d("writtebn xfsadf", "sdf" + questionsJJavaHashMap.get(queposition).getWritten_ans());
                }




                // Toast.makeText(getActivity(), ""+String.format("%02d", secs), Toast.LENGTH_SHORT).show();
                customHandler.postDelayed(this, 0);
            }

        };
        startTime = SystemClock.uptimeMillis();
        customHandler.postDelayed(updateTimerThread, 0);
        ansinput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                questionsJJavaHashMap.remove(queposition);
                questionsJJavaHashMap.put(queposition , new Questions_jJava(s.toString(), TimeTaken));
                Log.d("writtebn xfsadf" , "sdf "+questionsJJavaHashMap.get(queposition).getWritten_ans());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return FillView;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}

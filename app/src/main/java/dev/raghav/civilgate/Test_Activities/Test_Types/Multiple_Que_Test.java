package dev.raghav.civilgate.Test_Activities.Test_Types;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import dev.raghav.civilgate.Api.Long_Login;
import dev.raghav.civilgate.Const_Files.Questions_jJava;
import dev.raghav.civilgate.R;

import static dev.raghav.civilgate.Test_Activities.Main_Test_Activity.queposition;
import static dev.raghav.civilgate.Test_Activities.Main_Test_Activity.questionsJJavaHashMap;
import static dev.raghav.civilgate.Test_Activities.Main_Test_Activity.questionsJJavaLinkedList;

public class Multiple_Que_Test extends Fragment {
    TextView que_txt;
    RadioGroup radio_grp;
    RadioButton ans1, ans2, ans3, ans4;
    SpannableString spannableStringque;
    TextView clock;
    private long startTime = 0L;
    private Handler customHandler = new Handler();
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    String TimeTaken;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View MultipleView = inflater.inflate(R.layout.multiple_que, container, false);
        que_txt = MultipleView.findViewById(R.id.que_txtview);
        radio_grp = MultipleView.findViewById(R.id.radio_grp);
        spannableStringque = SpannableString.valueOf(questionsJJavaLinkedList.get(queposition).getQue());
        ans1 = MultipleView.findViewById(R.id.ans1);
        ans2 = MultipleView.findViewById(R.id.ans2);
        ans3 = MultipleView.findViewById(R.id.ans3);
        ans4 = MultipleView.findViewById(R.id.ans4);
        clock = MultipleView.findViewById(R.id.cock);
        que_txt.setText(Html.fromHtml(questionsJJavaLinkedList.get(queposition).getQue()));
        ans1.setText(questionsJJavaLinkedList.get(queposition).getAns_1());
        ans2.setText(questionsJJavaLinkedList.get(queposition).getAns_2());
        ans3.setText(questionsJJavaLinkedList.get(queposition).getAns_3());
        ans4.setText(questionsJJavaLinkedList.get(queposition).getAns_4());
//        radio_grp.findViewById(R.id.ans1);
//        radio_grp.findViewById(R.id.ans2);
//        radio_grp.findViewById(R.id.ans3);
//        radio_grp.findViewById(R.id.ans3);

         Runnable updateTimerThread = new Runnable() {

            public void run() {

                timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

                updatedTime = timeSwapBuff + timeInMilliseconds;
                    try {
                        if(!TimeTaken.isEmpty())
                        {
                            int secs = (int) (updatedTime / 1000);
                            int mins = secs / 60;
                            secs = secs % 60;
                            int milliseconds = (int) (updatedTime % 1000);
                            clock.setText("" + mins + ":"
                                    + String.format("%02d", secs)/* + ":"
                    + String.format("%03d", milliseconds)*/);
                            TimeTaken = String.format("%02d", secs);
//                            questionsJJavaHashMap.remove(queposition);
//                            questionsJJavaHashMap.put(queposition, new Questions_jJava("Not answered", TimeTaken));
                      //      Log.d("writtebn xfsadf", "sdf" + questionsJJavaHashMap.get(queposition).getWritten_ans());
                        }

                    }catch (Exception e)
                    {
                        int secs = (int) (updatedTime / 1000);
                        int mins = secs / 60;
                        secs = secs % 60;
                        int milliseconds = (int) (updatedTime % 1000);
                        clock.setText("" + mins + ":"
                                + String.format("%02d", secs)/* + ":"
                    + String.format("%03d", milliseconds)*/);
                        TimeTaken = String.format("%02d", secs);
                        questionsJJavaHashMap.remove(queposition);
                        questionsJJavaHashMap.put(queposition, new Questions_jJava("Not answered", TimeTaken));
                        Log.d("writtebn xfsadf", "sdf" + questionsJJavaHashMap.get(queposition).getWritten_ans());
                    }




                // Toast.makeText(getActivity(), ""+String.format("%02d", secs), Toast.LENGTH_SHORT).show();
                customHandler.postDelayed(this, 0);
            }

        };


        ans1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked ==true)
                {
                    questionsJJavaHashMap.remove(queposition);
                    questionsJJavaHashMap.put(queposition , new Questions_jJava(ans1.getText().toString() , TimeTaken));
                    Log.d("writtebn xfsadf" , "sdf"+questionsJJavaHashMap.get(queposition).getWritten_ans());
                  //  questionsJJavaHashMap.put(queposition , new Questions_jJava(ans1.getText().toString() , ))
                    ans2.setChecked(false);
                    ans3.setChecked(false);
                    ans4.setChecked(false);
                }
            }
        });   ans2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked ==true)
                {
                 //   questionsJJavaHashMap.put(queposition , new Questions_jJava())
                    questionsJJavaHashMap.remove(queposition);
                    questionsJJavaHashMap.put(queposition , new Questions_jJava(ans2.getText().toString() , TimeTaken));
                    Log.d("writtebn xfsadf" , "sdf"+questionsJJavaHashMap.get(queposition).getWritten_ans());
                    ans1.setChecked(false);
                    ans3.setChecked(false);
                    ans4.setChecked(false);
                }
            }
        });   ans3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked ==true)
                {
                    questionsJJavaHashMap.remove(queposition);
                    questionsJJavaHashMap.put(queposition , new Questions_jJava(ans3.getText().toString() , TimeTaken));
                    Log.d("writtebn xfsadf" , "sdf"+questionsJJavaHashMap.get(queposition).getWritten_ans());
                    ans4.setChecked(false);
                    ans2.setChecked(false);
                    ans1.setChecked(false);
                }
            }
        });
        ans4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked ==true)
                {
                    questionsJJavaHashMap.remove(queposition);
                    questionsJJavaHashMap.put(queposition , new Questions_jJava(ans4.getText().toString() , TimeTaken));
                    Log.d("writtebn xfsadf" , "sdf"+questionsJJavaHashMap.get(queposition).getWritten_ans());
                    ans2.setChecked(false);
                    ans3.setChecked(false);
                    ans1.setChecked(false);
                }
            }
        });

        startTime = SystemClock.uptimeMillis();
        customHandler.postDelayed(updateTimerThread, 0);

        return MultipleView;
//        return super.onCreateView(inflater, container, savedInstanceState);


    }

    @Override
    public void onAttachFragment(Fragment childFragment) {

        super.onAttachFragment(childFragment);
    }


}

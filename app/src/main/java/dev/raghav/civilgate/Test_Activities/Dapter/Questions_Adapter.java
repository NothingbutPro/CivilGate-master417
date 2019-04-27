package dev.raghav.civilgate.Test_Activities.Dapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dev.raghav.civilgate.Const_Files.Questions_jJava;
import dev.raghav.civilgate.Dapter.Level_Adapter;
import dev.raghav.civilgate.R;
import dev.raghav.civilgate.Test_Activities.Test_Types.Fill_In_Que_Test;
import dev.raghav.civilgate.Test_Activities.Test_Types.Multiple_Que_Test;

import static dev.raghav.civilgate.Test_Activities.Main_Test_Activity.queposition;
import static dev.raghav.civilgate.Test_Activities.Main_Test_Activity.questionsJJavaLinkedList;

public class Questions_Adapter extends RecyclerView.Adapter<Questions_Adapter.MyViewHolder> {
    List<Questions_jJava> questionsJJavas ;
    ArrayList personImages;

    Context context;
    public Questions_Adapter(Context context, List<Questions_jJava> questionsJJavas) {
        this.context = context;
        this.questionsJJavas = questionsJJavas;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.que_style, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.id.setText(String.valueOf(questionsJJavas.get(i).getId()));
        Log.d("postions" , "are working with"+i);

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int p = 0;
                Toast.makeText(context, ""+i, Toast.LENGTH_SHORT).show();
                int linksize = questionsJJavaLinkedList.size();
//                if(questionsJJavaLinkedList.get(i).getId() == questionsJJavas.get(i).)
                Log.e("linked lis is" , ""+questionsJJavaLinkedList);
                while (questionsJJavaLinkedList.iterator().hasNext() && p<questionsJJavaLinkedList.size())
                {
                    String  px =questionsJJavaLinkedList.get(p).getQue();
                    String cx = questionsJJavas.get(i).getQue();
                    Boolean bx = questionsJJavaLinkedList.iterator().hasNext();
                    Boolean pdfg =  px.equals(cx);
                    if(questionsJJavaLinkedList.iterator().next() !=null && pdfg )
                    {
                        Toast.makeText(context, "type is"+questionsJJavaLinkedList.iterator().next(), Toast.LENGTH_SHORT).show();
                        if(questionsJJavas.get(i).getType() == 1)
                        {
                            queposition = p;
                            FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            // fragmentTransaction.replace(R.id.container_dik , new Multiple_Que_Test(questionsJJavaLinkedList.get(p).getId(),questionsJJavaLinkedList.get(p).getQue() , questionsJJavaLinkedList.get(p).getAns_1(), questionsJJavaLinkedList.get(p).getAns_2(), questionsJJavaLinkedList.get(p).getAns_3(), questionsJJavaLinkedList.get(p).getAns())).commit();
                            fragmentTransaction.replace(R.id.container_dik , new Multiple_Que_Test()).commit();
                        }
                        else
                        {
                            queposition = p;
                            FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.container_dik , new Fill_In_Que_Test()).commit();
                        }
                    }
                    p++;
                }

            }
        });

    }

    @Override
    public int getItemCount() {
//        return 30;
        return questionsJJavas.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView id;
       // LinearLayout cardViewkli;
        //   ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            id = (TextView) itemView.findViewById(R.id.que_sty);
            //      image = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void registerAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);
    }
}
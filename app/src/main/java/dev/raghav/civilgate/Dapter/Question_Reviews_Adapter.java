package dev.raghav.civilgate.Dapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dev.raghav.civilgate.Const_Files.Full_Solution_Data;
import dev.raghav.civilgate.Const_Files.Full_Solutions;
import dev.raghav.civilgate.Const_Files.Questions_jJava;
import dev.raghav.civilgate.R;

public class Question_Reviews_Adapter extends RecyclerView.Adapter<Question_Reviews_Adapter.MyViewHolder>
{
    List<Full_Solution_Data> full_solutions ;
    ArrayList personImages;

    Context context;
    public Question_Reviews_Adapter(Context context, ArrayList<Full_Solution_Data> full_solutions) {
        this.context = context;
        this.full_solutions = full_solutions;
    }
    @Override
    public Question_Reviews_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.analysisreport, parent, false);

        return new Question_Reviews_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Question_Reviews_Adapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.id.setText(String.valueOf(i));
        Boolean bx = full_solutions.get(i).getAns() == full_solutions.get(i).getQueAns();
        Log.w("ans is", ""+full_solutions.get(i).getAns());
        Log.w("ques ans is", ""+full_solutions.get(i).getQueAns());
        if(full_solutions.get(i).getAns().equals(full_solutions.get(i).getQueAns()))
        {

            myViewHolder.id.setBackgroundColor(Color.GREEN);
            Log.d("postions" , "Green are working with"+bx+""+i);
        }
        else
            {
            myViewHolder.cardViewkli.setBackgroundColor(Color.RED);
            Log.d("postions" , "Red are working with"+bx +""+i);
        }

    }

    @Override
    public int getItemCount() {
//        return 30;
        return full_solutions.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        public   TextView id;
        public LinearLayout cardViewkli;
        // LinearLayout cardViewkli;
        //   ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            id = (TextView) itemView.findViewById(R.id.que_sty);
            cardViewkli = itemView.findViewById(R.id.cardViewkli);
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
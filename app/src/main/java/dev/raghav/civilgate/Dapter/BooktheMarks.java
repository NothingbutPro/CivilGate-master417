package dev.raghav.civilgate.Dapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import dev.raghav.civilgate.Const_Files.Booksmarks;
import dev.raghav.civilgate.Const_Files.BooktheMarksData;
import dev.raghav.civilgate.R;
import dev.raghav.civilgate.SessionManage.SessionManager;

public class BooktheMarks extends RecyclerView.Adapter<BooktheMarks.MyViewHolder> implements Filterable {
    String Name_of_Company;
    String iD_of_cons;
    public static List<BooktheMarksData> Filtered_Consult;
    SessionManager sessionManager;
    Context context;
    BooktheMarks adapter_consultant;
    //    private List<Consultants> moviesList;
    List<BooktheMarksData> Consult ;
    private Dialog for_profile;

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String charString = constraint.toString();
                if(charString.isEmpty()){
                    Filtered_Consult = Consult;

                }
                else{
                    Log.e("Query name ","is "+charString);
                    List<BooktheMarksData> filtered = new ArrayList<>();
                    for(BooktheMarksData row : Consult){
//                        if( row.getname_of_company().toLowerCase().contains(charString.toLowerCase()) || row.getname_of_company().contains(charString.toUpperCase())){
//                            Log.e("Consult name ","is "+row.getname_of_company());
////                            filtered.add(row);
//                            filtered.add(row);
//
//                        }
                    }
                    Filtered_Consult = filtered;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = Filtered_Consult;

                return filterResults;
            }


            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                Filtered_Consult = (List<BooktheMarksData>)  results.values;
//                Consult.clear();
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name_of_company, code_of_consultant, city_of_consultant,ans1,ans2,ans3,ans4,queno,ansornot1,ansornot2,ansornot3,ansornot5,ansornot4;
        RelativeLayout re_layout;
        TextView txt_n;
        public MyViewHolder(View view) {

            super(view);
            name_of_company = (TextView) view.findViewById(R.id.name_of_company);
            ans1 = (TextView) view.findViewById(R.id.ans1);
            ans2 = (TextView) view.findViewById(R.id.ans2);
            ans3 = (TextView) view.findViewById(R.id.ans3);
            ans4 = (TextView) view.findViewById(R.id.ans4);
            ans4 = (TextView) view.findViewById(R.id.ans4);
            ans4 = (TextView) view.findViewById(R.id.ans4);
            city_of_consultant = (TextView) view.findViewById(R.id.que_no_book);
            code_of_consultant = (TextView) view.findViewById(R.id.code_of_que);
            ansornot1 = (TextView) view.findViewById(R.id.ansornot1);
            ansornot2 = (TextView) view.findViewById(R.id.ansornot2);
            ansornot3 = (TextView) view.findViewById(R.id.ansornot3);
            ansornot4 = (TextView) view.findViewById(R.id.ansornot4);
            txt_n = (TextView) view.findViewById(R.id.txt_n);
        }
    }

    public BooktheMarks(List<BooktheMarksData> moviesList) {
        this.Consult = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bookcouns, parent, false);

        return new MyViewHolder(itemView);
    }
    public Boolean remove_consul_by_name(String name){
        List<Booksmarks> filtered = new ArrayList<>();
        for(BooktheMarksData row : Consult){
//            if( row.getname_of_company().toString().contains(name)){
//                Log.e("Consult name removal ","is "+row.getname_of_company());
////                            filtered.add(row);
//
//
//            }else{
//                Consult.remove(name);
//                notifyDataSetChanged();
//            }
        }
//       Filtered_Consult = filtered;
        return false;
    }

    public Boolean remove_consultants (int postion){
//          Consult.clear();
        Consult.remove(postion);
        notifyItemRemoved(postion);
        notifyDataSetChanged();
//          no_of_consult = myViewHolder.getAdapterPosition();
//          Consult.remove(no_of_consult);
//          myViewHolder.getLayoutPosition();
//          Consult.remove(no_of_consult);
        return true;

    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
         BooktheMarksData Cons = Consult.get(position);
        holder.txt_n.setText(holder.txt_n.getText().toString().concat(Cons.getQue()));
        holder.ans1.setText(Cons.getAns1());
        holder.ans2.setText(Cons.getAns2());
        holder.ans3.setText(Cons.getAns3());
        holder.ans4.setText(Cons.getAns4());
        holder.getAdapterPosition();
        Log.e("actual ans is" , ""+Cons.getAns());
        Log.e("actual ans is1" , ""+Cons.getAns1());
        Log.e("actual ans is2" , ""+Cons.getAns2());
        Log.e("actual ans is3" , ""+Cons.getAns3());
        Log.e("actual ans is4" , ""+Cons.getAns4());
        if(Cons.getAns().equals(Cons.getAns1()))
        {
            holder.ansornot1.setText(Cons.getSolution());
            holder.ansornot2.setVisibility(View.GONE);
            holder.ansornot3.setVisibility(View.GONE);
            holder.ansornot4.setVisibility(View.GONE);
        }
        if(Cons.getAns().equals(Cons.getAns2()))
        {
            holder.ansornot2.setText(Cons.getSolution());
            holder.ansornot1.setVisibility(View.GONE);
            holder.ansornot3.setVisibility(View.GONE);
            holder.ansornot4.setVisibility(View.GONE);
        }
        if(Cons.getAns().equals(Cons.getAns3()))
        {
            holder.ansornot3.setText(Cons.getSolution());
            holder.ansornot1.setVisibility(View.GONE);
            holder.ansornot2.setVisibility(View.GONE);
            holder.ansornot4.setVisibility(View.GONE);
        }
        if(Cons.getAns().equals(Cons.getAns4()))
        {
            holder.ansornot4.setText(Cons.getSolution());
            holder.ansornot1.setVisibility(View.GONE);
            holder.ansornot2.setVisibility(View.GONE);
            holder.ansornot3.setVisibility(View.GONE);
        }
//        Log.e("actual ans is" , ""+Cons.getAns());
//        Log.e("actual ans is1" , ""+Cons.getAns1());
//        Log.e("actual ans is2" , ""+Cons.getAns2());
//        Log.e("actual ans is3" , ""+Cons.getAns3());
//        Log.e("actual ans is4" , ""+Cons.getAns4());
//        if(Cons.getAns().equals(Cons.getAns1()))
//        {
//            holder.ansornot1.setText(Cons.getSolution());
//            holder.ansornot2.setVisibility(View.GONE);
//            holder.ansornot3.setVisibility(View.GONE);
//            holder.ansornot4.setVisibility(View.GONE);
//        }
//        if(Cons.getAns().equals(Cons.getAns2()))
//        {
//            holder.ansornot2.setText(Cons.getSolution());
//            holder.ansornot1.setVisibility(View.GONE);
//            holder.ansornot3.setVisibility(View.GONE);
//            holder.ansornot4.setVisibility(View.GONE);
//        }
//        if(Cons.getAns().equals(Cons.getAns3()))
//        {
//            holder.ansornot3.setText(Cons.getSolution());
//            holder.ansornot1.setVisibility(View.GONE);
//            holder.ansornot2.setVisibility(View.GONE);
//            holder.ansornot4.setVisibility(View.GONE);
//        }
//        if(Cons.getAns().equals(Cons.getAns4()))
//        {
//            holder.ansornot4.setText(Cons.getSolution());
//            holder.ansornot1.setVisibility(View.GONE);
//            holder.ansornot2.setVisibility(View.GONE);
//            holder.ansornot3.setVisibility(View.GONE);
//        }

//        holder.name_of_company.setText(Cons.getname_of_company());
//        holder.go_btn.setText(Cons.getGo_btn());
//        holder.view_pro.setText(Cons.getView_pro());

//        holder.name_of_company.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(v.getContext(), Cons.getname_of_company(), Toast.LENGTH_LONG).show();
//                Log.e("Item is", "" + position);
//                Name_of_Company = Cons.getname_of_company();
//
//                show_the_profile(v, Name_of_Company);
//            }
//        });
//        holder.city_of_consultant.setText(Cons.getcity_of_consultant());
//        holder.code_of_consultant.setText(Cons.getcode_of_consultant());
//        holder.re_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //  Toast.makeText(v.getContext(), Cons.getname_of_company(), Toast.LENGTH_LONG).show();
//                Log.e("Item is", "" + position);
//                holder.go_btn.setVisibility(View.VISIBLE);
//                holder.view_pro.setVisibility(View.VISIBLE);
////                Name_of_Company = Cons.getname_of_company();
////                iD_of_cons = Cons.getcode_of_consultant();
//
//                // show_the_profile(v, Name_of_Company, iD_of_cons);
//            }
//        });
    }

//    }

    @Override
    public int getItemCount() {
        return Consult.size();
    }


    //
//    public Filter getFilter(){
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                String charString = constraint.toString();
//                if(charString.isEmpty()){
//                    Filtered_Consult = Consult;
//
//                }
//                else{
//                    Log.e("Query name ","is "+charString);
//                    List<Consultants> filtered = new ArrayList<>();
//                    for(Consultants row : Consult){
//                        if(row.getname_of_company().toString().contains(charString)){
//                            Log.e("Consult name ","is "+row.getname_of_company());
////                            filtered.add(row);
//                            filtered.add(row);
//                        }
//                    }
//                    Filtered_Consult = filtered;
//                }
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = Filtered_Consult;
//
//                return filterResults;
//            }
//
//
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//                Filtered_Consult = (List<Consultants>)  results.values;
//                notifyDataSetChanged();
//            }
//        };
//
//    }
    public void updateList(List<BooktheMarksData> list){
        Consult = list;
        notifyDataSetChanged();
    }


}
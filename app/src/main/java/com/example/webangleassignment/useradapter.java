package com.example.webangleassignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class useradapter extends RecyclerView.Adapter<useradapter.userholder> implements Filterable{
    MainActivity mainActivity;
    List<usermodel> alluserlist;
    List<usermodel>alluserlistfull;

    public useradapter(MainActivity mainActivity, List<usermodel> alluserlist) {
        this.mainActivity=mainActivity;
        this.alluserlist= alluserlist;
        alluserlistfull=new ArrayList<>(alluserlist);


    }

    @NonNull
    @Override
    public userholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new userholder(LayoutInflater.from(mainActivity).inflate(R.layout.itemuser,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull userholder holder, int position) {
        holder.itemTxt.setText(alluserlist.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return alluserlist.size();
    }

    @Override
    public Filter getFilter() {
        return (Filter) alllist;
    }
private Filter alllist =new Filter() {
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        List<usermodel> filterdlist=new ArrayList<>();
        if(constraint==null || constraint.length()==0){
            filterdlist.addAll(alluserlistfull);
        }else{
            String filterpattern=constraint.toString().toLowerCase().trim();
            for(usermodel model:alluserlistfull){
                if(model.getTitle().toLowerCase().contains(filterpattern)){
                    filterdlist.add(model);
                }
            }
        }
        FilterResults results=new FilterResults();
        results.values=filterdlist;
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
    alluserlist.clear();
    alluserlist.addAll((List)results.values);
    notifyDataSetChanged();
    }
};

    class userholder extends RecyclerView.ViewHolder {
        TextView itemTxt;
        public userholder(@NonNull View itemView) {
            super(itemView);
            itemTxt=itemView.findViewById(R.id.item);
        }
    }
}

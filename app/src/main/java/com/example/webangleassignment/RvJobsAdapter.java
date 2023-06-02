package com.example.webangleassignment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webangleassignment.Network.JobResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RvJobsAdapter extends RecyclerView.Adapter<RvJobsAdapter.RVHolder>  {
    List<JobResult> jobResultList;

    Context context;
    public RvJobsAdapter(Context context, List<JobResult> jobResultList){
        this.context=context;
        this.jobResultList=jobResultList;
           // this.jobsearchall=new ArrayList<>(jobResultList);
    }
    @NonNull
    @Override
    public RVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_job,parent,false);
        return new RVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvJobsAdapter.RVHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.role.setText(jobResultList.get(position).getRole());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,ActivityJob.class);
                int jobid=jobResultList.get(position).getId();
                intent.putExtra("jobIdB",""+jobid);
                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return jobResultList.size();
    }


    public class RVHolder extends RecyclerView.ViewHolder{
        TextView role;
        CardView card;
        public RVHolder(@NonNull View itemView){
            super(itemView);
            card=itemView.findViewById(R.id.card);
            role=itemView.findViewById(R.id.role);
        }
    }
}

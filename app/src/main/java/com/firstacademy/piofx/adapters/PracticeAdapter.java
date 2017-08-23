package com.firstacademy.piofx.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.firstacademy.piofx.R;
import com.firstacademy.piofx.activities.PracticeQuiz;
import com.firstacademy.piofx.fragments.Practice;
import com.firstacademy.piofx.fragments.Quiz;

/**
 * Created by ananth on 14/8/17.
 */

public class PracticeAdapter extends RecyclerView.Adapter<PracticeAdapter.ViewHolder> implements
        View.OnClickListener{
    private Context context;

    public PracticeAdapter(Context context){
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_practice,
                parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.layout.setOnClickListener(this);
        holder.layout.setTag(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }

    @Override
    public void onClick(View view) {
        final ViewHolder holder=(ViewHolder)view.getTag();
        final int position=holder.getAdapterPosition();
        switch (view.getId()){
            case R.id.adapter_practice_layout:
                Intent practiceAdapter=new Intent(context, PracticeQuiz.class);
                practiceAdapter.putExtra("intent","practiceAdapter");
                context.startActivity(practiceAdapter);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout layout;
        public ViewHolder(View itemView) {
            super(itemView);
            layout=(LinearLayout)itemView.findViewById(R.id.adapter_practice_layout);
        }
    }
}

package com.firstacademy.piofx.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firstacademy.piofx.R;
import com.firstacademy.piofx.activities.PracticeQuiz;
import com.firstacademy.piofx.fragments.Practice;
import com.firstacademy.piofx.fragments.Quiz;
import com.firstacademy.piofx.models.PracticeModel;
import com.firstacademy.piofx.utils.Constants;

import java.util.ArrayList;

/**
 * Created by ananth on 14/8/17.
 */

public class PracticeAdapter extends RecyclerView.Adapter<PracticeAdapter.ViewHolder> implements
        View.OnClickListener{
    private Context context;
    private ArrayList<PracticeModel> data;

    public PracticeAdapter(Context context,ArrayList<PracticeModel> data){
        this.context=context;
        this.data=data;
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
        holder.tvLevel.setText(String.valueOf(data.get(position).getLevelText()));
        if(data.get(position).getId()==1){
            holder.tvLevel.setTextColor(Constants.backGroundColor);
            holder.progressBar.getProgressDrawable().setColorFilter(Constants.backGroundColor, PorterDuff.Mode.SRC_IN);
//            holder.progressBar.setBackgroundColor(Constants.backGroundColor);
            /*Drawable mDrawable = context.getResources().getDrawable(R.drawable.star_grey);
            mDrawable.setColorFilter(new PorterDuffColorFilter(
                    Constants.backGroundColor,
                    PorterDuff.Mode.MULTIPLY));*/
            holder.ivStar.setColorFilter(Constants.backGroundColor);
        }
//        holder.progressBar.setBackgroundColor(Constants.backGroundColor);

    }

    @Override
    public int getItemCount() {
        return data.size();
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
        private ImageView ivStar;
        private TextView tvLevel;
        private ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            layout=(LinearLayout)itemView.findViewById(R.id.adapter_practice_layout);
            ivStar=(ImageView) itemView.findViewById(R.id.adapter_practice_star_image);
            tvLevel=(TextView) itemView.findViewById(R.id.adapter_practice_text);
            progressBar=(ProgressBar) itemView.findViewById(R.id.adapter_practice_progressbar);

        }
    }
}

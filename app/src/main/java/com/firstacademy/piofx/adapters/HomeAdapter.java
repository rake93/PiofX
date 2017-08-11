package com.firstacademy.piofx.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firstacademy.piofx.R;
import com.firstacademy.piofx.activities.PracticeQuiz;
import com.firstacademy.piofx.models.HomeModel;
import com.firstacademy.piofx.utils.Constants;

import java.util.ArrayList;

/**
 * Created by ananth on 2/8/17.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> implements View.OnClickListener{
    private Context context;
    private ArrayList<HomeModel> dataSet;

    public HomeAdapter(Context context,ArrayList<HomeModel> dataSet){
        this.context=context;
        this.dataSet=dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home,
                parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.practice.setOnClickListener(this);
        holder.practice.setTag(holder);
        holder.quiz.setOnClickListener(this);
        holder.quiz.setTag(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.imageView.setImageResource(dataSet.get(position).getImage());
        holder.headding.setText(dataSet.get(position).getVocabularyHeading());
        holder.desc.setText(dataSet.get(position).getVocabularyDesc());

        if(dataSet.get(position).getId()==1){
            holder.textBgLayout.setBackgroundResource(R.color.card1_text_bkg_color);
            holder.practice.setTextColor(context.getResources().getColor(R.color.black));
            holder.quiz.setTextColor(context.getResources().getColor(R.color.white));
        }else if(dataSet.get(position).getId()==2){
            holder.textBgLayout.setBackgroundResource(R.color.card2_text_bkg_color);
            holder.practice.setTextColor(context.getResources().getColor(R.color.darker_gray));
            holder.quiz.setTextColor(context.getResources().getColor(R.color.white));
        }else if(dataSet.get(position).getId()==3){
            holder.textBgLayout.setBackgroundResource(R.color.card3_text_bkg_color);
            holder.practice.setTextColor(context.getResources().getColor(R.color.gold_color));
            holder.quiz.setTextColor(context.getResources().getColor(R.color.dark_sky));
            holder.progressBar.setBackgroundResource(R.color.progress_starting_color);
        }else if(dataSet.get(position).getId()==4){
            holder.textBgLayout.setBackgroundResource(R.color.card4_text_bkg_color);
            holder.practice.setTextColor(context.getResources().getColor(R.color.white));
            holder.quiz.setTextColor(context.getResources().getColor(R.color.gold_color));
            holder.progressBar.setBackgroundResource(R.color.progress_middle_color);
        }else if(dataSet.get(position).getId()==5){
            holder.textBgLayout.setBackgroundResource(R.color.card4_text_bkg_color);
            holder.practice.setTextColor(context.getResources().getColor(R.color.white));
            holder.quiz.setTextColor(context.getResources().getColor(R.color.gold_color));
            holder.progressBar.setBackgroundResource(R.color.progress_ending_color);
        }else if(dataSet.get(position).getId()==6){
            holder.textBgLayout.setBackgroundResource(R.color.card4_text_bkg_color);
            holder.practice.setTextColor(context.getResources().getColor(R.color.white));
            holder.quiz.setTextColor(context.getResources().getColor(R.color.gold_color));
        }else if(dataSet.get(position).getId()==7){
            holder.textBgLayout.setBackgroundResource(R.color.card4_text_bkg_color);
            holder.practice.setTextColor(context.getResources().getColor(R.color.white));
            holder.quiz.setTextColor(context.getResources().getColor(R.color.gold_color));
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public void onClick(View view) {
        final ViewHolder holder=(ViewHolder)view.getTag();
        final int position=holder.getAdapterPosition();
        int color=0;
        if(dataSet.get(position).getId()==1){
            color=context.getResources().getColor(R.color.card1_text_bkg_color);
        }else if(dataSet.get(position).getId()==2){
            color=context.getResources().getColor(R.color.card2_text_bkg_color);
        }else if(dataSet.get(position).getId()==3){
            color=context.getResources().getColor(R.color.card3_text_bkg_color);
        }else if(dataSet.get(position).getId()==4){
            color=context.getResources().getColor(R.color.card4_text_bkg_color);
        }else if(dataSet.get(position).getId()==5){
            color=context.getResources().getColor(R.color.card4_text_bkg_color);
        }else if(dataSet.get(position).getId()==6){
            color=context.getResources().getColor(R.color.card4_text_bkg_color);
        }else if(dataSet.get(position).getId()==7){
            color=context.getResources().getColor(R.color.card4_text_bkg_color);
        }

        switch (view.getId()){
            case R.id.adapter_home_practice:
                Constants.vocabularyTitle=dataSet.get(position).getVocabularyHeading();
                Constants.image=dataSet.get(position).getImage();
                Constants.backGroundColor=color;
                Intent practiceIntent=new Intent(context, PracticeQuiz.class);
                practiceIntent.putExtra("intent","practice");
                context.startActivity(practiceIntent);
                break;
            case R.id.adapter_home_quiz:
                Constants.vocabularyTitle=dataSet.get(position).getVocabularyHeading();
                Constants.image=dataSet.get(position).getImage();
                Constants.backGroundColor=color;
                Intent quizIntent=new Intent(context, PracticeQuiz.class);
                quizIntent.putExtra("intent","quiz");
                context.startActivity(quizIntent);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView headding,desc,practice,quiz;
        private LinearLayout textBgLayout;
        private ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.adapter_home_image);
            headding=(TextView)itemView.findViewById(R.id.adapter_home_heading);
            desc=(TextView)itemView.findViewById(R.id.adapter_home_desc);
            textBgLayout=(LinearLayout)itemView.findViewById(R.id.adapter_home_text_layout_bg);
            progressBar=(ProgressBar) itemView.findViewById(R.id.adapter_home_progressbar);
            practice=(TextView)itemView.findViewById(R.id.adapter_home_practice);
            quiz=(TextView)itemView.findViewById(R.id.adapter_home_quiz);

        }
    }
}

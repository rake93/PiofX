package com.firstacademy.piofx.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.firstacademy.piofx.models.HomeModel;
import com.firstacademy.piofx.utils.Constants;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;

/**
 * Created by ananth on 2/8/17.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> implements View.OnClickListener {

    int levelId;
    int overAllPracticeCount;
    private Context context;
    private ArrayList<HomeModel> dataSet;

    public HomeAdapter(Context context, ArrayList<HomeModel> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home,
                parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.practice.setOnClickListener(this);
        holder.practice.setTag(holder);
        holder.quiz.setOnClickListener(this);
        holder.quiz.setTag(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        levelId = dataSet.get(position).getId();
        overAllPracticeCount = CollectionUtils.isEmpty(dataSet.get(position).getPracticeCompletedList()) ? 0 : dataSet.get(position).getPracticeCompletedList().size();
        Log.i(" ****"," overAllPracticeCount ~~~~~~~~~~~~> " + overAllPracticeCount);
        holder.imageView.setImageResource(dataSet.get(position).getImage());
        holder.headding.setText(dataSet.get(position).getVocabularyHeading());
        holder.desc.setText(dataSet.get(position).getVocabularyDesc());

        if (levelId == 1) {
            holder.imageBg.setBackgroundResource(R.color.colorMediumTurquoise);
            if (overAllPracticeCount == 0) {
                holder.ribbonImage.setImageResource(R.drawable.grey_ribbon);
                holder.progressBar.setBackgroundResource(R.color.darker_gray);
            } else
                updateProgressBar(holder, position);
        }
        if (levelId == 2) {
            holder.imageBg.setBackgroundResource(R.color.card2_text_bkg_color);
            if (overAllPracticeCount == 0) {
                holder.ribbonImage.setImageResource(R.drawable.grey_ribbon);
                holder.progressBar.setBackgroundResource(R.color.darker_gray);
            } else
                updateProgressBar(holder, position);
        }
        if (levelId == 3) {
            holder.imageBg.setBackgroundResource(R.color.card3_image_bg_color);
            if (overAllPracticeCount == 0) {
                holder.ribbonImage.setImageResource(R.drawable.grey_ribbon);
                holder.progressBar.setBackgroundResource(R.color.darker_gray);
            } else
                updateProgressBar(holder, position);
        }
        if (levelId == 4) {
            holder.imageBg.setBackgroundResource(R.color.green);
            if (overAllPracticeCount == 0) {
                holder.ribbonImage.setImageResource(R.drawable.grey_ribbon);
                holder.progressBar.setBackgroundResource(R.color.darker_gray);
            } else
                updateProgressBar(holder, position);
        }
        if (levelId == 5) {
            holder.imageBg.setBackgroundResource(R.color.card2_text_bkg_color);
            if (overAllPracticeCount == 0) {
                holder.ribbonImage.setImageResource(R.drawable.grey_ribbon);
                holder.progressBar.setBackgroundResource(R.color.darker_gray);
            } else
                updateProgressBar(holder, position);
        }
        if (levelId == 6) {
            holder.imageBg.setBackgroundResource(R.color.card3_image_bg_color);
            if (overAllPracticeCount == 0) {
                holder.ribbonImage.setImageResource(R.drawable.grey_ribbon);
                holder.progressBar.setBackgroundResource(R.color.darker_gray);
            } else
                updateProgressBar(holder, position);
        }
        if (levelId == 7) {
            holder.imageBg.setBackgroundResource(R.color.green);
            if (overAllPracticeCount == 0) {
                holder.ribbonImage.setImageResource(R.drawable.grey_ribbon);
                holder.progressBar.setBackgroundResource(R.color.darker_gray);
            } else
                updateProgressBar(holder, position);
        }
        if (dataSet.get(position).getId() == 8) {
            holder.imageBg.setBackgroundResource(R.color.colorMediumTurquoise);
            holder.ribbonImage.setImageResource(R.drawable.grey_ribbon);
            holder.quiz.setText(context.getResources().getString(R.string.action_meet_the_team));
            holder.practice.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public void onClick(View view) {
        final ViewHolder holder = (ViewHolder) view.getTag();
        final int position = holder.getAdapterPosition();
        int color = 0;
        if (dataSet.get(position).getId() == 1) {
            color = context.getResources().getColor(R.color.colorMediumTurquoise);
        } else if (dataSet.get(position).getId() == 2) {
            color = context.getResources().getColor(R.color.card2_text_bkg_color);
        } else if (dataSet.get(position).getId() == 3) {
            color = context.getResources().getColor(R.color.card3_image_bg_color);
        } else if (dataSet.get(position).getId() == 4) {
            color = context.getResources().getColor(R.color.green);
        } else if (dataSet.get(position).getId() == 5) {
            color = context.getResources().getColor(R.color.card2_text_bkg_color);
        } else if (dataSet.get(position).getId() == 6) {
            color = context.getResources().getColor(R.color.card3_image_bg_color);
        } else if (dataSet.get(position).getId() == 7) {
            color = context.getResources().getColor(R.color.green);
        } else if (dataSet.get(position).getId() == 8) {
            color = context.getResources().getColor(R.color.colorMediumTurquoise);
        }
        Constants.vocabularyTitle = dataSet.get(position).getVocabularyHeading();
        Constants.image = dataSet.get(position).getImage();
        Constants.backGroundColor = color;
        switch (view.getId()) {
            case R.id.adapter_home_practice:
                Intent practiceIntent = new Intent(context, PracticeQuiz.class);
                practiceIntent.putExtra("intent", "practice");
                context.startActivity(practiceIntent);
                break;
            case R.id.adapter_home_quiz:
                Constants.vocabularyTitle = dataSet.get(position).getVocabularyHeading();
                Constants.image = dataSet.get(position).getImage();
                Constants.backGroundColor = color;
                Constants.homeAdapterId = dataSet.get(position).getId();
                Intent quizIntent = new Intent(context, PracticeQuiz.class);
                quizIntent.putExtra("intent", "quiz");
                context.startActivity(quizIntent);
        }
    }

    private void updateProgressBar(ViewHolder holder, int position) {
        Resources res = holder.itemView.getContext().getResources();
        Rect bounds = holder.progressBar.getProgressDrawable().getBounds();

        if (CollectionUtils.isNotEmpty(dataSet.get(position).getPracticeCompletedList())) {
            int percent = (int) ((overAllPracticeCount * 100) / Constants.OVERALL_LIMIT);
            Log.i(" ****"," percent ~~~~~~~~~~~~> " + percent);
            if (percent < Constants.PROGRESS_START_LIMIT) {
                holder.progressBar.getProgressDrawable().setBounds(bounds);
                holder.progressBar.setProgress(percent);
            }
            if (percent >= Constants.PROGRESS_START_LIMIT && percent < Constants.PROGRESS_CENTER_LIMIT) {
                holder.progressBar.getProgressDrawable().setBounds(bounds);
                holder.progressBar.setProgress(percent);
            }
            if (percent >= Constants.PROGRESS_CENTER_LIMIT && percent <= Constants.PROGRESS_END_LIMIT) {
                holder.progressBar.getProgressDrawable().setBounds(bounds);
                holder.progressBar.setProgress(percent);
                holder.ribbonImage.setImageResource(R.drawable.red_ribbon);
            }

        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView, ribbonImage;
        private RelativeLayout imageBg;
        private TextView headding, desc, practice, quiz;
        private LinearLayout textBgLayout;
        private ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.adapter_home_image);
            ribbonImage = (ImageView) itemView.findViewById(R.id.adapter_home_ivribbon);
            imageBg = (RelativeLayout) itemView.findViewById(R.id.adapter_home_image_bg);
            headding = (TextView) itemView.findViewById(R.id.adapter_home_heading);
            desc = (TextView) itemView.findViewById(R.id.adapter_home_desc);
            progressBar = (ProgressBar) itemView.findViewById(R.id.adapter_home_progressbar);
            practice = (TextView) itemView.findViewById(R.id.adapter_home_practice);
            quiz = (TextView) itemView.findViewById(R.id.adapter_home_quiz);
        }
    }
}

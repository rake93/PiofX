package com.firstacademy.piofx.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firstacademy.piofx.PiofXApp;
import com.firstacademy.piofx.R;
import com.firstacademy.piofx.activities.PracticeQuiz;
import com.firstacademy.piofx.models.PracticeModel;
import com.firstacademy.piofx.utils.Constants;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;

/**
 * Created by ananth on 14/8/17.
 */

public class PracticeAdapter extends RecyclerView.Adapter<PracticeAdapter.ViewHolder> implements
        View.OnClickListener {
    private Context context;
    private ArrayList<PracticeModel> data;
    int practiceLevelId;
    int level1ProgressCount, level2ProgressCount, level3ProgressCount,level4ProgressCount,
            level5ProgressCount,level6ProgressCount,level7ProgressCount,level8ProgressCount;
    int levelId;
    int levelProgressCount;

    public PracticeAdapter(Context context, ArrayList<PracticeModel> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_practice,
                parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.layout.setOnClickListener(this);
        holder.layout.setTag(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvLevel.setText(String.valueOf(data.get(position).getLevelText()));
        levelId = data.get(position).getId();
        levelProgressCount = CollectionUtils.isEmpty(data.get(position).getPracticeCompletedList()) ? 0 : data.get(position).getPracticeCompletedList().size();
        if (levelId == 1) {
            if (levelProgressCount == 0) {
                heighlightNextLevelBackground(holder);
            } else
                updateProgressBar(holder, position);
            level1ProgressCount = levelProgressCount;
        }
        if (levelId == 2) {
            if (level1ProgressCount > 0 && levelProgressCount ==0) {
                heighlightNextLevelBackground(holder);
            } else updateProgressBar(holder, position);
            level2ProgressCount = levelProgressCount;
        }
        if (levelId == 3) {
            if (level2ProgressCount > 0 && levelProgressCount ==0) {
                heighlightNextLevelBackground(holder);
            } else
                updateProgressBar(holder, position);
            level3ProgressCount = levelProgressCount;
        }
        if (levelId == 4) {
            if (level3ProgressCount > 0 && levelProgressCount ==0) {
                heighlightNextLevelBackground(holder);
            } else updateProgressBar(holder, position);
            level4ProgressCount = levelProgressCount;
        }
        if (levelId == 5) {
            if (level4ProgressCount > 0 && levelProgressCount ==0) {
                heighlightNextLevelBackground(holder);
            } else updateProgressBar(holder, position);
            level5ProgressCount = levelProgressCount;
        }
        if (levelId == 6) {
            if (level5ProgressCount > 0 && levelProgressCount ==0) {
                heighlightNextLevelBackground(holder);
            } else updateProgressBar(holder, position);
            level6ProgressCount = levelProgressCount;
        }
        if (levelId == 7) {
            if (level6ProgressCount > 0 && levelProgressCount ==0) {
                heighlightNextLevelBackground(holder);
            } else updateProgressBar(holder, position);
            level7ProgressCount = levelProgressCount;
        }
        if (levelId == 8) {
            if (level7ProgressCount > 0 && levelProgressCount ==0) {
                heighlightNextLevelBackground(holder);
            } else updateProgressBar(holder, position);
            level8ProgressCount = levelProgressCount;
        }

        if (data.get(position).getId() > 8) {
            holder.tvLevel.setEnabled(false);
            holder.layout.setEnabled(false);
            holder.progressBar.getProgressDrawable().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);
        }
    }

    private void heighlightNextLevelBackground(ViewHolder holder) {
        Resources resources = holder.itemView.getResources();
        holder.tvLevel.setTextColor(resources.getColor(R.color.lighter_gray));
        holder.progressBar.getProgressDrawable().setColorFilter(resources.getColor(R.color.lighter_gray), PorterDuff.Mode.SRC_IN);
        holder.ivStar.setColorFilter(resources.getColor(R.color.lighter_gray));
    }

    private void updateProgressBar(ViewHolder holder, int position) {
        Resources res = holder.itemView.getContext().getResources();
        Rect bounds = holder.progressBar.getProgressDrawable().getBounds();

        if (CollectionUtils.isNotEmpty(data.get(position).getPracticeCompletedList())) {
            int percent = (int) ((levelProgressCount*100 )/ Constants.LIMIT);
            if (percent < Constants.PROGRESS_START_LIMIT) {
                holder.tvLevel.setTextColor(res.getColor(R.color.progress_starting_color));
                holder.progressBar.getProgressDrawable().setBounds(bounds);
                holder.progressBar.setProgress(percent);
                holder.ivStar.setColorFilter(res.getColor(R.color.progress_starting_color));
            }
            if (percent >= Constants.PROGRESS_START_LIMIT && percent < Constants.PROGRESS_CENTER_LIMIT) {
                holder.tvLevel.setTextColor(res.getColor(R.color.progress_middle_color));
                holder.progressBar.getProgressDrawable().setBounds(bounds);
                holder.progressBar.setProgress(percent);
                holder.ivStar.setColorFilter(res.getColor(R.color.progress_middle_color));
            }
            if (percent >= Constants.PROGRESS_CENTER_LIMIT && percent <= Constants.PROGRESS_END_LIMIT) {
                holder.tvLevel.setTextColor(res.getColor(R.color.progress_ending_color));
                holder.progressBar.getProgressDrawable().setBounds(bounds);
                holder.progressBar.setProgress(percent);
                holder.ivStar.setColorFilter(res.getColor(R.color.progress_ending_color));
            }

        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View view) {
        final ViewHolder holder = (ViewHolder) view.getTag();
        final int position = holder.getAdapterPosition();
        practiceLevelId = data.get(position).getId();//String.valueOf(data.get(position).getLevelText());
        switch (view.getId()) {
            case R.id.adapter_practice_layout:
                Intent practiceAdapter = new Intent(context, PracticeQuiz.class);
                practiceAdapter.putExtra("intent", "practiceAdapter");
                practiceAdapter.putExtra("practiceLevelId", practiceLevelId);
                context.startActivity(practiceAdapter);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private ImageView ivStar;
        private TextView tvLevel;
        private ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = (LinearLayout) itemView.findViewById(R.id.adapter_practice_layout);
            ivStar = (ImageView) itemView.findViewById(R.id.adapter_practice_star_image);
            tvLevel = (TextView) itemView.findViewById(R.id.adapter_practice_text);
            progressBar = (ProgressBar) itemView.findViewById(R.id.adapter_practice_progressbar);

        }
    }
}

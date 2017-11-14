package com.firstacademy.piofx.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firstacademy.piofx.PiofXApp;
import com.firstacademy.piofx.R;
import com.firstacademy.piofx.activities.PracticeQuiz;
import com.firstacademy.piofx.adapters.HomeAdapter;
import com.firstacademy.piofx.adapters.PracticeAdapter;
import com.firstacademy.piofx.data.db.model.DaoSession;
import com.firstacademy.piofx.data.db.model.Question;
import com.firstacademy.piofx.data.db.repository.QuestionRepository;
import com.firstacademy.piofx.models.HomeModel;
import com.firstacademy.piofx.models.PracticeModel;
import com.firstacademy.piofx.utils.Constants;

import java.util.ArrayList;
import java.util.List;


public class Practice extends Fragment implements View.OnClickListener{
    private LinearLayout llBackLayout;
    private TextView tvVocabularyTitle;
    private ImageView ivImage;
    private RelativeLayout rlTopLayout;
    private RecyclerView practiceRecycler;

    private static ArrayList<PracticeModel> data;
    Integer[] idArray,texArray;

    private DaoSession daoSession;
    private QuestionRepository mQuestionRepository;
    private List<Question> practiceSkilledList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_practice, container, false);
        initializeViews(view);
        initializeClickListeners();

        daoSession = ((PiofXApp) getActivity().getApplication()).getDaoSession();
        mQuestionRepository = new QuestionRepository(daoSession);

        setText();
        setGridView();
        return view;
    }

    private void initializeViews(View view) {
        tvVocabularyTitle=(TextView)view.findViewById(R.id.practice_quiz_vocabulary_heading);

        ivImage=(ImageView) view.findViewById(R.id.practice_quiz_image);

        llBackLayout=(LinearLayout) view.findViewById(R.id.practice_quiz_back_layout);

        rlTopLayout=(RelativeLayout)view.findViewById(R.id.practice_quiz_top_layout);

        practiceRecycler=(RecyclerView)view.findViewById(R.id.practice_recyclerview);

        data=new ArrayList<PracticeModel>();
        idArray= new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,15};
        texArray= new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,15};

    }

    private void initializeClickListeners() {
        llBackLayout.setOnClickListener(this);
    }

    private void setText() {
        tvVocabularyTitle.setText(Constants.vocabularyTitle);
        ivImage.setImageResource(Constants.image);
        rlTopLayout.setBackgroundColor(Constants.backGroundColor);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.practice_quiz_back_layout:
                getActivity().finish();
                break;
        }
    }

    private void setGridView() {
        for (int i=0;i<idArray.length;i++){
            //for now I'm getting Data from DB for 8-Levels, as we have enabled only 8 practice levels
            if (i<8)
                practiceSkilledList = mQuestionRepository.getPracticedAtLevelCount(Constants.getQuestionCategoryByKey(Constants.vocabularyTitle), Constants.getQuestionSetsByKey(i+1));
            data.add(new PracticeModel(idArray[i],texArray[i],practiceSkilledList));
        }
        PracticeAdapter practiceAdapter = new PracticeAdapter(getActivity(),data);
        practiceAdapter.notifyDataSetChanged();
        practiceRecycler.setHasFixedSize(true);
        practiceRecycler.setLayoutManager(new GridLayoutManager(getActivity(),3));
        practiceRecycler.setAdapter(practiceAdapter);
    }
}

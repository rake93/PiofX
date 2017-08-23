package com.firstacademy.piofx.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firstacademy.piofx.R;
import com.firstacademy.piofx.adapters.HomeAdapter;
import com.firstacademy.piofx.adapters.PracticeAdapter;
import com.firstacademy.piofx.utils.Constants;


public class Practice extends Fragment implements View.OnClickListener{
    private LinearLayout llBackLayout;
    private TextView tvVocabularyTitle;
    private ImageView ivImage;
    private RelativeLayout rlTopLayout;
    private RecyclerView practiceRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_practice, container, false);
        initializeViews(view);
        initializeClickListeners();
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
        practiceRecycler.setHasFixedSize(true);
        practiceRecycler.setLayoutManager(new GridLayoutManager(getActivity(),3));
        practiceRecycler.setAdapter(new PracticeAdapter(getActivity()));
    }
}

package com.firstacademy.piofx.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firstacademy.piofx.R;
import com.firstacademy.piofx.activities.Silver;
import com.firstacademy.piofx.utils.Constants;
import com.firstacademy.piofx.utils.FlipAnimation;
import com.firstacademy.piofx.utils.Fonts;


public class Quiz extends Fragment implements View.OnClickListener {
    private TextView tvVocabularyTitle,tvQuestion,tvPartsOfSpeech,tvOptionA,tvOptionB,tvOptionC,
            tvOptionD,tvBottomText,tvBingoOOpsie,tvBackQuestion,tvBackPartsOfSpeech,tvBackMeaning,
            tvBackExample1,tvBackExample2;
    private ImageView ivImage;
    private LinearLayout llBackLayout,llALayout,llBLayout,llCLayout,llDLayout,llFrontCardLayout,
                llBackCardLayout,llRootLayout,llBackTextLayout;
    private RelativeLayout rlBottomLayout,rlTopLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_quiz, container, false);
        initializeViews(view);
        initializeClickListeners();
        setText();
        return view;
    }

    private void initializeClickListeners() {
        llBackLayout.setOnClickListener(this);
        rlBottomLayout.setOnClickListener(this);
        llALayout.setOnClickListener(this);
        llBLayout.setOnClickListener(this);
        llCLayout.setOnClickListener(this);
        llDLayout.setOnClickListener(this);
    }

    private void initializeViews(View view) {
        tvVocabularyTitle=(TextView)view.findViewById(R.id.fquiz_vocabulary_heading);
        tvQuestion=(TextView)view.findViewById(R.id.fquiz_question);
        tvPartsOfSpeech=(TextView)view.findViewById(R.id.fquiz_parts_of_speech);
        tvOptionA=(TextView)view.findViewById(R.id.fquiz_option_a);
        tvOptionB=(TextView)view.findViewById(R.id.fquiz_option_b);
        tvOptionC=(TextView)view.findViewById(R.id.fquiz_option_c);
        tvOptionD=(TextView)view.findViewById(R.id.fquiz_option_d);
        tvBottomText=(TextView)view.findViewById(R.id.fquiz_bottom_layout_text);
        tvBingoOOpsie=(TextView)view.findViewById(R.id.fquiz_oopsie_bingo);
        tvBackQuestion=(TextView)view.findViewById(R.id.fquiz_back_question);
        tvBackPartsOfSpeech=(TextView)view.findViewById(R.id.fquiz_back_parts_of_speech);
        tvBackMeaning=(TextView)view.findViewById(R.id.fquiz_back_meaning);
        tvBackExample1=(TextView)view.findViewById(R.id.fquiz_back_example1);
        tvBackExample2=(TextView)view.findViewById(R.id.fquiz_back_example2);

        ivImage=(ImageView) view.findViewById(R.id.fquiz_image);

        llBackLayout=(LinearLayout) view.findViewById(R.id.fquiz_back_layout);
        llALayout=(LinearLayout) view.findViewById(R.id.fquiz_option_a_layout);
        llBLayout=(LinearLayout) view.findViewById(R.id.fquiz_option_b_layout);
        llCLayout=(LinearLayout) view.findViewById(R.id.fquiz_option_c_layout);
        llDLayout=(LinearLayout) view.findViewById(R.id.fquiz_option_d_layout);
        llFrontCardLayout=(LinearLayout) view.findViewById(R.id.fquiz_front_card_layout);
        llBackCardLayout=(LinearLayout) view.findViewById(R.id.fquiz_back_card_layout);
        llRootLayout=(LinearLayout) view.findViewById(R.id.fquiz_rrot_layout);
        llBackTextLayout=(LinearLayout) view.findViewById(R.id.fquiz_back_text_layout);

        rlBottomLayout=(RelativeLayout)view.findViewById(R.id.fquiz_bottom_layout);
        rlTopLayout=(RelativeLayout)view.findViewById(R.id.fquiz_top_layout);

    }

    private void setText() {
        tvVocabularyTitle.setText(Constants.vocabularyTitle);
        ivImage.setImageResource(Constants.image);
        rlTopLayout.setBackgroundColor(Constants.backGroundColor);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fquiz_back_layout:
                getActivity().finish();
                break;
            case R.id.fquiz_bottom_layout:
                if(tvBottomText.getText().toString().equals(getResources().getString(R.string.skip))){
                    startActivity(new Intent(getActivity(), Silver.class));
                }else if(tvBottomText.getText().toString().equals(getResources().getString(R.string.next))){
                    tvBottomText.setText(getResources().getString(R.string.skip));
                    rlBottomLayout.setBackgroundColor(getResources().getColor(R.color.colorMediumTurquoise));
                    flipToQuestion();
                }else if(tvBottomText.getText().toString().equals(getResources().getString(R.string.got_it))){
                    tvBottomText.setText(getResources().getString(R.string.skip));
                    rlBottomLayout.setBackgroundColor(getResources().getColor(R.color.colorMediumTurquoise));
                    flipToQuestion();
                }
                break;
            case R.id.fquiz_option_a_layout:
                getAnswer(tvOptionA.getText().toString());
                break;
            case R.id.fquiz_option_b_layout:
                getAnswer(tvOptionB.getText().toString());
                break;
            case R.id.fquiz_option_c_layout:
                getAnswer(tvOptionC.getText().toString());
                break;
            case R.id.fquiz_option_d_layout:
                getAnswer(tvOptionD.getText().toString());
                break;
        }
    }


    private void getAnswer(String option) {
        if(option.equals("influence")){
            tvBingoOOpsie.setText("Bingo!");
            tvBingoOOpsie.setTextColor(getResources().getColor(R.color.agri_green));
            llBackTextLayout.setBackgroundColor(getResources().getColor(R.color.answer_right_bg_color));
            tvBottomText.setText(getResources().getString(R.string.next));
            rlBottomLayout.setBackgroundColor(getResources().getColor(R.color.green));
        }else {
            tvBingoOOpsie.setText("Oopsie!");
            tvBingoOOpsie.setTextColor(getResources().getColor(R.color.oopsie_color));
            llBackTextLayout.setBackgroundColor(getResources().getColor(R.color.answer_wrong_bg_color));
            tvBottomText.setText(getResources().getString(R.string.got_it));
            rlBottomLayout.setBackgroundColor(getResources().getColor(R.color.green));
        }
        flipCard();
    }

    private void flipCard() {
        FlipAnimation flipAnimation = new FlipAnimation(llFrontCardLayout, llBackCardLayout);

        if (llFrontCardLayout.getVisibility() == View.GONE)
        {
            flipAnimation.reverse();
        }
        llRootLayout.startAnimation(flipAnimation);
    }

    private void flipToQuestion() {
        FlipAnimation flipAnimation = new FlipAnimation(llBackCardLayout, llFrontCardLayout);

        if (llBackCardLayout.getVisibility() == View.GONE)
        {
            flipAnimation.reverse();
        }
        llRootLayout.startAnimation(flipAnimation);
    }
}

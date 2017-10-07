package com.firstacademy.piofx.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firstacademy.piofx.R;
import com.firstacademy.piofx.activities.Home;
import com.firstacademy.piofx.activities.PracticeQuiz;
import com.firstacademy.piofx.utils.Constants;
import com.firstacademy.piofx.utils.FlipAnimation;
import com.firstacademy.piofx.utils.Fonts;


public class Quiz extends Fragment implements View.OnClickListener {
    private TextView tvVocabularyTitle,tvQuestion,tvPartsOfSpeech,tvOptionA,tvOptionB,tvOptionC,
            tvOptionD,tvBottomText,tvBingoOOpsie,tvBackQuestion,tvBackPartsOfSpeech,tvBackMeaning,
            tvBackExample1,tvBackExample2;
    private ImageView ivImage,teamPersonalImage;
    private LinearLayout llBackLayout,llALayout,llBLayout,llCLayout,llDLayout,llFrontCardLayout,
                llBackCardLayout,llRootLayout,llBackTextLayout;
    private RelativeLayout rlBottomLayout,rlTopLayout,teamPersonalImageLayout;

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

    private void initializeViews(View view) {
        tvVocabularyTitle=(TextView)view.findViewById(R.id.practice_quiz_vocabulary_heading);
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

        ivImage=(ImageView) view.findViewById(R.id.practice_quiz_image);
        teamPersonalImage=(ImageView) view.findViewById(R.id.fquiz_team_image);

        llBackLayout=(LinearLayout) view.findViewById(R.id.practice_quiz_back_layout);
        llALayout=(LinearLayout) view.findViewById(R.id.fquiz_option_a_layout);
        llBLayout=(LinearLayout) view.findViewById(R.id.fquiz_option_b_layout);
        llCLayout=(LinearLayout) view.findViewById(R.id.fquiz_option_c_layout);
        llDLayout=(LinearLayout) view.findViewById(R.id.fquiz_option_d_layout);
        llFrontCardLayout=(LinearLayout) view.findViewById(R.id.fquiz_front_card_layout);
        llBackCardLayout=(LinearLayout) view.findViewById(R.id.fquiz_back_card_layout);
        llRootLayout=(LinearLayout) view.findViewById(R.id.fquiz_rrot_layout);
        llBackTextLayout=(LinearLayout) view.findViewById(R.id.fquiz_back_text_layout);

        rlBottomLayout=(RelativeLayout)view.findViewById(R.id.fquiz_bottom_layout);
        rlTopLayout=(RelativeLayout)view.findViewById(R.id.practice_quiz_top_layout);
        teamPersonalImageLayout=(RelativeLayout) view.findViewById(R.id.fquiz_team_image_layout);


        llFrontCardLayout.setVisibility(View.VISIBLE);
        if(Constants.homeAdapterId==8){
            tvBottomText.setText(getResources().getString(R.string.who_next));
            rlBottomLayout.setBackgroundColor(getResources().getColor(R.color.who_next_bg_color));
            tvQuestion.setText("Pratyusha");
            tvPartsOfSpeech.setText("Captain");
            tvOptionA.setText("Second Name: Simharaju");
            tvOptionB.setText("Likes: Working out, Succeeding");
            tvOptionC.setText("USP: Second name means lion king");
            tvOptionD.setText("Work: Coding, Design");
            teamPersonalImageLayout.setVisibility(View.VISIBLE);
            teamPersonalImage.setImageResource(R.drawable.prathyusha);
        }
    }

    private void initializeClickListeners() {
        llBackLayout.setOnClickListener(this);
        rlBottomLayout.setOnClickListener(this);
        llALayout.setOnClickListener(this);
        llBLayout.setOnClickListener(this);
        llCLayout.setOnClickListener(this);
        llDLayout.setOnClickListener(this);
    }

    private void setText() {
        tvVocabularyTitle.setText(Constants.vocabularyTitle);
        ivImage.setImageResource(Constants.image);
        rlTopLayout.setBackgroundColor(Constants.backGroundColor);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.practice_quiz_back_layout:
                getActivity().finish();
                break;
            case R.id.fquiz_bottom_layout:
                llBackCardLayout.setVisibility(View.GONE);
                llFrontCardLayout.setVisibility(View.VISIBLE);
                if(tvBottomText.getText().toString().equals(getResources().getString(R.string.skip))){
                    if(Constants.skip.equals("quiz")) {
//                        showSilverPopUp();
//                        showGoldPopUp();
                        showBronzePopUp();

                    }else if(Constants.skip.equals("practiceAdapter")){
                        llBackCardLayout.setVisibility(View.VISIBLE);
                        llFrontCardLayout.setVisibility(View.GONE);
                        tvBottomText.setText(getResources().getString(R.string.take_ahead));
                        rlBottomLayout.setBackgroundColor(getResources().getColor(R.color.green));
                        tvBingoOOpsie.setText(getResources().getString(R.string.learning_time));
                        tvBingoOOpsie.setTextColor(getResources().getColor(R.color.colorMediumTurquoise));
                        llBackTextLayout.setBackgroundColor(getResources().getColor(R.color.learning_time_bg_color));
                        flipCard();
                    }
                }else if(tvBottomText.getText().toString().equals(getResources().getString(R.string.next))){
                    tvBottomText.setText(getResources().getString(R.string.skip));
                    rlBottomLayout.setBackgroundColor(getResources().getColor(R.color.colorMediumTurquoise));
                    flipToQuestion();
                }else if(tvBottomText.getText().toString().equals(getResources().getString(R.string.got_it))){
                    tvBottomText.setText(getResources().getString(R.string.skip));
                    rlBottomLayout.setBackgroundColor(getResources().getColor(R.color.colorMediumTurquoise));
                    flipToQuestion();
                }else if(tvBottomText.getText().toString().equals(getResources().getString(R.string.take_ahead))){
                    tvBottomText.setText(getResources().getString(R.string.skip));
                    rlBottomLayout.setBackgroundColor(getResources().getColor(R.color.colorMediumTurquoise));
                    flipToQuestion();
                }else if(tvBottomText.getText().toString().equals(getResources().getString(R.string.who_next))){
                    tvBottomText.setText(getResources().getString(R.string.who_next));
                    rlBottomLayout.setBackgroundColor(getResources().getColor(R.color.who_next_bg_color));
//                    flipToQuestion();
                    flipToNextPerson();
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
        if(tvBottomText.getText().toString().equals(getResources().getString(R.string.who_next))) {
            tvBottomText.setText(getResources().getString(R.string.who_next));
            rlBottomLayout.setBackgroundColor(getResources().getColor(R.color.who_next_bg_color));
            flipToNextPerson();
        } else if(option.equals("influence")){
            llBackCardLayout.setVisibility(View.VISIBLE);
            llFrontCardLayout.setVisibility(View.GONE);
            tvBingoOOpsie.setText("Bingo!");
            tvBingoOOpsie.setTextColor(getResources().getColor(R.color.agri_green));
            llBackTextLayout.setBackgroundColor(getResources().getColor(R.color.answer_right_bg_color));
            tvBottomText.setText(getResources().getString(R.string.next));
            rlBottomLayout.setBackgroundColor(getResources().getColor(R.color.green));
        }else {
            llBackCardLayout.setVisibility(View.VISIBLE);
            llFrontCardLayout.setVisibility(View.GONE);
            tvBingoOOpsie.setText("Oopsie!");
            tvBingoOOpsie.setTextColor(getResources().getColor(R.color.oopsie_color));
            llBackTextLayout.setBackgroundColor(getResources().getColor(R.color.answer_wrong_bg_color));
            tvBottomText.setText(getResources().getString(R.string.got_it));
            rlBottomLayout.setBackgroundColor(getResources().getColor(R.color.green));
        }
        flipCard();
    }

    private void flipCard() {
        Animation zoomout = AnimationUtils.loadAnimation(getActivity(), R.anim.translate);
        llBackCardLayout.setAnimation(zoomout);
        llBackCardLayout.startAnimation(zoomout);

/*
        FlipAnimation flipAnimation = new FlipAnimation(llFrontCardLayout, llBackCardLayout);

        if (llFrontCardLayout.getVisibility() == View.GONE)
        {
            flipAnimation.reverse();
        }
        llRootLayout.startAnimation(flipAnimation);*/
    }

    private void flipToQuestion() {
        llBackCardLayout.setVisibility(View.GONE);
        llFrontCardLayout.setVisibility(View.VISIBLE);
        Animation zoomin = AnimationUtils.loadAnimation(getActivity(), R.anim.translate);
        llFrontCardLayout.setAnimation(zoomin);
        llFrontCardLayout.startAnimation(zoomin);
    /*    FlipAnimation flipAnimation = new FlipAnimation(llBackCardLayout, llFrontCardLayout);

        if (llBackCardLayout.getVisibility() == View.GONE)
        {
            flipAnimation.reverse();
        }
        llRootLayout.startAnimation(flipAnimation);*/
    }


    private void flipToNextPerson() {
        llBackCardLayout.setVisibility(View.GONE);
        llFrontCardLayout.setVisibility(View.VISIBLE);
        Animation zoomin = AnimationUtils.loadAnimation(getActivity(), R.anim.translate);
        llFrontCardLayout.setAnimation(zoomin);
        llFrontCardLayout.startAnimation(zoomin);

        if(tvQuestion.getText().toString().equals("Pratyusha")) {
            tvQuestion.setText("Sagarika");
            tvPartsOfSpeech.setText("Captain");
            tvOptionA.setText("Second Name: Nagunoori");
            tvOptionB.setText("Likes: Working out, Succeeding");
            tvOptionC.setText("USP: Second name means lion king");
            tvOptionD.setText("Work: Design,Development");
            teamPersonalImage.setImageResource(R.drawable.prathyusha);
        }else if(tvQuestion.getText().toString().equals("Sagarika")) {
            tvQuestion.setText("Rakesh");
            tvPartsOfSpeech.setText("Captain");
            tvOptionA.setText("Second Name: Muppa");
            tvOptionB.setText("Likes: Working out, Succeeding");
            tvOptionC.setText("USP: Second name means lion king");
            tvOptionD.setText("Work: Development");
            teamPersonalImage.setImageResource(R.drawable.prathyusha);
        }else if(tvQuestion.getText().toString().equals("Rakesh")) {
            tvQuestion.setText("Pratyusha");
            tvPartsOfSpeech.setText("Captain");
            tvOptionA.setText("Second Name: Simharaju");
            tvOptionB.setText("Likes: Working out, Succeeding");
            tvOptionC.setText("USP: Second name means lion king");
            tvOptionD.setText("Work: Coding, Design");
            teamPersonalImage.setImageResource(R.drawable.prathyusha);
        }

    }


    private void showSilverPopUp() {
        final Button retakeQuiz,mayBeLater;

        final AlertDialog.Builder silverPopUP = new AlertDialog.Builder(getActivity());
        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View f = factory.inflate(R.layout.silver_popup, null);
        silverPopUP.setView(f);

        final AlertDialog alert = silverPopUP.create();

        alert.show();
        alert.setCancelable(false);
        alert.setCanceledOnTouchOutside(false);

        retakeQuiz=(Button)f.findViewById(R.id.silver_retake_quiz);
        mayBeLater=(Button)f.findViewById(R.id.silver_may_be_later);

             /*setting font*/
        Fonts.setFont(retakeQuiz,Fonts.robotoMedium,"Button");
        Fonts.setFont(mayBeLater,Fonts.robotoMedium,"Button");

        retakeQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
            }
        });

        mayBeLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Home.class));
            }
        });

    }

    private void showGoldPopUp() {
        final Button shareNews,learnMore;

        final AlertDialog.Builder silverPopUP = new AlertDialog.Builder(getActivity());
        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View f = factory.inflate(R.layout.gold_medal_popup, null);
        silverPopUP.setView(f);

        final AlertDialog alert = silverPopUP.create();

        alert.show();
        alert.setCancelable(false);
        alert.setCanceledOnTouchOutside(false);

        shareNews=(Button)f.findViewById(R.id.gold_medal_share_news);
        learnMore=(Button)f.findViewById(R.id.gold_medal_learn_more);

             /*setting font*/
        Fonts.setFont(shareNews,Fonts.robotoMedium,"Button");
        Fonts.setFont(learnMore,Fonts.robotoMedium,"Button");

        shareNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        learnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }


    private void showBronzePopUp() {
        final Button bronzeRetakeQuiz,bronzeMaybeLater;

        final AlertDialog.Builder silverPopUP = new AlertDialog.Builder(getActivity());
        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View f = factory.inflate(R.layout.bronze_medal_popup, null);
        silverPopUP.setView(f);

        final AlertDialog alert = silverPopUP.create();

        alert.show();
        alert.setCancelable(false);
        alert.setCanceledOnTouchOutside(false);

        bronzeRetakeQuiz=(Button)f.findViewById(R.id.bronze_retake_quiz);
        bronzeMaybeLater=(Button)f.findViewById(R.id.bronze_may_be_later);

        /*setting font*/
        Fonts.setFont(bronzeRetakeQuiz,Fonts.robotoMedium,"Button");
        Fonts.setFont(bronzeMaybeLater,Fonts.robotoMedium,"Button");


        bronzeRetakeQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
            }
        });

        bronzeMaybeLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Home.class));
            }
        });
    }


}

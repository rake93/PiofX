package com.firstacademy.piofx.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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

import com.firstacademy.piofx.PiofXApp;
import com.firstacademy.piofx.R;
import com.firstacademy.piofx.activities.Home;
import com.firstacademy.piofx.activities.PracticeQuiz;
import com.firstacademy.piofx.data.db.model.DaoSession;
import com.firstacademy.piofx.data.db.model.Option;
import com.firstacademy.piofx.data.db.model.PracticeProgress;
import com.firstacademy.piofx.data.db.model.Question;
import com.firstacademy.piofx.data.db.repository.OptionRepository;
import com.firstacademy.piofx.data.db.repository.PracticeProgressRepository;
import com.firstacademy.piofx.data.db.repository.QuestionRepository;
import com.firstacademy.piofx.utils.Constants;
import com.firstacademy.piofx.utils.Fonts;
import com.firstacademy.piofx.utils.PracticeProgressUtil;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Quiz extends Fragment implements View.OnClickListener {

    private static final String TAG = "QuizFragment";

    private TextView tvVocabularyTitle, tvQuestion, tvPartsOfSpeech, tvOptionA, tvOptionB, tvOptionC,
            tvOptionD, tvBottomText, tvBingoOOpsie, tvBackQuestion, tvBackPartsOfSpeech, tvBackMeaning,
            tvBackExample1, tvBackExample2;
    private ImageView ivImage, teamPersonalImage;
    private LinearLayout llBackLayout, llALayout, llBLayout, llCLayout, llDLayout, llFrontCardLayout,
            llBackCardLayout, llRootLayout, llBackTextLayout;
    private RelativeLayout rlBottomLayout, rlTopLayout, teamPersonalImageLayout;


    private DaoSession daoSession;
    private QuestionRepository mQuestionRepository;
    private OptionRepository mOptionRepository;
    private PracticeProgressRepository practiceProgressRepository;
    private PracticeProgressUtil practiceProgressUtil = new PracticeProgressUtil();

    private List<Question> questionList;
    private List<Question> quizReviewResults = new ArrayList<>();
    private int questionIndex = 0;
    private int correctAnsweredCount = 0;
    private List<Question> practiceSkippedAndUnskilledList = new ArrayList<>();
    private List<PracticeProgress> practiceProgresses = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        initializeViews(view);
        initializeClickListeners();
        daoSession = ((PiofXApp) getActivity().getApplication()).getDaoSession();
        mQuestionRepository = new QuestionRepository(daoSession);
        mOptionRepository = new OptionRepository(daoSession);
        practiceProgressRepository = new PracticeProgressRepository(daoSession);
        if (Constants.vocabularyTitle.equals("Basic 1")
                || Constants.vocabularyTitle.equals("Basic 2")
                || Constants.vocabularyTitle.equals("Intermediate 1")
                || Constants.vocabularyTitle.equals("Intermediate 2")
                || Constants.vocabularyTitle.equals("Advanced 1")
                || Constants.vocabularyTitle.equals("Advanced 2")
                || Constants.vocabularyTitle.equals("Super Hero")) {
            if (Constants.skip.equals("practiceAdapter")) {
                questionList = mQuestionRepository.getQuestionsForPractice(Constants.getQuestionCategoryByKey(Constants.vocabularyTitle), Constants.getQuestionSetsByKey(Constants.practiceLevel));
                practiceProgresses = practiceProgressRepository.fetchPracticeProgressesList(Constants.getQuestionCategoryByKey(Constants.vocabularyTitle), Constants.getQuestionSetsByKey(Constants.practiceLevel));
                if (CollectionUtils.isNotEmpty(practiceProgresses)) {
                    if (CollectionUtils.isEmpty(questionList))
                        questionList = practiceProgressUtil.convertPracticeEntityToQuestion(practiceProgresses);
                    else
                        questionList.addAll(practiceProgressUtil.convertPracticeEntityToQuestion(practiceProgresses));
                }
            } else {
                questionList = mQuestionRepository.getQuestionsForQuiz(Constants.getQuestionCategoryByKey(Constants.vocabularyTitle));
                tvBottomText.setText(getResources().getString(R.string.next));
                tvBottomText.setEnabled(false);
                rlBottomLayout.setEnabled(false);
                rlBottomLayout.setBackgroundColor(Color.LTGRAY);
            }
            setQuestionAndOptionText(questionList.get(questionIndex));
        } else if (Constants.homeAdapterId == 8) {
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
        } /*else {
            questionList = mQuestionRepository.getAllQuestions();
            setQuestionAndOptionText(questionList.get(questionIndex));
        }*/
        setText();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Constants.skip.equals("quizReview") && !quizReviewResults.isEmpty()) {
            questionList.clear();
            questionList.addAll(quizReviewResults);
            questionIndex = 0;
            setQuizReviewCard(questionList.get(questionIndex));
            tvBottomText.setText(getResources().getString(R.string.next));
        }
    }

    private void initializeViews(View view) {
        tvVocabularyTitle = (TextView) view.findViewById(R.id.practice_quiz_vocabulary_heading);
        tvQuestion = (TextView) view.findViewById(R.id.fquiz_question);
        tvPartsOfSpeech = (TextView) view.findViewById(R.id.fquiz_parts_of_speech);
        tvOptionA = (TextView) view.findViewById(R.id.fquiz_option_a);
        tvOptionB = (TextView) view.findViewById(R.id.fquiz_option_b);
        tvOptionC = (TextView) view.findViewById(R.id.fquiz_option_c);
        tvOptionD = (TextView) view.findViewById(R.id.fquiz_option_d);
        tvBottomText = (TextView) view.findViewById(R.id.fquiz_bottom_layout_text);
        tvBingoOOpsie = (TextView) view.findViewById(R.id.fquiz_oopsie_bingo);
        tvBackQuestion = (TextView) view.findViewById(R.id.fquiz_back_question);
        tvBackPartsOfSpeech = (TextView) view.findViewById(R.id.fquiz_back_parts_of_speech);
        tvBackMeaning = (TextView) view.findViewById(R.id.fquiz_back_meaning);
        tvBackExample1 = (TextView) view.findViewById(R.id.fquiz_back_example1);
        tvBackExample2 = (TextView) view.findViewById(R.id.fquiz_back_example2);

        ivImage = (ImageView) view.findViewById(R.id.practice_quiz_image);
        teamPersonalImage = (ImageView) view.findViewById(R.id.fquiz_team_image);

        llBackLayout = (LinearLayout) view.findViewById(R.id.practice_quiz_back_layout);
        llALayout = (LinearLayout) view.findViewById(R.id.fquiz_option_a_layout);
        llBLayout = (LinearLayout) view.findViewById(R.id.fquiz_option_b_layout);
        llCLayout = (LinearLayout) view.findViewById(R.id.fquiz_option_c_layout);
        llDLayout = (LinearLayout) view.findViewById(R.id.fquiz_option_d_layout);
        llFrontCardLayout = (LinearLayout) view.findViewById(R.id.fquiz_front_card_layout);
        llBackCardLayout = (LinearLayout) view.findViewById(R.id.fquiz_back_card_layout);
        llRootLayout = (LinearLayout) view.findViewById(R.id.fquiz_rrot_layout);
        llBackTextLayout = (LinearLayout) view.findViewById(R.id.fquiz_back_text_layout);

        rlBottomLayout = (RelativeLayout) view.findViewById(R.id.fquiz_bottom_layout);
        rlTopLayout = (RelativeLayout) view.findViewById(R.id.practice_quiz_top_layout);
        teamPersonalImageLayout = (RelativeLayout) view.findViewById(R.id.fquiz_team_image_layout);

        llFrontCardLayout.setVisibility(View.VISIBLE);
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
        if (questionList != null && questionIndex <= questionList.size() - 1 && Constants.skip.equals("quiz")) {

            llALayout.setBackgroundColor(View.INVISIBLE);
            llBLayout.setBackgroundColor(View.INVISIBLE);
            llCLayout.setBackgroundColor(View.INVISIBLE);
            llDLayout.setBackgroundColor(View.INVISIBLE);
            Question question = questionList.get(questionIndex);
            switch (view.getId()) {
                case R.id.practice_quiz_back_layout:
                    getActivity().finish();
                    break;
                case R.id.fquiz_bottom_layout:
                    llBackCardLayout.setVisibility(View.GONE);
                    llFrontCardLayout.setVisibility(View.VISIBLE);
                    if (tvBottomText.getText().toString().equals(getResources().getString(R.string.skip))
                            || tvBottomText.getText().toString().equals(getResources().getString(R.string.done))) {
                        quizReviewResults.add(question);
                        if (correctAnsweredCount <= Constants.BRONZE_SCORE)
                            showBronzePopUp();
                        if (correctAnsweredCount >= Constants.SILVER_SCORE_START && correctAnsweredCount <= Constants.SILVER_SCORE_END)
                            showSilverPopUp();
                        if (correctAnsweredCount == Constants.GOLD_SCORE)
                            showGoldPopUp();

                    } else if (tvBottomText.getText().toString().equals(getResources().getString(R.string.next))) {
                        tvBottomText.setText(getResources().getString(R.string.next));
                        rlBottomLayout.setBackgroundColor(getResources().getColor(R.color.light_gray));
                        rlBottomLayout.setEnabled(false);
                        quizReviewResults.add(question);
                        flipToQuestion(questionIndex);
                    }
                    break;
                case R.id.fquiz_option_a_layout:
                    enableNextForQuiz(question, tvOptionA.getText().toString(), "a");
                    break;
                case R.id.fquiz_option_b_layout:
                    enableNextForQuiz(question, tvOptionB.getText().toString(), "b");
                    break;
                case R.id.fquiz_option_c_layout:
                    enableNextForQuiz(question, tvOptionC.getText().toString(), "c");
                    break;
                case R.id.fquiz_option_d_layout:
                    enableNextForQuiz(question, tvOptionD.getText().toString(), "d");
                    break;
            }
        } else if (questionList != null && questionIndex <= questionList.size() - 1 && Constants.skip.equals("quizReview")) {

            llALayout.setBackgroundColor(View.INVISIBLE);
            llBLayout.setBackgroundColor(View.INVISIBLE);
            llCLayout.setBackgroundColor(View.INVISIBLE);
            llDLayout.setBackgroundColor(View.INVISIBLE);
            switch (view.getId()) {
                case R.id.practice_quiz_back_layout:
                    getActivity().finish();
                    break;
                case R.id.fquiz_bottom_layout:
                    llBackCardLayout.setVisibility(View.GONE);
                    llFrontCardLayout.setVisibility(View.VISIBLE);
                    if (tvBottomText.getText().toString().equals(getResources().getString(R.string.skip))
                            || tvBottomText.getText().toString().equals(getResources().getString(R.string.done))) {
                        startActivity(new Intent(getActivity(), Home.class));
                    } else if (tvBottomText.getText().toString().equals(getResources().getString(R.string.next))) {
                        flipToQuizReview(questionIndex);
                    }
                    break;
            }
        } else if (questionList != null && questionIndex <= questionList.size() - 1 && Constants.skip.equals("practiceAdapter")) {
            Question question = questionList.get(questionIndex);
            switch (view.getId()) {
                case R.id.practice_quiz_back_layout:
                    getActivity().finish();
                    break;
                case R.id.fquiz_bottom_layout:
                    llBackCardLayout.setVisibility(View.GONE);
                    llFrontCardLayout.setVisibility(View.VISIBLE);
                    if (tvBottomText.getText().toString().equals(getResources().getString(R.string.skip))) {
                        llBackCardLayout.setVisibility(View.VISIBLE);
                        llFrontCardLayout.setVisibility(View.GONE);
                        tvBottomText.setText(getResources().getString(R.string.take_ahead));
                        rlBottomLayout.setBackgroundColor(getResources().getColor(R.color.green));
                        tvBingoOOpsie.setText(getResources().getString(R.string.learning_time));
                        tvBingoOOpsie.setTextColor(getResources().getColor(R.color.colorMediumTurquoise));
                        llBackTextLayout.setBackgroundColor(getResources().getColor(R.color.learning_time_bg_color));
                        question.setIsPracticed(true);
                        mQuestionRepository.updateQuestion(question);
                        practiceSkippedAndUnskilledList.add(question);
                        flipCard();
                    } else if (tvBottomText.getText().toString().equals(getResources().getString(R.string.next))) {
                        tvBottomText.setText(getResources().getString(R.string.skip));
                        rlBottomLayout.setBackgroundColor(getResources().getColor(R.color.colorMediumTurquoise));
                        flipToQuestion(questionIndex);
                    } else if (tvBottomText.getText().toString().equals(getResources().getString(R.string.got_it))) {
                        tvBottomText.setText(getResources().getString(R.string.skip));
                        rlBottomLayout.setBackgroundColor(getResources().getColor(R.color.colorMediumTurquoise));
                        flipToQuestion(questionIndex);
                    } else if (tvBottomText.getText().toString().equals(getResources().getString(R.string.take_ahead))) {
                        if (questionIndex == questionList.size() - 1) {
                            if (practiceSkippedAndUnskilledList != null && !practiceSkippedAndUnskilledList.isEmpty()) {
                                tvBottomText.setText(getResources().getString(R.string.skip));
                                processPracticeProgress(false);
                                flipToQuestion(questionIndex);
                            } else
                                tvBottomText.setText(getResources().getString(R.string.done));
                        } else {
                            tvBottomText.setText(getResources().getString(R.string.skip));
                            flipToQuestion(questionIndex);
                        }
                        rlBottomLayout.setBackgroundColor(getResources().getColor(R.color.colorMediumTurquoise));
                    } else if (tvBottomText.getText().toString().equals(getResources().getString(R.string.done))) {
                        if (practiceSkippedAndUnskilledList != null && !practiceSkippedAndUnskilledList.isEmpty()) {
                            processPracticeProgress(false);
                            tvBottomText.setText(getResources().getString(R.string.next));
                            flipToQuestion(questionIndex);
                        } else
                            showLevelCompletePopUp();
                    }
                    break;
                case R.id.fquiz_option_a_layout:
                    getAnswer(question, tvOptionA.getText().toString());
                    break;
                case R.id.fquiz_option_b_layout:
                    getAnswer(question, tvOptionB.getText().toString());
                    break;
                case R.id.fquiz_option_c_layout:
                    getAnswer(question, tvOptionC.getText().toString());
                    break;
                case R.id.fquiz_option_d_layout:
                    getAnswer(question, tvOptionD.getText().toString());
                    break;
            }
        } else {
            switch (view.getId()) {
                case R.id.practice_quiz_back_layout:
                    getActivity().finish();
                    break;
                case R.id.fquiz_bottom_layout:
                    if (tvBottomText.getText().toString().equals(getResources().getString(R.string.who_next))) {
                        tvBottomText.setText(getResources().getString(R.string.who_next));
                        rlBottomLayout.setBackgroundColor(getResources().getColor(R.color.who_next_bg_color));
                        flipToNextPerson();
                    } else if (tvBottomText.getText().toString().equals(getResources().getString(R.string.done))) {
                        showLevelCompletePopUp();
                    }
                    break;
                case R.id.fquiz_option_a_layout:
                    flipToNextPerson();
                    break;
                case R.id.fquiz_option_b_layout:
                    flipToNextPerson();
                    break;
                case R.id.fquiz_option_c_layout:
                    flipToNextPerson();
                    break;
                case R.id.fquiz_option_d_layout:
                    flipToNextPerson();
                    break;
            }
        }
    }

    private void enableNextForQuiz(Question question, String option, String selectedOption) {
        Option option1 = mOptionRepository.getOptionEntity(question.getId());
        if (option.equals(option1.getAnswerText())) {
            question.setQuizAnswered(true);
            llBackCardLayout.setVisibility(View.GONE);
            llFrontCardLayout.setVisibility(View.VISIBLE);
            if (questionIndex == questionList.size() - 1)
                tvBottomText.setText(getResources().getString(R.string.done));
            else
                tvBottomText.setText(getResources().getString(R.string.next));
            correctAnsweredCount++;
        } else {
            question.setQuizAnswered(false);
            question.setSelectedOption(selectedOption);
            llBackCardLayout.setVisibility(View.GONE);
            llFrontCardLayout.setVisibility(View.VISIBLE);

            if (questionIndex == questionList.size() - 1)
                tvBottomText.setText(getResources().getString(R.string.done));
            else
                tvBottomText.setText(getResources().getString(R.string.next));
        }
        if (selectedOption.equals("a"))
            llALayout.setBackgroundColor(getResources().getColor(R.color.light_gray));
        if (selectedOption.equals("b"))
            llBLayout.setBackgroundColor(getResources().getColor(R.color.light_gray));
        if (selectedOption.equals("c"))
            llCLayout.setBackgroundColor(getResources().getColor(R.color.light_gray));
        if (selectedOption.equals("d"))
            llDLayout.setBackgroundColor(getResources().getColor(R.color.light_gray));

        rlBottomLayout.setBackgroundColor(getResources().getColor(R.color.colorMediumTurquoise));
        rlBottomLayout.setEnabled(true);
    }

    private void getAnswer(Question question, String option) {
        Option option1 = mOptionRepository.getOptionEntity(question.getId());
        if (tvBottomText.getText().toString().equals(getResources().getString(R.string.who_next))) {
            tvBottomText.setText(getResources().getString(R.string.who_next));
            rlBottomLayout.setBackgroundColor(getResources().getColor(R.color.who_next_bg_color));
            flipToNextPerson();
        } else if (option.equals(option1.getAnswerText())) {
            if (Constants.skip.equals("practiceAdapter")) {
                question.setIsAnswered(true);
                question.setIsPracticed(true);
                mQuestionRepository.updateQuestion(question);
                if (practiceProgressRepository.isPracticeProgressEmptyByQuestionId(question.getId()))
                    practiceProgressRepository.deletePracticeProgressByQuestionId(question.getId());
            }
            llBackCardLayout.setVisibility(View.VISIBLE);
            llFrontCardLayout.setVisibility(View.GONE);
            tvBingoOOpsie.setText("Bingo!");
            tvBingoOOpsie.setTextColor(getResources().getColor(R.color.agri_green));
            llBackTextLayout.setBackgroundColor(getResources().getColor(R.color.answer_right_bg_color));
            if (questionIndex == questionList.size() - 1) {
                if (practiceSkippedAndUnskilledList != null && !practiceSkippedAndUnskilledList.isEmpty()) {
                    processPracticeProgress(false);
                    tvBottomText.setText(getResources().getString(R.string.next));
                } else
                    tvBottomText.setText(getResources().getString(R.string.done));
            } else
                tvBottomText.setText(getResources().getString(R.string.next));
            rlBottomLayout.setBackgroundColor(getResources().getColor(R.color.green));
            correctAnsweredCount++;
        } else {
            if (Constants.skip.equals("practiceAdapter") && !question.getIsPracticed()) {
                question.setIsPracticed(true);
                mQuestionRepository.updateQuestion(question);
                practiceSkippedAndUnskilledList.add(question);
                practiceSkippedAndUnskilledList.add(question);
            }
            llBackCardLayout.setVisibility(View.VISIBLE);
            llFrontCardLayout.setVisibility(View.GONE);
            tvBingoOOpsie.setText("Oopsie!");
            tvBingoOOpsie.setTextColor(getResources().getColor(R.color.oopsie_color));
            llBackTextLayout.setBackgroundColor(getResources().getColor(R.color.answer_wrong_bg_color));
            if (questionIndex == questionList.size() - 1) {
                if (practiceSkippedAndUnskilledList != null && !practiceSkippedAndUnskilledList.isEmpty()) {
                    processPracticeProgress(false);
                    tvBottomText.setText(getResources().getString(R.string.next));
                } else
                    tvBottomText.setText(getResources().getString(R.string.done));
            } else
                tvBottomText.setText(getResources().getString(R.string.got_it));
            rlBottomLayout.setBackgroundColor(getResources().getColor(R.color.green));
        }
        flipCard();
    }

    private void flipCard() {
        Animation zoomout = AnimationUtils.loadAnimation(getActivity(), R.anim.translate);
        llBackCardLayout.setAnimation(zoomout);
        llBackCardLayout.startAnimation(zoomout);
    }

    private void flipToQuestion(int questionIndexVal) {
        ++questionIndexVal;
        llBackCardLayout.setVisibility(View.GONE);
        llFrontCardLayout.setVisibility(View.VISIBLE);
        Animation zoomin = AnimationUtils.loadAnimation(getActivity(), R.anim.translate);
        llFrontCardLayout.setAnimation(zoomin);
        llFrontCardLayout.startAnimation(zoomin);

        if (questionIndexVal < questionList.size())
            setQuestionAndOptionText(questionList.get(questionIndexVal));
        questionIndex = questionIndexVal;
    }

    private void flipToQuizReview(int questionIndexVal) {
        ++questionIndexVal;
        llBackCardLayout.setVisibility(View.GONE);
        llFrontCardLayout.setVisibility(View.VISIBLE);
        Animation zoomin = AnimationUtils.loadAnimation(getActivity(), R.anim.translate);
        llFrontCardLayout.setAnimation(zoomin);
        llFrontCardLayout.startAnimation(zoomin);

        if (questionIndexVal < questionList.size())
            setQuizReviewCard(questionList.get(questionIndexVal));
        questionIndex = questionIndexVal;
        if (questionIndex == questionList.size() - 1)
            tvBottomText.setText(getResources().getString(R.string.done));
    }

    private void flipToNextPerson() {
        llBackCardLayout.setVisibility(View.GONE);
        llFrontCardLayout.setVisibility(View.VISIBLE);
        Animation zoomin = AnimationUtils.loadAnimation(getActivity(), R.anim.translate);
        llFrontCardLayout.setAnimation(zoomin);
        llFrontCardLayout.startAnimation(zoomin);

        if (tvQuestion.getText().toString().equals("Pratyusha")) {
            tvQuestion.setText("Sagarika");
            tvPartsOfSpeech.setText("Captain");
            tvOptionA.setText("Second Name: Nagunoori");
            tvOptionB.setText("Likes: Working out, Succeeding, Meditation");
            tvOptionC.setText("USP: Second name means lion king");
            tvOptionD.setText("Work: Design,Development");
            teamPersonalImage.setImageResource(R.drawable.sagarika);
        } else if (tvQuestion.getText().toString().equals("Sagarika")) {
            tvQuestion.setText("Rakesh");
            tvPartsOfSpeech.setText("Captain");
            tvOptionA.setText("Second Name: Muppa");
            tvOptionB.setText("Likes: Succeding, Playing Games");
            tvOptionC.setText("USP: Second name means lion king");
            tvOptionD.setText("Work: Development");
            teamPersonalImage.setImageResource(R.drawable.rakesh);
        } else if (tvQuestion.getText().toString().equals("Rakesh")) {
            tvQuestion.setText("Teju");
            tvPartsOfSpeech.setText("Captain");
            tvOptionA.setText("Second Name: xxx");
            tvOptionB.setText("Likes: Working out, Succeeding");
            tvOptionC.setText("USP: Second name means lion king");
            tvOptionD.setText("Work: Development");
            teamPersonalImage.setImageResource(R.drawable.teju);
        } else if (tvQuestion.getText().toString().equals("Teju")) {
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
        final Button reviewQuizResults, mayBeLater;

        final AlertDialog.Builder silverPopUP = new AlertDialog.Builder(getActivity());
        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View f = factory.inflate(R.layout.silver_popup, null);
        silverPopUP.setView(f);

        final AlertDialog alert = silverPopUP.create();

        alert.show();
        alert.setCancelable(false);
        alert.setCanceledOnTouchOutside(false);

        reviewQuizResults = (Button) f.findViewById(R.id.silver_review_quiz);
        mayBeLater = (Button) f.findViewById(R.id.silver_may_be_later);

             /*setting font*/
        Fonts.setFont(reviewQuizResults, Fonts.robotoMedium, "Button");
        Fonts.setFont(mayBeLater, Fonts.robotoMedium, "Button");

        reviewQuizResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
                Constants.skip = "quizReview";
                onStart();
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
        final Button shareNews, learnMore;

        final AlertDialog.Builder silverPopUP = new AlertDialog.Builder(getActivity());
        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View f = factory.inflate(R.layout.gold_medal_popup, null);
        silverPopUP.setView(f);

        final AlertDialog alert = silverPopUP.create();

        alert.show();
        alert.setCancelable(false);
        alert.setCanceledOnTouchOutside(false);

        shareNews = (Button) f.findViewById(R.id.gold_medal_share_news);
        learnMore = (Button) f.findViewById(R.id.gold_medal_learn_more);

             /*setting font*/
        Fonts.setFont(shareNews, Fonts.robotoMedium, "Button");
        Fonts.setFont(learnMore, Fonts.robotoMedium, "Button");

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
        final Button bronzeRetakeQuiz, bronzeReviewQuiz, bronzeMaybeLater;

        final AlertDialog.Builder silverPopUP = new AlertDialog.Builder(getActivity());
        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View f = factory.inflate(R.layout.bronze_medal_popup, null);
        silverPopUP.setView(f);

        final AlertDialog alert = silverPopUP.create();

        alert.show();
        alert.setCancelable(false);
        alert.setCanceledOnTouchOutside(false);

        //bronzeRetakeQuiz = (Button) f.findViewById(R.id.bronze_retake_quiz);
        bronzeReviewQuiz = (Button) f.findViewById(R.id.bronze_review_quiz);
        bronzeMaybeLater = (Button) f.findViewById(R.id.bronze_may_be_later);

        /*setting font*/
        Fonts.setFont(bronzeReviewQuiz, Fonts.robotoMedium, "Button");
        Fonts.setFont(bronzeMaybeLater, Fonts.robotoMedium, "Button");


        bronzeReviewQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
                Constants.skip = "quizReview";
                onStart();
            }
        });

        bronzeMaybeLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Home.class));
            }
        });
    }

    private void showLevelCompletePopUp() {
        final Button practiceLevelComplete;

        final AlertDialog.Builder levelPopUP = new AlertDialog.Builder(getActivity());
        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View f = factory.inflate(R.layout.level_complete_popup, null);
        levelPopUP.setView(f);

        final AlertDialog alert = levelPopUP.create();

        alert.show();
        alert.setCancelable(false);
        alert.setCanceledOnTouchOutside(false);

        practiceLevelComplete = (Button) f.findViewById(R.id.section_complete_quiz);

        /*setting font*/
        Fonts.setFont(practiceLevelComplete, Fonts.robotoMedium, "Button");

        practiceLevelComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PracticeQuiz.class).putExtra("intent", "practice"));
            }
        });
    }

    public void setQuestionAndOptionText(Question question) {
        Option option = mOptionRepository.getOptionEntity(question.getId());
        tvQuestion.setText(question.getQuestionText());
        tvPartsOfSpeech.setText(question.getQuestionClause());
        tvOptionA.setText(option.getOptionTextA());
        tvOptionB.setText(option.getOptionTextB());
        tvOptionC.setText(option.getOptionTextC());
        tvOptionD.setText(option.getOptionTextD());

        tvBackQuestion.setText(question.getQuestionText());
        tvBackPartsOfSpeech.setText(question.getQuestionClause());
        tvBackMeaning.setText(question.getWord_definition());
        tvBackExample1.setText(question.getWord_description1());
        tvBackExample2.setText(question.getWord_description2());
    }

    public void setQuizReviewCard(Question question) {
        Option option = mOptionRepository.getOptionEntity(question.getId());
        tvQuestion.setText(question.getQuestionText());
        tvPartsOfSpeech.setText(question.getQuestionClause());

        tvOptionA.setText(option.getOptionTextA());
        tvOptionB.setText(option.getOptionTextB());
        tvOptionC.setText(option.getOptionTextC());
        tvOptionD.setText(option.getOptionTextD());

        llALayout.setEnabled(false);
        llBLayout.setEnabled(false);
        llCLayout.setEnabled(false);
        llDLayout.setEnabled(false);

        if (!question.isQuizAnswered()) {
            if (question.getSelectedOption().equalsIgnoreCase("a"))
                llALayout.setBackgroundColor(getResources().getColor(R.color.answer_wrong_bg_color));
            if (question.getSelectedOption().equalsIgnoreCase("b"))
                llBLayout.setBackgroundColor(getResources().getColor(R.color.answer_wrong_bg_color));
            if (question.getSelectedOption().equalsIgnoreCase("c"))
                llCLayout.setBackgroundColor(getResources().getColor(R.color.answer_wrong_bg_color));
            if (question.getSelectedOption().equalsIgnoreCase("d"))
                llDLayout.setBackgroundColor(getResources().getColor(R.color.answer_wrong_bg_color));
        }
        if (option.getOptionTextA().equals(option.getAnswerText()))
            llALayout.setBackgroundColor(getResources().getColor(R.color.answer_right_bg_color));
        if (option.getOptionTextB().equals(option.getAnswerText()))
            llBLayout.setBackgroundColor(getResources().getColor(R.color.answer_right_bg_color));
        if (option.getOptionTextC().equals(option.getAnswerText()))
            llCLayout.setBackgroundColor(getResources().getColor(R.color.answer_right_bg_color));
        if (option.getOptionTextD().equals(option.getAnswerText()))
            llDLayout.setBackgroundColor(getResources().getColor(R.color.answer_right_bg_color));
    }

    private void insertIntoPracticeProgressTable(List<Question> questions) {
        if (CollectionUtils.isNotEmpty(questions)) {
            List<PracticeProgress> practiceProgressList = practiceProgressUtil.convertUnskilledQuestionsToPracticeProgressList(questions);
            if (CollectionUtils.isNotEmpty(practiceProgressList))
                practiceProgressRepository.savePracticeProgressEntities(practiceProgressList);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (CollectionUtils.isNotEmpty(practiceSkippedAndUnskilledList))
            processPracticeProgress(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG," ***** onPause ~~~~~~~~~~> " +practiceSkippedAndUnskilledList.size());
        /*if (CollectionUtils.isNotEmpty(practiceSkippedAndUnskilledList))
            processPracticeProgress();*/
    }

    private void processPracticeProgress(boolean isPersist) {
        Collections.shuffle(practiceSkippedAndUnskilledList);
        questionList.addAll(practiceSkippedAndUnskilledList);
        if (isPersist)
            insertIntoPracticeProgressTable(practiceSkippedAndUnskilledList);
        practiceSkippedAndUnskilledList.clear();
    }
}

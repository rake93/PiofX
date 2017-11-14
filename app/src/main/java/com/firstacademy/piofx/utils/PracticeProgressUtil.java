package com.firstacademy.piofx.utils;

import com.firstacademy.piofx.data.db.model.PracticeProgress;
import com.firstacademy.piofx.data.db.model.Question;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rakesh Muppa on 15-10-2017.
 */

public class PracticeProgressUtil {

    public List<PracticeProgress> convertUnskilledQuestionsToPracticeProgressList(final List<Question> questionList){
        List<PracticeProgress> practiceProgresses = new ArrayList<>();
        for (Question question:questionList
             ) {
            PracticeProgress practiceProgress = new PracticeProgress();
            practiceProgress.setQuestionId(question.getId());
            practiceProgress.setQuestionText(question.getQuestionText());
            practiceProgress.setQuestionCategory(question.getQuestionCategory());
            practiceProgress.setQuestionSet(question.getQuestionSet());
            practiceProgress.setQuestionClause(question.getQuestionClause());
            practiceProgress.setWord_definition(question.getWord_definition());
            practiceProgress.setWord_description1(question.getWord_description1());
            practiceProgress.setWord_description2(question.getWord_description2());
            practiceProgress.setIsPracticed(question.getIsPracticed());

            practiceProgresses.add(practiceProgress);
        }

        return practiceProgresses;
    }

    public List<Question> convertPracticeEntityToQuestion(final List<PracticeProgress> practiceProgressList){
        List<Question> questionList = null;
        if (CollectionUtils.isNotEmpty(practiceProgressList)){
            questionList = new ArrayList<>();
            for (PracticeProgress practiceProgress:practiceProgressList
                 ) {
                Question question = new Question();
                question.setId(practiceProgress.getQuestionId());
                question.setQuestionText(practiceProgress.getQuestionText());
                question.setQuestionClause(practiceProgress.getQuestionClause());
                question.setQuestionCategory(practiceProgress.getQuestionCategory());
                question.setQuestionSet(practiceProgress.getQuestionSet());
                question.setWord_definition(practiceProgress.getWord_definition());
                question.setWord_description1(practiceProgress.getWord_description1());
                question.setWord_description2(practiceProgress.getWord_description2());
                question.setIsAnswered(practiceProgress.getIsAnswered());
                question.setIsPracticed(practiceProgress.getIsPracticed());

                questionList.add(question);
            }
        }
        return questionList;
    }

}

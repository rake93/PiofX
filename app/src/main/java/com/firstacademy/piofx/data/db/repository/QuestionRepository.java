package com.firstacademy.piofx.data.db.repository;


import android.util.Log;

import com.firstacademy.piofx.data.db.model.DaoSession;
import com.firstacademy.piofx.data.db.model.Question;
import com.firstacademy.piofx.data.db.model.QuestionDao;
import com.firstacademy.piofx.utils.Constants;

import org.apache.commons.collections.CollectionUtils;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by Rakesh Muppa on 15-08-2017.
 */

public class QuestionRepository {

    private final DaoSession mDaoSession;

    public QuestionRepository(DaoSession mDaoSession) {
        this.mDaoSession = mDaoSession;
    }

    public Boolean isQuestionEmpty() {
        return !(mDaoSession.getQuestionDao().count() > 0);
    }

    public Boolean saveQuestion(final Question question) {
        mDaoSession.getQuestionDao().insert(question);
        return true;
    }

    public Boolean saveQuestionList(final List<Question> questionList) {
        mDaoSession.getQuestionDao().insertInTx(questionList);
        return true;
    }

    public Boolean updateQuestion(final Question question) {
        mDaoSession.getQuestionDao().update(question);
        return true;
    }

    public List<Question> getAllQuestions() {
        return mDaoSession.getQuestionDao().loadAll();
    }

    public Question fetchQuestionById(final Long id) {
        return mDaoSession.getQuestionDao().load(id);
    }

    public List<Question> getQuestionsForQuiz(String category) {
        List<Question> questions = mDaoSession.getQuestionDao().queryBuilder()
                .where(QuestionDao.Properties.QuestionCategory.eq(category)).limit(Constants.LIMIT).orderRaw(" RANDOM()").list();
        return CollectionUtils.isNotEmpty(questions) ? questions : null;
    }

    public List<Question> getQuestionsForPractice(String category, String setLevel) {

        QueryBuilder<Question> queryBuilder = mDaoSession.getQuestionDao().queryBuilder();
        queryBuilder.where(QuestionDao.Properties.QuestionCategory.eq(category),
                QuestionDao.Properties.QuestionSet.eq(setLevel),
                QuestionDao.Properties.IsPracticed.eq(false))
                .limit(Constants.LIMIT);
        List<Question> questions = queryBuilder.list();
        return CollectionUtils.isNotEmpty(questions) ? questions : null;
    }

    public List<Question> getPracticedAtLevelCount(String category, String setLevel) {

        QueryBuilder<Question> queryBuilder = mDaoSession.getQuestionDao().queryBuilder();
        queryBuilder.where(QuestionDao.Properties.QuestionCategory.eq(category),
                QuestionDao.Properties.QuestionSet.eq(setLevel),
                QuestionDao.Properties.IsAnswered.eq(true));
        List<Question> questions = queryBuilder.list();
        return CollectionUtils.isNotEmpty(questions) ? questions : null;
    }

    public List<Question> getOverAllPracticedCount(String category) {

        Log.i("getOverAllPracticed" ,"getOverAllPracticedCount *************** " + category);
        QueryBuilder<Question> queryBuilder = mDaoSession.getQuestionDao().queryBuilder();
        queryBuilder.where(QuestionDao.Properties.QuestionCategory.eq(category),
                QuestionDao.Properties.IsAnswered.eq(true));
        List<Question> questions = queryBuilder.list();
        return CollectionUtils.isNotEmpty(questions) ? questions : null;
    }

    public List<Question> getNotPracticedQuestions(String category, String setLevel) {

        QueryBuilder<Question> queryBuilder = mDaoSession.getQuestionDao().queryBuilder();
        queryBuilder.where(QuestionDao.Properties.QuestionCategory.eq(category),
                QuestionDao.Properties.QuestionSet.eq(setLevel),
                QuestionDao.Properties.IsPracticed.eq(false));
        List<Question> questions = queryBuilder.list();
        return CollectionUtils.isNotEmpty(questions) ? questions : null;
    }
}

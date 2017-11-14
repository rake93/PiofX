package com.firstacademy.piofx.data.db.repository;

import com.firstacademy.piofx.data.db.model.DaoSession;
import com.firstacademy.piofx.data.db.model.PracticeProgress;
import com.firstacademy.piofx.data.db.model.PracticeProgressDao;

import org.apache.commons.collections.CollectionUtils;
import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by Rakesh Muppa on 15-10-2017.
 */

public class PracticeProgressRepository {

    private final DaoSession mDaoSession;


    public PracticeProgressRepository(DaoSession mDaoSession) {
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
        this.mDaoSession = mDaoSession;
    }

    public Boolean isPracticeProgressEmptyByQuestionId(final Long questionId) {
        return (mDaoSession.getPracticeProgressDao().queryBuilder().where(PracticeProgressDao.Properties.QuestionId.eq(questionId)).count() > 0);
    }

    public Boolean savePracticeProgressEntity(final PracticeProgress practiceProgress) {
        mDaoSession.getPracticeProgressDao().insertInTx(practiceProgress);
        return true;
    }

    public Boolean savePracticeProgressEntities(final List<PracticeProgress> practiceProgressList) {
        mDaoSession.getPracticeProgressDao().insertInTx(practiceProgressList);
        return true;
    }

    public Boolean updatePracticeProgressEntity(final PracticeProgress practiceProgress) {
        mDaoSession.getPracticeProgressDao().updateInTx(practiceProgress);
        return true;
    }

    public void deletePracticeProgressByQuestionId(final Long questionId) {
        final DeleteQuery<PracticeProgress> practiceProgressDeleteQuery = mDaoSession.getPracticeProgressDao().queryBuilder()
                .where(PracticeProgressDao.Properties.QuestionId.in(questionId))
                .buildDelete();
        practiceProgressDeleteQuery.executeDeleteWithoutDetachingEntities();
    }

    public List<PracticeProgress> fetchPracticeProgressesList(final String category, final String level) {
        List<PracticeProgress> practiceProgresses = mDaoSession.getPracticeProgressDao().queryBuilder()
                .where(PracticeProgressDao.Properties.QuestionCategory.eq(category),
                        PracticeProgressDao.Properties.QuestionSet.eq(level))
                .list();
        return CollectionUtils.isNotEmpty(practiceProgresses) ? practiceProgresses : null;
    }
}

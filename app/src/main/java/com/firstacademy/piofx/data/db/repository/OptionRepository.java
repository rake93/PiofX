package com.firstacademy.piofx.data.db.repository;

import com.firstacademy.piofx.data.db.model.DaoSession;
import com.firstacademy.piofx.data.db.model.Option;
import com.firstacademy.piofx.data.db.model.OptionDao;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Rakesh Muppa on 15-08-2017.
 */

public class OptionRepository {

    private final DaoSession mDaoSession;

    public OptionRepository(DaoSession mDaoSession) {
        this.mDaoSession = mDaoSession;
    }

    public Boolean isOptionEmpty() {
        return !(mDaoSession.getOptionDao().count() > 0);
    }

    public Boolean saveOption(final Option option) {
        mDaoSession.getOptionDao().insertInTx(option);
        return true;
    }

    public Boolean saveOptionList(final List<Option> optionList) {
        mDaoSession.getOptionDao().insertInTx(optionList);
        return true;
    }

    public Option getOptionEntity(Long questionId) {
        return mDaoSession.getOptionDao().queryBuilder().where(OptionDao.Properties.QuestionId.eq(questionId)).unique();
    }
}

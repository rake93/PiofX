/*
 * Copyright (C) 2017 Pi(X) LABS PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.firstacademy.piofx.data.db.repository;

import com.firstacademy.piofx.data.db.model.DaoSession;
import com.firstacademy.piofx.data.db.model.Option;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Rakesh Muppa on 10-08-2017.
 */

public class OptionRepository {

    private final DaoSession mDaoSession;

    @Inject
    public OptionRepository(DaoSession daoSession) {
        mDaoSession = daoSession;
    }

    public Observable<Boolean> isOptionEmpty() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !(mDaoSession.getOptionDao().count() > 0);
            }
        });
    }

    public Observable<Boolean> saveOption(final Option option) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getOptionDao().insertInTx(option);
                return true;
            }
        });
    }

    public Observable<Boolean> saveOptionList(final List<Option> optionList) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getOptionDao().insertInTx(optionList);
                return true;
            }
        });
    }
}

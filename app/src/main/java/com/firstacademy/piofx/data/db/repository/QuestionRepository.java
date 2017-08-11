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
import com.firstacademy.piofx.data.db.model.Question;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Rakesh Muppa on 10-08-2017.
 */

public class QuestionRepository {

    private final DaoSession mDaoSession;

    @Inject
    public QuestionRepository(DaoSession mDaoSession) {
        this.mDaoSession = mDaoSession;
    }

    public Observable<Boolean> isQuestionEmpty() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !(mDaoSession.getQuestionDao().count() > 0);
            }
        });
    }

    public Observable<Boolean> saveQuestion(final Question question) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getQuestionDao().insert(question);
                return true;
            }
        });
    }

    public Observable<Boolean> saveQuestionList(final List<Question> questionList) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getQuestionDao().insertInTx(questionList);
                return true;
            }
        });
    }

    public Observable<List<Question>> getAllQuestions() {
        return Observable.fromCallable(new Callable<List<Question>>() {
            @Override
            public List<Question> call() throws Exception {
                return mDaoSession.getQuestionDao().loadAll();
            }
        });
    }
}

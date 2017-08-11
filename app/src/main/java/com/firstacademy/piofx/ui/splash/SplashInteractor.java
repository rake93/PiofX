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

package com.firstacademy.piofx.ui.splash;

import android.content.Context;

import com.firstacademy.piofx.data.db.model.Option;
import com.firstacademy.piofx.data.db.model.Question;
import com.firstacademy.piofx.data.db.repository.OptionRepository;
import com.firstacademy.piofx.data.db.repository.QuestionRepository;
import com.firstacademy.piofx.di.ApplicationContext;
import com.firstacademy.piofx.ui.base.BaseInteractor;
import com.firstacademy.piofx.util.AppConstants;
import com.firstacademy.piofx.util.FileUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created by Rakesh Muppa on 10-08-2017.
 */

public class SplashInteractor extends BaseInteractor implements SplashMvpInteractor {

    private QuestionRepository mQuestionRepository;
    private OptionRepository mOptionRepository;
    private Context mContext;

    /*@Inject
    public SplashInteractor(@ApplicationContext Context context,
                            PreferencesHelper preferencesHelper,
                            ApiHelper apiHelper,
                            QuestionRepository questionRepository,
                            OptionRepository optionRepository) {

        super(preferencesHelper, apiHelper);
        mContext = context;
        mQuestionRepository = questionRepository;
        mOptionRepository = optionRepository;
    }*/

    @Inject
    public SplashInteractor(@ApplicationContext Context context,
                            QuestionRepository questionRepository,
                            OptionRepository optionRepository) {

        mContext = context;
        mQuestionRepository = questionRepository;
        mOptionRepository = optionRepository;
    }

    @Override
    public Observable<Boolean> seedDatabaseQuestions() {

        GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();

        return mQuestionRepository.isQuestionEmpty()
                .concatMap(new Function<Boolean, ObservableSource<? extends Boolean>>() {
                    @Override
                    public ObservableSource<? extends Boolean> apply(Boolean isEmpty)
                            throws Exception {
                        if (isEmpty) {
                            Type type = $Gson$Types.newParameterizedTypeWithOwner(
                                    null,
                                    List.class,
                                    Question.class);
                            List<Question> questionList = gson.fromJson(
                                    FileUtils.loadJSONFromAsset(
                                            mContext,
                                            AppConstants.SEED_DATABASE_QUESTIONS),
                                    type);

                            return mQuestionRepository.saveQuestionList(questionList);
                        }
                        return Observable.just(false);
                    }
                });
    }

    @Override
    public Observable<Boolean> seedDatabaseOptions() {

        GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();

        return mOptionRepository.isOptionEmpty()
                .concatMap(new Function<Boolean, ObservableSource<? extends Boolean>>() {
                    @Override
                    public ObservableSource<? extends Boolean> apply(Boolean isEmpty)
                            throws Exception {
                        if (isEmpty) {
                            Type type = new TypeToken<List<Option>>() {
                            }.getType();
                            List<Option> optionList = gson.fromJson(
                                    FileUtils.loadJSONFromAsset(
                                            mContext,
                                            AppConstants.SEED_DATABASE_OPTIONS),
                                    type);
                            return mOptionRepository.saveOptionList(optionList);
                        }
                        return Observable.just(false);
                    }
                });
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return 0;
    }

    /*@Override
    public int getCurrentUserLoggedInMode() {
        return getPreferencesHelper().getCurrentUserLoggedInMode();
    }*/
}

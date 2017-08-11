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

import com.firstacademy.piofx.R;
import com.firstacademy.piofx.ui.base.BasePresenter;
import com.firstacademy.piofx.util.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.ObservableSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by Rakesh Muppa on 10-08-2017.
 */

public class SplashPresenter<V extends SplashMvpView, I extends SplashMvpInteractor>
        extends BasePresenter<V,I> implements SplashMvpPresenter<V,I>{

    @Inject
    public SplashPresenter(I mvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(mvpInteractor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);

        getMvpView().startSyncService();

        getCompositeDisposable().add(getInteractor()
                .seedDatabaseQuestions()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui()).concatMap(new Function<Boolean, ObservableSource<Boolean>>() {
                    @Override
                    public ObservableSource<Boolean> apply(Boolean aBoolean) throws Exception {
                        return getInteractor().seedDatabaseOptions();
                    }
                })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        decideNextActivity();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        getMvpView().onError(R.string.some_error);
                        decideNextActivity();
                    }
                })) ;


    }

    private void decideNextActivity() {
        /*if (getInteractor().getCurrentUserLoggedInMode()
                == AppConstants.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType()) {
            getMvpView().openLoginActivity();
        } else {
            getMvpView().openMainActivity();
        }*/
        getMvpView().openMainActivity();
    }
}

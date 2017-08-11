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

package com.firstacademy.piofx.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.firstacademy.piofx.di.ActivityContext;
import com.firstacademy.piofx.di.PerActivity;
import com.firstacademy.piofx.ui.splash.SplashInteractor;
import com.firstacademy.piofx.ui.splash.SplashMvpInteractor;
import com.firstacademy.piofx.ui.splash.SplashMvpPresenter;
import com.firstacademy.piofx.ui.splash.SplashMvpView;
import com.firstacademy.piofx.ui.splash.SplashPresenter;
import com.firstacademy.piofx.util.rx.AppSchedulerProvider;
import com.firstacademy.piofx.util.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Rakesh Muppa on 08-08-2017.
 */
@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity mAppCompatActivity) {
        this.mActivity = mAppCompatActivity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() { return new AppSchedulerProvider();}

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView, SplashMvpInteractor> provideSplashPresenter(
            SplashPresenter<SplashMvpView, SplashMvpInteractor> presenter) {
        return presenter;
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    @PerActivity
    SplashMvpInteractor provideSplashMvpInteractor(SplashInteractor interactor) {
        return interactor;
    }
}

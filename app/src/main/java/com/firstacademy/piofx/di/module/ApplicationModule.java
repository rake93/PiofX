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

import android.app.Application;
import android.content.Context;

import com.firstacademy.piofx.R;
import com.firstacademy.piofx.data.db.DbOpenHelper;
import com.firstacademy.piofx.data.db.model.DaoMaster;
import com.firstacademy.piofx.data.db.model.DaoSession;
import com.firstacademy.piofx.di.ApplicationContext;
import com.firstacademy.piofx.di.DatabaseInfo;
import com.firstacademy.piofx.di.PreferenceInfo;
import com.firstacademy.piofx.util.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Rakesh Muppa on 07-08-2017.
 */
@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext(){ return mApplication;}

    @Provides
    Application provideApplication(){ return mApplication;}

    @Provides
    @DatabaseInfo
    String provideDatabaseName(){ return AppConstants.DB_NAME;}

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Open_Sans/OpenSans-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }

    @Provides
    @Singleton
    DaoSession provideDaoSession(DbOpenHelper dbOpenHelper) {
        return new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }

}

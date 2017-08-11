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

package com.firstacademy.piofx.di.component;

import com.firstacademy.piofx.di.PerActivity;
import com.firstacademy.piofx.di.module.ActivityModule;
import com.firstacademy.piofx.ui.main.MainActivity;
import com.firstacademy.piofx.ui.splash.SplashActivity;

import dagger.Component;

/**
 * Created by Rakesh Muppa on 08-08-2017.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(SplashActivity activity);
}

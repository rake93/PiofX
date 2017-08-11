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

package com.firstacademy.piofx.ui.base;

import com.firstacademy.piofx.util.AppConstants;

/**
 * Created by Rakesh Muppa on 10-08-2017.
 */

public class BaseInteractor implements MvpInteractor {


    /*
    Need to implement API and preference code.
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;
    */

    @Override
    public void setUserAsLoggedOut() {

    }

    @Override
    public void setAccessToken(String accessToken) {

    }

    @Override
    public void updateUserInfo(String accessToken, Long userId, AppConstants.LoggedInMode loggedInMode, String userName, String email, String profilePicPath) {

    }

    @Override
    public void updateApiHeader(Long userId, String accessToken) {

    }
}

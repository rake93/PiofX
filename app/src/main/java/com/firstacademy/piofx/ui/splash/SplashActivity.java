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
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.firstacademy.piofx.R;
import com.firstacademy.piofx.ui.base.BaseActivity;
import com.firstacademy.piofx.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity implements SplashMvpView{

    @BindView(R.id.lin_lay)
    LinearLayout mLinearLayout;

    @BindView(R.id.splash)
    ImageView mImageView;

    Thread splashTread;

    @Inject
    SplashMvpPresenter<SplashMvpView, SplashMvpInteractor> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        return intent;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        onAttachedToWindow();
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        StartAnimations();
        mPresenter.onAttach(this);
    }

    private void StartAnimations() {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        loadAnimation.reset();
        mLinearLayout.clearAnimation();
        mLinearLayout.startAnimation(loadAnimation);

        loadAnimation = AnimationUtils.loadAnimation(this, R.anim.translate);
        loadAnimation.reset();
        mImageView.clearAnimation();
        mImageView.startAnimation(loadAnimation);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (waited < 3500) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = SplashActivity.getStartIntent(SplashActivity.this);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashActivity.this.finish();
                } catch (InterruptedException e) {

                } finally {
                    SplashActivity.this.finish();
                }

            }
        };
        splashTread.start();

    }

    @Override
    public void openMainActivity() {
        //Intent intent = MainActivity.getStartIntent(SplashActivity.this);
        startActivity(MainActivity.getStartIntent(SplashActivity.this));
        finish();
    }

    @Override
    public void startSyncService() {
        //SyncService.start(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

    }
}

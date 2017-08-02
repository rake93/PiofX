package com.firstacademy.piofx;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Splashscreen extends AppCompatActivity {

    /*Thread splashTread;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        imageView = (ImageView)findViewById(R.id.splash);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        //int[] ids = new int[]{R.drawable.s_img,R.drawable.s_image_black, R.drawable.s_image_black2};
        //Random randomGenerator = new Random();
        //int r= randomGenerator.nextInt(ids.length);
        //this.imageView.setImageDrawable(getResources().getDrawable(ids[r]));

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3500) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(Splashscreen.this,
                            MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    Splashscreen.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    Splashscreen.this.finish();
                }

            }
        };
        splashTread.start();
    }*/

    @Inject
    CalligraphyConfig calligraphyConfig;

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    Thread splashTread;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        CalligraphyConfig.initDefault(calligraphyConfig);

        StartAnimations();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void StartAnimations() {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        loadAnimation.reset();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lin_lay);
        linearLayout.clearAnimation();
        linearLayout.startAnimation(loadAnimation);

        loadAnimation = AnimationUtils.loadAnimation(this, R.anim.translate);
        loadAnimation.reset();
        ImageView imageView = (ImageView) findViewById(R.id.splash);
        imageView.clearAnimation();
        imageView.startAnimation(loadAnimation);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (waited < 3500) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(Splashscreen.this,
                            MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    Splashscreen.this.finish();
                } catch (InterruptedException e) {

                } finally {
                    Splashscreen.this.finish();
                }

            }
        };
        splashTread.start();

    }
}

package com.firstacademy.piofx.activities;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.firstacademy.piofx.PiofXApp;
import com.firstacademy.piofx.R;
import com.firstacademy.piofx.data.db.model.DaoSession;
import com.firstacademy.piofx.data.db.model.Option;
import com.firstacademy.piofx.data.db.model.Question;
import com.firstacademy.piofx.data.db.repository.OptionRepository;
import com.firstacademy.piofx.data.db.repository.QuestionRepository;
import com.firstacademy.piofx.utils.Constants;
import com.firstacademy.piofx.utils.FileUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SplashActivity extends AppCompatActivity {

    private QuestionRepository mQuestionRepository;
    private OptionRepository mOptionRepository;

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
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

         /*to make the screen as full screen*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        CalligraphyConfig.initDefault(calligraphyConfig);

        DaoSession daoSession = ((PiofXApp) getApplication()).getDaoSession();
        mQuestionRepository = new QuestionRepository(daoSession);
        mOptionRepository = new OptionRepository(daoSession);

        /*if (((PiofXApp) getApplication()).doesDatabaseExist(Constants.DB_NAME))
            getApplication().deleteDatabase(Constants.DB_NAME);*/

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

        loadAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
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
                    Intent intent = new Intent(SplashActivity.this,
                            Home.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    try {
                        seedDatabaseQuestions();
                        seedDatabaseOptions();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    SplashActivity.this.finish();
                } catch (InterruptedException e) {

                } finally {
                    SplashActivity.this.finish();
                }

            }
        };
        splashTread.start();

    }

    public Boolean seedDatabaseQuestions() throws IOException {

        Log.i("seedDatabaseQuestions", "********** seedDatabaseQuestions ****** ");
        GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();
        Boolean isEmpty = mQuestionRepository.isQuestionEmpty();

        if (isEmpty) {
            Log.i("isEmpty", "********** isEmpty ****** seedDatabaseQuestions");
            Type type = $Gson$Types.newParameterizedTypeWithOwner(
                    null,
                    List.class,
                    Question.class);
            List<Question> questionList = gson.fromJson(
                    FileUtils.loadJSONFromAsset(
                            getApplicationContext(),
                            Constants.SEED_DATABASE_QUESTIONS),
                    type);
            Log.i("questionList", "********** questionList from json ****** " + questionList.size());
            return mQuestionRepository.saveQuestionList(questionList);
        }
        return false;
    }

    public Boolean seedDatabaseOptions() throws IOException {

        Log.i("seedDatabaseOptions", "********** seedDatabaseOptions ****** ");
        GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();
        Boolean isEmpty = mOptionRepository.isOptionEmpty();
        if (isEmpty) {
            Log.i("isEmpty", "********** isEmpty seedDatabaseOptions****** ");
            Type type = new TypeToken<List<Option>>() {
            }.getType();
            List<Option> optionList = gson.fromJson(
                    FileUtils.loadJSONFromAsset(
                            getApplicationContext(),
                            Constants.SEED_DATABASE_OPTIONS),
                    type);
            Log.i("optionList", "********** optionList from json ****** " + optionList.size());
            return mOptionRepository.saveOptionList(optionList);
        }
        return false;
    }
}

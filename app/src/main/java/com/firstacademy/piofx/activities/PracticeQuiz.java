package com.firstacademy.piofx.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.firstacademy.piofx.R;
import com.firstacademy.piofx.fragments.AboutUs;
import com.firstacademy.piofx.fragments.HomeFragment;
import com.firstacademy.piofx.fragments.Practice;
import com.firstacademy.piofx.fragments.Profile;
import com.firstacademy.piofx.fragments.Quiz;
import com.firstacademy.piofx.utils.Constants;

public class PracticeQuiz extends AppCompatActivity implements View.OnClickListener {
    private FragmentTransaction fragmentTransaction;
    private Toolbar toolbar;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_quiz);

        initializeViews();
        initializeClickListeners();

        if (getIntent().getStringExtra("intent").equals("aboutus")) {
            Constants.homeAdapterId = 8;
            fragmentTransaction.replace(R.id.practice_quiz_container_body, new Quiz());
            setSupportActionBar(toolbar);
            fragmentTransaction.commit();
            Constants.vocabularyTitle = getResources().getString(R.string.team);
            Constants.image = R.drawable.team;
            Constants.backGroundColor = getResources().getColor(R.color.colorMediumTurquoise);
            ;
        }

    }


    private void initializeViews() {
        toolbar = (Toolbar) findViewById(R.id.practice_quiz_toolbar);
        backButton = (ImageView) findViewById(R.id.practice_quiz_toolbar_back);
        loadDefaultFragment();
    }


    private void initializeClickListeners() {
        backButton.setOnClickListener(this);
    }

    private void loadDefaultFragment() {
        String receivingIntent = getIntent().getStringExtra("intent");
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (receivingIntent.equals("practice")) {
            Constants.back = "home";
            fragmentTransaction.replace(R.id.practice_quiz_container_body, new Practice());
            setSupportActionBar(toolbar);
            fragmentTransaction.addToBackStack("practice").commit();
        } else if (receivingIntent.equals("quiz")) {
            Constants.skip = "quiz";
            Constants.back = "quiz";
            fragmentTransaction.replace(R.id.practice_quiz_container_body, new Quiz());
            setSupportActionBar(toolbar);
            fragmentTransaction.addToBackStack("quiz").commit();
        } else if (receivingIntent.equals("practiceAdapter")) {
            Constants.skip = "practiceAdapter";
            Constants.back = "practice";
            Constants.practiceLevel = getIntent().getIntExtra("practiceLevelId", 0);
            fragmentTransaction.replace(R.id.practice_quiz_container_body, new Quiz());
            setSupportActionBar(toolbar);
            fragmentTransaction.addToBackStack("practiceAdapter").commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.practice_quiz_container_body, new AboutUs());
            setSupportActionBar(toolbar);
            fragmentTransaction.commit();
        }
        if (id == R.id.action_home) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.practice_quiz_container_body, new HomeFragment());
            setSupportActionBar(toolbar);
            fragmentTransaction.commit();
        }
        if (id == R.id.action_profile) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.practice_quiz_container_body, new Profile());
            setSupportActionBar(toolbar);
            fragmentTransaction.commit();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.practice_quiz_toolbar_back:
                if (Constants.back.equals("home")) {
                    Constants.back = null;
                    startActivity(new Intent(PracticeQuiz.this, Home.class));
                } else if (Constants.skip.equals("practiceAdapter") && Constants.back.equals("practice")) {
                    startActivity(new Intent(getApplicationContext(), PracticeQuiz.class).putExtra("intent", "practice"));
                    Constants.back = "home";
                } else
                    PracticeQuiz.this.finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(PracticeQuiz.this, Home.class));
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}

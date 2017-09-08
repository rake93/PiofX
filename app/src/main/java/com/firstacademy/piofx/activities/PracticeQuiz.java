package com.firstacademy.piofx.activities;

import android.app.ActionBar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

public class PracticeQuiz extends AppCompatActivity implements View.OnClickListener{
    private FragmentTransaction fragmentTransaction;
    private Toolbar toolbar;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_quiz);
    /*    ActionBar actionBar=getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);*/

        initializeViews();
        initializeClickListeners();

    }


    private void initializeViews() {
        toolbar=(Toolbar)findViewById(R.id.practice_quiz_toolbar);
        backButton=(ImageView) findViewById(R.id.practice_quiz_toolbar_back);
        loadDefaultFragment();
    }


    private void initializeClickListeners() {
        backButton.setOnClickListener(this);
    }

    private void loadDefaultFragment() {
        String receiveingIntent=getIntent().getStringExtra("intent");
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(receiveingIntent.equals("practice")) {
            fragmentTransaction.replace(R.id.practice_quiz_container_body, new Practice());
            setSupportActionBar(toolbar);
            fragmentTransaction.commit();
        }else if(receiveingIntent.equals("quiz")){
            Constants.skip="quiz";
            fragmentTransaction.replace(R.id.practice_quiz_container_body, new Quiz());
            setSupportActionBar(toolbar);
            fragmentTransaction.commit();
        }else if(receiveingIntent.equals("practiceAdapter")){
            Constants.skip="practiceAdapter";
            fragmentTransaction.replace(R.id.practice_quiz_container_body, new Quiz());
            setSupportActionBar(toolbar);
            fragmentTransaction.commit();
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
        switch (view.getId()){
            case R.id.practice_quiz_toolbar_back:
                PracticeQuiz.this.finish();
                break;
        }
    }
}

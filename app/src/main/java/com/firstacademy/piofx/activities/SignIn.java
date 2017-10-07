package com.firstacademy.piofx.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firstacademy.piofx.R;
import com.firstacademy.piofx.utils.Fonts;

public class SignIn extends AppCompatActivity implements View.OnClickListener{
    private Button signInLayout;
    private EditText etEmail,etPassword;
    private Button signIn,signInWithgoogle,signInWithFB;
    private Boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initializeViews();
        initializeClickListeners();
        setFontForViews();
    }


    private void initializeViews() {
        signInLayout=(Button)findViewById(R.id.signin_bnSignin);
        etEmail=(EditText)findViewById(R.id.signin_teEmail);
        etPassword=(EditText)findViewById(R.id.signin_etPassword);

        signIn=(Button) findViewById(R.id.signin_bnSignin);
        signInWithgoogle=(Button) findViewById(R.id.signin_bnGplus);
        signInWithFB=(Button) findViewById(R.id.signin_bnFB);

    }

    private void initializeClickListeners() {
        signInLayout.setOnClickListener(this);
    }

    private void setFontForViews() {
        Fonts.setFont(etEmail, Fonts.opensansRegular, "EditText");
        Fonts.setFont(etPassword, Fonts.opensansRegular, "EditText");

        Fonts.setFont(signIn,Fonts.patua,"Button");
        Fonts.setFont(signInWithgoogle,Fonts.patua,"Button");
        Fonts.setFont(signInWithFB,Fonts.patua,"Button");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signin_bnSignin:
                startActivity(new Intent(SignIn.this,Home.class));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
      /*  if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }*/

    }
}

package com.firstacademy.piofx.activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.firstacademy.piofx.R;
import com.firstacademy.piofx.utils.Fonts;

public class SignUp extends AppCompatActivity implements View.OnClickListener{
    private RelativeLayout backLayout;
    private EditText etName,etEmail,etPassword,etRetypePwd;
    private Button signUp,signupWithgoogle,signupWithFB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initializeViews();
        initializeClickListeners();
        setFontForViews();
    }


    private void initializeViews() {
        backLayout=(RelativeLayout)findViewById(R.id.signup_backLayout);

        etName=(EditText) findViewById(R.id.signup_teName);
        etEmail=(EditText) findViewById(R.id.signup_teEmail);
        etPassword=(EditText) findViewById(R.id.signup_tePassword);
        etRetypePwd=(EditText) findViewById(R.id.signup_teRetypePassword);
        signUp=(Button) findViewById(R.id.signup_bnSignup);
        signupWithgoogle=(Button) findViewById(R.id.signup_bnGplus);
        signupWithFB=(Button) findViewById(R.id.signup_bnFB);

    }

    private void initializeClickListeners() {
        backLayout.setOnClickListener(this);
    }


    private void setFontForViews() {
        Fonts.setFont(etName,Fonts.opensansRegular,"EditText");
        Fonts.setFont(etEmail,Fonts.opensansRegular,"EditText");
        Fonts.setFont(etPassword,Fonts.opensansRegular,"EditText");
        Fonts.setFont(etRetypePwd,Fonts.opensansRegular,"EditText");

        Fonts.setFont(signUp,Fonts.patua,"Button");
        Fonts.setFont(signupWithgoogle,Fonts.patua,"Button");
        Fonts.setFont(signupWithFB,Fonts.patua,"Button");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signup_backLayout:
                startActivity(new Intent(SignUp.this,Home.class));
                break;
        }
    }
}

package com.firstacademy.piofx.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firstacademy.piofx.R;
import com.firstacademy.piofx.utils.Constants;
import com.firstacademy.piofx.utils.Fonts;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SignIn";

    private Button signInLayout;
    private EditText etEmail, etPassword;
    private Button signIn, signInWithgoogle, signInWithFB;
    private Boolean exit = false;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initializeViews();
        initializeClickListeners();
        setFontForViews();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        //FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        //Log.i(TAG, " onStart current User ~~~~~~~~> " + currentUser.getDisplayName());
        /*if (currentUser != null){
            Constants.userId = currentUser.getUid();
            Constants.email = currentUser.getEmail();
            Constants.userName = currentUser.getDisplayName();
            Constants.isLoggedIn = true;
            Log.i(TAG," onStart current User ~~~~~~~~> " + currentUser.getDisplayName() + " **** " + currentUser.getEmail());
            startActivity(new Intent(SignIn.this,Home.class));
        }else*/
    }

    private void initializeViews() {
        signInLayout = (Button) findViewById(R.id.signin_bnSignin);
        etEmail = (EditText) findViewById(R.id.signin_teEmail);
        etPassword = (EditText) findViewById(R.id.signin_etPassword);
        progressBar = (ProgressBar) findViewById(R.id.signIn_progressBar);
        signIn = (Button) findViewById(R.id.signin_bnSignin);
        signInWithgoogle = (Button) findViewById(R.id.signin_bnGplus);
        signInWithFB = (Button) findViewById(R.id.signin_bnFB);

    }

    private void initializeClickListeners() {
        signInLayout.setOnClickListener(this);
    }

    private void setFontForViews() {
        Fonts.setFont(etEmail, Fonts.opensansRegular, "EditText");
        Fonts.setFont(etPassword, Fonts.opensansRegular, "EditText");

        Fonts.setFont(signIn, Fonts.patua, "Button");
        Fonts.setFont(signInWithgoogle, Fonts.patua, "Button");
        Fonts.setFont(signInWithFB, Fonts.patua, "Button");
    }

    private void signIn() {
        Log.i(TAG, " signIn() called............");

        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();

        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        //authenticate user
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        progressBar.setVisibility(View.GONE);
                        Log.i(TAG, "task.isSuccessful()........" + task.isSuccessful() + "@@@@@@@@@ " + task.getException());
                        if (!task.isSuccessful()) {
                            // there was an error
                            if (password.length() < 6) {
                                etPassword.setError(getString(R.string.minimum_password));
                            } else {
                                Toast.makeText(SignIn.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                            if (currentUser != null) {
                                Constants.userId = currentUser.getUid();
                                Constants.email = currentUser.getEmail();
                                Constants.userName = currentUser.getDisplayName();
                                Constants.isLoggedIn = true;
                                Log.i(TAG, " Current User details ~~~~~> " + Constants.userName + " ***** " + Constants.email);
                                startActivity(new Intent(SignIn.this,Home.class));
                                finish();
                            }
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signin_bnSignin:
                signIn();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = etEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            etEmail.setError(" Enter email address..!");
            valid = false;
        } else {
            etEmail.setError(null);
        }

        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Enter password..!");
            valid = false;
        } else {
            etPassword.setError(null);
        }

        return valid;
    }

}

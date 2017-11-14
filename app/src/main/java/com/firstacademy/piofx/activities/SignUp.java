package com.firstacademy.piofx.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.firstacademy.piofx.R;
import com.firstacademy.piofx.data.db.model.online.User;
import com.firstacademy.piofx.utils.Constants;
import com.firstacademy.piofx.utils.Fonts;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SignUp";

    private RelativeLayout backLayout;
    private EditText etName, etEmail, etMobile, etPassword, etRetypePwd;
    private TextInputLayout tILEmail, tILMobile, tILPassword, tILRePassword;
    private Button signUp, signupWithgoogle, signupWithFB;
    private LoginButton loginButton;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // [START initialize_fblogin]
        // Initialize Facebook Login button
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        mCallbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_sign_up);

        initializeViews();
        initializeClickListeners();
        setFontForViews();

        loginButton = (LoginButton) findViewById(R.id.fb_login_button);
        loginButton.setReadPermissions("email", "public_profile");

        signupWithFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButton.performClick();
                Log.i(TAG," Calling Facebook signup methos ~~~~~~~> ");
                facebookSignUp();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check auth on Activity start
        /*FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null)
            onAuthSuccess(currentUser);*/
    }

    private void onAuthSuccess(FirebaseUser user) {
        String username = user.getDisplayName() == null ? usernameFromEmail(user.getEmail()) : user.getDisplayName();
        // Write new user
        writeNewUser(user.getUid(), username, user.getEmail(), user.getPhotoUrl(),Constants.mobile);

        /*Constants.userId = user.getUid();
        Constants.email = user.getEmail();
        Constants.userName = user.getDisplayName();
        Constants.isLoggedIn = true;
        */
        startActivity(new Intent(SignUp.this, SignIn.class));
        finish();
    }

    private void initializeViews() {
        backLayout = (RelativeLayout) findViewById(R.id.signup_backLayout);

        etName = (EditText) findViewById(R.id.signup_teName);
        etMobile = (EditText) findViewById(R.id.signup_teMobile);
        etEmail = (EditText) findViewById(R.id.signup_teEmail);
        etPassword = (EditText) findViewById(R.id.signup_tePassword);
        etRetypePwd = (EditText) findViewById(R.id.signup_teRetypePassword);

        tILEmail = (TextInputLayout) findViewById(R.id.signup_tlEmail);
        tILMobile = (TextInputLayout) findViewById(R.id.signup_tlMobile);
        tILPassword = (TextInputLayout) findViewById(R.id.signup_tlPassword);
        tILRePassword = (TextInputLayout) findViewById(R.id.signup_tlRetypePassword);

        signUp = (Button) findViewById(R.id.signup_bnSignup);
        signupWithgoogle = (Button) findViewById(R.id.signup_bnGplus);
        signupWithFB = (Button) findViewById(R.id.signup_bnFB);
        progressBar = (ProgressBar) findViewById(R.id.signUp_progressBar);

    }

    private void initializeClickListeners() {
        backLayout.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    private void setFontForViews() {
        Fonts.setFont(etName, Fonts.opensansRegular, "EditText");
        Fonts.setFont(etMobile, Fonts.opensansRegular, "EditText");
        Fonts.setFont(etEmail, Fonts.opensansRegular, "EditText");
        Fonts.setFont(etPassword, Fonts.opensansRegular, "EditText");
        Fonts.setFont(etRetypePwd, Fonts.opensansRegular, "EditText");
        Fonts.setFont(signUp, Fonts.patua, "Button");
        Fonts.setFont(signupWithgoogle, Fonts.patua, "Button");
        Fonts.setFont(signupWithFB, Fonts.patua, "Button");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signup_backLayout:
                startActivity(new Intent(SignUp.this, Home.class));
                break;
            case R.id.signup_bnSignup:
                signUp();
            /*case R.id.signup_bnFB:
               facebookSignUp();*/
        }
    }

    private void signUp() {
        final String fullName = etName.getText().toString().trim();
        final String email = etEmail.getText().toString().trim();
        final String mobile = etMobile.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();
        if (!validateForm()) {
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        //create user
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(SignUp.this, "Successfully created user in Firebase" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            updateProfileDetails(fullName);
                            Constants.mobile = Integer.parseInt(mobile);
                            onAuthSuccess(task.getResult().getUser());
                        } else {
                            Toast.makeText(SignUp.this, "SignUp failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void facebookSignUp() {
        Log.i(TAG," facebookSignUp method ~~~~~~~> ");
        loginButton.registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        String accessToken = loginResult.getAccessToken()
                                .getToken();
                        Log.i(TAG," facebookSignUp method onSuccess ~~~~~~~> " + accessToken);
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object,
                                                            GraphResponse response) {
                                        try {
                                            Constants.userId = object.getString("id");
                                            try {
                                                URL profile_pic = new URL(
                                                        "http://graph.facebook.com/" + Constants.userId + "/picture?type=large");
                                                Log.i("profile_pic",
                                                        profile_pic + "");
                                                Log.i(TAG," facebookSignUp method onSuccess ~~~~~~~> " + profile_pic);
                                            } catch (MalformedURLException e) {
                                                e.printStackTrace();
                                            }
                                            Constants.userName = object.getString("name");
                                            Constants.email = object.getString("email");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields",
                                "id,name,email,gender, birthday");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        System.out.println("onCancel");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        System.out.println("onError");
                        Log.v("LoginActivity", exception.getCause().toString());
                    }
                });
    }

    // [START basic_write]
    private void writeNewUser(String userId, String name, String email, Uri picUrl,int mobile) {
        String photoURL = picUrl != null ? picUrl.toString() : null;
        User user = new User(name, email, photoURL,mobile);
        mDatabase.child("users").child(userId).setValue(user);
    }
    // [END basic_write]

    private void updateProfileDetails(final String fullName) {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(fullName)
                .build();
        FirebaseUser profile = mAuth.getCurrentUser();
        profile.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "User Display Name updated.....!");
                }
            }
        });

    }

    private boolean validateForm() {
        boolean valid = true;

        ForegroundColorSpan fgcspan = new ForegroundColorSpan(getResources().getColor(R.color.oopsie_color));
        SpannableStringBuilder ssbuilder = new SpannableStringBuilder();
        ssbuilder.append("Email can't be blank..!");
        ssbuilder.setSpan(fgcspan, 0, ssbuilder.length(), 0);
        etEmail.setError(ssbuilder);

        String email = etEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            tILEmail.setError("Invalid Email..!");
            valid = false;
        } else if (!isValidEmail(email)) {
            /*ssbuilder.append("Please enter correct email id..!");
            ssbuilder.setSpan(fgcspan, 0, ssbuilder.length(), 0);
            etEmail.setError(ssbuilder);*/
            tILPassword.setError("Invalid Password...!");
            valid = false;
        } else {
            etEmail.setError(null);
        }

        String password = etPassword.getText().toString();
        String rePassword = etRetypePwd.getText().toString();
        if (TextUtils.isEmpty(password) || !isValidPassword(password)) {
            tILPassword.setError("Password is too short...!");
            /*ssbuilder.append("Password too short, enter minimum 6 characters..!");
            ssbuilder.setSpan(fgcspan, 0, ssbuilder.length(), 0);
            etPassword.setError(ssbuilder);*/
            valid = false;
        } else if (!password.equals(rePassword)) {
            tILRePassword.setError("Password didn't match..!");
            /*ssbuilder.append();
            ssbuilder.setSpan(fgcspan, 0, ssbuilder.length(), 0);
            etPassword.setError(ssbuilder);*/
            valid = false;
        } else {
            etPassword.setError(null);
            etRetypePwd.setError(null);
        }
        return valid;
    }


    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 6) {
            return true;
        }
        return false;
    }

    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}

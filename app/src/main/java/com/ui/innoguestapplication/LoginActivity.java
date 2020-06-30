package com.ui.innoguestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import com.google.android.material.textfield.TextInputLayout;
import com.ui.innoguestapplication.backend.APIRequests;
import com.ui.innoguestapplication.backend.RespUser;
import com.ui.innoguestapplication.backend.ResponseRest;
import com.ui.innoguestapplication.sqlite_database.LocalSettingsStorage;
import com.ui.innoguestapplication.sqlite_database.LoginData;
import com.ui.innoguestapplication.sqlite_database.LoginLocalDatabase;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    LoginLocalDatabase loginLocalDatabase;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    //"(?=.*[@#$%^&+=])" +    //at least 1 special character (no need to use it)
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");
    private TextInputLayout til_email;
    private TextInputLayout til_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        til_email = findViewById(R.id.text_input_layout_email);
        til_password = findViewById(R.id.text_input_layout_password);
        final EditText text_email = findViewById(R.id.login_email_et);
        final EditText text_password = findViewById(R.id.login_password_et);
        ImageView image = findViewById(R.id.picture_login);
        Button login_button = findViewById(R.id.login_button);


        loginLocalDatabase = LoginLocalDatabase.getLoginLocalDatabase(getBaseContext());


        LoginData preloadedData = loginLocalDatabase.getLoginDataOrNull();

        if (preloadedData != null) {

            loginWithPreloaded(preloadedData);

        }

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginData loadedLoginData = new LoginData(text_email.getText().toString(), text_password.getText().toString());

                loginLocalDatabase.setLoginData(loadedLoginData);
                login(loadedLoginData);
            }
        });


        text_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //validateEmail();
                //uncomment to check after every letter
            }
        });

        text_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validateEmail();
                }
            }
        });

        text_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // validatePassword();
                //uncomment to check after every letter

            }
        });

        text_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validatePassword();
                }
            }
        });


    }


    private void login(LoginData loginData) {

        if (validateEmail() && validatePassword()) {
            APIRequests.checkValidityOfUser(loginData, new Callback<ResponseRest>() {
                @Override
                public void onResponse(Call<ResponseRest> call, Response<ResponseRest> response) {
                    //this is temporary
                    if (checkValidity(response.body())) {
                        Intent intent = new Intent(getApplicationContext(), BottomNavigatorControllerActivity.class);
                        String intentAction = getIntent().getAction();


                        intent.setAction(intentAction);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<ResponseRest> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }

    }

    private void loginWithPreloaded(LoginData loginData) {
        APIRequests.checkValidityOfUser(loginData, new Callback<ResponseRest>() {
            @Override
            public void onResponse(Call<ResponseRest> call, Response<ResponseRest> response) {
                //this is temporary

                Intent intent = new Intent(getApplicationContext(), BottomNavigatorControllerActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseRest> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    // some shitty method to validate auth response ¯\_(ツ)_/¯
    private boolean checkValidity(ResponseRest response) {
        if (response != null) {
            try {
                if (response.getError_request() == 1) { // bad request
                    Log.d("LOGVAL", "bad request");
                    return false;
                } else {
                    switch (response.getBody().getSuccess()) {
                        case 0: {  //onError
                            switch (response.getBody().getError()) {
                                case 1: { // email not found
                                    Log.d("LOGVAL", "email nnot found");
                                    return false;
                                }
                                case 2: { // incorrect password
                                    Log.d("LOGVAL", "incorrect pass");
                                    return false;
                                }
                            }
                        }
                        case 1: { //onSuccess
                            String token = response.getBody().getData().getToken();
                            RespUser user = response.getBody().getData().getUser();
                            //save token & userData
                            Log.d("LOGVAL", "Ok");
                            return true;
                        }
                    }
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
                Log.d("LOGVAL", "exception & ");
                return false;
            }
        } else {
            // network error
            Log.d("LOGVAL", "network err");
            return false;
        }

        Log.d("LOGVAL", "nothing");
        return false;
    }


    private boolean validateEmail() {
        String emailInput = til_email.getEditText().getText().toString().trim();
        if (emailInput.isEmpty()) {
            til_email.setError(getString(R.string.empty_field));
            til_email.setErrorEnabled(true);
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            til_email.setError(getString(R.string.email_error));
            til_email.setErrorEnabled(true);

            return false;
        } else {
            til_email.setError(null);
            til_email.setErrorEnabled(false);

            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = til_password.getEditText().getText().toString().trim();
        if (passwordInput.isEmpty()) {
            til_password.setError(getString(R.string.empty_field));
            til_password.setErrorEnabled(true);
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            til_password.setError(getString(R.string.password_error));
            til_password.setErrorEnabled(true);

            return false;
        } else {
            til_password.setError(null);
            til_password.setErrorEnabled(false);

            return true;
        }
    }

    @Override
    public void onBackPressed() {
        // do nothing
    }

    @Override
    protected void onDestroy() {
        loginLocalDatabase.getDbHelper().close();

        super.onDestroy();
    }

}
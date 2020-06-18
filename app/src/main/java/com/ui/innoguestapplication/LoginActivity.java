package com.ui.innoguestapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.ui.innoguestapplication.backend.APIRequests;
import com.ui.innoguestapplication.fragments.FAQFragment;
import com.ui.innoguestapplication.fragments.MapFragment;
import com.ui.innoguestapplication.fragments.MenuFragment;
import com.ui.innoguestapplication.fragments.SceduleFragment;
import com.ui.innoguestapplication.fragments.SettingsFragment;
import com.ui.innoguestapplication.sqlite_database.LoginData;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity  {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
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

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(new LoginData(text_email.getText().toString(),text_password.getText().toString()));
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
            if(APIRequests.checkValidityOfUser(loginData)){
                //this is temporary
                Intent intent = new Intent(this, BottomNavigatorControllerActivity.class);
                startActivity(intent);
            }
        }




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


}
package com.example.listreminder_mid;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;



public class SignUp_fragment extends Fragment {

    // initializing variable
    EditText etUsername, etPassword,etConfirmPass, etUserID;
    Button BtnSignUp;
    float v = 0;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_signup_fragment, container, false);

        // identifies the variable from the id that has been set
        etUserID = (EditText)root.findViewById(R.id.userID_signup);
        etUsername = (EditText)root.findViewById(R.id.username_signup);
        etPassword = (EditText)root.findViewById(R.id.password_signup);
        etConfirmPass = (EditText)root.findViewById(R.id.confirm_signup);
        BtnSignUp = (Button)root.findViewById(R.id.SignupBtn);

        // check confirm pass when click SignUp
        etConfirmPass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    Check();
                    return true;
                }
                return false;
            }
        });

        // click SignUp
        BtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check();
            }
        });

        // animation
        etUserID.setTranslationX(800);
        etUsername.setTranslationX(800);
        etPassword.setTranslationX(800);
        BtnSignUp.setTranslationX(800);
        etConfirmPass.setTranslationX(800);

        etUserID.setAlpha(v);
        etUsername.setAlpha(v);
        etPassword.setAlpha(v);
        BtnSignUp.setAlpha(v);
        etConfirmPass.setAlpha(v);

        etUserID.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        etUsername.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        etPassword.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        BtnSignUp.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        etConfirmPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        return root;
    }

    private void Check() {
        // reset to be error and make it default
        etUserID.setError(null);
        etUsername.setError(null);
        etPassword.setError(null);
        etConfirmPass.setError(null);
        View focus = null;
        boolean cancel = false;

        // get data and change it to string
        String userId = etUserID.getText().toString();
        String username = etUsername.getText().toString();
        String pass = etPassword.getText(). toString();
        String comfirmPass = etConfirmPass.getText().toString();

        // check id already exist or no
        if (TextUtils.isEmpty(userId)) {
            etUserID.setError("This field is required");
            focus = etUserID;
            cancel = true;
        } else if (checkId(userId)) {
            etUserID.setError("This ID is already exist");
            focus = etUserID;
            cancel = true;
        }

            // check username already exist or no
        if (TextUtils.isEmpty(username)) {
            etUsername.setError("This field is required");
            focus = etUsername;
            cancel = true;
        } else if (checkUsername(username)) {
            etUsername.setError("This Username is already exist");
            focus = etUsername;
            cancel = true;
        }

        // check password alrady exist or no
        if (TextUtils.isEmpty(pass)) {
            etPassword.setError("This field is required");
            focus = etPassword;
            cancel = true;
        }else if (!checkPass(pass, comfirmPass)) {
            etConfirmPass.setError("This password is incorrect!");
            focus = etConfirmPass;
            cancel = true;
        }

        /* if cancel false, variable will get focus. if cancel true can go to login
        activity */
        if (cancel) {
            focus.requestFocus();
        }else {
            Preferences.setRegisteredId(getActivity().getBaseContext(), userId);
            Preferences.setRegisteredUser(getActivity().getBaseContext(), username);
            Preferences.setRegisteredPass(getActivity().getBaseContext(), pass);
            Toast.makeText(getActivity().getApplicationContext(), "Success Registered", Toast.LENGTH_SHORT).show();
        }
    }

    // if pass and confirm pass match
    private boolean checkPass(String pass, String confirmPass) {
        return pass.equals(confirmPass);
    }

    // check username if already exist
    private boolean checkUsername(String username) {
        return username.equals(Preferences.getRegisteredUser(getActivity().getBaseContext()));
    }

    // check id if already exist
    private boolean checkId(String userId) {
        return userId.equals(Preferences.getRegisteredId(getActivity().getBaseContext()));
    }
}
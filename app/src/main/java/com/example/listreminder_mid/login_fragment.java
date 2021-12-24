package com.example.listreminder_mid;

import android.content.Intent;
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


public class login_fragment extends Fragment {

    // initialize variables
    EditText etUserName, etPass, etId;
    Button BtnLogin;
    TextView ForgetPass;
    float v = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login_fragment,container,false);

        // dideclare variable
        etId = (EditText)root.findViewById(R.id.userId_login);
        etUserName = (EditText)root.findViewById(R.id.username_login);
        etPass = (EditText)root.findViewById(R.id.password_login);
        ForgetPass = (TextView)root.findViewById(R.id.forget);
        BtnLogin = (Button)root.findViewById(R.id.LoginBtn);

        // check pass when click login button
        etPass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    Check();
                    return true;
                }
                return false;
            }
        });

        // when click login button
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check();
            }
        });

        // animation
        etId.setTranslationX(800);
        etUserName.setTranslationX(800);
        etPass.setTranslationX(800);
        ForgetPass.setTranslationX(800);
        BtnLogin.setTranslationX(800);

        etId.setAlpha(v);
        etUserName.setAlpha(v);
        etPass.setAlpha(v);
        ForgetPass.setAlpha(v);
        BtnLogin.setAlpha(v);

        etId.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        etUserName.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        etPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        ForgetPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        BtnLogin.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Preferences.getLoggedInStatus(getActivity().getBaseContext())) {
            startActivity(new Intent(getActivity().getBaseContext(), SignupNLogin.class));
        }
    }

    private void Check() {
        // reset to be error and make it default
        etId.setError(null);
        etUserName.setError(null);
        etPass.setError(null);
        View focus = null;
        boolean cancel = false;

        // get data and change it to string
        String id = etId.getText().toString();
        String username = etUserName.getText().toString();
        String pass =etPass.getText().toString();

        // check id field or no
        if (TextUtils.isEmpty(id)) {
            etId.setError("This field is required");
            focus = etId;
            cancel = true;
        }else if(!checkUserId(id)) {
            etId.setError("This ID is not found");
            focus = etId;
            cancel = true;
        }

        // check username field or no
        if (TextUtils.isEmpty(username)) {
            etUserName.setError("This field is required");
            focus = etUserName;
            cancel = true;
        }else if(!checkUsername(username)) {
            etUserName.setError("This username is not found");
            focus = etUserName;
            cancel = true;
        }

        // check password field or no
        if (TextUtils.isEmpty(pass)) {
            etPass.setError("This field is required");
            focus = etPass;
            cancel = true;
        }else if (!checkPassword(pass)) {
            etPass.setError("This password is incorrect!");
            focus = etPass;
            cancel = true;
        }

        // if cancel is true, variable will get focus. if no, move to login() action
        if (cancel) {
            focus.requestFocus();
        }else {
            Toast.makeText(getActivity().getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
            login();
        }
    }

    private void login() {
        Preferences.setLoggedInUser(getActivity().getBaseContext(), Preferences.getRegisteredUser(getActivity().getBaseContext()));
        Preferences.setLoggedInId(getActivity().getBaseContext(), Preferences.getRegisteredId(getActivity().getBaseContext()));
        Preferences.setLoggedInStatus(getActivity().getBaseContext(), true);
        startActivity(new Intent(getActivity().getBaseContext(), Homepage.class));
    }

    // check password
    private boolean checkPassword(String pass) {
        return pass.equals(Preferences.getRegisteredPass(getActivity().getBaseContext()));
    }

    // check username
    private boolean checkUsername(String username) {
        return username.equals(Preferences.getRegisteredUser(getActivity().getBaseContext()));
    }

    // check id
    private boolean checkUserId(String userId) {
        return userId.equals(Preferences.getRegisteredId(getActivity().getBaseContext()));
    }
}
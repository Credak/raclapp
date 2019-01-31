package com.example.halling.raclapp.activities;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import com.example.halling.raclapp.R;
import com.example.halling.raclapp.databinding.ActivityLoginBinding;
import com.example.halling.raclapp.viewmodel.UserViewModel;

public class LoginActivity extends AppCompatActivity {
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setupBindings();
    }

    @SuppressLint("NewApi")
    private void setupBindings() {
        ActivityLoginBinding activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        activityLoginBinding.setLifecycleOwner(this);
        activityLoginBinding.btnLogin.setOnClickListener(v -> this.attemptLogin(activityLoginBinding, userViewModel, v));
    }

    private void attemptLogin(ActivityLoginBinding activityLoginBinding, UserViewModel userViewModel, View v){
        if (TextUtils.isEmpty(activityLoginBinding.inputEmail.getText().toString())) {
            activityLoginBinding.inputEmail.setError("Required Field");
            activityLoginBinding.inputEmail.requestFocus();
        } else if (TextUtils.isEmpty(activityLoginBinding.inputPassword.getText())) {
            activityLoginBinding.inputPassword.setError("Required Field");
            activityLoginBinding.inputPassword.requestFocus();
        } else {
            userViewModel.getValidLogin(activityLoginBinding.inputEmail.getText().toString(), activityLoginBinding.inputPassword.getText().toString()).observe(this, user1 -> {
                if (user1 != null) {
                    Toast.makeText(v.getContext(), "User: " + user1.getUsername() + " with password" + user1.getPassword() + "successful logged in", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(v.getContext(), "No user found", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
package com.example.halling.raclapp.activities;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.example.halling.raclapp.R;
import com.example.halling.raclapp.database.DataGenerator;
import com.example.halling.raclapp.database.entity.User;
import com.example.halling.raclapp.databinding.ActivityLoginBinding;
import com.example.halling.raclapp.viewmodel.UserViewModel;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupBindings(savedInstanceState);
    }

    @SuppressLint("NewApi")
    private void setupBindings(Bundle savedInstanceState) {
        ActivityLoginBinding activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        activityLoginBinding.setLifecycleOwner(this);
        activityLoginBinding.btnLogin.setOnClickListener(v -> {
            userViewModel.getValidLogin(activityLoginBinding.inputEmail.getText().toString(), activityLoginBinding.inputPassword.getText().toString()).observe(this, user1 -> {
                    if (user1 != null) {
                        if (user1.getUsername().isEmpty()) {
                            activityLoginBinding.inputEmail.setError("Wrong Username");
                            activityLoginBinding.inputEmail.requestFocus();
                        } else if (user1.getPassword().isEmpty()) {
                            activityLoginBinding.inputPassword.setError("Wrong Password");
                            activityLoginBinding.inputPassword.requestFocus();
                        } else {
                            Toast.makeText(v.getContext(), "User: " + user1.getUsername() + " with password" + user1.getPassword() + "successful logged in", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(v.getContext(), "No user found", Toast.LENGTH_LONG).show();
                    }
            });
        });
    }
}
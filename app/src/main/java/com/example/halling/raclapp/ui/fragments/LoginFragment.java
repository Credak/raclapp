package com.example.halling.raclapp.ui.fragments;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.halling.raclapp.R;
import com.example.halling.raclapp.databinding.FragmentLoginBinding;
import com.example.halling.raclapp.viewmodel.UserViewModel;

public class LoginFragment extends Fragment {
    FragmentLoginBinding fragmentLoginBinding;
    UserViewModel userViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        return fragmentLoginBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupBindings();
    }

    @SuppressLint("NewApi")
    private void setupBindings() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        fragmentLoginBinding.setLifecycleOwner(this);
        fragmentLoginBinding.btnLogin.setOnClickListener(v -> this.attemptLogin(fragmentLoginBinding, userViewModel, v));
    }

    private void attemptLogin(FragmentLoginBinding fragmentLoginBinding, UserViewModel userViewModel, View v){
        if (TextUtils.isEmpty(fragmentLoginBinding.inputEmail.getText().toString())) {
            fragmentLoginBinding.inputEmail.setError("Required Field");
            fragmentLoginBinding.inputEmail.requestFocus();
        } else if (TextUtils.isEmpty(fragmentLoginBinding.inputPassword.getText())) {
            fragmentLoginBinding.inputPassword.setError("Required Field");
            fragmentLoginBinding.inputPassword.requestFocus();
        } else {
            userViewModel.getValidLogin(fragmentLoginBinding.inputEmail.getText().toString(), fragmentLoginBinding.inputPassword.getText().toString()).observe(this, user1 -> {
                if (user1 != null) {
                    Toast.makeText(v.getContext(), "User: " + user1.getUsername() + " with password" + user1.getPassword() + "successful logged in", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(v.getContext(), "No user found", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
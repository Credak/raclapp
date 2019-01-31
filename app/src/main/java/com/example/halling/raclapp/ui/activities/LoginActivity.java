package com.example.halling.raclapp.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.halling.raclapp.R;
import com.example.halling.raclapp.ui.fragments.LoginFragment;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_login);
        if (this.findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            LoginFragment loginFragment = new LoginFragment();
            loginFragment.setArguments(this.getIntent().getExtras());
            this.getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, loginFragment).commit();
        }

    }
}
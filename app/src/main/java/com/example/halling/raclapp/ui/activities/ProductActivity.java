package com.example.halling.raclapp.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.halling.raclapp.R;
import com.example.halling.raclapp.ui.fragments.ProductFragment;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_product);
        if (this.findViewById(R.id.fragment_product_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            ProductFragment productFragment = new ProductFragment();
            productFragment.setArguments(this.getIntent().getExtras());
            this.getSupportFragmentManager().beginTransaction().add(R.id.fragment_product_container, productFragment).commit();
        }
    }
}

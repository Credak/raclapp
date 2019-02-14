package com.example.halling.raclapp.ui.fragments;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.halling.raclapp.R;
import com.example.halling.raclapp.database.entity.Product;
import com.example.halling.raclapp.databinding.ContentOverviewBinding;
import com.example.halling.raclapp.databinding.FragmentOverviewBinding;
import com.example.halling.raclapp.ui.adapter.ProductAdapter;
import com.example.halling.raclapp.viewmodel.ProductViewModel;

import java.util.List;

public class ProductFragment extends Fragment {
    private ContentOverviewBinding contentOverviewBinding;
    private ProductAdapter productAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.contentOverviewBinding = DataBindingUtil.inflate(inflater, R.layout.content_overview, container, false);
        this.productAdapter = new ProductAdapter();
        contentOverviewBinding.overViewRecyclerview.setAdapter(productAdapter);
        return contentOverviewBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ProductViewModel productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.getAllProducts().observe(this, products -> productAdapter.setmProductList(products));
    }
}

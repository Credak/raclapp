package com.example.halling.raclapp.ui.adapter;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.halling.raclapp.R;
import com.example.halling.raclapp.database.entity.Product;
import com.example.halling.raclapp.databinding.FragmentOverviewBinding;

import java.util.List;
import java.util.Objects;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> mProductList;

    public ProductAdapter(){
        this.setHasStableIds(true);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        FragmentOverviewBinding fragmentOverviewBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.fragment_overview, viewGroup, false);
        return new ProductViewHolder(fragmentOverviewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        productViewHolder.fragmentOverviewBinding.setProduct(mProductList.get(i));
        productViewHolder.fragmentOverviewBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mProductList == null ? 0 : mProductList.size();
    }

    public void setmProductList(List<Product> productList){
        if(mProductList == null){
            mProductList = productList;
            this.notifyItemRangeInserted(0, productList.size());
        }else{
            DiffUtil.DiffResult result= DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mProductList.size();
                }

                @Override
                public int getNewListSize() {
                    return productList.size();
                }

                @Override
                public boolean areItemsTheSame(int i, int i1) {
                    return mProductList.get(i).getProductid() == productList.get(i1).getProductid();
                }

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public boolean areContentsTheSame(int i, int i1) {
                    Product newProduct = productList.get(i);
                    Product oldProduct = mProductList.get(i1);
                    return newProduct.getProductid() == oldProduct.getProductid()
                            && Objects.equals(newProduct.getProductid(), oldProduct.getProductid());
                }
            });
            mProductList = productList;
            result.dispatchUpdatesTo(this);
        }
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{
        final FragmentOverviewBinding fragmentOverviewBinding;

        public ProductViewHolder(FragmentOverviewBinding fragmentOverviewBinding) {
            super(fragmentOverviewBinding.getRoot());
            this.fragmentOverviewBinding = fragmentOverviewBinding;
        }
    }
}

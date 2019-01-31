package com.example.halling.raclapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;
import com.example.halling.raclapp.database.entity.Product;
import com.example.halling.raclapp.database.entity.User;
import com.example.halling.raclapp.database.repository.ProductRepository;
import com.example.halling.raclapp.database.repository.UserRepository;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private ProductRepository productRepository;

    private MediatorLiveData<List<Product>> productLiveData;


    public ProductViewModel(@NonNull Application application) {
        super(application);

        productLiveData = new MediatorLiveData<>();

        productLiveData.setValue(null);

        productRepository = new ProductRepository(application);
        LiveData<List<Product>> productList = productRepository.getAllProducts();

        productLiveData.addSource(productList, productLiveData::setValue);
    }

    public LiveData<List<Product>> getAllProducts(){
        return productRepository.getAllProducts();
    }

    public LiveData<Product> getProductByProductName(String productname){
        return  productRepository.getProduct(productname);
    }

    public void insertProduct(Product product){
        productRepository.insert(product);
    }
}

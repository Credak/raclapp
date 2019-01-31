package com.example.halling.raclapp.database.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.os.AsyncTask;
import com.example.halling.raclapp.database.RaclappDatabase;
import com.example.halling.raclapp.database.dao.ProductDAO;
import com.example.halling.raclapp.database.entity.Product;

import java.util.Collections;
import java.util.List;

public class ProductRepository {

    private MediatorLiveData<List<Product>> mProductList;
    private RaclappDatabase raclappDatabase;

    public ProductRepository(Application application) {
        this.raclappDatabase = RaclappDatabase.getDatabase(application);

        mProductList = new MediatorLiveData<>();

        mProductList.addSource(raclappDatabase.productDAO().getAllProducts(), products -> {
            if (raclappDatabase.getDatabaseCreated().getValue() != null) {
                mProductList.postValue(products);
            }
        });
    }

    public LiveData<List<Product>> getAllProducts() {
        return raclappDatabase.productDAO().getAllProducts();
    }

    public LiveData<Product> getProduct(String productname) {
        return raclappDatabase.productDAO().getProduct(productname);
    }

    public void insert(Product product) {
        new insertAsyncTask(raclappDatabase.productDAO()).execute(product);
    }


    private static class insertAsyncTask extends AsyncTask<Product, Void, Void> {

        private ProductDAO mAsyncTaskDao;

        insertAsyncTask(ProductDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Product... params) {
            mAsyncTaskDao.insertAllProducts(Collections.singletonList(params[0]));
            return null;
        }
    }
}

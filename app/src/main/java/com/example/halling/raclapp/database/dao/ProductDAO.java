package com.example.halling.raclapp.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;
import com.example.halling.raclapp.database.entity.Product;

import java.util.List;

@Dao
public interface ProductDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllProducts(List<Product> product);

    @Query("SELECT * FROM table_products WHERE productname = :productname")
    LiveData<Product> getProduct(String productname);

    @Query("DELETE FROM table_products")
    void deleteAllUser();

    @Query("SELECT * FROM table_products")
    LiveData<List<Product>> getAllProducts();

}

package com.example.retrofit.model.product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductRepository {

    interface GetProductListApi{
        @GET("product/getAllProducts.php")
        Call<List<Product>> getAllProduct();
    }

    interface GetProductByNameApi{
        @GET("product/getProductByName.php")
        Call<Product> getProductByName(@Query("name") String name);
    }
}

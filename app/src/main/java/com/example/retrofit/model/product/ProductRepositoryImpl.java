package com.example.retrofit.model.product;

import android.os.Handler;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.retrofit.utils.ProductsFragmentContract;
import com.example.retrofit.utils.RetrofitClient;

import java.util.List;

public class ProductRepositoryImpl implements ProductsFragmentContract.Interactor {
    private static final String TAG = "ProductRepositoryImpl";
    private static final int TIME_OUT = 1200;

    private ProductRepository.GetProductListApi getProductListApi;
    private ProductRepository.GetProductByNameApi getProductByNameApi;


    @Override
    public void getAllProduct(OnFinishedListener listener) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                getProductListApi = RetrofitClient
                        .getInstance()
                        .getRetrofit()
                        .create(ProductRepository.GetProductListApi.class);

                Call<List<Product>> call = getProductListApi.getAllProduct();

                call.enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        if (!response.isSuccessful()){
                            Log.d(TAG, "response was not Successful !");
                        }

                        //-- New Lines
                        Log.e(TAG, "log: -----------------------------");
                        Log.d(TAG, "onResponse: " + response.body());

                        if(response.raw().networkResponse() != null){
                            Log.d(TAG, "onResponse: response is from NETWORK...");
                        }
                        else if(response.raw().cacheResponse() != null
                                && response.raw().networkResponse() == null){
                            Log.d(TAG, "onResponse: response is from CACHE...");
                        }

                        //-- End of "New Lines"


                        listener.onGetAllProductFinished(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {
                        Log.d(TAG, t.getMessage());
                    }
                });
            }
        }, TIME_OUT);
    }
}

package com.example.retrofit.presenter;

import com.example.retrofit.model.product.Product;
import com.example.retrofit.model.product.ProductRepositoryImpl;
import com.example.retrofit.utils.ProductsFragmentContract;
import com.example.retrofit.view.ProductsFragment;

import java.util.List;

public class ProductPresenterImpl implements ProductsFragmentContract.Presenter, ProductsFragmentContract.Interactor.OnFinishedListener {

    ProductsFragmentContract.View view;
    ProductRepositoryImpl repository;

    public ProductPresenterImpl(ProductsFragmentContract.View view) {
        this.view = view;
        repository = new ProductRepositoryImpl();
    }

    @Override
    public void getProductList() {
        if (view != null){
            view.showProgress();
            repository.getAllProduct(this);
        }
    }

    @Override
    public void onGetAllProductFinished(List<Product> list) {
        if (view != null){
            view.hideProgress();
            view.setProductList(list);
            //repository = null;
        }
    }
}

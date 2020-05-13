package com.example.retrofit.utils;

import com.example.retrofit.model.product.Product;

import java.util.List;

public interface ProductsFragmentContract {
    interface View{
        void showProgress();
        void hideProgress();

        void setProductList(List<Product> list);
    }

    interface Presenter{
        void getProductList();
    }

    interface Interactor{
        interface OnFinishedListener{
            void onGetAllProductFinished(List<Product> list);
        }

        void getAllProduct(OnFinishedListener listener);
    }
}

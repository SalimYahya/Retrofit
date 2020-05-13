package com.example.retrofit.utils.recyclerview.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.retrofit.R;
import com.example.retrofit.model.product.Product;
import com.example.retrofit.utils.ApplicationContext;
import com.example.retrofit.utils.recyclerview.product.ProductCardViewHolder;

import java.util.List;

public class ProductCardRecyclerViewAdapter extends RecyclerView.Adapter<ProductCardViewHolder> {

    List<Product> productList;
    RequestOptions option;

    Context context;

    public ProductCardRecyclerViewAdapter(List<Product> productList) {
        this.productList = productList;

        option = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.ic_image_accent_24dp);

        context = ApplicationContext.getAppContext();
    }

    @NonNull
    @Override
    public ProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);

        return new ProductCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCardViewHolder holder, int position) {
        // Todo: Diffine Options + proucts
        if (productList != null && position < productList.size()){

            Product product = productList.get(position);

            holder.prodcutName.setText(product.name);
            holder.productPrice.setText(product.price);

            Glide.with(context).load(product.url).apply(option).into(holder.productImage);
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}

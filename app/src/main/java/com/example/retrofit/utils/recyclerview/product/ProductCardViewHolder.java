package com.example.retrofit.utils.recyclerview.product;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.R;
import com.google.android.material.card.MaterialCardView;

public class ProductCardViewHolder extends RecyclerView.ViewHolder {

    ImageView productImage;
    TextView prodcutName, productPrice;
    MaterialCardView productCardView;

    public ProductCardViewHolder(@NonNull View itemView) {
        super(itemView);

        productCardView = itemView.findViewById(R.id.product_card);
        productImage = itemView.findViewById(R.id.product_image);
        prodcutName = itemView.findViewById(R.id.product_name);
        productPrice = itemView.findViewById(R.id.product_price);
    }
}

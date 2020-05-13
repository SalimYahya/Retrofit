package com.example.retrofit.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.retrofit.R;
import com.example.retrofit.model.product.Product;
import com.example.retrofit.presenter.ProductPresenterImpl;
import com.example.retrofit.utils.ProductsFragmentContract;
import com.example.retrofit.utils.recyclerview.product.ProductCardRecyclerViewAdapter;

import java.util.List;

public class ProductsFragment extends Fragment implements ProductsFragmentContract.View{

    private static final String NAME_ARGS = "name";
    private static final String TAG = "ProductsFragment ";
    String mName;

    RecyclerView recyclerView;
    ProgressBar progressBar;

    ProductsFragmentContract.Presenter presenter;

    public static ProductsFragment  newInstance(final String name){
        final ProductsFragment productsFragment = new ProductsFragment();
        final Bundle args = new Bundle(1);
        args.putString(NAME_ARGS, name);
        productsFragment.setArguments(args);

        return productsFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        final Bundle args = getArguments();
        if (args == null || !args.containsKey(NAME_ARGS)){
            Log.d(TAG, "args is null or empty");
        }else {
            mName = args.getString(NAME_ARGS);
            Log.d(TAG, mName);
            Log.d(TAG, "onCreate()");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products_grid, container, false);
        setUpToolBar(view);
        initiateView(view);

        presenter = new ProductPresenterImpl(this);
        presenter.getProductList();

        return view;
    }

    private void setUpToolBar(View view) {

        Toolbar toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();

        if (activity != null){
            toolbar.setTitle(activity.getTitle().toString() + " - Products");
            activity.setSupportActionBar(toolbar);
        }
    }

    private void initiateView(View view) {
        recyclerView =  view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        progressBar = view.findViewById(R.id.progressBar);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setProductList(List<Product> list) {
        recyclerView.setAdapter(new ProductCardRecyclerViewAdapter(list));
    }
}

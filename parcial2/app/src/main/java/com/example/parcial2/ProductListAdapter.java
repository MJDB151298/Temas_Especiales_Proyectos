package com.example.parcial2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import com.example.parcial2.entitites.Category;
import com.example.parcial2.entitites.Product;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> products;
    private static FragmentActivity activity;

    public ProductListAdapter(Context context, List<Product> products, FragmentActivity activity) {
        this.context = context;
        this.products = products;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view  = inflater.inflate(R.layout.product_row, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ProductViewHolder holder, int position) {
        holder.bindData(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView productNameText;
        private TextView productPriceText;
        private TextView productCategoryText;
        private TextView productIdText;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameText = itemView.findViewById(R.id.productNameText);
            productPriceText = itemView.findViewById(R.id.productPriceText);
            productCategoryText = itemView.findViewById(R.id.productCategoryText);
            productIdText = itemView.findViewById(R.id.productIdText);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("PRODUCT_NAME", productNameText.getText().toString());
                    bundle.putString("PRODUCT_PRICE", productPriceText.getText().toString());
                    bundle.putString("CATEGORY_NAME", productCategoryText.getText().toString());
                    bundle.putInt("PRODUCT_ID", Integer.parseInt(productIdText.getText().toString()));

                    FragmentManager fragmentManager = activity.getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    UpdateDeleteProductFragment updateDeleteProductFragment = new UpdateDeleteProductFragment();
                    updateDeleteProductFragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragment_container, updateDeleteProductFragment).addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }

        public void bindData(Product product){
            System.out.println("ALOOOOO");
            productNameText.setText(product.getName());
            productPriceText.setText(Integer.toString(product.getPrice()));
            productCategoryText.setText(product.getCategory().getName());
            productIdText.setText(Integer.toString(product.getId()));
        }
    }

}

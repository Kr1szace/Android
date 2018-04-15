package nik.oe.hu.vsa;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import nik.oe.hu.model.Product;

/**
 * Created by Attila on 4/15/2018.
 */

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder>{

    List<Product> products;

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductViewHolder(View.inflate(parent.getContext(),R.layout.app_bar_main,null));
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
                Product product=products.get(position);
                holder.nameTextView.setText(product.getName());
                holder.priceTextView.setText(product.getPrice());
    }

    @Override
    public int getItemCount() {
        return products !=null ? products.size():0;
    }


    class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView nameTextView, priceTextView;

        public ProductViewHolder(View productListView) {
            super(productListView);
            nameTextView=productListView.findViewById(R.id.product_name);
            priceTextView=productListView.findViewById(R.id.product_price);
        }
    }
}
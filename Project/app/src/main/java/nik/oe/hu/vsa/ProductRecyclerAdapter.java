package nik.oe.hu.vsa;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import  java.util.List;

import java.util.List;

import nik.oe.hu.model.Product;

/**
 * Created by Attila on 4/15/2018.
 */

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder>{

    //csak példa miatt
    List<Product> products=new ArrayList<Product>();
    Product vmi=new Product(100,"termek1","dafadf","adfadf2r",1230,12,null);
    Product vmi1=new Product(110,"termek2","dafadf","adfadf2r",1230,12,null);

    public ProductRecyclerAdapter(){
        //kostruktorba majd vmi  string[] MyDataSet eseteleg
       products.add(vmi);
       products.add(vmi1);
    }

    @Override
    public ProductRecyclerAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Linear volt
        //View listItem = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list,parent,false);
         return new ProductViewHolder(View.inflate(parent.getContext(),R.layout.product_list,null));
        // viewHolder=new ProductViewHolder(listItem);
        //return viewHolder;

    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
                Product product=products.get(position);
                holder.nameTextView.setText(product.getName());
                //holder.priceTextView.setText(product.getPrice());
    }

    @Override
    public int getItemCount() {
        return products !=null ? products.size():0;
    }


    public static class ProductViewHolder extends RecyclerView.ViewHolder{

        public TextView nameTextView, priceTextView;

        public ProductViewHolder(View productListView) {
            super(productListView);
            nameTextView=productListView.findViewById(R.id.product_name);
            //priceTextView=productListView.findViewById(R.id.product_price);
        }
    }
}

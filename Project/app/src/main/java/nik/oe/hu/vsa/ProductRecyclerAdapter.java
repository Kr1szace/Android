package nik.oe.hu.vsa;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import  java.util.List;

import java.util.List;

import nik.oe.hu.mapservice.exceptions.AlreadyInShoppingListException;
import nik.oe.hu.mapservice.exceptions.NoSuchItemInShoppingListException;
import nik.oe.hu.model.AppDatabase;
import nik.oe.hu.model.Product;
import nik.oe.hu.model.ShoppingList;

/**
 * Created by Attila on 4/15/2018.
 */

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder>{

    //csak példa miatt
    List<Product> products;
    //Product vmi=new Product(100,"termek1","dafadf","adfadf2r",1230,12,null);
    //Product vmi1=new Product(110,"termek2","dafadf","adfadf2r",1230,12,null);
    private int whichActivity;
    //data
    ShoppingList shoppingList;

    public ProductRecyclerAdapter(List<Product> allProduct, int activity){
        this.whichActivity=activity;
        this.products=allProduct;
    }
    //lehet kell még egy konstrucktor?

    //shoppinglisté
    public ProductRecyclerAdapter(ShoppingList shoppingList){
        this.shoppingList=shoppingList;
    }
    @Override
    public ProductRecyclerAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Linear volt
        //View listItem = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list,parent,false);
        //jó igen tudom, ez így nem biztos h szép megoldás
        if(whichActivity==0){
            return new ProductViewHolder(View.inflate(parent.getContext(),R.layout.content_shopping_list,null),0);
        }
        else {
            return new ProductViewHolder(View.inflate(parent.getContext(), R.layout.product_list, null),1);
        }
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


    public void removeItem(Product product,int position){
        try {
            shoppingList.removeProduct(product);
        } catch (NoSuchItemInShoppingListException e) {
            e.printStackTrace();
            Log.e("SLE",e.getMessage());
        }
        notifyItemRemoved(position);
    }

    public void restore(Product product, int position){
        try {
            shoppingList.addProduct(product);
        } catch (AlreadyInShoppingListException e) {
            e.printStackTrace();
            Log.e("SLE",e.getMessage());
        }
        notifyItemInserted(position);
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{

        public TextView nameTextView, priceTextView;

        public RelativeLayout viewBackground, viewForeground;

        private int whichActivity;

        public ProductViewHolder(View productListView, int activity) {
            super(productListView);
            this.whichActivity=activity;

            if(whichActivity==1) {
                nameTextView = productListView.findViewById(R.id.product_name);
                //priceTextView=productListView.findViewById(R.id.product_price);
            }
            else {
                nameTextView=productListView.findViewById(R.id.product_item_name);
                viewBackground = productListView.findViewById(R.id.view_background);
                viewForeground = productListView.findViewById(R.id.view_foreground);
            }
        }
    }
}

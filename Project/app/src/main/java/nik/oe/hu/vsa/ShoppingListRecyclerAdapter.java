package nik.oe.hu.vsa;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import nik.oe.hu.mapservice.exceptions.AlreadyInShoppingListException;
import nik.oe.hu.mapservice.exceptions.NoSuchItemInShoppingListException;
import nik.oe.hu.model.Product;
import nik.oe.hu.model.ShoppingList;

/**
 * Created by Attila on 5/13/2018.
 */

public class ShoppingListRecyclerAdapter extends RecyclerView.Adapter<ShoppingListRecyclerAdapter.ShoppingViewHolder> {

    private ShoppingList shoppingList;

    private List<Product> products;

    public ShoppingListRecyclerAdapter(Context context) {
        this.shoppingList= new ShoppingList(context);
        this.products = shoppingList.getShoppingList();
    }

    public ShoppingListRecyclerAdapter(List<Product> products) {
        this.products = products;
    }

    @Override
    public ShoppingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_shopping_list, parent, false);

        return new ShoppingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ShoppingViewHolder holder, int position) {
            final Product product=products.get(position);
            holder.nameTextView.setText(product.getName());
    }

    @Override
    public int getItemCount() {
        return products != null ? products.size():0;
    }

    public void removeItem(Product product, int position){
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

    public class ShoppingViewHolder extends  RecyclerView.ViewHolder{

        public TextView nameTextView;
        public RelativeLayout viewBackground, viewForeground;

        public ShoppingViewHolder(View itemView) {
            super(itemView);
            nameTextView=itemView.findViewById(R.id.product_item_name);
            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);
        }
    }
}

package nik.oe.hu.vsa;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import nik.oe.hu.model.ShoppingList;

/**
 * Created by Attila on 5/13/2018.
 */

public class ShoppingListRecyclerAdapter extends RecyclerView.Adapter<ShoppingListRecyclerAdapter.ShoppingViewHolder> {

    private ShoppingList shoppingList;

    public ShoppingListRecyclerAdapter(ShoppingList shoppingList) {
        this.shoppingList=shoppingList;
    }

    @Override
    public ShoppingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_shopping_list, parent, false);

        return new ShoppingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ShoppingViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ShoppingViewHolder extends  RecyclerView.ViewHolder{

        public RelativeLayout viewBackground, viewForeground;

        public ShoppingViewHolder(View itemView) {
            super(itemView);

            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);
        }
    }
}

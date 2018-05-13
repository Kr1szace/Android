package nik.oe.hu.vsa;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import nik.oe.hu.model.Product;
import nik.oe.hu.model.ShoppingList;

public class SLActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    public ShoppingList shoppingList;
    public ProductRecyclerAdapter productAdapter;

    public ShoppingListRecyclerAdapter shoppingListRecyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sl);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        shoppingList=new ShoppingList(this);
        List<Product> habi = shoppingList.getShoppingList().subList(1,4);

        //shoppingListRecyclerAdapter=new ShoppingListRecyclerAdapter(shoppingList);
        shoppingListRecyclerAdapter = new ShoppingListRecyclerAdapter(habi);

        LinearLayoutManager layoutManager= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView = (RecyclerView) findViewById(R.id.shopping_recycle_view);
        recyclerView.setLayoutManager(layoutManager);
        //separátor
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(shoppingListRecyclerAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}

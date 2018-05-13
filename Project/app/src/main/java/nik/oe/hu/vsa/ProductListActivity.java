package nik.oe.hu.vsa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import nik.oe.hu.model.AppDatabase;
import nik.oe.hu.model.Product;

/**
 * Created by Attila on 4/17/2018.
 */

public class ProductListActivity extends Activity {

    private RecyclerView recyclerView;
    //private RecyclerView.Adapter adapter;
    //private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productlist);

        LinearLayoutManager layoutManager= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        final ProductRecyclerAdapter adapter=new ProductRecyclerAdapter(AppDatabase.getAppDatabase(this).productDAO().getAllProduct(),1);

        recyclerView=(RecyclerView) findViewById(R.id.my_recycler_view);

        recyclerView.setLayoutManager(layoutManager);
        //separátor
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //itt kéne bemenni egy product activitybe
                Product product=adapter.products.get(position);
                Intent intent = new Intent(view.getContext(),ProductActivity.class);
                intent.putExtra("barcode_s",product.getBarcode());
                startActivity(intent);
                //Movie movie = movieList.get(position);
                //Toast.makeText(getApplicationContext(), movie.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }


}

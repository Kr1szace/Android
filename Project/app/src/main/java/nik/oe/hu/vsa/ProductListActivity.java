package nik.oe.hu.vsa;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import nik.oe.hu.model.AppDatabase;

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


        ProductRecyclerAdapter adapter=new ProductRecyclerAdapter(AppDatabase.getAppDatabase(this).productDAO().getAllProduct());

        recyclerView=(RecyclerView) findViewById(R.id.my_recycler_view);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }


}

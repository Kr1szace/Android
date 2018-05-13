package nik.oe.hu.vsa;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import nik.oe.hu.model.AppDatabase;
import nik.oe.hu.model.Product;
import nik.oe.hu.model.ShoppingList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,RecyclerItemTouchHelperListener.RecyclerItemTouchHelperListenerInterface {

    private RecyclerView recyclerView;
    public ShoppingList shoppingList;
    public ProductRecyclerAdapter productAdapter;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        this.createFakeInstances();

        //bevásárló lista része---------------------------------------------------------------------
       shoppingList=new ShoppingList(this);
        productAdapter=new ProductRecyclerAdapter(shoppingList);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //shoppinglist==null ?
        if (shoppingList.getShoppingList().isEmpty()) {
         //Üres a bevásárló listád üzi
            Toast.makeText(this, "A bevásárlólista üres", Toast.LENGTH_LONG);
        }
        else {
            ProductRecyclerAdapter shoppinglistAdapter = new ProductRecyclerAdapter(shoppingList.getShoppingList(), 0);

            recyclerView = (RecyclerView) findViewById(R.id.shopping_recycle_view);
            recyclerView.setLayoutManager(layoutManager);
            //separátor
            recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
            recyclerView.setAdapter(shoppinglistAdapter);
        }

        //ShoppingListHandler();

    }
/*
    @Override
    protected void onResume() {
        super.onResume();

        ShoppingListHandler();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ShoppingListHandler();
    }
*/
    private void ShoppingListHandler(){
        shoppingList=new ShoppingList(this);
        productAdapter=new ProductRecyclerAdapter(shoppingList);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //shoppinglist==null ?
        if (shoppingList.getShoppingList()==null) {
            //Üres a bevásárló listád üzi
        }
        else {
            ProductRecyclerAdapter shoppinglistAdapter = new ProductRecyclerAdapter(shoppingList.getShoppingList(), 0);

            recyclerView = (RecyclerView) findViewById(R.id.shopping_recycle_view);
            recyclerView.setLayoutManager(layoutManager);
            //separátor
            recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
            recyclerView.setAdapter(shoppinglistAdapter);
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            //ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 1600);
            Intent intent = new Intent(this, CameraActivityMain.class);
            startActivity(intent);


        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(this, ProductListActivity.class);
            startActivity(intent);
        }  else if (id == R.id.nav_manage) {
            Intent intent = new Intent(this, ProductActivity.class);
            startActivity(intent);
        } /*else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private List<Product> createFakeInstances() {
        ArrayList<Product> prod = new ArrayList<>();

        Product prod1 = new Product();
        prod1.setName("Kifli");
        prod1.setDescription("Inycsiklandó péksütemény");
        prod1.setBarcode("*268*");
        prod1.setAmount(100);
        prod1.setPrice(15);
        prod1.setImage_url("https://secure.ce-tescoassets.com/assets/HU/955/211955/ShotType1_328x328.jpg");
        prod.add(prod1);


        Product prod2=new Product();
        prod2.setName("Császár zsömle");
        prod2.setDescription("Helyben sütött patika császár zsömle");
        prod2.setBarcode("269");
        prod2.setAmount(3242);
        prod2.setPrice(15);
        prod2.setImage_url("http://www.nosalty.hu/files/imagecache/recept/recept_kepek/hazi-magvas-zsemle.JPG");
        prod.add(prod2);

        Product szorp = new Product();
        szorp.setName("Piroska Málna");
        szorp.setDescription("Finom, édes, málna ízű szörp.");
        szorp.setBarcode("5998818562252");
        szorp.setPrice(450);
        szorp.setAmount(200);
        szorp.setImage_url("https://sporolok.com/wp-content/uploads/2016/06/piroska_szorp-620x329.jpg");
        prod.add(szorp);

        if (AppDatabase.getAppDatabase(this).productDAO().getProductsByName("Kifli").size() < 1)
            AppDatabase.getAppDatabase(this).productDAO().Insert(prod1);

        if (AppDatabase.getAppDatabase(this).productDAO().getProductsByName("Császár zsömle").size() < 1)
            AppDatabase.getAppDatabase(this).productDAO().Insert(prod2);

        if (AppDatabase.getAppDatabase(this).productDAO().getProductsByName("Piroska Málna").size() < 1)
            AppDatabase.getAppDatabase(this).productDAO().Insert(szorp);
        //AppDatabase.getAppDatabase(this).productDAO().getAllProduct();

        return prod;
    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {

        //pozició alapján(int position) adott product eltávolítása a bevásárlólistából
        Product product=shoppingList.getShoppingList().get(position);

        productAdapter.removeItem(product,position);

        //UNDO opció
        Snackbar snackbar = Snackbar.make(coordinatorLayout,"eltávolítva a kosárból",Snackbar.LENGTH_LONG );
        snackbar.setAction("UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //adott product adott helyre visszatétele
                //productAdapter.restore();
            }
        });

        snackbar.setActionTextColor(Color.BLUE);
        snackbar.show();
    }
}


//teszt
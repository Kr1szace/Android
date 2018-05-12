package nik.oe.hu.vsa;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.barcode.Barcode;
import com.squareup.picasso.Picasso;

import nik.oe.hu.model.AppDatabase;
import nik.oe.hu.model.Product;
import nik.oe.hu.model.ProductDAO;
import nik.oe.hu.model.ShoppingList;

//Csuba
import com.google.android.gms.common.api.CommonStatusCodes;
import android.content.Intent;


//váge


public class ProductActivity extends AppCompatActivity {

    TextView name, description, price, barcode, amount;
    ImageView image;

    Product product;

    ShoppingList shoppingList;
    //Csuba Mixe
     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
          if (resultCode == CommonStatusCodes.SUCCESS) {
          if (data != null) {
                  Barcode barcode = data.getParcelableExtra("barcode");
                  //name.setText("Az eredmény tesám: " + barcode.displayValue);

                  product = AppDatabase.getAppDatabase(this).productDAO().getProductByBarCode(barcode.displayValue);


                  //product = AppDatabase.getAppDatabase(this).productDAO().getProductByBarCode(barcode.displayValue);

               } else {
                 name.setText("No barcode found");
          }
     } else {
        super.onActivityResult(requestCode, resultCode, data);
     }
        }}
    //itt a vége

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoppingList.addProduct(product);
                Snackbar.make(view, "Hozzáadva a bevásárlólistához", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Product local = new Product(1,"Kifli","Finom, tápláló péksütemény","123456",15,100,"http://m.blog.hu/li/lifeadvice/image/gasztro/kifli.jpg");


        if (getIntent().getExtras() != null) {
            Barcode barcode_camera = getIntent().getExtras().getParcelable("barcode");
            product = AppDatabase.getAppDatabase(this).productDAO().getProductByBarCode(barcode_camera.displayValue);
        }

        if (product != null){
            local = product;

        }

        name = (TextView) findViewById(R.id.product_name);
        name.setText(local.getName());


        description = (TextView) findViewById(R.id.product_descrip);
        description.setText(local.getDescription());

        price = (TextView) findViewById(R.id.product_price);
        price.setText(local.getPrice() + " Ft");

        barcode = (TextView) findViewById(R.id.product_barcode);
        barcode.setText(local.getBarcode());

        Typeface face = Typeface.createFromAsset(getAssets(),
                "font/code128.ttf");
        barcode.setTypeface(face);

        amount = (TextView) findViewById(R.id.product_amount);
        amount.setText(local.getAmount() + " db");

        image = (ImageView) findViewById(R.id.product_image);
        Picasso.get().load(local.getImage_url()).into(image);

    }
    /*
    @Override
    protected void onStart() {
        super.onStart();

        setContentView(R.layout.activity_product);
    }*/
}

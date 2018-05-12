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

import com.squareup.picasso.Picasso;

import nik.oe.hu.model.Product;

public class ProductActivity extends AppCompatActivity {

    TextView name, description, price, barcode, amount;
    ImageView image;

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
                Snackbar.make(view, "Hozzáadva a bevásárlólistához", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        //Product local = getIntent().getExtras().getParcelable("product");
        Product local = new Product(1,"Kifli","Finom, tápláló péksütemény","123456",15,100,"http://m.blog.hu/li/lifeadvice/image/gasztro/kifli.jpg");

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

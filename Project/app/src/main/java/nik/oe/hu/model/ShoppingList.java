package nik.oe.hu.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * Created by Haku on 2018. 05. 11..
 */

public class ShoppingList {
    private ArrayList<Product> productsToBuy ;
    private Context appcontext;
    public ShoppingList(Context context) {
        this.appcontext = context;
        this.initList();
    }

    private void initList(){
        if (getShoppingList() == null){
            this.productsToBuy = new ArrayList<>();
            this.saveList();
        }else{
            this.productsToBuy = this.getShoppingList();
        }
    }

    public void saveList(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.appcontext);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(this.productsToBuy);
        editor.putString("shoppinglist", json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    public void addProduct(Product product){
        if (!productsToBuy.contains(product))
            this.productsToBuy.add(product);

        this.saveList();
    }

    public void removeProduct(Product product){
        if (productsToBuy.contains(product))
            this.productsToBuy.remove(product);

        this.saveList();
    }

    public ArrayList<Product> getShoppingList(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(appcontext);
        Gson gson = new Gson();
        String json = prefs.getString("shoppinglist", null);
        Type type = new TypeToken<ArrayList<Product>>() {}.getType();
        return gson.fromJson(json, type);
    }


}

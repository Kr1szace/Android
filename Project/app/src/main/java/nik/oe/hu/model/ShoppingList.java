package nik.oe.hu.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import nik.oe.hu.mapservice.exceptions.AlreadyInShoppingListException;
import nik.oe.hu.mapservice.exceptions.NoSuchItemInShoppingListException;


/**
 * Created by Haku on 2018. 05. 11..x
 */

public class ShoppingList {
    private List<Product> productsToBuy ;
    private Context appcontext;
    public ShoppingList(Context context) {
        this.appcontext = context;
        this.initList();
    }

    private void initList(){
        if (getShoppingList().isEmpty()){
            this.productsToBuy = new ArrayList<>();
            this.saveList();
            getShoppingList();
        }else{
            this.productsToBuy = this.getShoppingList();
        }
    }

    private void saveList(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.appcontext);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(this.productsToBuy);
        editor.putString("shoppinglist", json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    public void addProduct(Product product) throws AlreadyInShoppingListException {
        getShoppingList();
        if (!productsToBuy.contains(product)) {
            this.productsToBuy.add(product);
            this.saveList();
            getShoppingList();
        }else {
            throw new AlreadyInShoppingListException(product);
        }
    }

    public void removeProduct(Product product) throws NoSuchItemInShoppingListException {
        getShoppingList();
        if (productsToBuy.contains(product)){
            this.productsToBuy.remove(product);
            this.saveList();
            getShoppingList();
        }else{
            throw new NoSuchItemInShoppingListException(product);
        }
    }

    public void removeAllProcuct(){
        getShoppingList();
        this.productsToBuy.clear();
        this.saveList();
    }

    public List<Product> getShoppingList(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(appcontext);
        Gson gson = new Gson();
        String json = prefs.getString("shoppinglist", null);
        Type type = new TypeToken<ArrayList<Product>>() {}.getType();
        productsToBuy = gson.fromJson(json, type);
        return productsToBuy;
    }


}

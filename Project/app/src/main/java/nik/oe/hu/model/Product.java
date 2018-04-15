package nik.oe.hu.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Haku on 2018. 04. 15..
 */

@Entity
public class Product {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "barcode")
    private String barcode;
    @ColumnInfo(name = "price")
    private int price;
    @ColumnInfo(name = "amount")
    private int amount;
    @ColumnInfo(name = "url")
    private String image_url;

    @Ignore
    public Product(int id, String name, String description, String barcode, int price, int amount, String image_url) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.barcode = barcode;
        this.price = price;
        this.amount = amount;
        this.image_url = image_url;
    }

    public Product() {
        //
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}

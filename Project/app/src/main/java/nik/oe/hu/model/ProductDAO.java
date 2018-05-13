package nik.oe.hu.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Haku on 2018. 04. 15..
 */

@Dao
public interface ProductDAO {

    @Query("SELECT * FROM product")
    List<Product> getAllProduct();

    @Query("SELECT * FROM product WHERE name LIKE :name")
    List<Product> getProductsByName(String name);

    @Query("SELECT * FROM product WHERE barcode = :barcode")
    Product getProductByBarCode(String barcode);

    @Query("SELECT * FROM product WHERE id = :id")
    Product getProductByBarID(int id);

    @Insert
    void Insert(Product product);
}

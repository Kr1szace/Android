package nik.oe.hu.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Haku on 2018. 04. 15..
 *
 *  HA BAJ VAN:
 *  https://medium.com/@ajaysaini.official/building-database-with-room-persistence-library-ecf7d0b8f3e9
 */

@Database(entities = {Product.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract ProductDAO productDAO();

    public static AppDatabase getAppDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "product-database").allowMainThreadQueries().build();
        }return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}

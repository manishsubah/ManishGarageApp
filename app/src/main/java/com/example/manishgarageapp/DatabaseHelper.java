package com.example.manishgarageapp;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = CarData.class, exportSchema = false, version = 1)
public abstract class DatabaseHelper extends RoomDatabase {

    private static DatabaseHelper instance;
    private static final String DB_NAME = "vehicledatabase";

    public static synchronized DatabaseHelper getDB(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context, DatabaseHelper.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract VehicleDao vehicleDao();
}

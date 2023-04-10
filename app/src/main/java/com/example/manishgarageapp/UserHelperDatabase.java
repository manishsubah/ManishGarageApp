package com.example.manishgarageapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = UserData.class, exportSchema = false, version = 1)
public abstract class UserHelperDatabase extends RoomDatabase {

    private static final String UserDB_NAME = "userDatabaseTable";
    public static UserHelperDatabase instanc;

    public static synchronized UserHelperDatabase getDB(Context context) {
        if(instanc == null) {
            instanc = Room.databaseBuilder(context, UserHelperDatabase.class, UserDB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instanc;
    }
    public abstract UserDao userDao();
}

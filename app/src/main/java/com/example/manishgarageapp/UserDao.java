package com.example.manishgarageapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void addUser(UserData userData);
    @Delete
    void deleteUser(UserData userData);

    @Query("SELECT * FROM userDatabaseTable")
    List<UserData> getUserData();

    @Query("SELECT id,userid FROM userDatabaseTable WHERE id = :num")
    public List<UserData> loadUserid(int num);


    @Query("SELECT id,userid,password FROM userDatabaseTable WHERE id = :uid")
    public List<UserData> loadPassword(String uid);



}

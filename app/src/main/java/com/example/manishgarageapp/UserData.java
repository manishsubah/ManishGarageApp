package com.example.manishgarageapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "userDatabaseTable")
public class UserData {

    @PrimaryKey (autoGenerate = true)
    int id = 0;

    @ColumnInfo(name = "userid")
    private String userid;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "password")
    private String password;

    public UserData(int id, String userid, String email, String password) {
        this.id = id;
        this.userid = userid;
        this.email = email;
        this.password = password;
    }

    @Ignore
    public UserData(String userid, String email, String password) {
        this.userid = userid;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUserid() {
        return userid;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

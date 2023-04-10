package com.example.manishgarageapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "vehicledatabase")
public class CarData {

    @PrimaryKey(autoGenerate = true)
    int id = 0;
    @ColumnInfo(name = "makeName")
    private String makeName;
    @ColumnInfo(name = "modelName")
    private String modelName;

    CarData(int id, String makeName, String modelName) {
        this.id = id;
        this.makeName = makeName;
        this.modelName = modelName;
    }
    @Ignore
    CarData(String makeName, String modelName) {
        this.makeName = makeName;
        this.modelName = modelName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

}

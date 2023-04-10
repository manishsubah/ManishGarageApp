package com.example.manishgarageapp;

import com.google.gson.annotations.SerializedName;

public class MyDataModel {

    @SerializedName("Make_ID")
    private String Make_ID;
    @SerializedName("Make_Name")
    private String Make_Name;
    @SerializedName("Model_ID")
    private String Model_ID;
    @SerializedName("Model_Name")
    private String Model_Name;

    public String getMake_ID() {
        return Make_ID;
    }
    public void setMake_ID(String Make_ID) {
        this.Make_ID = Make_ID;
    }

    public String getMake_Name() {
        return Make_Name;
    }
    public void setMake_Name(String Make_Name) {
        this.Make_Name = Make_Name;
    }

    public String getModel_ID() {
        return Model_ID;
    }

    public void setModel_ID(String Model_ID) {
        this.Model_ID = Model_ID;
    }

    public String getModel_Name() {
        return Model_Name;
    }

    public void setModel_Name(String Model_Name) {
        this.Model_Name = Model_Name;
    }

    public MyDataModel(String Make_ID, String Make_Name, String Model_ID, String Model_Name) {
        this.Make_ID = Make_ID;
        this.Make_Name = Make_Name;
        this.Model_ID = Model_ID;
        this.Model_Name = Model_Name;
    }
}

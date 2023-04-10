package com.example.manishgarageapp;

import com.google.gson.annotations.SerializedName;

public class MyData {

    @SerializedName("Make_ID")
    private String Make_ID;
    @SerializedName("Make_Name")
    private String Make_Name;
    public String getMakeId() {
        return Make_ID;
    }

    public void setMakeId(String Make_ID) {
        this.Make_ID = Make_ID;
    }

    public String getMakeName() {
        return Make_Name;
    }
    public void setMakeName(String Make_Name) {
        this.Make_Name = Make_Name;
    }

    public MyData(String Make_ID, String Make_Name) {
        this.Make_ID = Make_ID;
        this.Make_Name = Make_Name;
    }
}

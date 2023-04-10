package com.example.manishgarageapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface VehicleDao {

    @Insert
    void addTx(CarData cardata);

    @Update
    void updataTx(CarData cardata);

    @Delete
    void deleteTx(CarData cardata);

    @Query("DELETE FROM vehicledatabase WHERE id = :did")
    void deleteById(int did);


    @Query("SELECT * FROM vehicledatabase")
    List<CarData> getAllData();
    //LiveData<List<CarData>> getAllData();
}

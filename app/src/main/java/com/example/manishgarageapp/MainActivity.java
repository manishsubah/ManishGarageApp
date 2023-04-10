package com.example.manishgarageapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<String> vehicleID;
    private ArrayList<String> vehicleModel;
    private Spinner makeId;
    private Spinner modelId;
    private ArrayList<MyData> vehicleData;
    private ArrayList<MyDataModel> vehicleModelData;
    private Button addCar;
    DatabaseHelper databaseHelper;
    public String textMakeId;
    public String textCarName;
    public String textCarModel;
    public String searchMakeId;
    List<CarData> carDatabase = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.carlist);
        makeId = findViewById(R.id.makeid);
        modelId = findViewById(R.id.modelid);
        addCar = findViewById(R.id.addcar);

        vehicleID = new ArrayList<String>();
        vehicleModel = new ArrayList<String>();

        databaseHelper = DatabaseHelper.getDB(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        modelId.setEnabled(false);

        showVehicle();
        AddVehicle();
        LogOut();
        SpinnerIdMake();
        FirstApiCall();
    }

    private void FirstApiCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://vpic.nhtsa.dot.gov/api/vehicles/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        firstApi retrofitApi = retrofit.create(firstApi.class);
        Call<JsonArray> call = retrofitApi.getCourse();
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                JsonArray jsonArray = response.body();
                assert jsonArray != null;
                vehicleData = new ArrayList<>(Arrays.asList(jsonArray.Results.toArray(new MyData[0])));
                vehicleID.add("Select make");
                for(int i=0; i<vehicleData.size(); i++) {
                    vehicleID.add(vehicleData.get(i).getMakeId().toString());
                }

                ArrayAdapter<String> ad = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, vehicleID);
                ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                makeId.setAdapter(ad);
            }
            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {

            }
        });

    }

    private void SpinnerIdMake() {
        makeId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textMakeId = makeId.getSelectedItem().toString();
                vehicleModel.clear();
                SecondApiCall();
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void LogOut() {
        Button logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showVehicle() {
        ArrayList<CarData> arrCar = (ArrayList<CarData>) databaseHelper.vehicleDao().getAllData();
        recyclerView.setAdapter(new MyAdapter(arrCar, this));
    }

    private void SecondApiCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://vpic.nhtsa.dot.gov/api/vehicles/GetModelsForMakeId/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        secondApi retrofitApi = retrofit.create(secondApi.class);
        Call<JsonArrayModel> call = retrofitApi.getModel();
        call.enqueue(new Callback<JsonArrayModel>() {
            @Override
            public void onResponse(Call<JsonArrayModel> call, Response<JsonArrayModel> response) {
                JsonArrayModel jArray = response.body();
                vehicleModelData = new ArrayList<>(Arrays.asList(jArray.Results.toArray(new MyDataModel[0])));
                vehicleModel.add("Select model");
                for(int i=0; i<vehicleModelData.size(); i++) {
                    searchMakeId = vehicleModelData.get(i).getMake_ID().toString();
                    //vehicleModel.add(vehicleModelData.get(i).getModel_ID().toString());
                    if(Objects.equals(textMakeId, searchMakeId)) {
                        vehicleModel.add(vehicleModelData.get(i).getModel_ID().toString());
                        textCarName = vehicleModelData.get(i).getMake_Name().toString();
                        textCarModel = vehicleModelData.get(i).getModel_Name().toString();
                        Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                    }
                }

                ArrayAdapter<String> ad = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, vehicleModel);
                ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                modelId.setAdapter(ad);
                modelId.setEnabled(true);



            }

            @Override
            public void onFailure(Call<JsonArrayModel> call, Throwable t) {

            }
        });


    }

    void AddVehicle() {
            addCar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String cName;
                    String cModel;
                    cName = textCarName;
                    cModel = textCarModel;
                    if((makeId.getSelectedItem() != "Select make") && (modelId.getSelectedItem() != "Select model")) {
                        if (!Objects.equals(textCarName, "") && !Objects.equals(textCarModel, "")) {
                            databaseHelper.vehicleDao().addTx(new CarData(cName, cModel));
                            showVehicle();
                            textCarModel = "";
                            textCarName = "";
                        } else {
                            Toast.makeText(MainActivity.this, "Please Select Other Car. This Car already in the List.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(modelId.getSelectedItem() == "Select model") {
                        Toast.makeText(MainActivity.this, "Please Select Model.", Toast.LENGTH_SHORT).show();
                    }


                }
            });

        }
}
package com.example.manishgarageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SignUp extends AppCompatActivity {
    Button signUp;
    TextInputEditText userId;
    TextInputEditText email;
    TextInputEditText passWord;
    TextView signIn;
    UserHelperDatabase userHelperDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userHelperDatabase =UserHelperDatabase.getDB(this);


        signUp = findViewById(R.id.signup);
        userId = findViewById(R.id.user);
        email = findViewById(R.id.email);
        passWord = findViewById(R.id.password);
        signIn = findViewById(R.id.signin);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uId;
                String mail;
                String pass;
                uId = Objects.requireNonNull(userId.getText()).toString();
                mail = Objects.requireNonNull(email.getText()).toString();
                pass = Objects.requireNonNull(passWord.getText()).toString();

                if(!userId.getText().toString().equals("") && !email.getText().toString().equals("") && !passWord.getText().toString().equals("")) {
                    userHelperDatabase.userDao().addUser(new UserData(uId, mail, pass));
                    Toast.makeText(SignUp.this, "User Registered.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(SignUp.this, "Enter UserId, Email, Password.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
                startActivity(intent);

            }
        });


    }
}
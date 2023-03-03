package com.example.myfirstregistration_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    String username;
    String password;
    EditText user;
    EditText pass;
    Button reg;
    Button login;
    public static String name;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        login = findViewById(R.id.loginBttn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = findViewById(R.id.editTextTextEmailAddress);
                username = user.getText().toString();

                pass = findViewById(R.id.editTextTextPassword);
                password = pass.getText().toString();

                getUsername(username, password);
            }
        });



        reg = findViewById(R.id.registerBttn);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homepage = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(homepage);

            }
        });



    }
    public void getUsername(String User, String Pass){
        db.collection("users").document(User)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {

                            String password = documentSnapshot.getString("password");
                            Toast.makeText(MainActivity.this, "pass "+password, Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this, "password "+Pass, Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this, "User "+User, Toast.LENGTH_SHORT).show();
                            if(Pass.equals(password) ){
                                name = "WELCOME "+documentSnapshot.getString("firstname") +" "+ documentSnapshot.getString("lastname");
                                Toast.makeText(MainActivity.this, "login successfully", Toast.LENGTH_SHORT).show();
                                Intent homepage = new Intent(MainActivity.this, HelloActivity.class);
                                startActivity(homepage);

                            }else {
                                Toast.makeText(MainActivity.this, "wrong inputs", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "document not found", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
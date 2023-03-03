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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    String register_user;
    String register_password;
    String register_first;
    String register_last;
    EditText edit_user;
    EditText edit_password;
    EditText edit_first;
    EditText edit_last;
    Button bttn;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edit_user = findViewById(R.id.editTextTextPersonName);
        edit_password = findViewById(R.id.editTextTextPersonName2);
        edit_first = findViewById(R.id.editTextTextPersonName3);
        edit_last = findViewById(R.id.editTextTextPersonName5);




        bttn = findViewById(R.id.button);

        bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                register_user = edit_user.getText().toString();
                register_password = edit_password.getText().toString();
                register_first = edit_first.getText().toString();
                register_last = edit_last.getText().toString();
                
                Map<String,Object> user = new HashMap<>();

                user.put("username",register_user);
                user.put("password",register_password);
                user.put("firstname",register_first);
                user.put("lastname",register_last);

                db.collection("users")
                        .document(register_user)
                        .set(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(RegisterActivity.this, "Succesfully added", Toast.LENGTH_SHORT).show();
                                Intent homepage = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(homepage);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(RegisterActivity.this, "Failed added", Toast.LENGTH_SHORT).show();
                            }
                        });



            }
        });


    }
}
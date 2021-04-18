package com.example.assignment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    String email;
    String password;
    String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
    }

    public void signUpCustomer(View view) {

        EditText emailText = findViewById(R.id.emailText);
        EditText passwordText = findViewById(R.id.passwordText);

        email = emailText.getText().toString();
        password = passwordText.getText().toString();
        userType = "Customer";


        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Log.d("MyActivity", "createUserWithEmail:success");
                            mUser = mAuth.getCurrentUser();
                            String userID = mUser.getUid();
                            DatabaseReference db = FirebaseDatabase.getInstance().getReference();
                            User user = new User.Builder(email).atUserType(userType).build();
                            db.child("User").child(userID).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(MainActivity.this, "Customer Signed Up", Toast.LENGTH_SHORT).show();
                                }
                            });
                            Intent intent = new Intent(getApplicationContext(), CustomerPage.class);
                            startActivity(intent);
                        } else {
                            Log.w("MyActivity", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void signUpAdmin(View view) {

        EditText emailText = findViewById(R.id.emailText);
        EditText passwordText = findViewById(R.id.passwordText);

        email = emailText.getText().toString();
        password = passwordText.getText().toString();
        userType = "Admin";

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Log.d("MyActivity", "createUserWithEmail:success");
                            mUser = mAuth.getCurrentUser();
                            String userID = mUser.getUid();
                            DatabaseReference db = FirebaseDatabase.getInstance().getReference();
                            User user = new User.Builder(email).atUserType(userType).build();
                            db.child("User").child(userID).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(MainActivity.this, "Admin Signed Up", Toast.LENGTH_SHORT).show();
                                }
                            });
                            Intent intent = new Intent(getApplicationContext(), AdminPage.class);
                            startActivity(intent);
                        } else {
                            Log.w("MyActivity", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void signIn(View view) {

        EditText emailText = findViewById(R.id.emailText);
        EditText passwordText = findViewById(R.id.passwordText);

        email = emailText.getText().toString();
        password = passwordText.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            mUser = mAuth.getCurrentUser();
                            String userID = mUser.getUid();
                            DatabaseReference fireDB = FirebaseDatabase.getInstance().getReference("User").child(userID);
                            fireDB.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    User userObj = snapshot.getValue(User.class);
                                    userType = userObj.getUserType();
                                    if (userType.equalsIgnoreCase("Customer")){
                                        Toast.makeText(MainActivity.this, "Customer signed in", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), CustomerPage.class);
                                        startActivity(intent);
                                    }
                                   if (userType.equalsIgnoreCase("Admin")){
                                        Toast.makeText(MainActivity.this, "Admin signed in", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), AdminPage.class);
                                        startActivity(intent);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        } else {
                            Log.w("MySignin", "SignInUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
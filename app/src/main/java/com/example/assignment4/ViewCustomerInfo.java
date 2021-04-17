package com.example.assignment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewCustomerInfo extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer_info);

        EditText edtName = (EditText) findViewById(R.id.edTextName);
        EditText edtAddress = (EditText) findViewById(R.id.edTextAddress);
        EditText edtCcno = (EditText) findViewById(R.id.edTextCcno);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        String userID = mUser.getUid();
        DatabaseReference fireDB = FirebaseDatabase.getInstance().getReference("User").child(userID);
        fireDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userObj = snapshot.getValue(User.class);
                edtName.setText(userObj.getName());
                edtAddress.setText(userObj.getAddress());
                edtCcno.setText(userObj.getCcno());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("DBError", "Cancel Access DB");
            }
        });
    }

    public void confirmInfo(View view){
        EditText edtName = (EditText) findViewById(R.id.edTextName);
        EditText edtAddress = (EditText) findViewById(R.id.edTextAddress);
        EditText edtCcno = (EditText) findViewById(R.id.edTextCcno);

        String name = edtName.getText().toString();
        String address = edtAddress.getText().toString();
        String ccno = edtCcno.getText().toString();

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        String userID = mUser.getUid();
        DatabaseReference fireDB = FirebaseDatabase.getInstance().getReference("User").child(userID);
        fireDB.child("name").setValue(name);
        fireDB.child("address").setValue(address);
        fireDB.child("ccno").setValue(ccno);
        Toast.makeText(this, "Customer Info Confirmed", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, CustomerPage.class);
        startActivity(intent);
    }
}
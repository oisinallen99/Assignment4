package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddItem extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
    }

    public void createItem(View view){
        EditText edtTitle = findViewById(R.id.edTextTitle);
        String title = edtTitle.getText().toString();
        EditText edtManufacturer = findViewById(R.id.edTextManufacturer);
        String manufacturer = edtManufacturer.getText().toString();
        EditText edtCategory = findViewById(R.id.edTextCategory);
        String category = edtCategory.getText().toString();
        EditText edtPrice = findViewById(R.id.edTextPrice);
        String price = edtPrice.getText().toString();
        EditText edtStock = findViewById(R.id.edTextStock);
        int stock = Integer.parseInt(edtStock.getText().toString());

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        Item item = new Item(title,manufacturer,category,price,stock);
        db.child("Item").child(title).setValue(item).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(AddItem.this, "Item Added", Toast.LENGTH_SHORT).show();
            }
        });
        Intent intent = new Intent(getApplicationContext(), AdminPage.class);
        startActivity(intent);
    }
}
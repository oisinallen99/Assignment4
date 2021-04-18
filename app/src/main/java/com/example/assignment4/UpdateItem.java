package com.example.assignment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);

        Item myItem = getIntent().getParcelableExtra("item");
        EditText edtTitle = (EditText) findViewById(R.id.edTextTitle);
        EditText edtManufacturer = (EditText) findViewById(R.id.edTextManufacturer);
        EditText edtCategory = (EditText) findViewById(R.id.edTextCategory);
        EditText edtStock = (EditText) findViewById(R.id.edTextStock);
        EditText edtPrice = (EditText) findViewById(R.id.edTextPrice);

        edtTitle.setText(myItem.getTitle());
        edtManufacturer.setText(myItem.getManufacturer());
        edtCategory.setText(myItem.getCategory());
        edtStock.setText(String.valueOf(myItem.getStock()));
        edtPrice.setText(myItem.getPrice());
    }

    public void updateItemDetails(View view){
        EditText edtTitle = (EditText) findViewById(R.id.edTextTitle);
        EditText edtManufacturer = (EditText) findViewById(R.id.edTextManufacturer);
        EditText edtCategory = (EditText) findViewById(R.id.edTextCategory);
        EditText edtStock = (EditText) findViewById(R.id.edTextStock);
        EditText edtPrice = (EditText) findViewById(R.id.edTextPrice);

        String title = edtTitle.getText().toString();
        String manufacturer = edtManufacturer.getText().toString();
        String category = edtCategory.getText().toString();
        int stock = Integer.parseInt(edtStock.getText().toString());
        String price = edtPrice.getText().toString();

        DatabaseReference fireDB = FirebaseDatabase.getInstance().getReference("Item").child(title);
        fireDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                fireDB.child("category").setValue(category);
                fireDB.child("manufacturer").setValue(manufacturer);
                fireDB.child("price").setValue(price);
                fireDB.child("stock").setValue(stock);
                fireDB.child("title").setValue(title);

                Toast.makeText(UpdateItem.this, "Item Updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AdminPage.class);
                startActivity(intent);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
package com.example.assignment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddToBasket extends AppCompatActivity implements PruchaseItemFacade, Command {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    String name;
    String title;
    int stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_basket);

        mAuth = FirebaseAuth.getInstance();

        Item myItem = getIntent().getParcelableExtra("item");
        EditText edtTitle = (EditText) findViewById(R.id.edTextTitle);
        EditText edtManufacturer = (EditText) findViewById(R.id.edTextManufacturer);
        EditText edtCategory = (EditText) findViewById(R.id.edTextCategory);
        EditText edtStock = (EditText) findViewById(R.id.edTextStock);
        EditText edtPrice = (EditText) findViewById(R.id.edTextPrice);

        title = myItem.getTitle();

        edtTitle.setText(myItem.getTitle());
        edtManufacturer.setText(myItem.getManufacturer());
        edtCategory.setText(myItem.getCategory());
        edtStock.setText(String.valueOf(myItem.getStock()));
        edtPrice.setText(myItem.getPrice());
    }

    public void purchaseItem(View view) {
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        String userID = mUser.getUid();
        DatabaseReference fireDB = FirebaseDatabase.getInstance().getReference("User").child(userID);
        fireDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userObj = snapshot.getValue(User.class);
                name = userObj.getName();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference fireDB2 = FirebaseDatabase.getInstance().getReference("Item").child(title);
        fireDB2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Item itemObj = snapshot.getValue(Item.class);
                if (purchaseItemFac(itemObj)) {
                    stock--;
                    fireDB2.child("stock").setValue(stock);
                    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
                    Purchase purchase = new Purchase(name, title, 1);
                    Long tsLong = System.currentTimeMillis() / 1000;
                    String timestamp = tsLong.toString();
                    db.child("Purchase").child(timestamp).setValue(purchase).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            executeSuccess();
                        }
                    });
                } else {
                    executeFail();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    public boolean purchaseItemFac(Item item) {
        stock = item.getStock();
        if (stock > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void executeSuccess() {
        Toast.makeText(AddToBasket.this, "Item Purchased", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), CustomerPage.class);
        startActivity(intent);
    }

    @Override
    public void executeFail() {
        Toast.makeText(AddToBasket.this, "Error: no stock", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), CustomerPage.class);
        startActivity(intent);
    }
}
package com.example.assignment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewCustomers extends AppCompatActivity {

    ArrayList<String> myDataset= new ArrayList<String>();
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customers);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.my_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        DatabaseReference fireDB = FirebaseDatabase.getInstance().getReference("User");
        fireDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()){
                    user = data.getValue(User.class);
                    if(user.getUserType().equalsIgnoreCase("Customer")){
                        myDataset.add("Name: " + user.getName() + "\nEmail: " + user.getEmail() + "\nAddress: " + user.getAddress());
                    }
                }
                mRecyclerView.setLayoutManager(mLayoutManager);
                MyAdapter mAdapter = new MyAdapter(myDataset);
                mRecyclerView.addItemDecoration(new DividerItemDecoration(ViewCustomers.this, LinearLayoutManager.VERTICAL));
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
package com.example.assignment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchResult extends AppCompatActivity implements MyAdapter.clickListener {

    ArrayList<String> myDataset= new ArrayList<String>();
    Item item = new Item();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.my_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        String type = getIntent().getStringExtra("type");
        String search = getIntent().getStringExtra("search");

        DatabaseReference fireDB = FirebaseDatabase.getInstance().getReference("Item");
        fireDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()){
                    if (type.equalsIgnoreCase("Title")) {
                        item = data.getValue(Item.class);
                        if (item.getTitle().contains(search)) {
                            myDataset.add("Title: " + item.getTitle() + "\nCategory: " + item.getCategory() + "\nManufacturer: " + item.getManufacturer() + "\nPrice: " + item.getPrice() + "\nStock: " + item.getStock());
                        }
                    }
                        if (type.equalsIgnoreCase("Manufacturer")) {
                            item = data.getValue(Item.class);
                            if (item.getManufacturer().contains(search)) {
                                myDataset.add("Title: " + item.getTitle() + "\nCategory: " + item.getCategory() + "\nManufacturer: " + item.getManufacturer() + "\nPrice: " + item.getPrice() + "\nStock: " + item.getStock());
                            }
                        }
                    if (type.equalsIgnoreCase("Category")) {
                        item = data.getValue(Item.class);
                        if (item.getCategory().contains(search)) {
                            myDataset.add("Title: " + item.getTitle() + "\nCategory: " + item.getCategory() + "\nManufacturer: " + item.getManufacturer() + "\nPrice: " + item.getPrice() + "\nStock: " + item.getStock());
                        }
                    }
                }
                mRecyclerView.setLayoutManager(mLayoutManager);
                MyAdapter mAdapter = new MyAdapter(myDataset);
                mRecyclerView.addItemDecoration(new DividerItemDecoration(SearchResult.this, LinearLayoutManager.VERTICAL));
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(int position) {
        myDataset.get(position);
        Intent intent = new Intent(this, AddToBasket.class);
        startActivity(intent);
    }
}
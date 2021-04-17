package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CustomerPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_page);
        Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"Manufacturer", "Title", "Category"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    public void search(View view){
        Spinner dropdown = findViewById(R.id.spinner1);
        EditText searchText = findViewById(R.id.editTextSearch);
        String search = searchText.getText().toString();
        String searchType = dropdown.getSelectedItem().toString();
            Intent intent = new Intent(this, SearchResult.class);
            intent.putExtra("search", search);
            intent.putExtra("type", searchType);
            startActivity(intent);
    }

    public void myInfo(View view){
        Intent intent = new Intent(this, ViewCustomerInfo.class);
        startActivity(intent);
    }

    public void checkout(View view){
        Intent intent = new Intent(this, ViewCustomerInfo.class);
        startActivity(intent);
    }
}
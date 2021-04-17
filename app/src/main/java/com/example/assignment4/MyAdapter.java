package com.example.assignment4;

import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<String> mylistvalues;
    private RecyclerViewClickInterface recyclerViewClickInterface;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtView;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtView = (TextView) itemView.findViewById(R.id.textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewClickInterface.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    public MyAdapter(ArrayList<String> myDataset, RecyclerViewClickInterface recyclerViewClickInterface){
        this.mylistvalues = myDataset;
        this.recyclerViewClickInterface = recyclerViewClickInterface;

    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.row_layout, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final String name = mylistvalues.get(position);
        holder.txtView.setText(name);
    }

    @Override
    public int getItemCount() {
        return mylistvalues.size();
    }

}
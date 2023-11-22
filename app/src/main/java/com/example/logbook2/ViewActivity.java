package com.example.logbook2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    MyDatabaseHelper myDB;
    ArrayList<String> user_name, user_dob, user_email;
    ArrayList<Integer> image_id;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        myDB = new MyDatabaseHelper(ViewActivity.this);
        image_id = new ArrayList<>();
        user_name = new ArrayList<>();
        user_dob = new ArrayList<>();
        user_email = new ArrayList<>();

        storeDataInArrays();

        myAdapter = new MyAdapter(ViewActivity.this,this, image_id, user_name, user_dob,
                user_email);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    private void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                image_id.add(cursor.getInt(0));
                user_name.add(cursor.getString(1));
                user_dob.add(cursor.getString(2));
                user_email.add(cursor.getString(3));
            }
        }
    }
}
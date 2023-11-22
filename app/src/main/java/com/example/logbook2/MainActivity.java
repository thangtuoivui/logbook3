package com.example.logbook2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    ImageView imageView;
    Button change, add, view, date;
    TextView name_ip, dob_ip, email_ip;
    int count;

    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.profile_picture);
        name_ip = findViewById(R.id.name_input);
        dob_ip = findViewById(R.id.dob_input);
        email_ip = findViewById(R.id.email_input);

        change = findViewById(R.id.change_button);
        add = findViewById(R.id.add_button);
        view = findViewById(R.id.view_button);

        TypedArray images = getResources().obtainTypedArray(R.array.random_images);
        imageView.setImageResource(images.getResourceId(0, -1));
        count = 0;

        String name = name_ip.getText().toString().trim();
        String dob = dob_ip.getText().toString().trim();
        String email = email_ip.getText().toString().trim();

        date = findViewById(R.id.date_bt);


        date.setOnClickListener(view ->{
            openDatePicker();
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count < (images.length() - 1)){
                    count++;
                }
                else {
                    count = 0;
                }
                imageView.setImageResource(images.getResourceId(count, -1));
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);
                if(name != "" && dob != "" && email != ""){
                    if (emailValidator()){
                        myDB.addUser(count, name_ip.getText().toString().trim(),
                                dob_ip.getText().toString().trim(),
                                email_ip.getText().toString().trim());
                        Toast.makeText(getApplicationContext(), "Email Verified !", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Enter valid Email address !", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Fill all text box first!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openView();
            }
        });
    }

    public void openView() {
        Intent intent = new Intent(this, ViewActivity.class);
        this.startActivity(intent);
    }
    private void openDatePicker(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                dob_ip.setText(String.valueOf(day)+ "/"+String.valueOf(month)+ "/"+String.valueOf(year));
            }
        }, 2023, 01, 20);
        datePickerDialog.show();
    }

    public boolean emailValidator() {

        // extract the entered data from the EditText
        String emailToText = email_ip.getText().toString();

        return !emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches();
    }


}
package com.example.zhup0115.mycontactapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    DatabaseHelper myDb;
    EditText editName;
    EditText editAge;
    EditText editAddress;
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper (this);

        editName = (EditText)(findViewById(R.id.editName));
        editAge = (EditText)(findViewById(R.id.editAge));
        editAddress = (EditText)(findViewById(R.id.editAddress));
    }

    public void addData (View v){
        boolean isInserted = myDb.insertData(editName.getText().toString(),editAge.getText().toString(),editAddress.getText().toString());

        if(isInserted == true){
            Log.d("MyContact", "Data insertion successful");
            //create toast message to user indicating data inserted correctly
            Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
        }

        else {
            Log.d("MyContact", "Data insertion NOT successful");
            //create toast message to user indicating data inserted incorrectly
            Toast.makeText(getApplicationContext(), "NOT Success", Toast.LENGTH_SHORT).show();
        }
    }

    public void viewData(View v){
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0){
            showMessage ("Error","No data found in database");
            // put a Log.d message and toast
            return;
        }
        StringBuffer buffer = new StringBuffer();
        //setup loop with cursor movetonext method while loop
        // append each column(COL) to each buffer (use append method)
        // use get string method
        while (res.getCount()>0){

        }



        showMessage ("Data", buffer.toString());
    }



    private void showMessage(String error, String s) {
    }
}

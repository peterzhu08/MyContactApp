package com.example.zhup0115.mycontactapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
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
    EditText editSearch;
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper (this);

        editName = (EditText)(findViewById(R.id.editName));
        editAge = (EditText)(findViewById(R.id.editAge));
        editAddress = (EditText)(findViewById(R.id.editAddress));
        editSearch = (EditText)(findViewById(R.id.editSearch));

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
            Log.d("MyContact", "No data found in database");
            Toast.makeText(getApplicationContext(), "No data found in database", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        //setup loop with cursor movetonext method while loop
        // append each column(COL) to each buffer (use append method)
        // use get string method

        res.moveToFirst();
        while (res.moveToNext()){
            for (int i = 1; i<=3 ; i++) {
                buffer.append(res.getString(i));
                buffer.append("\n");
            }

            buffer.append("\n");
}
        showMessage ("Data", buffer.toString());
    }

    public void searchData (View v){
        Cursor res = myDb.getAllData();

        if (res.getCount() == 0){
            showMessage ("Error","No data found in database");
            Log.d("MyContact", "No data found in database");
            Toast.makeText(getApplicationContext(), "No data found in database", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuffer buffer = new StringBuffer();

        res.moveToFirst();

        while (res.moveToNext()){

            for (int i = 1; i<=3 ; i++) {

                if(res.getString(i).equals(editSearch.getText().toString())) {

                    for (int j = 1; j <=3 ; j++){
                        buffer.append(res.getString(j));
                        buffer.append("\n");
                    }
                }
            }

            buffer.append("\n");
        }
        showMessage ("Data", buffer.toString());
    }



    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}

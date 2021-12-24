package com.example.listreminder_mid;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Homepage extends AppCompatActivity {

    // initialize variables
    Button btnHistory, btnGet, btnView;
    DataBaseHelper db;
    TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hompage);

        // declare variable
        db = new DataBaseHelper(this);
        tvName = (TextView)findViewById(R.id.UserName);
        btnHistory = (Button)findViewById(R.id.BtnHistory);
        btnView = (Button)findViewById(R.id.BtnView);
        btnGet = (Button)findViewById(R.id.BtnGet);

        // get data from shared preference and sets the text to be displayed
        tvName.setText(Preferences.getLoggedInUser(getBaseContext()));

        // when user click btn Transaction
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor result = db.view();
                if (result.getCount()==0) {
                    Toast.makeText(getApplicationContext(), "No Data Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (result.moveToNext()) {
                    buffer.append("Id: "+result.getString(0)+"\n");
                    buffer.append("Customer: "+result.getString(1)+"\n");
                    buffer.append("Product: "+result.getString(2)+"\n");
                    buffer.append("Quantity: "+result.getString(3)+"\n");
                    buffer.append("Price: "+result.getString(4)+"\n");
                    buffer.append("Total: "+result.getString(5)+"\n");
                    buffer.append("Description: "+result.getString(6)+"\n\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(Homepage.this);
                builder.setCancelable(true);
                builder.setTitle("Transaction History");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }

    public void onView(View view) {
        Intent intent = new Intent(Homepage.this, Web_View.class);
        startActivity(intent);
    }

    public void onGet(View view) {
        Intent intent = new Intent(Homepage.this, Main_Product.class);
        startActivity(intent);
    }

    public void onOut(View view) {
        Intent intent = new Intent(Homepage.this, SignupNLogin.class);
        startActivity(intent);
    }

}
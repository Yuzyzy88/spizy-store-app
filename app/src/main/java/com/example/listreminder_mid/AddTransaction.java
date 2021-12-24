package com.example.listreminder_mid;

import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AddTransaction extends AppCompatActivity {

    // initialize variables
    EditText etQuantity, etDescription;
    TextView tvHistoryP, tvCostumer, tvTotal, tvId, tvProduct, tvPrice;
    DataBaseHelper db;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // declare variable
        db = new DataBaseHelper(this);
        add = (Button)findViewById(R.id.AddBtn);
        etQuantity = (EditText) findViewById(R.id.editTextQuantity);
        etDescription = (EditText) findViewById(R.id.editTextDescription);
        tvHistoryP = (TextView)findViewById(R.id.tvHistory);
        tvPrice = (TextView) findViewById(R.id.textViewPrice);
        tvProduct = (TextView) findViewById(R.id.textViewProduct);
        tvId = (TextView) findViewById(R.id.textViewId);
        tvCostumer = (TextView)findViewById(R.id.textViewCostumer);
        tvTotal = (TextView)findViewById(R.id.textViewTotal);

        // get data from shared preference and sets the text to be displayed
        tvCostumer.setText(Preferences.getLoggedInUser(getBaseContext()));
        tvId.setText(Preferences.getLoggedInId(getBaseContext()));

        // Retrieve extended data from RecyclerViewAdapter.java use 'name'
        String product = getIntent().getStringExtra("productName");
        String price = getIntent().getStringExtra("productPrice");

        // after retrieve data from recycler view adapter, now setting data to each view
        tvProduct.setText(product);
        tvPrice.setText(price);

        // when user enter quantity edit text
        etQuantity.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            String EtQuantity = etQuantity.getText().toString();
                            float result =  (Float.parseFloat(price)) * (Integer.parseInt(EtQuantity));
                            tvTotal.setText("" + result);
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
    }

    public void AddTransac (View view) {
        ConnFileAdd add = new ConnFileAdd(this);

        // get data and change it to string
        String transID = tvId.getText().toString();
        String transCustomer = tvCostumer.getText().toString();
        String transProduct = tvProduct.getText().toString();
        String transQty = etQuantity.getText().toString();
        String transPrice = tvPrice.getText().toString();
        String transTotal = tvTotal.getText().toString();
        String transDesc = etDescription.getText().toString();

        // input to the web server with the help of the ConnFile.java file
        add.execute("Add", transID, transCustomer, transProduct, transQty, transPrice, transTotal, transDesc);

        // input to sqlLite
        Boolean checkInsert = db.insert(transID, transCustomer, transProduct, transQty, transPrice, transTotal, transDesc);
        if (checkInsert==true) {
            Toast.makeText(getApplicationContext(), "Transaction Success, Click History to check", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Transaction Failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void History (View view) {
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

        AlertDialog.Builder builder = new AlertDialog.Builder(AddTransaction.this);
        builder.setCancelable(true);
        builder.setTitle("Transaction History");
        builder.setMessage(buffer.toString());
        builder.show();
    }
}
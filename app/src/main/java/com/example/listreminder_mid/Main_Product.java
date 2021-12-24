package com.example.listreminder_mid;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main_Product extends AppCompatActivity {

    // initialize variables
    RecyclerView recyclerView;
    List<Product> product;
    private final String JSON_URL = "https://papanmain.com/test/get_final_product.php";
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__product);

        // declare variable
        recyclerView = findViewById(R.id.recyclerView1);
        product = new ArrayList<>();
        extractProduct();
    }

    //
    private void extractProduct() {
        // A queue containing the Network / HTTP Requests that need to be generated.
        RequestQueue queue = Volley.newRequestQueue(this);

        //Specify a URL and get a JSON object or array (each) in response.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            // ordered sequence of values
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        // used to get objects from a pair
                        JSONObject jsonObject = response.getJSONObject(i);

                        /* set into the variable that was created in
                        * Product.java, get data object from JSON and and change to string*/
                        Product products = new Product();
                        products.setProductId(jsonObject.getString("final_productID").toString());
                        products.setName(jsonObject.getString("final_productName").toString());
                        products.setPrice(jsonObject.getString("final_productPrice").toString());
                        products.setProduct_type(jsonObject.getString("final_productType").toString());
                        product.add(products);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new RecyclerViewAdapter(getApplicationContext(), product);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("", "onErrorResponse: " + error.getMessage());
            }
        });

        queue.add(jsonArrayRequest);
    }
}

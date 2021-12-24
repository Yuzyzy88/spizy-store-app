package com.example.listreminder_mid;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class ConnFileAdd extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog alertDialog;

    ConnFileAdd(Context ctx) {
        context = ctx;
    };

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String add_url = "https://papanmain.com/test/add_transaction.php";

        if(type.equals("Add")) {
            try {
                String transID = params[1];
                String transCustomer = params[2];
                String transProduct = params[3];
                String transQty = params[4];
                String transPrice = params[5];
                String transTotal = params[6];
                String transDesc = params[7];

                URL url = new URL(add_url);
                HttpURLConnection httpURLConnection;
                httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("transID", "UTF-8") + "=" + URLEncoder.encode(transID, "UTF-8") + "&"
                        + URLEncoder.encode("transCustomer", "UTF-8") + "=" + URLEncoder.encode(transCustomer, "UTF-8") + "&"
                        + URLEncoder.encode("transProduct", "UTF-8") + "=" + URLEncoder.encode(transProduct, "UTF-8") + "&"
                        + URLEncoder.encode("transQty", "UTF-8") + "=" + URLEncoder.encode(transQty, "UTF-8") + "&"
                        + URLEncoder.encode("transPrice", "UTF-8") + "=" + URLEncoder.encode(transPrice, "UTF-8") + "&"
                        + URLEncoder.encode("transTotal", "UTF-8") + "=" + URLEncoder.encode(transTotal, "UTF-8") + "&"
                        + URLEncoder.encode("transDesc", "UTF-8") + "=" + URLEncoder.encode(transDesc, "UTF-8") + "&"
                        + URLEncoder.encode("Submit", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();

                /*BufferedReader to provide for the efficient reading of characters, arrays, and lines
                * from url papanmain.com/test/add_transaction.php*/
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = " ";
                String line = " ";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
    }
}

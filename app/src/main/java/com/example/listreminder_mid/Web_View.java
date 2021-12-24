package com.example.listreminder_mid;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class Web_View extends AppCompatActivity {

    // initialize variables
    WebView webView;
    WebSettings webSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        // create Mock version of the WebView

        webView = (WebView)findViewById(R.id.WebView1);
        webSettings = webView.getSettings();

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://papanmain.com/test/view_final_product.php");
    }
}
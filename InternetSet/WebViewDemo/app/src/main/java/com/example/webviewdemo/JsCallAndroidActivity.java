package com.example.webviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class JsCallAndroidActivity extends AppCompatActivity {
    WebView myWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_call_android);
        myWebView = (WebView)findViewById(R.id.js_show);
        // 此处为了简化编程，使用file协议加载本地assets目录下的HTML页面
        // 如有需要，也可以使用http协议加载远程网站的HTML页面
        // 获取WebView的设置对象
        WebSettings webSettings = myWebView.getSettings();
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.setWebChromeClient(new WebChromeClient());
        myWebView.loadUrl("file:///android_asset/test.html");

        // 开启JavaScript调用
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSupportMultipleWindows(true);
        // 将MyObject对象暴露给JavaScript脚本
        // 这样test.html页面中的JavaScript可以通过myObj来调用MyObject的方法
        myWebView.addJavascriptInterface(new MyObject(this),"myObj");
    }
}

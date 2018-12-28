package com.example.echartsdemo2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.abel533.echarts.Option;

import java.util.ArrayList;
import java.util.List;
import static com.example.echartsdemo2.AccessData.getWeekData;

public class MainActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView)findViewById(R.id.webView);

        // 获取指定数据格式的数据,此处可以和外部交互
        List<AccessData> datas = getWeekData();

        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSupportZoom(true);
        webSettings.setDisplayZoomControls(true);

        webView.addJavascriptInterface(new myLineChart(this,datas), "myLine");

        webView.loadUrl("file:///android_asset/myechart.html");
        webView.setWebViewClient(new WebViewClient());
    }


}

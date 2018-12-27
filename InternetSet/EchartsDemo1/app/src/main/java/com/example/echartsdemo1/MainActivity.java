package com.example.echartsdemo1;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    WebView echart_show;
    String TAG;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initListener();
    }

    private void initView() {
        echart_show = (WebView) findViewById(R.id.show_echart);
    }

    // 进度条
    private void initData() {
        dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage("玩儿命加载中...");

        TAG = this.getClass().getName();
    }

    private void initListener() {

        // 进行WebView设置
        WebSettings webSettings = echart_show.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(true);
        echart_show.loadUrl("file:///android_asset/myechart.html");
        echart_show.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                dialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //最好在这里调用js代码 以免网页未加载完成
                echart_show.loadUrl("javascript:createChart('line'," + EchartsDataBean.getInstance().getEchartsLineJson() + ");");
                echart_show.loadUrl("javascript:createChart2('bar'," + EchartsDataBean.getInstance().getEchartsBarJson() + ");");
                echart_show.loadUrl("javascript:createChart3('pie'," + EchartsDataBean.getInstance().getEchartsPieJson() + ");");
                dialog.dismiss();
            }
        });
    }
}

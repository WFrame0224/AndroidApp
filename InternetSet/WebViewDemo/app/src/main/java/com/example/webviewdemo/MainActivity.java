package com.example.webviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText url;
    WebView show;
    Button bn_js;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        url = (EditText) findViewById(R.id.url);
        show = (WebView) findViewById(R.id.show);
        bn_js = (Button) findViewById(R.id.bn_js);
        url.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int keyCode, KeyEvent keyEvent) {
                if (keyCode == EditorInfo.IME_ACTION_GO) {
                    String urlStr = url.getText().toString();
                    urlStr = "http://" + urlStr;
                    // 加载并显示URL对应的网页
                    /**
                     * 需要按照以下25行代码的设置，方能实现不跳转到系统浏览器加载自己的WebView界面
                     * 并能正常运行JavaScript代码
                     */
                    show.getSettings().setJavaScriptEnabled(true);
                    show.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                    show.getSettings().setSupportMultipleWindows(true);
                    show.setWebViewClient(new WebViewClient());
                    show.setWebChromeClient(new WebChromeClient());

                    show.loadUrl(urlStr);
                }
                return false;
            }
        });

        /**
         * 以下代码实现使用WebView加载HTML代码
         */
        /*
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>欢迎您</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<h2>欢迎您访问<a href=\"http://www.crazyit.org\">" + "疯狂Java联盟</a></h2>");
        sb.append("</body>");
        sb.append("</html>");
        // 加载并显示HTML，
        show.getSettings().setJavaScriptEnabled(true);
        show.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        show.getSettings().setSupportMultipleWindows(true);
        show.setWebViewClient(new WebViewClient());
        show.setWebChromeClient(new WebChromeClient());
        show.loadDataWithBaseURL(null,sb.toString(),"text/html","utf-8",null);
        */

        bn_js.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, JsCallAndroidActivity.class);
                startActivity(intent);
            }
        });
    }
}

package com.example.drawabletest;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class SecondActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        final ImageView image = (ImageView) findViewById(R.id.image);
        // 加载动画资源
        final Animation anim = AnimationUtils.loadAnimation(this,R.anim.my_anim);
        // 设置动画结束后保留结束状态
        anim.setFillAfter(true);
        Button bn = (Button)findViewById(R.id.bn);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 开始动画
                image.startAnimation(anim);
            }
        });
        Button bn1 = (Button)findViewById(R.id.bn1);
        bn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 根据XML资源的ID获取解析该资源的解析器
                // XmlResourceParser是XmlPullParser的子类
                XmlResourceParser xrp = getResources().getXml(R.xml.books);
                try{
                    StringBuilder sb = new StringBuilder("");
                    // 还没有到XML文档的结尾处
                    while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT){
                        // 如果遇到了开始标签
                        if (xrp.getEventType() == XmlResourceParser.START_TAG){
                            // 获取该标签的标签名
                            String tagName = xrp.getName();
                            // 如果遇到book标签
                            if (tagName.equals("book")){
                                // 根据属性名开获取属性值
                                String bookName = xrp.getAttributeValue(null,"price");
                                sb.append("价格为：");
                                sb.append(bookName);
                                // 根据属性索引来获取属性值
                                String bookPrice = xrp.getAttributeValue(1);
                                sb.append("出版日期：");
                                sb.append(bookPrice);
                                sb.append("书名");
                                // 获取文本节点的值
                                sb.append(xrp.nextText());
                            }
                            sb.append("\n");
                        }
                        // 获取解析器的下一个事件
                        xrp.next();
                    }
                    EditText show = (EditText)findViewById(R.id.show);
                    show.setText(sb.toString());

                }catch (XmlPullParserException e){
                    e.printStackTrace();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
}

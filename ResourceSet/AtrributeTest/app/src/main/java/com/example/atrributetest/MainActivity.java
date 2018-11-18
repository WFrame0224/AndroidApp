package com.example.atrributetest;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer1 = null;
    MediaPlayer mediaPlayer2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 直接根据声音文件的ID来创建MediaPlayer
        mediaPlayer1 = MediaPlayer.create(this,R.raw.bomb);
        // 获取该应用的AssetManager
        AssetManager am = getAssets();
        try {
            // 获取指定文件对应的AssetFileDescriptor
            AssetFileDescriptor afd = am.openFd("shot.mp3");
            mediaPlayer2 = new MediaPlayer();
            // 使用MediaPlayer加载指定的声音文件
            mediaPlayer2.setDataSource(afd.getFileDescriptor());
            mediaPlayer2.prepare();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void displayRaw(View view) {
        mediaPlayer1.start();
    }

    public void displayAssets(View view) {
        mediaPlayer2.start();
    }
}

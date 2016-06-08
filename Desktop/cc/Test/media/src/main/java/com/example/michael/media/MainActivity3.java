package com.example.michael.media;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.IOException;

/**
 * Created by Michael on 2016/6/7.
 */

public class MainActivity3 extends Activity {

    private MediaPlayer mediaPlayer;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        播放一段音乐

         */
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(!mediaPlayer.isPlaying()){
                mediaPlayer.start();
            }
            }
        });
  findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
            }
        });
  findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.reset();
                    initMedia();
                }
            }
        });
        initMedia();


    }

    private void initMedia() {
    /*
    初始化媒体播放器
     */
        File file=new File(getCacheDir(),"aa.mp3");//获取当前的路径
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.release();
        }
    }
}

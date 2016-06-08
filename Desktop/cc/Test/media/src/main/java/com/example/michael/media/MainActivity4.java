package com.example.michael.media;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;

/**
 * Created by Michael on 2016/6/7.
 */

public class MainActivity4 extends Activity {

   // private VideoView mediaPlayer;
    private File file;
    private VideoView mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = (VideoView) findViewById(R.id.video_view);
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
                    mediaPlayer.resume();//重新播放
                  //  initMedia();
                }
            }
        });
        initMedia();


    }

    private void initMedia() {
    /*
    初始化媒体播放器

    getFileDir() ----- /data/data/cn.xxx.xxx(当前包)/files
getCacheDir() ----- /data/data/cn.xxx.xxx（当前包）/cache
     */
        File file=new File(getCacheDir(),"cc.3gp");//获取当前的路径
       // mediaPlayer = new VideoView();
        try {
            mediaPlayer.setVideoPath(file.getPath());
           // mediaPlayer.prepare();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.suspend();
        }
    }
}

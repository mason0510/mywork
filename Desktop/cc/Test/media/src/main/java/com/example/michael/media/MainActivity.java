package com.example.michael.media;
import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static android.content.Context.NOTIFICATION_SERVICE;

public class MainActivity extends AppCompatActivity {

    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        发送一条通知
         */

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                需求发一条通知
                 */
                //获取系统服务
                notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                //创建通知对象
                Notification.Builder notification=new Notification.Builder(getApplicationContext());
                notification.setColor(Color.BLUE);
                notification.setContentTitle("我是通知");
                notification.setSmallIcon(R.drawable.aa);
                notification.setDefaults(Notification.DEFAULT_SOUND);
                notification.setContentText("今日最新消息");

                //设置id
                notificationManager.notify(1, notification.build());

                /*
                功能的改进
                设置自定义的声音

                Uri soundUri = Uri.fromFile(new File("/system/media/audio/ringtones/ Basic_tone.ogg"));
                notification.sound = soundUri;


                设置手机震动
                短信来到 如果想要立即开启 震动一秒 静止一秒 再震动一秒 那么可以
                long[] vibrates = {0, 1000, 1000, 1000};
                notification.vibrate = vibrates;
                手机震动需要权限 <uses-permission android:name="android.permission.VIBRATE" />


                 实现led灯的闪烁效果 可以这么写
                 notification.ledARGB = Color.GREEN;
                notification.ledOnMS = 1000; //灯亮时长
                notification.ledOffMS = 1000;//灯暗的时长
                notification.flags = Notification.FLAG_SHOW_LIGHTS;
                默认的效果notification.defaults = Notification.DEFAULT_ALL;
                 */

            }
        });



    }
}

package com.example.michael.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.michael.remoteservice.IMyService;

public class ａａａ extends AppCompatActivity {
    private IMyService iservice;
    private Myconnection myconnection;

    //手机上设置   模拟器 5.0  ｒｏｏｔ－手机权限管理　万能
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();//隐士意图
        intent.setAction("com.example.Michael");
        //  intent.setPackage("com.example.michael.remoteservice");
        myconnection = new Myconnection();
        bindService(intent, myconnection, Context.BIND_AUTO_CREATE);//自动绑定
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    iservice.methodService();
                    Log.d("ａａａ", "调用成功");
                } catch (RemoteException e) {
                    e.printStackTrace();
                    Log.d("ａａａ", "掉用失败");
                }
            }
        });
    }
   /* public void onClick(View view){
        try {
            Toast.makeText(getBaseContext(),"调用远程服务",Toast.LENGTH_SHORT).show();

            iservice.methodService();
          //  Toast.makeText(getBaseContext(),"调用远程服务",Toast.LENGTH_SHORT).show();

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }*/


    private class Myconnection implements ServiceConnection {


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {//监视服务的状态
//拿到Ibinder 直接调用方法就可以静态方法。
            iservice = IMyService.Stub.asInterface(service);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(getApplicationContext(), "没连上", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(myconnection);
    }
}

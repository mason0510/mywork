package com.example.michael.asyntask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.ContentObserver;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Policy;

/**
 * Created by Michael on 2016/6/6.
 */

public class MyAsynTask extends AsyncTask<URL,Integer,Boolean>{
    Context context;
    URL url1;
    Button button;
    Button button2;
    private ProgressDialog progressDialog;
    HttpURLConnection httpURLConnections=null;


    public MyAsynTask(Context context, URL url, Button button1,Button button2) {
        super();
        this.context= context;
        this.url1=url;
        this.button=button1;
        this.button2=button2;
    }

    @Override
    protected void onCancelled() {
    //    super.onCancelled();
        progressDialog.setTitle("取消下载");
        progressDialog.setProgress(0);
        button.setEnabled(true);
        button2.setEnabled(false);
    }

    @Override
    protected Boolean doInBackground(URL... params) {
        try {
            url1=params[0];
            httpURLConnections=(HttpURLConnection)params[0].openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            httpURLConnections.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        httpURLConnections.setConnectTimeout(50000);
        httpURLConnections.setReadTimeout(50000);
        int contentlength;

        try {
            if(httpURLConnections.getResponseCode()==200){
                contentlength=httpURLConnections.getContentLength();
                File file=new File(context.getCacheDir(),"aa.jpg");
                FileOutputStream outputStream=new FileOutputStream(file);
                InputStream inputStream=httpURLConnections.getInputStream();
                int len;
                byte[] bytes=new byte[1024];
                long totalsize=0;
                while((len=inputStream.read())!=-1){
                    outputStream.write(bytes,0,len);
                   // totalsize+=len*100;
                    totalsize+=len*100;
                    int progress= (int) (totalsize/contentlength);
                    publishProgress(progress);
                    try {
                        Thread.sleep(50000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    protected void onPreExecute() {
        //super.onPreExecute();
        //准备工作
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("下载进度");
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.show();//调用这个方法
    }



    @Override
    protected void onProgressUpdate(Integer...values) {
      //  super.onProgressUpdate(values);
        progressDialog.setProgress(values[0]);
    }

/*
httpget
http://blog.csdn.net/liuhe688/article/details/6532519
protected String doInBackground(String... params) {
            Log.i(TAG, "doInBackground(Params... params) called");
            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(params[0]);
                HttpResponse response = client.execute(get);
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    InputStream is = entity.getContent();
                    long total = entity.getContentLength();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buf = new byte[1024];
                    int count = 0;
                    int length = -1;
                    while ((length = is.read(buf)) != -1) {
                        baos.write(buf, 0, length);
                        count += length;
                        //调用publishProgress公布进度,最后onProgressUpdate方法将被执行
                        publishProgress((int) ((count / (float) total) * 100));
                        //为了演示进度,休眠500毫秒
                        Thread.sleep(500);
                    }
                    return new String(baos.toByteArray(), "gb2312");
 */
    @Override
    protected void onPostExecute(Boolean aBoolean) {
//        super.onPostExecute(aBoolean);
        progressDialog.dismiss();
        button.setEnabled(true);
        button2.setEnabled(false);
        if (aBoolean){
            Toast.makeText(context,"成功",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"失败",Toast.LENGTH_SHORT).show();
        }
    }
}

package com.example.michael.media;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Michael on 2016/6/7.
 */

public class MainActivity2 extends Activity {
    private Uri uri;
    private ImageView imageView;
    private Bitmap bitmap;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //  super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    //拍好了
                    Intent intent=new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(uri,"image/*");//指定输出地址 输出到同样的地址
                    intent.putExtra("scale",true);
                    //开始裁剪 输出
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                    startActivityForResult(intent,2);
                }
                break;
            case 2:
                //裁剪完毕
                try {
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                imageView.setImageBitmap(bitmap);
                break;
            default:
                break;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file=new File(Environment.getExternalStorageDirectory(),"output_image.jpg");
                if(file.exists()){
                    file.delete();
                }

                try {
                    file.createNewFile();
                    Uri uri=Uri.fromFile(file);
                    Intent intent=new Intent("android.intent.action.GET_CONTENT");
                    intent.setType("image/*");//设置必要的参数
                    intent.putExtra("crop",true);
                    intent.putExtra("scale",true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                    startActivityForResult(intent,2);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    /*
    从摄相册中选择数据
     */

}

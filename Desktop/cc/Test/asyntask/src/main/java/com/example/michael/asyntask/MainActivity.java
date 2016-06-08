package com.example.michael.asyntask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public Button button1;
    public Button button2;
    private MyAsynTask myAsynTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button1 = (Button) findViewById(R.id.btn);
        button2 = (Button) findViewById(R.id.btn1);
                button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    URL url;
                    url = new URL("http://localhost:8080/aa.jpg");
                    myAsynTask = new MyAsynTask(MainActivity.this,url,button1,button2);
                    myAsynTask.execute(url);
                    button1.setEnabled(false);
                    button2.setEnabled(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    myAsynTask.cancel(true);
            }
});
    }
}

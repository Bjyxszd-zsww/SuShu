package com.example.sushu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = (TextView)findViewById(R.id.textview);
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                textView.setText(msg.arg1+" ");
            }
        };
//        final EditText editText = (EditText)findViewById(R.id.editText);
        final Runnable myWorker = new Runnable(){
            public void run(){

                int i;
                int j;
                int t=0;
                for(i=0;i<1000;i++){
                    for(j=2;j<i;j++){
                        if((i%j)==0)
                            t++;
                    }

                    if(i!=1&&t<2){
                        Message msg = new Message();
                        msg.arg1 = i;
                        handler.sendMessage(msg);
                    }
                    Message msg = handler.obtainMessage();
                    msg.arg1 = i;
                    handler.sendMessage(msg);
                }
//                while(progress<=1000){
//                    Message msg = new Message();
//                    msg.arg1 = progress;
//                    handler.sendMessage(msg);
//                    progress += 10;
//                    try{
//                        Thread.sleep(1000);
//                    }catch (InterruptedException e){
//                        e.printStackTrace();
//                    }
//                }
//                Message msg = handler.obtainMessage();
//                msg.arg1 = -1;
//                handler.sendMessage(msg);
            }
        };
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread workThread = new Thread(null,myWorker,"workThread");
                workThread.start();
            }
        });

    }
}